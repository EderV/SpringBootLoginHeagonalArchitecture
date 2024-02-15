package com.eder.rider.SpringBootLoginHexagonalArchitecture.application;

import com.eder.rider.SpringBootLoginHexagonalArchitecture.domain.Registration;
import com.eder.rider.SpringBootLoginHexagonalArchitecture.domain.Role;
import com.eder.rider.SpringBootLoginHexagonalArchitecture.domain.User;
import com.eder.rider.SpringBootLoginHexagonalArchitecture.domain.exceptions.UserAlreadyRegisteredException;
import com.eder.rider.SpringBootLoginHexagonalArchitecture.domain.in.RegistrationServicePort;
import com.eder.rider.SpringBootLoginHexagonalArchitecture.domain.out.UserRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
@RequiredArgsConstructor
public class RegistrationServiceDefault implements RegistrationServicePort {

    private final PasswordEncoder passwordEncoder;
    private final UserRepositoryPort userRepositoryPort;

    @Override
    public void registerUser(Registration registration) throws UserAlreadyRegisteredException {
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

        var user = createUserEntity(email, username, password);
        userRepositoryPort.saveUser(user);
    }

    private User createUserEntity(String email, String username, String password) {
        var user = User.builder()
                .email(email)
                .username(username)
                .password(passwordEncoder.encode(password))
                .accountEnabled(true)
                .accountExpired(false)
                .accountLocked(false)
                .credentialsExpired(false)
                .build();

        var roleDefault = Role.builder()
                .user(user)
                .role("ROLE_USER")
                .enabled(true)
                .build();

        var roles = Arrays.asList(roleDefault); // , roleUser);
        user.setRoles(roles);

        return user;
    }

}
