package com.unosquare.exercise.myaccount.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author frils
 *
 */
@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class InternalException extends RuntimeException {
    public InternalException(String message) {
        super(message);
    }
}
