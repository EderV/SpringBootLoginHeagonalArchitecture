package com.eder.rider.SpringBootLoginHexagonalArchitecture.infrastructure.mappers;

import com.eder.rider.SpringBootLoginHexagonalArchitecture.domain.Credentials;
import com.eder.rider.SpringBootLoginHexagonalArchitecture.infrastructure.dto.request.CredentialsRequest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CredentialsMapper {

    CredentialsMapper MAPPER = Mappers.getMapper(CredentialsMapper.class);

    Credentials toCredentials(CredentialsRequest credentialsRequest);

}
