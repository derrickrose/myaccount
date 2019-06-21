package com.unosquare.exercise.myaccount.web.model;

import lombok.Getter;
import lombok.Setter;

/**
 * @author frils
 *
 */
@Getter
@Setter
public class PinChangingModel {
    private String actualPin;
    private String newPin;
}
