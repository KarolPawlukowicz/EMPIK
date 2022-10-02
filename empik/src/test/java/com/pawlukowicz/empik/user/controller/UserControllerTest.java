package com.pawlukowicz.empik.user.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pawlukowicz.empik.user.dto.UserDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {

    @Autowired private MockMvc mockMvc;

    @Test
    @DisplayName("Should return correct value")
    void getUserByCorrectLogin() throws Exception {
        // Given
        String login = "KarolPawlukowicz";

        // When
        ResultActions response = mockMvc.perform(MockMvcRequestBuilders.get("/users/" + login));

        // Then
        ObjectMapper mapper = new ObjectMapper();
        int expectedResponseCode = 200;
        UserDTO expectedResponse = new UserDTO(
                60007028L,
                "KarolPawlukowicz",
                "Karol Pawlukowicz",
                "User",
                "https://avatars.githubusercontent.com/u/60007028?v=4",
                "2020-01-17T14:45:54Z",
                21.0
        );
        String expectedContentType = "application/json";

        response.andExpect(result -> {
                var resp = result.getResponse();
                Assertions.assertEquals(mapper.writeValueAsString(expectedResponse), resp.getContentAsString());
                Assertions.assertEquals(expectedContentType, resp.getContentType());
                Assertions.assertEquals(expectedResponseCode, resp.getStatus());
            }
        );
    }

    @Test
    @DisplayName("Should throw an exception")
    void getUserByIncorrectLogin() throws Exception {
        // Given
        String login = "KarolPawlukowicz321432";

        // When
        ResultActions response = mockMvc.perform(MockMvcRequestBuilders.get("/users/" + login));

        // Then
        int expectedResponseCode = 404;
        String expectedContentType = "application/json";
        response.andExpect(result -> {
                    var resp = result.getResponse();
                    Assertions.assertEquals(expectedContentType, resp.getContentType());
                    Assertions.assertEquals(expectedResponseCode, resp.getStatus());
                }
        );
    }
}