package com.morozik.app.exception;

public class BadRequestException extends ApplicationException {
    public BadRequestException(String message, int code) {
        super(message, code);
    }
}
