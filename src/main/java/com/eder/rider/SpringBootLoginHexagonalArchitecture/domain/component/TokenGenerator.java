package com.eder.rider.SpringBootLoginHexagonalArchitecture.domain.component;

import com.eder.rider.SpringBootLoginHexagonalArchitecture.domain.Token;
import org.springframework.security.core.Authentication;

public interface TokenGenerator {

    Token createToken(Authentication authentication);

}
