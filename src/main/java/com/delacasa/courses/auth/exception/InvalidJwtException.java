package com.delacasa.courses.auth.exception;

import com.delacasa.courses.exception.CustomException;
import lombok.Getter;

public class InvalidJwtException extends CustomException {
    private static final String DEFAULT_MSG = "Jwt is invalid";

    @Getter
    private final String name = "Invalid jwt";

    @Getter
    private final int httpStatus = 401;

    public InvalidJwtException() {
        super(DEFAULT_MSG);
    }

    public InvalidJwtException(String message) {
        super(message);
    }
}
