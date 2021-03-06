package com.unosquare.exercise.myaccount.util;

import com.unosquare.exercise.myaccount.exception.UnexpectedException;
import lombok.Getter;

/**
 * Enumeration that list all type of transaction, might be deposit, withdraw.
 * Should perform debit, and check too.
 * @author frils
 *
 */
@Getter
public enum TransactionType {

    DEPOSIT("DEPOSIT"),
    WITHDRAW("WITHDRAW");

    private String type;

    TransactionType(String type) {
        this.type = type;
    }

    public static final TransactionType getDefault(String transactionType) {
        if (DEPOSIT.type.equalsIgnoreCase(transactionType))
            return DEPOSIT;
        else if (WITHDRAW.type.equalsIgnoreCase(transactionType))
            return WITHDRAW;
        throw new UnexpectedException("Unexpected value of transaction type " + transactionType + ".");
    }


}
