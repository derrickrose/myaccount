package com.unosquare.exercise.myaccount.service;

import com.unosquare.exercise.myaccount.domain.entity.Account;
import com.unosquare.exercise.myaccount.domain.entity.User;
import com.unosquare.exercise.myaccount.web.model.AccountCreationModel;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static com.unosquare.exercise.myaccount.rest.AccountsControllerTest.accountCreationModel;
import static com.unosquare.exercise.myaccount.rest.AccountsControllerTest.createAccountCreationModel;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Ignore
public class AccountsServiceTest {

    @Autowired
    AccountsService accountsService;

    @Test
    public void save_Test() {
        assertThat("Account id shouldn't be null.", accountsService.save(accountCreationModel).getId(), notNullValue());
    }

    @Test
    public void update_Test() {
        AccountCreationModel accountCreationModel = createAccountCreationModel("mama", "papa", "9999999999", "5545");
        Account account = accountsService.save(accountCreationModel);

        User expectedUser = createUser("gggg", "eeeee", "6666655544455");

        assertThat("FirstName should be updated.", accountsService.update(expectedUser, account.getId()).getUser().getFirstName(), equalTo(expectedUser.getFirstName()));
    }


    public static final User createUser(String firstName, String lastName, String idNumber) {
        User user = new User();
        user.setIdNumber(idNumber);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        return user;
    }
}