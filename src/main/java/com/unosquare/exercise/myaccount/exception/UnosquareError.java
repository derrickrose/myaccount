package com.unosquare.exercise.myaccount.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

/**
 * @author frils
 *
 */
@Getter
@Setter
@AllArgsConstructor
public class UnosquareError {

    private HttpStatus status;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
    private LocalDateTime timestamp;
    private String message;
//    private String debugMessage;
//    private List<ParentError> parentErrors;

    private UnosquareError() {
        timestamp = LocalDateTime.now();
    }

    UnosquareError(HttpStatus status) {
        this();
        this.status = status;
    }

    UnosquareError(HttpStatus status, Throwable ex) {
        this();
        this.status = status;
        this.message = "Unexpected error";
//        this.debugMessage = ex.getLocalizedMessage();
    }

    UnosquareError(HttpStatus status, String message, Throwable ex) {
        this();
        this.status = status;
        this.message = message;
//        this.debugMessage = ex.getLocalizedMessage();
    }
}