package com.example.solid.discounts;

import com.example.solid.interfaces.DiscountCalculator;

/**
 * OCP: Another discount strategy implementation
 */
public class FixedAmountDiscount implements DiscountCalculator {
    private final double discountAmount;
    
    public FixedAmountDiscount(double discountAmount) {
        this.discountAmount = discountAmount;
    }
    
    @Override
    public double calculateDiscount(double originalPrice) {
        return Math.min(discountAmount, originalPrice);
    }
    
    @Override
    public String getDiscountType() {
        return "$" + discountAmount + " Off";
    }
}