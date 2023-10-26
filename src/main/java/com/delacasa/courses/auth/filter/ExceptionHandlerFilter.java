package com.delacasa.courses.auth.filter;

import com.delacasa.courses.exception.CustomException;
import com.delacasa.courses.exception.InternalServerErrException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@RequiredArgsConstructor
@Order(value = Ordered.HIGHEST_PRECEDENCE)
@Component
public class ExceptionHandlerFilter extends OncePerRequestFilter {

    private final ObjectMapper jsonMapper;

    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse resp, FilterChain fc) throws ServletException, IOException {
        resp.setContentType("application/json");
        try {
            fc.doFilter(req, resp);
        } catch (RuntimeException e) {
            handleException(e, resp, req);
        }
    }

    private void handleException(RuntimeException e, HttpServletResponse resp, HttpServletRequest req) throws IOException {
        if (e instanceof CustomException ce) {
            resp.setStatus(ce.getHttpStatus());
            resp.getWriter()
                    .write(jsonMapper.writeValueAsString(ce.toJson(req.getRequestURI())));
            return;
        }
        
        resp.setStatus(500);
        resp.getWriter()
                .write(jsonMapper.writeValueAsString(
                        new InternalServerErrException().toJson(req.getRequestURI())));
    }

}


