package com.unosquare.exercise.myaccount.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.unosquare.exercise.myaccount.lib.domain.entity.UnosquareEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.Date;
import java.util.List;

/**
 * @author frils
 *
 */
@ToString
@Getter
@Setter
@Entity
public class Account extends UnosquareEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date created;

    @JsonIgnore
    @NotBlank
    @Pattern(regexp = "\\d{4}")
    private String pin;

    @Embedded
    User user;

    @OneToOne(cascade = CascadeType.ALL)
    private Balance balance;

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


