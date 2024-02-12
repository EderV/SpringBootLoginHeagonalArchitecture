package com.eder.rider.SpringBootLoginHexagonalArchitecture.infrastructure.controller;

import com.eder.rider.SpringBootLoginHexagonalArchitecture.domain.Credentials;
import com.eder.rider.SpringBootLoginHexagonalArchitecture.domain.Registration;
import com.eder.rider.SpringBootLoginHexagonalArchitecture.domain.Token;
import com.eder.rider.SpringBootLoginHexagonalArchitecture.domain.in.*;
import com.eder.rider.SpringBootLoginHexagonalArchitecture.infrastructure.dto.request.CredentialsRequest;
import com.eder.rider.SpringBootLoginHexagonalArchitecture.infrastructure.dto.request.RegistrationRequest;
import com.eder.rider.SpringBootLoginHexagonalArchitecture.infrastructure.dto.request.TokenRequest;
import com.eder.rider.SpringBootLoginHexagonalArchitecture.infrastructure.mappers.CredentialsMapper;
import com.eder.rider.SpringBootLoginHexagonalArchitecture.infrastructure.mappers.RegistrationMapper;
import com.eder.rider.SpringBootLoginHexagonalArchitecture.infrastructure.mappers.TokenMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class UserController {

    private final RegistrationCheckerPort registrationCheckerPort;
    private final RegistrationServicePort registrationServicePort;
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
        var tokenRequest = generateTokenRequest(authentication);

        return new ResponseEntity<>(tokenRequest, HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegistrationRequest registrationRequest)
            throws IllegalArgumentException {

        var registration = toRegistration(registrationRequest);

        registrationCheckerPort.checkRegistration(registration);

        var authentication = registrationServicePort.registerUser(registration);
        var tokenRequest = generateTokenRequest(authentication);

        return new ResponseEntity<>(tokenRequest, HttpStatus.OK);
    }

    private TokenRequest generateTokenRequest(Authentication authentication) {
        var token = jwtServicePort.getAccessToken(authentication);
        var tokenRequest = toTokenRequest(token);

        tokenRequest.setSessionExpiration(accessTokenDuration);

        return tokenRequest;
    }

    private Credentials toCredentials(CredentialsRequest credentialsRequest) {
        return CredentialsMapper.MAPPER.toCredentials(credentialsRequest);
    }

    private TokenRequest toTokenRequest(Token token) {
        return TokenMapper.MAPPER.toTokenRequest(token);
    }

    private Registration toRegistration(RegistrationRequest registrationRequest) {
        return RegistrationMapper.MAPPER.toRegistration(registrationRequest);
    }

}
