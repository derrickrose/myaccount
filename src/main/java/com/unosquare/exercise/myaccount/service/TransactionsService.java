package com.unosquare.exercise.myaccount.service;

import com.unosquare.exercise.myaccount.domain.entity.Transaction;
import com.unosquare.exercise.myaccount.domain.entity.TransactionDetail;
import com.unosquare.exercise.myaccount.exception.CustomException;
import com.unosquare.exercise.myaccount.exception.InternalException;
import com.unosquare.exercise.myaccount.exception.UnexpectedException;
import com.unosquare.exercise.myaccount.facade.AccountsFacade;
import com.unosquare.exercise.myaccount.facade.TransactionsFacade;
import com.unosquare.exercise.myaccount.lib.service.UnosquareService;
import com.unosquare.exercise.myaccount.remote.service.ExchangesService;
import com.unosquare.exercise.myaccount.web.model.TransactionModel;
import lombok.extern.log4j.Log4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

import static com.unosquare.exercise.myaccount.util.Constants.DEFAULT_CURRENCY;
import static com.unosquare.exercise.myaccount.web.model.TransactionModel.fromModelToTransaction;

/**
 * @author frils
 *
 */
@Log4j
@Service
public class TransactionsService extends UnosquareService<Transaction> {

    @Autowired
    AccountsFacade accountsFacade;

    @Autowired
    private ExchangesService exchangesService;

    private TransactionsFacade transactionsFacade;

    @Autowired
    protected TransactionsService(TransactionsFacade transactionsFacade) {
        super(transactionsFacade);
        this.transactionsFacade = transactionsFacade;
    }

    public List<Transaction> getTransactionsByAccountIdAndSourceCurrency(Long accountId, String currency) {
        List<Transaction> transactions = transactionsFacade.getTransactionsByAccountIdAndCurrency(accountId, currency);
        if (transactions == null || transactions.isEmpty())
            throw new InternalException("No transaction matching the criteria.");
        return transactions;
    }

    public Transaction performTransaction(TransactionModel transactionModel) {

        try {
            validateCurrency(transactionModel.getCurrency());
        } catch (CustomException e) {
            log.error(e);
            throw new UnexpectedException(e.getMessage());
        }

        return fromModelToTransaction.apply(transactionModel).map(
                tx -> {
                    tx.setDate(new Date());
                    tx.setAccount(accountsFacade.getAccount(transactionModel.getAccountId()));

                    Float exchangeRate = 1f;

                    if (!DEFAULT_CURRENCY.equalsIgnoreCase(tx.getTransactionDetail().getCurrency())) {
                        exchangeRate = exchangesService.getExchangeRate(tx.getTransactionDetail().getCurrency());
                    }

                    tx.setAmount(tx.getTransactionDetail().getInitialAmount().floatValue() / exchangeRate.floatValue());

                    return transactionsFacade.performTransaction(setExchangeRate.apply(tx, exchangeRate));
                }
        ).orElseGet(() -> {
                    throw new InternalException("UnosquareError while performing transaction.");
                }
        );
    }


    public List<Transaction> getLatest5Transactions(Long accountId) {
        return transactionsFacade.getLatest5Transactions(accountId);
    }

    public BiFunction<Transaction, Float, Transaction> setExchangeRate = (Transaction tx, Float exchangeRate) -> {
        TransactionDetail transactionDetail = tx.getTransactionDetail();
        transactionDetail.setExchangeRate(exchangeRate);
        tx.setTransactionDetail(transactionDetail);
        return tx;
    };

    private static final void validateCurrency(String currency) throws CustomException {
        if (StringUtils.isBlank(currency))
            throw new CustomException("Got Empty Or Blank Currency.");
    }


    public List<Transaction> getTransactionsByAccountIdAndTargetCurrency(Long accountId, String targetCurrency) {
        List<Transaction> transactions = getTranasactionsByAccountId(accountId);

        return transactions.stream().map(

                tx -> {
                    return convertFromASourceCurrencyToTargetCurrency(tx, targetCurrency);
                }
        ).collect(Collectors.toList());
    }

    private List<Transaction> getTranasactionsByAccountId(Long accountId) {
        List<Transaction> transactions = transactionsFacade.getTransactionsByAccountId(accountId);
        if (transactions == null || transactions.isEmpty())
            throw new InternalException("Empty transactions for given account.");
        return transactions;

    }

    private Transaction convertFromASourceCurrencyToTargetCurrency(Transaction transaction, String targetCurrency) {
        if (transaction.getTransactionDetail().getCurrency().equalsIgnoreCase(targetCurrency))
            return transaction;
        Float exchangeRate = 1f;
        if (!DEFAULT_CURRENCY.equalsIgnoreCase(targetCurrency))
            exchangeRate = exchangesService.getExchangeRate(targetCurrency);
        Float convertedAmount = transaction.getAmount().floatValue() * exchangeRate.floatValue();
        TransactionDetail transactionDetail = transaction.getTransactionDetail();
        transactionDetail.setExchangeRate(exchangeRate);
        transactionDetail.setInitialAmount(convertedAmount);
        transactionDetail.setCurrency(targetCurrency);
        transaction.setTransactionDetail(transactionDetail);
        return transaction;
    }
}
