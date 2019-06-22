package com.unosquare.exercise.myaccount.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.unosquare.exercise.myaccount.lib.domain.entity.UnosquareEntity;
import com.unosquare.exercise.myaccount.util.TransactionType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Used to save transaction.
 *
 * @author frils
 */
@ApiModel(value = "Entity for transaction.")
@Getter
@Setter
@Entity
public class Transaction extends UnosquareEntity {

    @ApiModelProperty("Identifier of a transaction.")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ApiModelProperty("Transaction description.")
    @NotBlank
    private String description;

    @ApiModelProperty("Amount of a given transaction in default currency.")
    @NotNull
    @Min(1)
    private Float amount;

    @ApiModelProperty("Date of the transaction.")
    private Date date;

    @ApiModelProperty("Relation to account.")
    @ManyToOne
    @JsonIgnore
    private Account account;

    @ApiModelProperty("Type of transaction, may be deposit, withdraw.")
    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;

    @ApiModelProperty("Relation to transaction detail (original currency, amount and exchange rate.")
    @Embedded
    private TransactionDetail transactionDetail;


}
