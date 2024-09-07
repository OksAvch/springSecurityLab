package org.javamp.module4.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.javamp.module4.dto.ChangePasswordDto;
import org.javamp.module4.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.Collections;

import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
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

        ResultActions resultActions = mockMvc.perform(patch("/users/changePassword")
                .contentType(MediaType.APPLICATION_JSON)
                .with(csrf())
                .content(userJson));

        resultActions
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "admin")
    void shouldReturnDisabledUsers() throws Exception {
        when(mockUserServiceMock.getBlockedUsers()).thenReturn(Collections.singletonList("Blocked_user"));

        ResultActions resultActions = mockMvc.perform(get("/users/blocked")
                .with(csrf()));

        resultActions
                .andExpect(content().string("[\"Blocked_user\"]"))
                .andExpect(status().isOk());
    }
}