package com.app.alex.calculadora.math;

/**
 * Created by alex on 2/8/17.
 */

public class Multiplication implements ArithmeticOperation {
    @Override
    public Double perform(Double a, Double b) {
        return a * b;
    }
}
