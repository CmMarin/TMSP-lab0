package com.example.solid.interfaces;

/**
 * OCP: Software entities should be open for extension but closed for modification.
 * This interface allows new discount strategies without changing existing code.
 */
public interface DiscountCalculator {
    double calculateDiscount(double originalPrice);
    String getDiscountType();
}