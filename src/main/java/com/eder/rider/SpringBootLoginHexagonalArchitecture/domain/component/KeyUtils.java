package com.eder.rider.SpringBootLoginHexagonalArchitecture.domain.component;

import java.security.Key;

public interface KeyUtils {

    Key getAccessTokenPrivateKey();

    Key getAccessTokenPublicKey();

}
