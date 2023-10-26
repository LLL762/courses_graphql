package com.delacasa.courses.model;

import java.time.Instant;

public record JsonError(String msg, String name, String path, int httpStatus, String utc) {

    public JsonError(String msg, String name, String path, int httpStatus) {
        this(msg, name, path, httpStatus, Instant.now().toString());
    }

}
