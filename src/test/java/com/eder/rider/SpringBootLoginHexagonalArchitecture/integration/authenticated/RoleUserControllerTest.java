package com.eder.rider.SpringBootLoginHexagonalArchitecture.integration.authenticated;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class RoleUserControllerTest extends RoleControllerBaseTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void user_without_roles_should_return_UNAUTHORIZED() throws Exception {
        var tokenRequest = loginUser("UserNoRoles", "123456");

        mockMvc.perform(get("/api/user")
                        .header("Authorization", tokenRequest.getAccessToken())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(""))
                .andExpect(status().isUnauthorized());
    }

    @Test
    public void call_to_admin_with_role_user_should_return_FORBIDDEN() throws Exception {
        var tokenRequest = loginUser("user1", "123456");

        mockMvc.perform(get("/api/admin")
                        .header("Authorization", "Bearer " + tokenRequest.getAccessToken())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(""))
                .andExpect(status().isForbidden());
    }

    @Test
    public void user_with_role_user_should_return_OK() throws Exception {
        var tokenRequest = loginUser("user1", "123456");

        mockMvc.perform(get("/api/user")
                        .header("Authorization", "Bearer " + tokenRequest.getAccessToken())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(""))
                .andExpect(status().isOk());
    }

}
