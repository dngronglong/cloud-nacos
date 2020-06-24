package com.example.security.exception;

import org.springframework.security.core.AuthenticationException;

public class LoginCountToManyException extends AuthenticationException {
    public LoginCountToManyException(String msg, Throwable t) {
        super(msg, t);
    }

    public LoginCountToManyException(String msg) {
        super(msg);
    }
}
