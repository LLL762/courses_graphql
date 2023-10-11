package com.delacasa.courses.auth.model;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;

public class CustomAuthToken extends AbstractAuthenticationToken {
    private final CustomPrincipal principal;

    public CustomAuthToken(CustomPrincipal principal, List<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.principal = principal;
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getDetails() {
        return null;
    }

    @Override
    public CustomPrincipal getPrincipal() {
        return principal;
    }

}
