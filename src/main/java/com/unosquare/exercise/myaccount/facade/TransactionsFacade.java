package com.unosquare.exercise.myaccount.facade;

import com.unosquare.exercise.myaccount.domain.entity.Transaction;
import com.unosquare.exercise.myaccount.domain.repository.TransactionsRepository;
import com.unosquare.exercise.myaccount.exception.InternalException;
import com.unosquare.exercise.myaccount.lib.facade.UnosquareFacade;
import com.unosquare.exercise.myaccount.util.IBalanceValidator;
import com.unosquare.exercise.myaccount.util.IFundValidator;
import com.unosquare.exercise.myaccount.util.TransactionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Used to perform transaction.
 * @author frils
 *
 */
@Component
public class TransactionsFacade extends UnosquareFacade<Transaction> {

    @Autowired
    BalancesFacade balancesFacade;

    private TransactionsRepository transactionsRepository;

    @Autowired
    public TransactionsFacade(TransactionsRepository transactionsRepository) {
        super(transactionsRepository);
        this.transactionsRepository = transactionsRepository;
    }

    private static final IBalanceValidator iBalanceValidator = (balance) -> {
        boolean isInCredit = balance != null && balance.getAmount() != null && balance.getAmount().floatValue() >= 0;
        if (isInCredit) return isInCredit;
        throw new InternalException("Overdrawn account.");
    };

    private static final IFundValidator iFundValidator = (balance, amount) -> {
        boolean isFundAvailable = balance != null && balance.getAmount() != null && balance.getAmount().floatValue() >= amount.floatValue();
        if (isFundAvailable) return isFundAvailable;
        throw new InternalException("Amount unavailable, " + amount + ".");
    };

    @Transactional
    public Transaction performTransaction(Transaction transaction) {

        if (TransactionType.WITHDRAW.equals(transaction.getTransactionType())) {
            iFundValidator.isFundAvailable(transaction.getAccount().getBalance(), transaction.getAmount());
            balancesFacade.performWithDraw(transaction.getAccount().getBalance(), transaction.getAmount());
        } else if (TransactionType.DEPOSIT.equals(transaction.getTransactionType())) {
            balancesFacade.performDeposit(transaction.getAccount().getBalance(), transaction.getAmount());
        }

        return this.save(transaction);
    }

    public List<Transaction> getLatest5Transactions(Long accountId) {
        return transactionsRepository.getLatest5TransactionsByAccountId(accountId);
    }

    public List<Transaction> getTransactionsByAccountIdAndCurrency(Long accountId, String currency) {
        return transactionsRepository.getTransactionsByAccountIdAndCurrency(accountId, currency);
    }

    public List<Transaction> getTransactionsByAccountId(Long account_id) {
        return transactionsRepository.getTransactionsByAccountId(account_id);
    }


}
