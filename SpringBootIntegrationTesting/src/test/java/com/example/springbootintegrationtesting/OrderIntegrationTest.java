package com.example.springbootintegrationtesting;

import com.example.springbootintegrationtesting.model.Order;
import com.example.springbootintegrationtesting.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class OrderIntegrationTest
{
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private OrderRepository repository;

    @Test
    void shouldCreateAndFetchOrderSuccessFully() throws Exception
    {
        String orderJson = """    
        {
            "name" : "Mobile", 
            "price": 25000
        }
         """;

        //Create Order
        mockMvc.perform(post("/orders").contentType(MediaType.APPLICATION_JSON)
                .content(orderJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.name").value("Mobile"));

        //Validate DB Entry
        assertThat(repository.findAll()).hasSize(1);

        //Fetch Orders
        mockMvc.perform(get("/orders")).andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Mobile"));




    }


}
