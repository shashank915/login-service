package com.frostinteractive.loginservice.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@Slf4j
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        log.error("Responding with error message - {}", authException.getMessage());
//        ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Please Change your password");
        System.out.println(authException.getMessage());

        response.getOutputStream().print(authException.getMessage());
//        response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
    }
}