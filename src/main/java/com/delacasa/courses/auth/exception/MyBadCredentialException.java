package com.delacasa.courses.auth.exception;

import com.delacasa.courses.exception.CustomException;
import lombok.Getter;

public class MyBadCredentialException extends CustomException {

    private static final String DEFAULT_MSG = "Bad credentials";

    @Getter
    private final String name = "Bad credentials";

    @Getter
    private final int httpStatus = 401;

    public MyBadCredentialException() {
        super(DEFAULT_MSG);
    }

    public MyBadCredentialException(String message) {
        super(message);
    }

}
