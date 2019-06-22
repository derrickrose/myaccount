package com.unosquare.exercise.myaccount.remote.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Map;

/**
 * Mapping object for the remote connection to exchange rate api.
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
