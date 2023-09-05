package ru.sber.beautifulcode.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class BeautifulCodeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldReturnResponse() throws Exception {
        String JSON = "{\"text\": \"anyText\"}";
        mockMvc.perform(MockMvcRequestBuilders.
                        post("/api/checkBrackets")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(header().exists("requestId"));
    }
}