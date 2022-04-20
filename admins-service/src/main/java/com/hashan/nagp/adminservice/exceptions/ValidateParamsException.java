package com.hashan.nagp.adminservice.exceptions;


public class ValidateParamsException extends Exception {

    public ValidateParamsException(String message) {
        super(message);
    }
    public ValidateParamsException(String message, Throwable cause) {
        super(message, cause);
    }
}
