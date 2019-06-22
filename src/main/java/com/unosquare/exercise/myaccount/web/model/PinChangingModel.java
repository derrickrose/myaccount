package com.unosquare.exercise.myaccount.web.model;

import lombok.Getter;
import lombok.Setter;

/**
 * Model for pin change.
 * @author frils
 *
 */
@Getter
@Setter
public class PinChangingModel {
    private String actualPin;
    private String newPin;
}
