package com.eder.rider.SpringBootLoginHexagonalArchitecture.infrastructure.repository;

import com.eder.rider.SpringBootLoginHexagonalArchitecture.infrastructure.dto.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, UUID> {

    @Query("" +
            "SELECT u FROM UserEntity u " +
            "LEFT JOIN FETCH u.roles r " +
            "WHERE u.username = ?1" +
            "")
    Optional<UserEntity> getUserByUsername(String username);

}
