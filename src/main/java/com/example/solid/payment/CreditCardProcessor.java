package com.example.solid.payment;

import com.example.solid.interfaces.PaymentProcessor;

/**
 * DIP: Concrete implementation of PaymentProcessor
 */
public class CreditCardProcessor implements PaymentProcessor {
    
    @Override
    public boolean processPayment(double amount) {
        System.out.println("Processing credit card payment of $" + amount);
        // Simulate payment processing
        return amount > 0 && amount <= 10000; // Reject very large amounts
    }
    
    @Override
    public String getPaymentMethod() {
        return "Credit Card";
    }
}