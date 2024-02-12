package com.eder.rider.SpringBootLoginHexagonalArchitecture.domain.in;

import com.eder.rider.SpringBootLoginHexagonalArchitecture.domain.Registration;
import com.eder.rider.SpringBootLoginHexagonalArchitecture.domain.exceptions.UserAlreadyRegisteredException;
import org.springframework.security.core.Authentication;

public interface RegistrationServicePort {

    Authentication registerUser(Registration registration) throws UserAlreadyRegisteredException;

}
