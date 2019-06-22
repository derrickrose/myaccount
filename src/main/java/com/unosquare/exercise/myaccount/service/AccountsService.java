package com.unosquare.exercise.myaccount.service;

import com.unosquare.exercise.myaccount.domain.entity.Account;
import com.unosquare.exercise.myaccount.domain.entity.Balance;
import com.unosquare.exercise.myaccount.domain.entity.User;
import com.unosquare.exercise.myaccount.exception.UnexpectedException;
import com.unosquare.exercise.myaccount.facade.AccountsFacade;
import com.unosquare.exercise.myaccount.lib.service.UnosquareService;
import com.unosquare.exercise.myaccount.remote.service.ExchangesService;
import com.unosquare.exercise.myaccount.web.model.AccountCreationModel;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Date;
import java.util.function.Consumer;

import static com.unosquare.exercise.myaccount.util.Constants.DEFAULT_CURRENCY;
import static com.unosquare.exercise.myaccount.web.model.AccountCreationModel.creationModelToAccount;


/**
 * Business logic for Account.
 *
 * @author frils
 */
@Service
public class AccountsService extends UnosquareService<Account> {

    private AccountsFacade accountsFacade;

    private Consumer<Account> setCreationDate = (account) -> account.setCreated(new Date());

    @Autowired
    private TransactionsService transactionsService;

    @Autowired
    private ExchangesService exchangesService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    public AccountsService(AccountsFacade accountsFacade) {
        super(accountsFacade);
        this.accountsFacade = accountsFacade;
    }

    /**
     * @param user information to update.
     * @param id   account identifier (account number).
     * @return updated account.
     */
    public Account update(User user, Long id) {
        Account account = this.getAccount(id);
        account.setUser(user);
        return (super.update(account)).withTransactions(transactionsService.getLatest5Transactions(id));
    }

    /**
     * @param id       account id.
     * @param currency given currency.
     * @return account detail with converted currency.
     */
    public Account getAccountWithTargetCurrency(@PathVariable Long id, @PathVariable String currency) {
        Account account = getAccount(id);
        Balance balance = account.getBalance();
        Float exchangeRate = 1f;

        if (!DEFAULT_CURRENCY.equalsIgnoreCase(currency)) {
            exchangeRate = exchangesService.getExchangeRate(currency);
        }

        balance.setCurrency(currency.toUpperCase());
        balance.setAmount(account.getBalance().getAmount().floatValue() * exchangeRate.floatValue());
        account.setBalance(balance);
        return account;
    }

    /**
     * @param accountModel model used for account creation.
     * @return created account.
     */
    public Account save(AccountCreationModel accountModel) {
        Account account = creationModelToAccount.apply(accountModel);
        account = encryptPin(account);
        setCreationDate.accept(account);
        return super.save(account);
    }

    /**
     * @param id account identifier (account number).
     * @return account.
     */
    public Account getAccount(Long id) {
        return accountsFacade.getAccount(id).withTransactions(transactionsService.getLatest5Transactions(id));
    }

    private Account encryptPin(Account account) {
        if (StringUtils.isBlank(account.getPin()) || !account.getPin().matches("\\d{4}"))
            throw new UnexpectedException("Pin should be 4 digits.");

        if (account.getPin().matches("0000")) {
            throw new UnexpectedException("Pin should not be 0000.");
        }
        account.setPin(passwordEncoder.encode(account.getPin()));
        return account;
    }


}
