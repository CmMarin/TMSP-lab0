# Laboratory Report: SOLID Principles Implementation

**Student:** Clima Marin  
**Group:** FAF-231  
**Class:** TMSP  
**Date:** October 8, 2025

## Objective

Implement and demonstrate three key SOLID principles through a practical Java application: Single Responsibility Principle (SRP), Open/Closed Principle (OCP), and Dependency Inversion Principle (DIP).

## Implementation Overview

The laboratory implements an interactive order processing system where users can place orders, select payment methods, choose notifications, and apply discounts. The system demonstrates SOLID principles through clean separation of concerns and extensible design.

## SOLID Principles Analysis

### 1. Single Responsibility Principle (SRP)

**Definition:** A class should have only one reason to change.

**Implementation:** Each class handles a single responsibility:

```java
// OrderProcessor.java - Only responsible for order processing logic
public class OrderProcessor {
    private PaymentProcessor paymentProcessor;
    private NotificationService notificationService;
    
    public void processOrder(String customerName, double amount, 
                           DiscountCalculator discount) {
        double finalAmount = amount - discount.calculateDiscount(amount);
        
        if (paymentProcessor.processPayment(finalAmount)) {
            notificationService.sendNotification(
                "Order processed successfully! Amount: $" + finalAmount);
        }
    }
}
```

```java
// CreditCardProcessor.java - Only handles credit card payments
public class CreditCardProcessor implements PaymentProcessor {
    private static final double CREDIT_LIMIT = 10000.0;
    
    @Override
    public boolean processPayment(double amount) {
        if (amount <= CREDIT_LIMIT) {
            System.out.println("Processing credit card payment of $" + amount);
            return true;
        }
        return false;
    }
}
```

### 2. Open/Closed Principle (OCP)

**Definition:** Classes should be open for extension but closed for modification.

**Implementation:** New discount types can be added without modifying existing code:

```java
// Interface for extensibility
public interface DiscountCalculator {
    double calculateDiscount(double amount);
    String getDiscountType();
}

// Existing implementation
public class PercentageDiscount implements DiscountCalculator {
    private double percentage;
    
    public PercentageDiscount(double percentage) {
        this.percentage = percentage;
    }
    
    @Override
    public double calculateDiscount(double amount) {
        return amount * (percentage / 100);
    }
}

// New discount type added without modifying existing classes
public class BuyOneGetOneFree implements DiscountCalculator {
    @Override
    public double calculateDiscount(double amount) {
        return amount * 0.5; // 50% discount
    }
    
    @Override
    public String getDiscountType() {
        return "Buy One Get One Free";
    }
}
```

### 3. Dependency Inversion Principle (DIP)

**Definition:** High-level modules should not depend on low-level modules. Both should depend on abstractions.

**Implementation:** OrderProcessor depends on interfaces, not concrete implementations:

```java
// High-level module depends on abstractions
public class OrderProcessor {
    private PaymentProcessor paymentProcessor;      // Interface dependency
    private NotificationService notificationService; // Interface dependency
    
    // Constructor injection of dependencies
    public OrderProcessor(PaymentProcessor paymentProcessor, 
                         NotificationService notificationService) {
        this.paymentProcessor = paymentProcessor;
        this.notificationService = notificationService;
    }
}

// Abstraction
public interface PaymentProcessor {
    boolean processPayment(double amount);
    String getPaymentMethod();
}

// Low-level module implements abstraction
public class PayPalProcessor implements PaymentProcessor {
    @Override
    public boolean processPayment(double amount) {
        // PayPal-specific implementation
        return amount <= 5000.0;
    }
}
```

## Project Structure

```
src/main/java/com/example/solid/
├── interfaces/              # Abstractions (DIP)
│   ├── PaymentProcessor.java
│   ├── DiscountCalculator.java
│   └── NotificationService.java
├── payment/                 # Payment implementations (SRP, OCP)
│   ├── CreditCardProcessor.java
│   └── PayPalProcessor.java
├── discounts/              # Discount strategies (SRP, OCP)
│   ├── PercentageDiscount.java
│   ├── FixedAmountDiscount.java
│   ├── BuyOneGetOneFree.java
│   └── NoDiscount.java
├── notifications/          # Notification implementations (SRP, OCP)
│   ├── EmailNotification.java
│   └── SmsNotification.java
├── OrderProcessor.java     # Main business logic (SRP, DIP)
└── SolidDemo.java         # Interactive demo application
```

## Execution Results

The interactive demo allows users to:
- Enter customer details and order amounts
- Choose payment methods (Credit Card: $10K limit, PayPal: $5K limit)
- Select notification types (Email, SMS)
- Apply discount strategies (None, Percentage, Fixed Amount, BOGO)

**Sample execution:**
```
Enter customer name: John Smith
Enter order amount: $150.00
Choose Payment Method: 1 (Credit Card)
Choose Notification: 1 (Email)
Enter email: john@example.com
Choose Discount: 2 (Percentage)
Enter percentage: 15

=== Processing Order ===
Original amount: $150.0
Discount (15.0% Discount): $22.5
Final amount: $127.5
[SUCCESS] Payment successful!
[EMAIL] Order processed successfully! Amount: $127.5
```

## Benefits Achieved

1. **Maintainability:** Each class has a single, well-defined responsibility
2. **Extensibility:** New payment methods, discounts, and notifications can be added without modifying existing code
3. **Testability:** Dependencies can be easily mocked for unit testing
4. **Flexibility:** Components can be swapped at runtime through dependency injection

## Conclusion

The implementation successfully demonstrates all three SOLID principles through a practical order processing system. The code is maintainable, extensible, and follows object-oriented design best practices. New features can be added with minimal impact on existing code, proving the effectiveness of SOLID principles in software design.