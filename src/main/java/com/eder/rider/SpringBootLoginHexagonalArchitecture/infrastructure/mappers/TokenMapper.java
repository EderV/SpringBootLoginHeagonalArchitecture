package com.eder.rider.SpringBootLoginHexagonalArchitecture.infrastructure.mappers;

import com.eder.rider.SpringBootLoginHexagonalArchitecture.domain.Token;
import com.eder.rider.SpringBootLoginHexagonalArchitecture.infrastructure.dto.request.TokenRequest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface TokenMapper {

    TokenMapper MAPPER = Mappers.getMapper(TokenMapper.class);

    TokenRequest toTokenRequest(Token token);

}
