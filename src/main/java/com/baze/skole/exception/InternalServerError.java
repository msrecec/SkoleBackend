package com.baze.skole.exception;

public class InternalServerError extends Exception{

    public InternalServerError() {
        super();
    }

    public InternalServerError(String message) {
        super(message);
    }

    public InternalServerError(String message, Throwable cause) {
        super(message, cause);
    }

    public InternalServerError(Throwable cause) {
        super(cause);
    }
}
