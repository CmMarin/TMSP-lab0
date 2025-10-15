package com.example.solid;

import com.example.solid.discounts.*;
import com.example.solid.interfaces.*;
import com.example.solid.notifications.*;
import com.example.solid.payment.*;
import java.util.Scanner;

/**
 * Interactive demo application showing SOLID principles in action
 * 
 * Run with: mvn exec:java
 * Or: mvn compile exec:java -Dexec.mainClass=com.example.solid.SolidDemo
 */
public class SolidDemo {
    private static Scanner scanner = new Scanner(System.in);
    
    public static void main(String[] args) {
        System.out.println("SOLID Principles Interactive Demo");
        System.out.println("=================================");
        System.out.println("Welcome to the Order Processing System!");
        System.out.println("This demonstrates SRP, OCP, and DIP principles.\n");
        
        boolean continueOrdering = true;
        
        while (continueOrdering) {
            processInteractiveOrder();
            
            System.out.print("\nWould you like to process another order? (y/n): ");
            String response = scanner.nextLine().trim().toLowerCase();
            continueOrdering = response.equals("y") || response.equals("yes");
        }
        
        System.out.println("\nThank you for using the Order Processing System!");
        System.out.println("\nSOLID Principles Demonstrated:");
        System.out.println("* SRP: OrderProcessor has single responsibility (processing orders)");
        System.out.println("* OCP: New discount/payment/notification types can be added without changing OrderProcessor");
        System.out.println("* DIP: OrderProcessor depends on interfaces, not concrete classes");
        
        scanner.close();
    }
    
    private static void processInteractiveOrder() {
        System.out.println("\n==================================================");
        System.out.println("NEW ORDER");
        System.out.println("==================================================");
        
        // Get customer information
        System.out.print("Enter customer name: ");
        String customerName = scanner.nextLine().trim();
        
        System.out.print("Enter order amount: $");
        double orderAmount = 0;
        try {
            orderAmount = Double.parseDouble(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Invalid amount. Using $100.00 as default.");
            orderAmount = 100.0;
        }
        
        // Choose payment method
        PaymentProcessor paymentProcessor = choosePaymentMethod();
        
        // Choose notification method
        NotificationService notificationService = chooseNotificationMethod();
        
        // Choose discount
        DiscountCalculator discountCalculator = chooseDiscount();
        
        // Create order processor and process the order
        OrderProcessor processor = new OrderProcessor(paymentProcessor, notificationService);
        boolean success = processor.processOrder(customerName, orderAmount, discountCalculator);
        
        if (success) {
            System.out.println("\nORDER COMPLETED SUCCESSFULLY!");
        } else {
            System.out.println("\nORDER FAILED - Please try again with different payment method or amount.");
        }
    }
    
    private static PaymentProcessor choosePaymentMethod() {
        System.out.println("\nChoose Payment Method:");
        System.out.println("1. Credit Card (limit: $10,000)");
        System.out.println("2. PayPal (limit: $5,000)");
        System.out.print("Enter choice (1-2): ");
        
        String choice = scanner.nextLine().trim();
        switch (choice) {
            case "2":
                return new PayPalProcessor();
            case "1":
            default:
                return new CreditCardProcessor();
        }
    }
    
    private static NotificationService chooseNotificationMethod() {
        System.out.println("\nChoose Notification Method:");
        System.out.println("1. Email");
        System.out.println("2. SMS");
        System.out.print("Enter choice (1-2): ");
        
        String choice = scanner.nextLine().trim();
        switch (choice) {
            case "1":
                System.out.print("Enter email address: ");
                String email = scanner.nextLine().trim();
                if (email.isEmpty()) email = "customer@example.com";
                return new EmailNotification(email);
            case "2":
                System.out.print("Enter phone number: ");
                String phone = scanner.nextLine().trim();
                if (phone.isEmpty()) phone = "+1-555-0000";
                return new SmsNotification(phone);
            default:
                return new EmailNotification("customer@example.com");
        }
    }
    
    private static DiscountCalculator chooseDiscount() {
        System.out.println("\nChoose Discount Type:");
        System.out.println("1. No Discount");
        System.out.println("2. Percentage Discount");
        System.out.println("3. Fixed Amount Discount");
        System.out.println("4. Buy One Get One Free (50% off)");
        System.out.print("Enter choice (1-4): ");
        
        String choice = scanner.nextLine().trim();
        switch (choice) {
            case "2":
                System.out.print("Enter percentage (e.g., 10 for 10%): ");
                try {
                    double percentage = Double.parseDouble(scanner.nextLine().trim());
                    return new PercentageDiscount(percentage);
                } catch (NumberFormatException e) {
                    System.out.println("Invalid percentage. Using 10% default.");
                    return new PercentageDiscount(10);
                }
            case "3":
                System.out.print("Enter fixed discount amount: $");
                try {
                    double amount = Double.parseDouble(scanner.nextLine().trim());
                    return new FixedAmountDiscount(amount);
                } catch (NumberFormatException e) {
                    System.out.println("Invalid amount. Using $10 default.");
                    return new FixedAmountDiscount(10);
                }
            case "4":
                return new DiscountCalculator() {
                    @Override
                    public double calculateDiscount(double originalPrice) {
                        return originalPrice * 0.5;
                    }
                    
                    @Override
                    public String getDiscountType() {
                        return "Buy One Get One Free";
                    }
                };
            case "1":
            default:
                return new NoDiscount();
        }
    }
}