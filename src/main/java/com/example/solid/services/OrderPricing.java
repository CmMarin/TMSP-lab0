package com.example.solid.services;

/**
 * Immutable representation of an order's pricing after discount application.
 */
public class OrderPricing {
    private final double originalAmount;
    private final double discountApplied;
    private final double finalAmount;
    private final String discountLabel;

    public OrderPricing(double originalAmount, double discountApplied, double finalAmount, String discountLabel) {
        this.originalAmount = originalAmount;
        this.discountApplied = discountApplied;
        this.finalAmount = finalAmount;
        this.discountLabel = discountLabel;
    }

    public double getOriginalAmount() {
        return originalAmount;
    }

    public double getDiscountApplied() {
        return discountApplied;
    }

    public double getFinalAmount() {
        return finalAmount;
    }

    public String getDiscountLabel() {
        return discountLabel;
    }
}
