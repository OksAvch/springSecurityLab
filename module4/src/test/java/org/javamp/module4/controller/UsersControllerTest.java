package org.javamp.module4.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.javamp.module4.data.ChangePasswordDto;
import org.javamp.module4.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UsersController.class)
class UsersControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private UserServiceImpl mockUserServiceMock;

    @Test
    @WithMockUser(username = "admin")
    void shouldChangePassword() throws Exception {
        ChangePasswordDto dto = new ChangePasswordDto("admin", "old", "new");

        String userJson = objectMapper.writeValueAsString(dto);

        ResultActions resultActions = mockMvc.perform(patch("/changePassword")
                .contentType(MediaType.APPLICATION_JSON)
                .with(csrf())
                .content(userJson));

        resultActions
                .andExpect(status().isOk());
    }
}