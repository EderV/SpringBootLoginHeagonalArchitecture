package com.eder.rider.SpringBootLoginHexagonalArchitecture.application;

import com.eder.rider.SpringBootLoginHexagonalArchitecture.domain.Token;
import com.eder.rider.SpringBootLoginHexagonalArchitecture.domain.component.KeyUtils;
import com.eder.rider.SpringBootLoginHexagonalArchitecture.domain.component.TokenGenerator;
import com.eder.rider.SpringBootLoginHexagonalArchitecture.domain.in.JwtServicePort;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.SignatureException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JwtServiceDefault implements JwtServicePort {

    private final TokenGenerator tokenGenerator;
    private final KeyUtils keyUtils;

    @Override
    public Token getAccessToken(Authentication authentication) {
        return tokenGenerator.createToken(authentication);
    }

    @Override
    public String extractUsernameFromAccessToken(String token) {
        return extractClaimsFromAccessToken(token).getSubject();
    }

    @Override
    public boolean validateAccessToken(String token)
            throws SignatureException,
                MalformedJwtException,
                ExpiredJwtException,
                UnsupportedJwtException,
                IllegalArgumentException
    {
        return extractUsernameFromAccessToken(token) != null;
    }

    private Claims extractClaimsFromAccessToken(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(keyUtils.getAccessTokenPublicKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

}
