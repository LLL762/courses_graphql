package com.delacasa.courses.auth.filter;

import com.delacasa.courses.auth.exception.InvalidJwtException;
import com.delacasa.courses.auth.model.CustomAuthToken;
import com.delacasa.courses.auth.service.AuthenticateService;
import com.delacasa.courses.auth.service.JwtService;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jwt.SignedJWT;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import static com.delacasa.courses.auth.service.JwtService.JWT_PREFIX;


@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private static final List<String> EXCLUDED_URIS = List.of(
            "/login"
    );

    private final JwtService jwtServ;
    private final AuthenticateService authServ;


    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws ServletException, IOException {
        final String authorization = req.getHeader(HttpHeaders.AUTHORIZATION);

        if (authorization == null || !authorization.startsWith(JWT_PREFIX)) {
            chain.doFilter(req, res);
            return;
        }
        try {
            SignedJWT jws = jwtServ.validate(authorization)
                    .orElseThrow(InvalidJwtException::new);
            CustomAuthToken authentication = jwtServ.setUpAuthToken(jws);

            SecurityContextHolder.getContext().setAuthentication(authentication);
            chain.doFilter(req, res);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        } catch (JOSEException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    protected boolean shouldNotFilter(HttpServletRequest request)
            throws ServletException {
        String path = request.getRequestURI();
        return EXCLUDED_URIS.contains(path);
    }


}
