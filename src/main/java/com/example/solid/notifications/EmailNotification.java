package com.example.solid.notifications;

import com.example.solid.interfaces.NotificationService;

/**
 * DIP: Concrete implementation of NotificationService
 */
public class EmailNotification implements NotificationService {
    private final String emailAddress;
    
    public EmailNotification(String emailAddress) {
        this.emailAddress = emailAddress;
    }
    
    @Override
    public void sendNotification(String message) {
        System.out.println("[EMAIL to " + emailAddress + "] " + message);
    }
    
    @Override
    public String getNotificationType() {
        return "Email";
    }
}