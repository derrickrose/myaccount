package com.unosquare.exercise.myaccount.domain.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;

/**
 * @author frils
 *
 */
@Getter
@Setter
@Embeddable
public class TransactionDetail {
    private String currency;
    private Float initialAmount;
    private Float exchangeRate;
}
