package com.example.solid;

import com.example.solid.interfaces.DiscountCalculator;
import com.example.solid.interfaces.NotificationService;
import com.example.solid.interfaces.PaymentProcessor;
import com.example.solid.services.OrderNotificationService;
import com.example.solid.services.OrderPaymentService;
import com.example.solid.services.OrderPricing;
import com.example.solid.services.OrderPricingService;
import com.example.solid.services.PaymentOutcome;

/**
 * SRP: This class has a single responsibility - processing orders
 * DIP: It depends on abstractions (interfaces) not concrete implementations
 * OCP: New discount types, payment methods, and notification services can be added without modifying this class
 */
public class OrderProcessor {
    private final PaymentProcessor paymentProcessor;
    private final NotificationService notificationService;
    private final OrderPricingService orderPricingService;
    private final OrderPaymentService orderPaymentService;
    private final OrderNotificationService orderNotificationService;
    
    // DIP: Constructor injection of dependencies (abstractions)
    public OrderProcessor(PaymentProcessor paymentProcessor, NotificationService notificationService) {
        this(paymentProcessor, notificationService, new OrderPricingService(), new OrderPaymentService(), new OrderNotificationService());
    }

    public OrderProcessor(PaymentProcessor paymentProcessor,
                          NotificationService notificationService,
                          OrderPricingService orderPricingService,
                          OrderPaymentService orderPaymentService,
                          OrderNotificationService orderNotificationService) {
        this.paymentProcessor = paymentProcessor;
        this.notificationService = notificationService;
        this.orderPricingService = orderPricingService;
        this.orderPaymentService = orderPaymentService;
        this.orderNotificationService = orderNotificationService;
    }
    
    // SRP: Single method with single responsibility - process an order
    public boolean processOrder(String customerName, double orderAmount, DiscountCalculator discountCalculator) {
        System.out.println("\n=== Processing Order for " + customerName + " ===");
        
        // Calculate discount
        OrderPricing pricing = orderPricingService.applyDiscount(orderAmount, discountCalculator);

        System.out.println("Original amount: $" + pricing.getOriginalAmount());
        System.out.println("Discount (" + pricing.getDiscountLabel() + "): $" + pricing.getDiscountApplied());
        System.out.println("Final amount: $" + pricing.getFinalAmount());
        
        // Process payment
        PaymentOutcome paymentOutcome = orderPaymentService.processPayment(paymentProcessor, pricing.getFinalAmount());
        System.out.println("Payment method: " + paymentOutcome.getPaymentMethod());
        
        // Send notification
        String message = orderNotificationService.composeMessage(paymentOutcome, pricing);
        if (paymentOutcome.isSuccessful()) {
            System.out.println("[SUCCESS] Payment successful!");
        } else {
            System.out.println("[FAILED] Payment failed!");
        }

        orderNotificationService.send(notificationService, message);

        return paymentOutcome.isSuccessful();
    }
}