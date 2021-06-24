package com.zova0.rinjindoudeshouserver.exception;

public class AppException extends RuntimeException {
    public AppException(String message, Exception exception) {
        super(message, exception);
    }

    public AppException(String exceptionMessage) {
        super(exceptionMessage);
    }
}
