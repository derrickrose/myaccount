package com.unosquare.exercise.myaccount.service;

import com.unosquare.exercise.myaccount.domain.entity.Account;
import com.unosquare.exercise.myaccount.domain.entity.Balance;
import com.unosquare.exercise.myaccount.domain.entity.User;
import com.unosquare.exercise.myaccount.facade.AccountsFacade;
import com.unosquare.exercise.myaccount.lib.service.UnosquareService;
import com.unosquare.exercise.myaccount.remote.service.ExchangesService;
import com.unosquare.exercise.myaccount.web.model.AccountCreationModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Date;
import java.util.function.Consumer;

import static com.unosquare.exercise.myaccount.util.Constants.DEFAULT_CURRENCY;
import static com.unosquare.exercise.myaccount.web.model.AccountCreationModel.creationModelToAccount;


/**
 * @author frils
 *
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
    public AccountsService(AccountsFacade accountsFacade) {
        super(accountsFacade);
        this.accountsFacade = accountsFacade;
    }


    public Account update(User user, Long id) {
        Account account = this.getAccount(id);
        account.setUser(user);
        return (super.update(account)).withTransactions(transactionsService.getLatest5Transactions(id));
    }


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

    public ResponseEntity<Account> save(AccountCreationModel accountModel) {
        Account account = creationModelToAccount.apply(accountModel);
        setCreationDate.accept(account);
        account = super.save(account);

        if (account == null) return ResponseEntity.noContent().build();

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(account.getId())
                .toUri();

        return ResponseEntity.created(location).body(account.withTransactions(transactionsService.getLatest5Transactions(account.getId())));
    }

    public Account getAccount(Long id) {
        return accountsFacade.getAccount(id).withTransactions(transactionsService.getLatest5Transactions(id));
    }


}
