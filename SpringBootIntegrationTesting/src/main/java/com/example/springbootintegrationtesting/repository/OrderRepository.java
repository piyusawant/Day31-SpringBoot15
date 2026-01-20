package com.example.springbootintegrationtesting.repository;

import com.example.springbootintegrationtesting.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
