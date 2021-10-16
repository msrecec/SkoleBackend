package com.baze.skole.exception;

public class BadParamsException extends Exception{
    public BadParamsException() {
        super();
    }

    public BadParamsException(String message) {
        super(message);
    }

    public BadParamsException(String message, Throwable cause) {
        super(message, cause);
    }

    public BadParamsException(Throwable cause) {
        super(cause);
    }
}
