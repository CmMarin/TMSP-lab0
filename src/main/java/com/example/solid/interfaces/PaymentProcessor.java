package com.example.solid.interfaces;

/**
 * DIP: High-level modules should not depend on low-level modules.
 * Both should depend on abstractions.
 */
public interface PaymentProcessor {
    boolean processPayment(double amount);
    String getPaymentMethod();
}