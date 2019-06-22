package com.unosquare.exercise.myaccount.web.model;

import com.unosquare.exercise.myaccount.domain.entity.Account;
import com.unosquare.exercise.myaccount.domain.entity.Balance;
import com.unosquare.exercise.myaccount.domain.entity.User;
import com.unosquare.exercise.myaccount.exception.CustomException;
import com.unosquare.exercise.myaccount.exception.UnexpectedException;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;

import java.util.function.Function;

import static com.unosquare.exercise.myaccount.util.Constants.DEFAULT_CURRENCY;

/**
 * Model for account creation.
 *
 * @author frils
 */
@Getter
@Setter
@Log4j
public class AccountCreationModel extends User {

    private String pin;

    public static final Function<AccountCreationModel, Account> creationModelToAccount = (accountModel) -> {

        try {
            validateFirstName(accountModel.getFirstName());
            validateLastName(accountModel.getLastName());
            validateIdNumber(accountModel.getIdNumber());
        } catch (CustomException e) {
            log.error(e);
            throw new UnexpectedException(e.getMessage());
        }

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


    private static final void validateFirstName(String firstName) throws CustomException {
        if (StringUtils.isBlank(firstName))
            throw new CustomException("First Name shouldn't be blank or empty.");
    }

    private static final void validateLastName(String lastName) throws CustomException {
        if (StringUtils.isBlank(lastName))
            throw new CustomException("Last Name shouldn't be blank or empty.");
    }

    private static final void validateIdNumber(String idNumber) throws CustomException {
        if (StringUtils.isBlank(idNumber))
            throw new CustomException("Id number shouldn't be blank or empty.");
    }

}
