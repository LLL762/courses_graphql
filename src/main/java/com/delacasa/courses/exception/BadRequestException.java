package com.delacasa.courses.exception;

import lombok.Getter;

public class BadRequestException extends CustomException {
    private static final String DEFAULT_MSG = "Bad request error";

    @Getter
    private final String name = "BadRequest error";
    @Getter
    private final int httpStatus = 400;

    public BadRequestException() {
        super(DEFAULT_MSG);
    }

    public BadRequestException(String message) {
        super(message);
    }
}
