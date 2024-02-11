package com.eder.rider.SpringBootLoginHexagonalArchitecture.domain.in;

import com.eder.rider.SpringBootLoginHexagonalArchitecture.domain.Credentials;

public interface AuthCheckerPort {

    void checkCredentials(Credentials credentials) throws IllegalArgumentException;

}
