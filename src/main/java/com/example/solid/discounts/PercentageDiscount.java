package com.example.solid.discounts;

import com.example.solid.interfaces.DiscountCalculator;

/**
 * OCP: Implementation of discount strategy - can be added without modifying existing code
 */
public class PercentageDiscount implements DiscountCalculator {
    private final double percentage;
    
    public PercentageDiscount(double percentage) {
        this.percentage = percentage;
    }
    
    @Override
    public double calculateDiscount(double originalPrice) {
        return originalPrice * (percentage / 100.0);
    }
    
    @Override
    public String getDiscountType() {
        return percentage + "% Discount";
    }
}