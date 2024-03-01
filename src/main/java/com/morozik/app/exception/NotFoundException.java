package com.morozik.app.exception;

public class NotFoundException extends ApplicationException {
    public NotFoundException(String message, int code) {
        super(message, code);
    }
}
