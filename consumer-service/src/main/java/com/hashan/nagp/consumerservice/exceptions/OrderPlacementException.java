package com.hashan.nagp.consumerservice.exceptions;

public class OrderPlacementException extends Exception {

    public OrderPlacementException(String message) {
        super(message);
    }
    public OrderPlacementException(String message, Throwable cause) {
        super(message, cause);
    }
}
