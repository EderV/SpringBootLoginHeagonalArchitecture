package com.eder.rider.SpringBootLoginHexagonalArchitecture.integration.authenticated;

import com.eder.rider.SpringBootLoginHexagonalArchitecture.infrastructure.dto.request.CredentialsRequest;
import com.eder.rider.SpringBootLoginHexagonalArchitecture.infrastructure.dto.request.TokenRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("qa")
public abstract class RoleControllerBaseTest {

    @Autowired
    protected MockMvc mockMvc;

    protected TokenRequest loginUser(String username, String password) throws Exception {
        CredentialsRequest request = new CredentialsRequest();
        request.setUsername(username);
        request.setPassword(password);

        ObjectMapper objectMapper = new ObjectMapper();
        String requestJson = objectMapper.writeValueAsString(request);

        var mvcResult = mockMvc.perform(post("/api/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andReturn();

        var stringResponse = mvcResult.getResponse().getContentAsString();

        return new ObjectMapper().readValue(stringResponse, TokenRequest.class);
    }

}
