package com.example.solid.discounts;

import com.example.solid.interfaces.DiscountCalculator;

/**
 * OCP: No discount strategy - demonstrates null object pattern
 */
public class NoDiscount implements DiscountCalculator {
    
    @Override
    public double calculateDiscount(double originalPrice) {
        return 0.0;
    }
    
    @Override
    public String getDiscountType() {
        return "No Discount";
    }
}