package com.eder.rider.SpringBootLoginHexagonalArchitecture.domain.in;

import com.eder.rider.SpringBootLoginHexagonalArchitecture.domain.Credentials;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

public interface AuthServicePort {

    Authentication login(Credentials credentials) throws AuthenticationException;

}
