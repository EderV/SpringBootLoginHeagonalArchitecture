package com.eder.rider.SpringBootLoginHexagonalArchitecture.domain.out;

import com.eder.rider.SpringBootLoginHexagonalArchitecture.domain.User;

public interface UserRepositoryPort {

    User getUserFromUsername(String username);

}
