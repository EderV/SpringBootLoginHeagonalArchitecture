package com.eder.rider.SpringBootLoginHexagonalArchitecture.application;

import com.eder.rider.SpringBootLoginHexagonalArchitecture.domain.Registration;
import com.eder.rider.SpringBootLoginHexagonalArchitecture.domain.exceptions.UserAlreadyRegisteredException;
import com.eder.rider.SpringBootLoginHexagonalArchitecture.domain.in.RegistrationServicePort;
import com.eder.rider.SpringBootLoginHexagonalArchitecture.domain.out.UserRepositoryPort;
import com.eder.rider.SpringBootLoginHexagonalArchitecture.infrastructure.dto.entity.RoleEntity;
import com.eder.rider.SpringBootLoginHexagonalArchitecture.infrastructure.dto.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
@RequiredArgsConstructor
public class RegistrationServiceDefault implements RegistrationServicePort {

    private final PasswordEncoder passwordEncoder;
    private final UserRepositoryPort userRepositoryPort;

    @Override
    public Authentication registerUser(Registration registration) throws UserAlreadyRegisteredException {
        var email = registration.getEmail();
        var username = registration.getUsername();
        var password = registration.getPassword();

        var userByEmail = userRepositoryPort.getUserFromEmail(email);
        if (userByEmail != null) {
            throw new UserAlreadyRegisteredException("Email " + email + " already exists");
        }

        var userByUsername = userRepositoryPort.getUserFromUsername(username);
        if (userByUsername != null) {
            throw new UserAlreadyRegisteredException("Username " + username + " already exists");
        }

        var userEntity = createUserEntity(email, username, password);
        userRepositoryPort.saveUser(userEntity);

        return new UsernamePasswordAuthenticationToken(
                userEntity, userEntity.getPassword(), userEntity.getAuthorities());
    }

    private UserEntity createUserEntity(String email, String username, String password) {
        var user = UserEntity.builder()
                .email(email)
                .username(username)
                .password(passwordEncoder.encode(password))
                .accountEnabled(true)
                .accountExpired(false)
                .accountLocked(false)
                .credentialsExpired(false)
                .build();

        var roleDefault = RoleEntity.builder()
                .user(user)
                .role("ROLE_USER")
                .enabled(true)
                .build();

        var roles = Arrays.asList(roleDefault); // , roleUser);
        user.setRoles(roles);

        return user;
    }

}
