package com.example.datajpa.repository;

import com.example.springbootintegrationtesting.InApplication;
import com.example.springbootintegrationtesting.model.Product;
import com.example.springbootintegrationtesting.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@ContextConfiguration(classes = InApplication.class)
public class ProductRepositoryTest
{
    @Autowired
    private ProductRepository productRepository;

    @Test
    void shouldSaveAndFetchProduct() {

        Product product = new Product("Laptop", 50000);

        Product saved = productRepository.save(product);

        Optional<Product> fetched =
                productRepository.findById(saved.getId());

        assertTrue(fetched.isPresent());
        assertEquals("Laptop", fetched.get().getName());
    }

    @Test
    void shouldFindProductByName() {

        productRepository.save(new Product("Mobile", 20000));
        productRepository.save(new Product("Laptop", 50000));

        List<Product> products =
                productRepository.findByName("Mobile");

        assertEquals(1, products.size());
        assertEquals("Mobile", products.get(0).getName());
    }

    @Test
    void shouldFindProductsCostlierThan() {

        productRepository.save(new Product("Mobile", 20000));
        productRepository.save(new Product("Laptop", 50000));

        List<Product> products =
                productRepository.findProductsCostlierThan(30000);

        assertEquals(1, products.size());
        assertEquals("Laptop", products.get(0).getName());
    }

    @Test
    void shouldFindProductsCheaperThan() {

        productRepository.save(new Product("Tablet", 15000));
        productRepository.save(new Product("Laptop", 60000));

        List<Product> products =
                productRepository.findProductsCheaperThan(20000);

        assertEquals(1, products.size());
        assertEquals("Tablet", products.get(0).getName());
    }

    @Test
    void verifyRollbackAfterTest() {

        productRepository.save(new Product("Camera", 30000));

        assertEquals(1, productRepository.count());
    }

}
