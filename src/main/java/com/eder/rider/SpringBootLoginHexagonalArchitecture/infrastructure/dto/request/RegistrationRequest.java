package com.eder.rider.SpringBootLoginHexagonalArchitecture.infrastructure.dto.request;

import lombok.Data;

@Data
public class RegistrationRequest {

    private String email;
    private String username;
    private String password;

}
