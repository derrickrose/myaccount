package com.unosquare.exercise.myaccount.web.controller;

import com.unosquare.exercise.myaccount.domain.entity.Account;
import com.unosquare.exercise.myaccount.domain.entity.User;
import com.unosquare.exercise.myaccount.exception.InternalException;
import com.unosquare.exercise.myaccount.lib.web.controller.UnosquareController;
import com.unosquare.exercise.myaccount.service.AccountsService;
import com.unosquare.exercise.myaccount.web.model.AccountCreationModel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;


/**
 * Controller to handle all requests with relation to account.
 *
 * @author frils
 */
@Api("Handle all requests with relation to account.")
@RestController
@RequestMapping(value = "/accounts")
public class AccountsController extends UnosquareController<Account> {

    private AccountsService accountsService;

    @Autowired
    public AccountsController(AccountsService accountsService) {
        super(accountsService);
        this.accountsService = accountsService;
    }

    @ApiOperation(value = "Used to create an account.")
    @PostMapping
    public ResponseEntity<Account> createAccount(@RequestBody AccountCreationModel accountModel) {

        Account account = accountsService.save(accountModel);
        if (account == null) return ResponseEntity.noContent().build();

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(account.getId())
                .toUri();
        return ResponseEntity.created(location).body(account);
    }

    @ApiOperation(value = "To list all accounts.")
    @GetMapping
    public List<Account> getAccounts() {
        return super.findAll();
    }

    @ApiOperation(value = "Shows information of a given account by its id (account number).")
    @GetMapping(value = "/{id}")
    public Account getAccount(@PathVariable Long id) {
        return accountsService.getAccount(id);
    }

    @ApiOperation(value = "Shows account details while converting its currency.")
    @GetMapping(value = "/{id}/targetCurrency/{currency}")
    public Account getAccountWithTargetCurrency(@PathVariable Long id, @PathVariable String currency) {
        return accountsService.getAccountWithTargetCurrency(id, currency);
    }

    @ApiOperation(value = "Update user information (firstName, lastName, personal ID number).")
    @PutMapping("/{id}")
    public Account updateAccount(@RequestBody User user, @PathVariable Long id) {
        return accountsService.update(user, id);
    }

    @ApiOperation(value = "Delete a given account by its account number (id).")
    @DeleteMapping("/{id}")
    public void deleteAccount(@PathVariable Long id) {
        Account account = getAccount(id);
        if (account.getBalance().getAmount().floatValue() < 0)
            throw new InternalException("Overdrawn account would not be deleted.");
        super.delete(id);
    }

}
