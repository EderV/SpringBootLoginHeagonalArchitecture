package com.eder.rider.SpringBootLoginHexagonalArchitecture.domain.exceptions;

import org.springframework.security.core.AuthenticationException;

public class CustomAuthenticationException extends AuthenticationException {

    public CustomAuthenticationException(String msg) {
        super(msg);
    }

}
