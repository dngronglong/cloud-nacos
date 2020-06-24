package com.example.security.exception;

import org.springframework.security.core.AuthenticationException;

public class VerifyFailedException extends AuthenticationException {
    public VerifyFailedException(String msg, Throwable t) {
        super(msg, t);
    }

    public VerifyFailedException(String msg) {
        super(msg);
    }
}
