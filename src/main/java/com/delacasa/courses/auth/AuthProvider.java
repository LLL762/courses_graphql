package com.delacasa.courses.auth;

import com.delacasa.courses.auth.entity.AppUser;
import com.delacasa.courses.auth.service.AuthenticateService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuthProvider implements AuthenticationProvider {

    private final PasswordEncoder passwordEncoder;
    private final AuthenticateService authenticateServ;

    @Override
    public Authentication authenticate(Authentication auth) throws AuthenticationException {
        final String username = (String) auth.getPrincipal();
        final String password = (String) auth.getCredentials();

        final AppUser user = authenticateServ.canAuthenticate(username, password)
                .orElseThrow(() -> new BadCredentialsException("Bad credentials"));

        return authenticateServ.setUpAuthToken(user);
    }


    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
