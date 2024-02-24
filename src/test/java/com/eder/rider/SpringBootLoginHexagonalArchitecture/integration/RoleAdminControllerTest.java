package com.eder.rider.SpringBootLoginHexagonalArchitecture.integration;

import com.eder.rider.SpringBootLoginHexagonalArchitecture.infrastructure.dto.request.CredentialsRequest;
import com.eder.rider.SpringBootLoginHexagonalArchitecture.infrastructure.dto.request.TokenRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("qa")
public class RoleAdminControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void not_admin_user_should_return_UNAUTHORIZED() throws Exception {
        var tokenRequest = loginUser("user1", "123456");

        mockMvc.perform(get("/api/admin")
                        .header("Authorization", tokenRequest.getAccessToken())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(""))
                .andExpect(status().isUnauthorized());
    }

    @Test
    public void admin_user_should_return_OK() throws Exception {
        var tokenRequest = loginUser("AdminUser", "123456");

        mockMvc.perform(get("/api/admin")
                        .header("Authorization", "Bearer " + tokenRequest.getAccessToken())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(""))
                .andExpect(status().isOk());
    }

    private TokenRequest loginUser(String username, String password) throws Exception {
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
