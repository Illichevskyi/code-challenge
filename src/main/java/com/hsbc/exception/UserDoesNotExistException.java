package com.hsbc.exception;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class UserDoesNotExistException extends RuntimeException {
    @Value("${user-does-not-exist-exception-text}")
    private static String ERROR_MESSAGE;

    public UserDoesNotExistException() {
        super(ERROR_MESSAGE);
    }
}
