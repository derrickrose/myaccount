package com.unosquare.exercise.myaccount.rest;

import com.unosquare.exercise.myaccount.service.AccountsService;
import com.unosquare.exercise.myaccount.service.TransactionsService;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@WebMvcTest
public abstract class AbstractControllerTest {

    @Autowired
    protected MockMvc mockMvc;

    @MockBean
    protected AccountsService accountsService;

    @MockBean
    protected TransactionsService transactionsService;

    @Before
    public void setUp() {
        Mockito.reset(accountsService, transactionsService);
    }

}
