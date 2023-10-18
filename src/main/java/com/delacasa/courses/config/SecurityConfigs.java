package com.delacasa.courses.config;

import com.delacasa.courses.auth.AuthProvider;
import com.delacasa.courses.auth.filter.ExceptionHandlerFilter;
import com.delacasa.courses.auth.filter.JwtFilter;
import com.delacasa.courses.auth.filter.UsernameAndPasswordFilter;
import com.delacasa.courses.auth.service.AuthenticateService;
import com.delacasa.courses.auth.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfigs {

    private final AuthProvider authProvider;
    private final JwtService jwtServ;
    private final AuthenticateService authenticateServ;

    private final ExceptionHandlerFilter exceptionHandlerFilter;

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.csrf(AbstractHttpConfigurer::disable)
                .cors(AbstractHttpConfigurer::disable)
                .sessionManagement(s -> s.sessionCreationPolicy(STATELESS))
                .addFilterBefore(exceptionHandlerFilter, UsernameAndPasswordFilter.class)
                .addFilter(new UsernameAndPasswordFilter(authManager(), jwtServ))
                .addFilterBefore(new JwtFilter(jwtServ, authenticateServ), UsernameAndPasswordFilter.class)
        ;

        return http.build();
    }

    @Bean
    ProviderManager authManager() {
        return new ProviderManager(authProvider);
    }

}
