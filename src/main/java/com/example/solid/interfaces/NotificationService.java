package com.example.solid.interfaces;

/**
 * DIP: Abstraction for notification services
 */
public interface NotificationService {
    void sendNotification(String message);
    String getNotificationType();
}