package com.example.springbootunittesting.service;

import com.example.springbootunittesting.model.ProductOrder;
import com.example.springbootunittesting.repository.OrderRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class OrderServiceWithMockitoTest
{
    @Mock
    private OrderRepository repository;

    @InjectMocks
    private ProductOrderService productOrderService;

    @Test
    void shouldCreateOrderSuccessfully()
    {
        ProductOrder order = new ProductOrder(1L, "Laptop", 50000);

        when(repository.save(order)).thenReturn(order);

        ProductOrder savedOrder = productOrderService.createOrder(order);

        assertEquals("Laptop", savedOrder.getProductName());
        verify(repository,times(1)).save(order);
    }

    @Test
    void shouldReturnOrderWhenFound()
    {
        ProductOrder order = new ProductOrder(1L, "Mobile", 20000);
        when(repository.findById(1L)).thenReturn(Optional.of(order));
        ProductOrder result = productOrderService.getOrder(1L);
        assertEquals("Mobile",result.getProductName());
        verify(repository).findById(1L);
    }

    @Test
    void shouldThrowExceptionWhenOrderNotFound()
    {
        when(repository.findById(1L)).thenReturn(Optional.empty());
        RuntimeException exception = assertThrows(RuntimeException.class,
                () -> productOrderService.getOrder(1L));

        assertEquals("Order Not Found", exception.getMessage());

    }

    @Test
    void shouldThrowExceptionWhenRepositoryFails()
    {
        ProductOrder order = new ProductOrder(1L, "TV", 30000);

        when(repository.save(order)).thenThrow(new RuntimeException("DB Error"));

        RuntimeException exception = assertThrows(RuntimeException.class,
                () -> productOrderService.createOrder(order));

        assertEquals("DB Error",exception.getMessage());
    }

    @Test
    void shouldNotCallRepositoryWhenPriceInvalid()
    {
        ProductOrder order = new ProductOrder(1L, "Invalid", -100);

        assertThrows(IllegalArgumentException.class,
                ()-> productOrderService.createOrder(order));

        verify(repository,never()).save(any());
    }











}
