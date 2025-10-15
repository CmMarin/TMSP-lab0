package com.example.solid;

import com.example.solid.interfaces.DiscountCalculator;
import com.example.solid.interfaces.NotificationService;
import com.example.solid.interfaces.PaymentProcessor;

/**
 * SRP: This class has a single responsibility - processing orders
 * DIP: It depends on abstractions (interfaces) not concrete implementations
 * OCP: New discount types, payment methods, and notification services can be added without modifying this class
 */
public class OrderProcessor {
    private final PaymentProcessor paymentProcessor;
    private final NotificationService notificationService;
    
    // DIP: Constructor injection of dependencies (abstractions)
    public OrderProcessor(PaymentProcessor paymentProcessor, NotificationService notificationService) {
        this.paymentProcessor = paymentProcessor;
        this.notificationService = notificationService;
    }
    
    // SRP: Single method with single responsibility - process an order
    public boolean processOrder(String customerName, double orderAmount, DiscountCalculator discountCalculator) {
        System.out.println("\n=== Processing Order for " + customerName + " ===");
        
        // Calculate discount
        double discount = discountCalculator.calculateDiscount(orderAmount);
        double finalAmount = orderAmount - discount;
        
        System.out.println("Original amount: $" + orderAmount);
        System.out.println("Discount (" + discountCalculator.getDiscountType() + "): $" + discount);
        System.out.println("Final amount: $" + finalAmount);
        
        // Process payment
        System.out.println("Payment method: " + paymentProcessor.getPaymentMethod());
        boolean paymentSuccess = paymentProcessor.processPayment(finalAmount);
        
        // Send notification
        String message;
        if (paymentSuccess) {
            message = "Order processed successfully! Amount: $" + finalAmount;
            System.out.println("[SUCCESS] Payment successful!");
        } else {
            message = "Order failed! Payment of $" + finalAmount + " was declined.";
            System.out.println("[FAILED] Payment failed!");
        }
        
        notificationService.sendNotification(message);
        
        return paymentSuccess;
    }
}