package com.eder.rider.SpringBootLoginHexagonalArchitecture.domain.out;

import com.eder.rider.SpringBootLoginHexagonalArchitecture.domain.User;
import com.eder.rider.SpringBootLoginHexagonalArchitecture.infrastructure.dto.entity.UserEntity;

public interface UserRepositoryPort {

    void saveUser(UserEntity userEntity);

    User getUserFromUsername(String username);

    User getUserFromEmail(String email);

}
