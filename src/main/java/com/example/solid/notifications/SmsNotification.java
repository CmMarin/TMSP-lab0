package com.example.solid.notifications;

import com.example.solid.interfaces.NotificationService;

/**
 * DIP: Another concrete implementation of NotificationService
 */
public class SmsNotification implements NotificationService {
    private final String phoneNumber;
    
    public SmsNotification(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    
    @Override
    public void sendNotification(String message) {
        System.out.println("[SMS to " + phoneNumber + "] " + message);
    }
    
    @Override
    public String getNotificationType() {
        return "SMS";
    }
}