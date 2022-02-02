package com.interview.app.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class CalculatorControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldReturnBadRequestWhenIncorrectValuePassInQuery() throws Exception {
        String query = "..";
        mockMvc.perform(get("/calculus")
                        .param("query", query))
                .andExpect(status().isBadRequest())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message").value("Unable to decode base64 value:[" + query + "]"));
    }

    @Test
    void shouldReturnDecodedQuery() throws Exception {
        mockMvc.perform(get("/calculus")
                        .param("query", "MiAqICgyMy8oMyozKSktIDIzICogKDIqMyk"))
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message").value("2 * (23/(3*3))- 23 * (2*3)"));
    }

}