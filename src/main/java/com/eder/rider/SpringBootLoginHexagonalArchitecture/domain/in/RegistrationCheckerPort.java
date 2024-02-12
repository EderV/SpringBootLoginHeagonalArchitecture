package com.eder.rider.SpringBootLoginHexagonalArchitecture.domain.in;

import com.eder.rider.SpringBootLoginHexagonalArchitecture.domain.Registration;

public interface RegistrationCheckerPort {

    void checkRegistration(Registration registration) throws IllegalArgumentException;

}
