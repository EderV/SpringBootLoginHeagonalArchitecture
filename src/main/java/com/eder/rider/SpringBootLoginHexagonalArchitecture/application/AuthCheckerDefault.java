package com.eder.rider.SpringBootLoginHexagonalArchitecture.application;

import com.eder.rider.SpringBootLoginHexagonalArchitecture.application.base.BaseTextChecker;
import com.eder.rider.SpringBootLoginHexagonalArchitecture.domain.Credentials;
import com.eder.rider.SpringBootLoginHexagonalArchitecture.domain.in.AuthCheckerPort;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class AuthCheckerDefault extends BaseTextChecker implements AuthCheckerPort {

    // TODO Implement unit/functional tests

    @Override
    public void checkCredentials(Credentials credentials) throws IllegalArgumentException {
        nullCheck(credentials);
        checkMinimumDataRequired(credentials);
        checkIllegalCharactersInAllFields(credentials);
    }

    private void checkMinimumDataRequired(Credentials credentials) throws IllegalArgumentException {
        if (credentials.getUsername() == null || credentials.getUsername().isBlank() ||
            credentials.getPassword() == null || credentials.getPassword().isBlank()
        ) {
            throw new IllegalArgumentException(
                    "Data provided in Credentials is not minimum required and/or is not correct");
        }
    }

    private void checkIllegalCharactersInAllFields(Credentials credentials) throws IllegalArgumentException {
        var username = credentials.getUsername();
        var password = credentials.getPassword();

        var texts = Arrays.asList(username, password);
        checkIllegalCharacters(texts);
    }

}
