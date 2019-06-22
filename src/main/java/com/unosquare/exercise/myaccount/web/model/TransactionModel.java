package com.unosquare.exercise.myaccount.web.model;

import com.unosquare.exercise.myaccount.domain.entity.Transaction;
import com.unosquare.exercise.myaccount.domain.entity.TransactionDetail;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import java.util.Date;
import java.util.Optional;
import java.util.function.Function;

import static com.unosquare.exercise.myaccount.util.TransactionType.getDefault;

/**
 * Model INPUT for transaction.
 *
 * @author frils
 */
@Getter
@Setter
public class TransactionModel {

    private Long accountId;

    private String transactionType;

    private Float amount;

    private String description;

    private String currency;

    private String pin;

    public static final Function<TransactionModel, Optional<Transaction>> fromModelToTransaction = (transactionModel -> {

        Transaction transaction = new Transaction();
        BeanUtils.copyProperties(transactionModel, transaction);
        transaction.setTransactionType(getDefault(transactionModel.getTransactionType()));
        transaction.setDate(new Date());
        TransactionDetail transactionDetail = new TransactionDetail();
        transactionDetail.setCurrency(transactionModel.getCurrency());
        transactionDetail.setInitialAmount(transactionModel.getAmount());
        transaction.setAmount(null); //correct amount value will be set during the process
        transaction.setTransactionDetail(transactionDetail);

        return Optional.of(transaction);
    });

}
