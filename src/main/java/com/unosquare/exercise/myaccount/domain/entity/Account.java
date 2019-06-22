package com.unosquare.exercise.myaccount.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.unosquare.exercise.myaccount.lib.domain.entity.UnosquareEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.List;

/**
 * Used to save account.
 * @author frils
 *
 */
@ApiModel(value="Entity for account.")
@ToString
@Getter
@Setter
@Entity
public class Account extends UnosquareEntity {

    @ApiModelProperty("Id of the account.")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ApiModelProperty("Creation date.")
    private Date created;

    @ApiModelProperty("Client pin code.")
    @JsonIgnore
    @NotBlank
    private String pin;

    @ApiModelProperty("User information.")
    @Embedded
    User user;

    @ApiModelProperty("Relation to account balance.")
    @OneToOne(cascade = CascadeType.ALL)
    private Balance balance;

    @ApiModelProperty("List of 5 most recent transactions.")
    @Transient
    List<Transaction> transactions;

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", created=" + created +
                ", pin='" + pin + '\'' +
                ", user=" + user +
                ", balance=" + balance +
                '}';
    }

    public Account withTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
        return this;
    }
}


