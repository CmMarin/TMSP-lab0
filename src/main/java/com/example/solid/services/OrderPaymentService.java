package com.example.solid.services;

import com.example.solid.interfaces.PaymentProcessor;

/**
 * Delegates payment processing to the selected payment processor.
 */
public class OrderPaymentService {

    public PaymentOutcome processPayment(PaymentProcessor paymentProcessor, double amountToCharge) {
        boolean success = paymentProcessor.processPayment(amountToCharge);
        return new PaymentOutcome(paymentProcessor.getPaymentMethod(), success);
    }
}
