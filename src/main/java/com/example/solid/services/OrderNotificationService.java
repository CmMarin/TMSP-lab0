package com.example.solid.services;

import com.example.solid.interfaces.NotificationService;

/**
 * Builds and delivers customer-facing order status notifications.
 */
public class OrderNotificationService {

    public String composeMessage(PaymentOutcome paymentOutcome, OrderPricing pricing) {
        if (paymentOutcome.isSuccessful()) {
            return "Order processed successfully! Amount: $" + pricing.getFinalAmount();
        }
        return "Order failed! Payment of $" + pricing.getFinalAmount() + " was declined.";
    }

    public void send(NotificationService notificationService, String message) {
        notificationService.sendNotification(message);
    }
}
