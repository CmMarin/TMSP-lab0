package com.example.solid.payment;

import com.example.solid.interfaces.PaymentProcessor;

/**
 * DIP: Another concrete implementation of PaymentProcessor
 */
public class PayPalProcessor implements PaymentProcessor {
    
    @Override
    public boolean processPayment(double amount) {
        System.out.println("Processing PayPal payment of $" + amount);
        // Simulate PayPal processing
        return amount > 0 && amount <= 5000; // Different limit than credit card
    }
    
    @Override
    public String getPaymentMethod() {
        return "PayPal";
    }
}