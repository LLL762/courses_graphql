package com.delacasa.courses.auth.exception;

import com.delacasa.courses.auth.service.JwtService;
import com.delacasa.courses.exception.CustomException;
import lombok.Getter;

public class NoJwtException extends CustomException {
    private static final String DEFAULT_MSG = "No jwt found in Authorization header. " +
            "Jwt must be prefixed with " + "'" + JwtService.JWT_PREFIX + "'";

    @Getter
    private final String name = "Missing jwt";

    @Getter
    private final int httpStatus = 401;

    public NoJwtException() {
        super(DEFAULT_MSG);
    }

    public NoJwtException(String message) {
        super(message);
    }
}
