package com.example.solid.services;

/**
 * Immutable value describing the result of a payment attempt.
 */
public class PaymentOutcome {
    private final String paymentMethod;
    private final boolean successful;

    public PaymentOutcome(String paymentMethod, boolean successful) {
        this.paymentMethod = paymentMethod;
        this.successful = successful;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public boolean isSuccessful() {
        return successful;
    }
}
