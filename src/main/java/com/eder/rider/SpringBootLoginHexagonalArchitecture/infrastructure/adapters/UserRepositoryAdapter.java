package com.eder.rider.SpringBootLoginHexagonalArchitecture.infrastructure.adapters;

import com.eder.rider.SpringBootLoginHexagonalArchitecture.domain.User;
import com.eder.rider.SpringBootLoginHexagonalArchitecture.domain.out.UserRepositoryPort;
import com.eder.rider.SpringBootLoginHexagonalArchitecture.infrastructure.dto.entity.UserEntity;
import com.eder.rider.SpringBootLoginHexagonalArchitecture.infrastructure.mappers.UserMapper;
import com.eder.rider.SpringBootLoginHexagonalArchitecture.infrastructure.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserRepositoryAdapter implements UserRepositoryPort {

    private final UserRepository userRepository;

    @Override
    public User getUserFromUsername(String username) {
        var userEntity = userRepository.getUserByUsername(username);
        return userEntity.map(this::toUser).orElse(null);
    }

    private User toUser(UserEntity userEntity) {
        return UserMapper.MAPPER.toUser(userEntity);
    }

}
