package com.example.junittesting;

public class MathUtil
{
    public int add(int a, int b)
    {
        return a + b;
    }

    public int substraction(int a, int b)
    {
        return a - b;
    }

    public int multiplication(int a, int b)
    {
        return a * b;
    }

    public int division(int a, int b)
    {
        if(b == 0)
        {
            throw new IllegalArgumentException("Cannot Divide By Zero");
        }
        return a / b;
    }
}
