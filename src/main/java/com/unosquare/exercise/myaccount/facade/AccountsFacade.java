package com.unosquare.exercise.myaccount.facade;

import com.unosquare.exercise.myaccount.domain.entity.Account;
import com.unosquare.exercise.myaccount.domain.repository.AccountsRepository;
import com.unosquare.exercise.myaccount.exception.EntityNotFoundException;
import com.unosquare.exercise.myaccount.lib.facade.UnosquareFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author frils
 *
 */
@Component
public class AccountsFacade extends UnosquareFacade<Account> {

    @Autowired
    public AccountsFacade(AccountsRepository repository) {
        super(repository);
    }

    public Account getAccount(Long id) {
        Account account = super.findById(id);
        if (account == null) throw new EntityNotFoundException("Account not found with id " + id);
        return account;
    }

}
