package com.unosquare.exercise.myaccount.web.controller;

import com.unosquare.exercise.myaccount.domain.entity.Transaction;
import com.unosquare.exercise.myaccount.lib.web.controller.UnosquareController;
import com.unosquare.exercise.myaccount.service.TransactionsService;
import com.unosquare.exercise.myaccount.web.model.TransactionModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author frils
 *
 */
@RestController
@RequestMapping(value = "/transactions")
public class TransactionsController extends UnosquareController<Transaction> {

    private TransactionsService transactionsService;

    @Autowired
    public TransactionsController(TransactionsService transactionsService) {
        super(transactionsService);
        this.transactionsService = transactionsService;
    }

    @PostMapping
    public Transaction performTransaction(@RequestBody TransactionModel transactionModel) {
        return transactionsService.performTransaction(transactionModel);
    }

    @GetMapping("/account/{accountId}/sourcecurrency/{currency}")
    public List<Transaction> getTransactionsByAccountIdAndSourceCurrency(@PathVariable Long accountId, @PathVariable String currency) {
        return transactionsService.getTransactionsByAccountIdAndSourceCurrency(accountId, currency);
    }

    @GetMapping("/account/{accountId}/targetcurrency/{currency}")
    public List<Transaction> getTransactionsByAccountIdAndTargetCurrency(@PathVariable Long accountId, @PathVariable String currency) {
        return transactionsService.getTransactionsByAccountIdAndTargetCurrency(accountId, currency);
    }
}
