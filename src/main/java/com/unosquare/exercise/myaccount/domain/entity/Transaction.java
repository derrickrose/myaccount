package com.unosquare.exercise.myaccount.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.unosquare.exercise.myaccount.lib.domain.entity.UnosquareEntity;
import com.unosquare.exercise.myaccount.util.TransactionType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @author frils
 *
 */
@Getter
@Setter
@Entity
public class Transaction extends UnosquareEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    @NotNull
    @Min(1)
    private Float amount;

    private Date date;

    @ManyToOne
    @JsonIgnore
    private Account account;

    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;

    @Embedded
    private TransactionDetail transactionDetail;


}
