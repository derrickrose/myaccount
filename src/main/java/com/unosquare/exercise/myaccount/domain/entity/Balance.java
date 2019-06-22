package com.unosquare.exercise.myaccount.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.unosquare.exercise.myaccount.lib.domain.entity.UnosquareEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

import lombok.Setter;

import javax.persistence.*;

/**
 * Used to save account balance.
 * @author frils
 *
 */
@ApiModel(value="Entity for account balance.")
@Entity
@Getter
@Setter
public class Balance extends UnosquareEntity {

    @ApiModelProperty("Identifier for the account balance.")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ApiModelProperty("Amount of the account balance.")
    private Float amount;

    @ApiModelProperty("Relation to the account.")
    @OneToOne
    @JsonIgnore
    private Account account;

    @ApiModelProperty("Currency of the account balance.")
    private String currency;

    @Override
    public String toString() {
        return "Balance{" +
                "id=" + id +
                ", amount=" + amount +
                ", account=" + account +
                '}';
    }
}



