package com.app.alex.calculadora.math;

/**
 * Created by alex on 2/8/17.
 */

public class ArithmeticCalculation {

    private ArithmeticOperation operation;

    private Double num1;

    private Double num2;

    public ArithmeticCalculation(ArithmeticOperation operation, Double num1, Double num2) {
        this.operation = operation;
        this.num1 = num1;
        this.num2 = num2;
    }

    public Double solve() {
        return operation.perform(num1, num2);
    }

    @Override
    public String toString() {
        return "ArithmeticCalculation{" +
                "operation=" + operation +
                ", num1=" + num1 +
                ", num2=" + num2 +
                '}';
    }
}
