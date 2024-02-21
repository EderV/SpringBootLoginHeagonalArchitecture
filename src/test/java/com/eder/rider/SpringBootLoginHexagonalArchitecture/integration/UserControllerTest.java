package com.eder.rider.SpringBootLoginHexagonalArchitecture.integration;

import com.eder.rider.SpringBootLoginHexagonalArchitecture.infrastructure.dto.request.CredentialsRequest;
import com.eder.rider.SpringBootLoginHexagonalArchitecture.infrastructure.dto.request.RegistrationRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("qa")
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void registration_new_user_should_return_OK() throws Exception {
        RegistrationRequest request = new RegistrationRequest();
        request.setEmail("user2@myemail.com");
        request.setUsername("user2");
        request.setPassword("123456");

        ObjectMapper objectMapper = new ObjectMapper();
        String requestJson = objectMapper.writeValueAsString(request);

        mockMvc.perform(post("/api/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(status().isCreated());
    }

    @Test
    public void registration_existing_username_should_return_CONFLICT() throws Exception {
        RegistrationRequest request = new RegistrationRequest();
        request.setEmail("user2@myemail.com");
        request.setUsername("user2");
        request.setPassword("123456");

        ObjectMapper objectMapper = new ObjectMapper();
        String requestJson = objectMapper.writeValueAsString(request);

        mockMvc.perform(post("/api/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(status().isConflict());
    }

    @Test
    public void registration_without_data_should_return_BAD_REQUEST() throws Exception {
        RegistrationRequest request = new RegistrationRequest();

        ObjectMapper objectMapper = new ObjectMapper();
        String requestJson = objectMapper.writeValueAsString(request);

        mockMvc.perform(post("/api/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void login_should_return_OK() throws Exception {
        CredentialsRequest request = new CredentialsRequest();
        request.setUsername("user1");
        request.setPassword("123456");

        ObjectMapper objectMapper = new ObjectMapper();
        String requestJson = objectMapper.writeValueAsString(request);

        mockMvc.perform(post("/api/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                        .andExpect(status().isOk());
    }

    @Test
    public void login_bad_credentials_should_return_UNAUTHORIZED() throws Exception {
        CredentialsRequest request = new CredentialsRequest();
        request.setUsername("user1");
        request.setPassword("aaaaaaa");

        ObjectMapper objectMapper = new ObjectMapper();
        String requestJson = objectMapper.writeValueAsString(request);

        mockMvc.perform(post("/api/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                        .andExpect(status().isUnauthorized());
    }

    @Test
    public void login_without_data_should_return_BAD_REQUEST() throws Exception {
        CredentialsRequest request = new CredentialsRequest();

        ObjectMapper objectMapper = new ObjectMapper();
        String requestJson = objectMapper.writeValueAsString(request);

        mockMvc.perform(post("/api/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                        .andExpect(status().isBadRequest());
    }

}
