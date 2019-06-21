package com.unosquare.exercise.myaccount.web.model;

import com.unosquare.exercise.myaccount.domain.entity.Account;
import com.unosquare.exercise.myaccount.domain.entity.Balance;
import com.unosquare.exercise.myaccount.domain.entity.User;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import java.util.function.Function;

import static com.unosquare.exercise.myaccount.util.Constants.DEFAULT_CURRENCY;

/**
 * @author frils
 *
 */
@Getter
@Setter
public class AccountCreationModel extends User {

    private String pin;

    public static final Function<AccountCreationModel, Account> creationModelToAccount = (accountModel) -> {

        User user = new User();
        BeanUtils.copyProperties(accountModel, user);
        Account account = new Account();
        account.setUser(user);
        Balance balance = new Balance();
        balance.setAmount(0f);
        balance.setCurrency(DEFAULT_CURRENCY);
        account.setBalance(balance);
        account.setPin(accountModel.getPin());

        return account;
    };


}
