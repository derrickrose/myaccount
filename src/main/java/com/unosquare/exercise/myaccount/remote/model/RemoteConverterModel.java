package com.unosquare.exercise.myaccount.remote.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Map;

/**
 * @author frils
 *
 */
@ToString
@Getter
@Setter
public class RemoteConverterModel {
    private String base;
    private String date;
    private Map<String, Float> rates;
}
