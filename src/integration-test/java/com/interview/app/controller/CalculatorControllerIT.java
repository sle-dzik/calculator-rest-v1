package com.interview.app.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class CalculatorControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldReturnBadRequestWhenIncorrectValuePassInQuery() throws Exception {
        String query = "wrongData";
        mockMvc.perform(get("/calculus")
                        .param("query", query))
                .andExpect(status().isBadRequest())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content()
                        .string("{\"error\":true,\"message\":\"Unable to decode base64 value:[wrongData]\"}"));
    }

    @Test
    void shouldReturnBadRequestWhenIncorrectOperationPassedInQuery() throws Exception {
        String expression = "2*(2/0)";
        String query = new String(Base64.getEncoder().encode(expression.getBytes(StandardCharsets.UTF_8)));

        mockMvc.perform(get("/calculus")
                        .param("query", query))
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content()
                        .string("{\"error\":true,\"message\":\"Divide by 0 is prohibited\"}"));
    }

    @Test
    void shouldReturnCorrectResult() throws Exception {
        String expression = "2 * (23/(3*3))- 23 * (2*3)";
        String query = new String(Base64.getEncoder().encode(expression.getBytes(StandardCharsets.UTF_8)));

        mockMvc.perform(get("/calculus")
                        .param("query", query))
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content()
                        .string("{\"error\":false,\"result\":-132.89}"));
    }

}