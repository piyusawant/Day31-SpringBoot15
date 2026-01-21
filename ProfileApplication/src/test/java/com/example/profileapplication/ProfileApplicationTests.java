package com.example.profileapplication;

import com.example.profileapplication.entity.Product;
import com.example.profileapplication.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ActiveProfiles("test")
class ProfileApplicationTests {

    @Autowired
    private ProductService productService;

    @Test
    void shouldUseH2Database()
    {
        productService.save(new Product("Laptop", 50000));

        Long count = productService.count();

        assertEquals(1, count);
    }

}
