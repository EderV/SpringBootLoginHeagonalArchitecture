package com.eder.rider.SpringBootLoginHexagonalArchitecture.application;

import com.eder.rider.SpringBootLoginHexagonalArchitecture.application.base.BaseTextChecker;
import com.eder.rider.SpringBootLoginHexagonalArchitecture.domain.Registration;
import com.eder.rider.SpringBootLoginHexagonalArchitecture.domain.in.RegistrationCheckerPort;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class RegistrationCheckerDefault extends BaseTextChecker implements RegistrationCheckerPort {

    @Override
    public void checkRegistration(Registration registration) throws IllegalArgumentException {
        nullCheck(registration);
        checkMinimumDataRequired(registration);
        checkIllegalCharactersInAllFields(registration);
    }

    private void checkMinimumDataRequired(Registration registration) throws IllegalArgumentException {
        if (registration.getEmail() == null || registration.getEmail().isBlank() ||
                registration.getUsername() == null || registration.getUsername().isBlank() ||
                registration.getPassword() == null || registration.getPassword().isBlank()
        ) {
            throw new IllegalArgumentException(
                    "Data provided in Credentials is not minimum required and/or is not correct");
        }
    }

    private void checkIllegalCharactersInAllFields(Registration registration) throws IllegalArgumentException {
        var email = registration.getEmail();
        var username = registration.getUsername();
        var password = registration.getPassword();

        var texts = Arrays.asList(email, username, password);
        checkIllegalCharacters(texts);
    }

}
