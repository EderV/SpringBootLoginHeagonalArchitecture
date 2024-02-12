package com.eder.rider.SpringBootLoginHexagonalArchitecture.domain.exceptions;

public class UserAlreadyRegisteredException extends RuntimeException {

    public UserAlreadyRegisteredException(String message) {
        super(message);
    }
}
