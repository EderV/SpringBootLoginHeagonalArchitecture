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
    public void saveUser(User user) {
        var userEntity = toUserEntity(user);
        userRepository.save(userEntity);
    }

    @Override
    public User getUserFromUsername(String username) {
        var userEntity = userRepository.getUserByUsername(username);
        return userEntity.map(this::toUser).orElse(null);
    }

    @Override
    public User getUserFromEmail(String email) {
        var userEntity = userRepository.getUserByEmail(email);
        return userEntity.map(this::toUser).orElse(null);
    }

    private User toUser(UserEntity userEntity) {
        return UserMapper.MAPPER.toUser(userEntity);
    }

    private UserEntity toUserEntity(User user) {
        return UserMapper.MAPPER.toUserEntity(user);
    }

}
