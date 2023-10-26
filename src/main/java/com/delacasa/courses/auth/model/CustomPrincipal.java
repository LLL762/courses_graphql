package com.delacasa.courses.auth.model;

import com.delacasa.courses.auth.entity.AppUser;
import com.delacasa.courses.auth.entity.AppUserRole;

import java.util.List;


public record CustomPrincipal(String username, List<String> roles) {

    public CustomPrincipal(AppUser user) {
        this(user.getUsername(),
                user.getRoles().stream().map(AppUserRole::getName).toList());
    }

}
