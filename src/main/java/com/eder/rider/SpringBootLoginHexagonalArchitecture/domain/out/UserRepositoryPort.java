package com.eder.rider.SpringBootLoginHexagonalArchitecture.domain.out;

import com.eder.rider.SpringBootLoginHexagonalArchitecture.domain.User;

public interface UserRepositoryPort {

    void saveUser(User user);

    User getUserFromUsername(String username);

    User getUserFromEmail(String email);

}
