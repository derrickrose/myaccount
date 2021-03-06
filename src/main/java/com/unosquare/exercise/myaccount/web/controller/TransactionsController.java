package com.unosquare.exercise.myaccount.web.controller;

import com.unosquare.exercise.myaccount.domain.entity.Transaction;
import com.unosquare.exercise.myaccount.lib.web.controller.UnosquareController;
import com.unosquare.exercise.myaccount.service.TransactionsService;
import com.unosquare.exercise.myaccount.web.model.TransactionModel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Used to handle all requests related with transaction.
 * @author frils
 *
 */
@Api("Handles all requests related with transaction.")
@RestController
@RequestMapping(value = "/transactions")
public class TransactionsController extends UnosquareController<Transaction> {

    private TransactionsService transactionsService;

    @Autowired
    public TransactionsController(TransactionsService transactionsService) {
        super(transactionsService);
        this.transactionsService = transactionsService;
    }

    @ApiOperation(value = "Creates and performs transaction (deposit, withdraw) with given currency and saves it.")
    @PostMapping
    public Transaction performTransaction(@RequestBody TransactionModel transactionModel) {
        return transactionsService.performTransaction(transactionModel);
    }

    @ApiOperation(value = "Lists all transactions made by a given account with a given currency.")
    @GetMapping("/account/{accountId}/sourceCurrency/{currency}")
    public List<Transaction> getTransactionsByAccountIdAndSourceCurrency(@PathVariable Long accountId, @PathVariable String currency) {
        return transactionsService.getTransactionsByAccountIdAndSourceCurrency(accountId, currency);
    }

    @ApiOperation(value = "Lists all transactions and change to a given currency.")
    @GetMapping("/account/{accountId}/targetCurrency/{currency}")
    public List<Transaction> getTransactionsByAccountIdAndTargetCurrency(@PathVariable Long accountId, @PathVariable String currency) {
        return transactionsService.getTransactionsByAccountIdAndTargetCurrency(accountId, currency);
    }
}
