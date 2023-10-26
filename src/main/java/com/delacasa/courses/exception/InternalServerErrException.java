package com.delacasa.courses.exception;

import lombok.Getter;

public class InternalServerErrException extends CustomException {
    private static final String DEFAULT_MSG = "Internal server error";

    @Getter
    private final String name = "Internal server error";

    @Getter
    private final int httpStatus = 500;

    public InternalServerErrException() {
        super(DEFAULT_MSG);
    }

    public InternalServerErrException(String message) {
        super(message);
    }
}
