package com.unosquare.exercise.myaccount.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author frils
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
class ValidationError extends ParentError {
    private String object;
    private String field;
    private Object rejectedValue;
    private String message;

    ValidationError(String object, String message) {
        this.object = object;
        this.message = message;
    }
}