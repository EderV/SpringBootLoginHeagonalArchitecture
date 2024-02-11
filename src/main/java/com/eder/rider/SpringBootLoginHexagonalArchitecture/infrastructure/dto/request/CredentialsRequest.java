package com.eder.rider.SpringBootLoginHexagonalArchitecture.infrastructure.dto.request;

import lombok.Data;

@Data
public class CredentialsRequest {

    String username;
    String password;

}
