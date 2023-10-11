package com.delacasa.courses.auth.filter;

import com.delacasa.courses.auth.model.CustomAuthToken;
import com.delacasa.courses.auth.service.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;

import static com.delacasa.courses.auth.service.JwtService.JWT_PREFIX;

@RequiredArgsConstructor
public class UsernameAndPasswordFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtServ;


    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {

        final String principal = request.getParameter("username");
        final String credentials = request.getParameter("password");
        final UsernamePasswordAuthenticationToken auth =
                new UsernamePasswordAuthenticationToken(principal, credentials);

        return authenticationManager.authenticate(auth);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {

        response.setHeader("Authorization", JWT_PREFIX + jwtServ.createToken((CustomAuthToken) authResult));
    }

    @Override
    //TODO do
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        super.unsuccessfulAuthentication(request, response, failed);
    }
}
