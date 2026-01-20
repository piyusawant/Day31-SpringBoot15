package com.example.springbootunittesting.service;

import com.example.springbootunittesting.model.OrderItem;
import com.example.springbootunittesting.repository.TaxRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class OrderServiceTest
{

    @Mock
    private TaxRepository taxRepository;

    @InjectMocks
    private OrderService orderService;


    @Test
    void shouldCalculateTotalWithoutDiscount() {

        when(taxRepository.getTaxRate()).thenReturn(0.05); // 5% tax

        List<OrderItem> items = List.of(
                new OrderItem("Pen",100 , 2),
                new OrderItem("Book", 200, 1));

        double total = orderService.calculateOrderTotal(items);

        assertEquals(420.0, total);
       // verify(taxRepository, times(1)).getTaxRate();
    }

    @Test
    void shouldApplyDiscountWhenTotalAbove1000()
    {
        when(taxRepository.getTaxRate()).thenReturn(0.05);
        List<OrderItem> items = List.of(
                new OrderItem("Laptop",1200,1));

        double total = orderService.calculateOrderTotal(items);

        assertEquals(1134.0, total);
    }

    @Test
    void shouldThrowExceptionForEmptyOrder()
    {
         assertThrows(IllegalArgumentException.class,
                () -> orderService.calculateOrderTotal(List.of()));

       // assertEquals("Order Item Cannot be Empty", exception.getMessage());

    }

    @Test
    void shouldThrowExceptionForInvalidItem()
    {
        List<OrderItem> items = List.of(
                new OrderItem("Invalid", -10, 2));

                assertThrows(IllegalArgumentException.class, () ->
                        orderService.calculateOrderTotal(items));
    }
}
