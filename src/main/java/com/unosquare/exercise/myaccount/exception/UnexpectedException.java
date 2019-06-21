package com.unosquare.exercise.myaccount.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author frils
 *
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UnexpectedException extends RuntimeException {
    public UnexpectedException(String message) {
        super(message);
    }
}
