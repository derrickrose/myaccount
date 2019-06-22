package com.unosquare.exercise.myaccount.rest;

import com.unosquare.exercise.myaccount.domain.entity.Account;
import com.unosquare.exercise.myaccount.domain.entity.Balance;
import com.unosquare.exercise.myaccount.domain.entity.User;
import com.unosquare.exercise.myaccount.web.model.AccountCreationModel;
import org.junit.Ignore;
import org.junit.Test;

import java.util.Date;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class AccountsControllerTest extends AbstractControllerTest {

    public static final Account account = addIdToAccount(1l, createNewAcount(1l, "rose", "jack", "9999999955", "5654"));

    public static final AccountCreationModel accountCreationModel = createAccountCreationModel("tsara", "faniry", "666666444441111", "9854");

    @Ignore
    @Test
    public void createAccount_Test() throws Exception {

        String contentBody = "{ \"firstName\": \"string\", \"idNumber\": \"string\",  \"lastName\": \"string\", \"pin\": \"1254\"}";

        when(accountsService.save(accountCreationModel)).thenReturn(null);

        mockMvc.perform(post("/accounts")
                        .content(contentBody)
                        .contentType(APPLICATION_JSON_UTF8)
//                .accept(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isCreated());

    }

    @Test
    public void getAccountById_Test() throws Exception {

        // when
        when(accountsService.getAccount(1l)).thenReturn(account);

        // then
        mockMvc.perform(get("/accounts/1").accept(APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.id", is(1)));

    }

    private static final Account createNewAcount(Long id, String firstName, String lastName, String idNumber, String pin) {
        Account account = new Account();

        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setIdNumber(idNumber);

        Balance balance = new Balance();
        balance.setAmount(0f);
        balance.setCurrency("USD");

//        account.setPin(pin);
        account.setUser(user);
        account.setBalance(balance);
        account.setCreated(new Date());

        return account;

    }



    private static final Account addIdToAccount(Long id, Account account) {
        Balance balance = account.getBalance();
        balance.setId(id);
        account.setBalance(balance);
        account.setId(id);
        return account;
    }

    public static final AccountCreationModel createAccountCreationModel(String firstName, String lastName, String idNumber, String pin) {
        AccountCreationModel accountCreationModel = new AccountCreationModel();
        accountCreationModel.setFirstName(firstName);
        accountCreationModel.setLastName(lastName);
        accountCreationModel.setIdNumber(idNumber);
        accountCreationModel.setPin(pin);
        return accountCreationModel;
    }


}