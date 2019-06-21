package com.unosquare.exercise.myaccount.util;

import com.unosquare.exercise.myaccount.domain.entity.Balance;

/**
 * @author frils
 *
 */
@FunctionalInterface
public interface IFundValidator {
    public boolean isFundAvailable(Balance balance, Float amount);
}
