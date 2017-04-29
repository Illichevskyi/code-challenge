package com.hsbc.exception;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class PostSizeExceedException extends RuntimeException {
    @Value("${post-exception-text}")
    private static String ERROR_MESSAGE;

    public PostSizeExceedException() {
        super(ERROR_MESSAGE);
    }
}
