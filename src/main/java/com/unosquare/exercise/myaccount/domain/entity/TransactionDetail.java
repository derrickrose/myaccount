package com.unosquare.exercise.myaccount.domain.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;

/**
 * Used to save transaction details.
 * @author frils
 *
 */
@ApiModel(value="Embeddable class for currency.")
@Getter
@Setter
@Embeddable
public class TransactionDetail {
    @ApiModelProperty("Given currency of a transaction.")
    private String currency;

    @ApiModelProperty("Amount of a transaction with a given currency.")
    private Float initialAmount;

    @ApiModelProperty("Amount of exchange rate from default currency at the moment of a transaction.")
    private Float exchangeRate;
}
