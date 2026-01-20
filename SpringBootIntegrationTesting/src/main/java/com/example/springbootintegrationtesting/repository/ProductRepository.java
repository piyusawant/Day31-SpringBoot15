package com.example.springbootintegrationtesting.repository;

import com.example.springbootintegrationtesting.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    // Derived query
    List<Product> findByName(String name);

    // JPQL query
    @Query("SELECT p FROM Product p WHERE p.price > :price")
    List<Product> findProductsCostlierThan(@Param("price") double price);

    // Native query
    @Query(value = "SELECT * FROM products WHERE price < ?1", nativeQuery = true)
    List<Product> findProductsCheaperThan(double price);
}
