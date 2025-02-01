package com.pdl.backend;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(OrderAnnotation.class)
public class ImageControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @BeforeAll
    public static void reset() {
        ReflectionTestUtils.setField(Image.class, "count", Long.valueOf(0));
    }

    @Test
    @Order(1)
    public void getImageListShouldReturnSuccess() throws Exception {
        mockMvc.perform(get("/images")).andDo(print()).andExpect(status().isOk());
    }

    @Test
    @Order(2)
    public void getImageShouldReturnNotFound() throws Exception {
        mockMvc.perform(get("/images/999")).andDo(print()).andExpect(status().isNotFound());
    }

    @Test
    @Order(3)
    public void createImageShouldReturnSuccess() throws Exception {
        MockMultipartFile file = new MockMultipartFile("file", "test.jpg", "image/jpeg", "test data".getBytes());
        mockMvc.perform(MockMvcRequestBuilders.multipart("/images").file(file))
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    @Order(4)
    public void getImageShouldReturnSuccess() throws Exception {
        mockMvc.perform(get("/images/0")).andDo(print()).andExpect(status().isOk());
    }

    @Test
    @Order(5)
    public void deleteImageShouldReturnSuccess() throws Exception {
        mockMvc.perform(delete("/images/0")).andDo(print()).andExpect(status().isOk());
    }

    @Test
    @Order(6)
    public void deleteImageShouldReturnNotFound() throws Exception {
        mockMvc.perform(delete("/images/999")).andDo(print()).andExpect(status().isNotFound());
    }

}
