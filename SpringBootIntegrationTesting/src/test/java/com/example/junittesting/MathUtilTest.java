package com.example.junittesting;

import com.example.junittesting.MathUtil;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class MathUtilTest
{
    private MathUtil mathUtil;

    @BeforeAll // run once
    static void beforeAll()
    {
        System.out.println("Before All Tests");
    }

    @BeforeEach //setup
    void setup()
    {
        mathUtil = new MathUtil();
        System.out.println("SetUp Before Each Test");
    }

    //write tests with Assertions
    @Test
    @DisplayName("Add Two Numbers")
    void testAdd()
    {
        int result = mathUtil.add(10, 20);
        assertEquals(30, result);
    }

    @Test
    @DisplayName("Substract Two Numbers")
    void testSubtract()
    {
        assertEquals(5,
                mathUtil.substraction(10, 5));
    }

    @Test
    @DisplayName("Multiply Two Numbers")
    void testMultiply()
    {
        assertEquals(25,
                mathUtil.multiplication(5, 5));
    }

    @Test
    @DisplayName("Throw Exception on divide by Zero")
    void testDivide()
    {
        Exception exception = assertThrows(
                IllegalArgumentException.class,
                () -> mathUtil.division(5, 0)
        );
        assertEquals("Cannot Divide By Zero", exception.getMessage());
    }

    @Test
    @Tag("fast")
    @DisplayName("Fast add test")
    void fast()
    {
        assertEquals(30, mathUtil.add(20, 10));
    }

    @AfterEach
    void cleanup()
    {
        System.out.println("Cleanup after each test");
    }

    @AfterAll
    static void afterAll()
    {
        System.out.println("After All Tests");
    }

}
