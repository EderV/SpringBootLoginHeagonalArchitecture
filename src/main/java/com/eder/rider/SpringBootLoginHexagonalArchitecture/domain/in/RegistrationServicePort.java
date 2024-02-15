package com.eder.rider.SpringBootLoginHexagonalArchitecture.domain.in;

import com.eder.rider.SpringBootLoginHexagonalArchitecture.domain.Registration;
import com.eder.rider.SpringBootLoginHexagonalArchitecture.domain.exceptions.UserAlreadyRegisteredException;

public interface RegistrationServicePort {

    void registerUser(Registration registration) throws UserAlreadyRegisteredException;

}
