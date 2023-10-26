package com.delacasa.courses.exception;

import com.delacasa.courses.model.JsonError;

import java.util.Map;

public abstract class CustomException extends RuntimeException {

    public CustomException(String message) {
        super(message);
    }

    public abstract String getName();

    public abstract int getHttpStatus();

    public Map<String, JsonError> toJson(String path) {
        return Map.of("error",
                new JsonError(getMessage(), getName(), path, getHttpStatus()));
    }


}
