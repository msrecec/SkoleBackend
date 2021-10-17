package com.baze.skole.model.error;

public enum ErrorMessageConstants {
    RESOURCE_NOT_FOUND("resource was not found"), BAD_PARAMS("bad url parameters provided");

    public final String message;

    ErrorMessageConstants(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "ErrorMessageConstants{" +
                "message='" + message + '\'' +
                '}';
    }
}
