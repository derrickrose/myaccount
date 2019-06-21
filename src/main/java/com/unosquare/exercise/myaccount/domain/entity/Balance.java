package com.unosquare.exercise.myaccount.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.unosquare.exercise.myaccount.lib.domain.entity.UnosquareEntity;
import lombok.Getter;

import lombok.Setter;

import javax.persistence.*;

/**
 * @author frils
 *
 */
@Entity
@Getter
@Setter
public class Balance extends UnosquareEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Float amount;

    @OneToOne
    @JsonIgnore
    private Account account;

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



