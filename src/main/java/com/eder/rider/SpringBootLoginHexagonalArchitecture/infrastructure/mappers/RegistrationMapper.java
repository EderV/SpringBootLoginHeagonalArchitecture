package com.eder.rider.SpringBootLoginHexagonalArchitecture.infrastructure.mappers;

import com.eder.rider.SpringBootLoginHexagonalArchitecture.domain.Registration;
import com.eder.rider.SpringBootLoginHexagonalArchitecture.infrastructure.dto.request.RegistrationRequest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface RegistrationMapper {

    RegistrationMapper MAPPER = Mappers.getMapper(RegistrationMapper.class);

    Registration toRegistration(RegistrationRequest registrationRequest);

}
