package com.unosquare.exercise.myaccount.web.controller;

import com.unosquare.exercise.myaccount.domain.entity.Account;
import com.unosquare.exercise.myaccount.domain.entity.User;
import com.unosquare.exercise.myaccount.exception.InternalException;
import com.unosquare.exercise.myaccount.lib.web.controller.UnosquareController;
import com.unosquare.exercise.myaccount.service.AccountsService;
import com.unosquare.exercise.myaccount.web.model.AccountCreationModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * @author frils
 *
 */
@RestController
@RequestMapping(value = "/accounts")
public class AccountsController extends UnosquareController<Account> {

    private AccountsService accountsService;

    @Autowired
    public AccountsController(AccountsService accountsService) {
        super(accountsService);
        this.accountsService = accountsService;
    }

    @PostMapping
    public ResponseEntity<Account> createAccount(@RequestBody AccountCreationModel accountModel) {
        return accountsService.save(accountModel);
    }

    @GetMapping
    public List<Account> getAccounts() {
        return super.findAll();
    }

    @GetMapping(value = "/{id}")
    public Account getAccount(@PathVariable Long id) {
        return accountsService.getAccount(id);
    }

    @GetMapping(value = "/{id}/targetcurrency/{currency}")
    public Account getAccountWithTargetCurrency(@PathVariable Long id, @PathVariable String currency) {
        return accountsService.getAccountWithTargetCurrency(id,currency);
    }

    @PutMapping("/{id}")
    public Account updateAccount(@RequestBody User user, @PathVariable Long id) {
        return accountsService.update(user, id);
    }

    @DeleteMapping("/{id}")
    public void deleteAccount(@PathVariable Long id) {
        Account account = getAccount(id);
        if(account.getBalance().getAmount().floatValue()<0)
            throw new InternalException("Overdrawn account would not be deleted.");
        super.delete(id);
    }

}
