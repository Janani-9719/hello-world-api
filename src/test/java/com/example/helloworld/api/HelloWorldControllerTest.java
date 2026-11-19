package com.example.helloworld.api;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.helloworld.service.GreetingService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(HelloWorldController.class)
@Import(GreetingService.class)
class HelloWorldControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void returns200ForNameInFirstHalfOfAlphabet() throws Exception {
        mockMvc.perform(get("/hello-world").param("name", "alice"))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"message\":\"Hello Alice\"}", true));
    }

    @Test
    void returns400ForNameInSecondHalfOfAlphabet() throws Exception {
        mockMvc.perform(get("/hello-world").param("name", "zoe"))
                .andExpect(status().isBadRequest())
                .andExpect(content().json("{\"error\":\"Invalid Input\"}", true));
    }

    @Test
    void returns400WhenNameIsMissing() throws Exception {
        mockMvc.perform(get("/hello-world"))
                .andExpect(status().isBadRequest())
                .andExpect(content().json("{\"error\":\"Invalid Input\"}", true));
    }

    @Test
    void returns400WhenNameIsEmpty() throws Exception {
        mockMvc.perform(get("/hello-world").param("name", ""))
                .andExpect(status().isBadRequest())
                .andExpect(content().json("{\"error\":\"Invalid Input\"}", true));
    }
}
