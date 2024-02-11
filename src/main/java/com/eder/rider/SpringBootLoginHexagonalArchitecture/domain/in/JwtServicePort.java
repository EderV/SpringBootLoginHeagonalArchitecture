package com.eder.rider.SpringBootLoginHexagonalArchitecture.domain.in;

import com.eder.rider.SpringBootLoginHexagonalArchitecture.domain.Token;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.security.core.Authentication;

public interface JwtServicePort {

    Token getAccessToken(Authentication authentication);

    String extractUsernameFromAccessToken(String token);

    boolean validateAccessToken(String token)
            throws SignatureException, MalformedJwtException, ExpiredJwtException, UnsupportedJwtException, IllegalArgumentException;;

}
