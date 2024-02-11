package com.eder.rider.SpringBootLoginHexagonalArchitecture.infrastructure.controller;

import com.eder.rider.SpringBootLoginHexagonalArchitecture.domain.Credentials;
import com.eder.rider.SpringBootLoginHexagonalArchitecture.domain.Token;
import com.eder.rider.SpringBootLoginHexagonalArchitecture.domain.in.AuthCheckerPort;
import com.eder.rider.SpringBootLoginHexagonalArchitecture.domain.in.AuthServicePort;
import com.eder.rider.SpringBootLoginHexagonalArchitecture.domain.in.JwtServicePort;
import com.eder.rider.SpringBootLoginHexagonalArchitecture.infrastructure.dto.request.CredentialsRequest;
import com.eder.rider.SpringBootLoginHexagonalArchitecture.infrastructure.dto.request.TokenRequest;
import com.eder.rider.SpringBootLoginHexagonalArchitecture.infrastructure.mappers.CredentialsMapper;
import com.eder.rider.SpringBootLoginHexagonalArchitecture.infrastructure.mappers.TokenMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class UserController {

    private final AuthCheckerPort authCheckerPort;
    private final AuthServicePort authServicePort;
    private final JwtServicePort jwtServicePort;

    @Value("${access-token.duration_minutes}")
    private int accessTokenDuration;

    @GetMapping
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody CredentialsRequest credentialsRequest)
            throws IllegalArgumentException, AuthenticationException {

        var credentials = toCredentials(credentialsRequest);

        authCheckerPort.checkCredentials(credentials);

        var authentication = authServicePort.login(credentials);
        var token = jwtServicePort.getAccessToken(authentication);
        var tokenRequest = toTokenRequest(token);

        tokenRequest.setSessionExpiration(accessTokenDuration);

        return new ResponseEntity<>(tokenRequest, HttpStatus.OK);
    }

    private Credentials toCredentials(CredentialsRequest credentialsRequest) {
        return CredentialsMapper.MAPPER.toCredentials(credentialsRequest);
    }

    private TokenRequest toTokenRequest(Token token) {
        return TokenMapper.MAPPER.toTokenRequest(token);
    }

}
