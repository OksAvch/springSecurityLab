package org.javamp.module4.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(DataController.class)
class DataControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser(username = "admin")
    void shouldReturnSecuredData() throws Exception {
        ResultActions resultActions = mockMvc.perform(get("/info"));

        resultActions
                .andExpect(status().isOk())
                .andExpect(content().string("MVC application"));
    }

    @Test
    void shouldReturnErrorIfNotAuthorized() throws Exception {
        ResultActions resultActions = mockMvc.perform(get("/info"));

        resultActions
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithMockUser(username = "user")
    void shouldReturnUnsecuredData() throws Exception {
        ResultActions resultActions = mockMvc.perform(get("/home"));

        resultActions
                .andExpect(status().isOk())
                .andExpect(content().string("It is a Home page"));
    }

    @Test
    @WithMockUser(username = "user")
    void shouldReturnAdministrationData() throws Exception {
        ResultActions resultActions = mockMvc.perform(get("/admin"));

        resultActions
                .andExpect(status().isOk())
                .andExpect(content().string("It is an Administration page"));
    }
}