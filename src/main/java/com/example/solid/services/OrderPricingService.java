package com.example.solid.services;

import com.example.solid.interfaces.DiscountCalculator;

/**
 * Applies discount strategies to compute final order pricing.
 */
public class OrderPricingService {

    public OrderPricing applyDiscount(double orderAmount, DiscountCalculator discountCalculator) {
        double discount = discountCalculator.calculateDiscount(orderAmount);
        double finalAmount = orderAmount - discount;
        return new OrderPricing(orderAmount, discount, finalAmount, discountCalculator.getDiscountType());
    }
}
