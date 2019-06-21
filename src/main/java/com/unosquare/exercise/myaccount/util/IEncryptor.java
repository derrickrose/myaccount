package com.unosquare.exercise.myaccount.util;

/**
 * @author frils
 *
 */
@FunctionalInterface
public interface IEncryptor {
    String encrypt(String value);
}
