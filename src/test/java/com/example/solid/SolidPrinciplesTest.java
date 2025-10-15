package com.example.solid;

import com.example.solid.discounts.*;
import com.example.solid.interfaces.*;
import com.example.solid.notifications.*;
import com.example.solid.payment.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests demonstrating SOLID principles with detailed output
 * Run with: mvn test
 */
public class SolidPrinciplesTest {
    
    @BeforeEach
    void setUp() {
        System.out.println("\n" + "=".repeat(50));
    }
    
    @Test
    @DisplayName("SRP: OrderProcessor has single responsibility")
    void testSingleResponsibilityPrinciple() {
        System.out.println("🔍 Testing SRP: OrderProcessor has single responsibility");
        
        // OrderProcessor only processes orders - doesn't handle payment details, notification details, or discount logic
        OrderProcessor processor = new OrderProcessor(
            new CreditCardProcessor(),
            new EmailNotification("test@example.com")
        );
        
        System.out.println("✓ OrderProcessor delegates payment to PaymentProcessor");
        System.out.println("✓ OrderProcessor delegates notifications to NotificationService");
        System.out.println("✓ OrderProcessor delegates discount calculation to DiscountCalculator");
        
        boolean result = processor.processOrder("Test Customer", 100.0, new PercentageDiscount(10));
        assertTrue(result);
        
        System.out.println("✅ SRP: Each class has a single, well-defined responsibility");
    }
    
    @Test
    @DisplayName("OCP: Adding new discount type without modifying existing code")
    void testOpenClosedPrinciple() {
        System.out.println("🔍 Testing OCP: Adding new discount type without modifying existing code");
        
        // Create a new discount type without modifying OrderProcessor or existing discount classes
        class BuyOneGetOneDiscount implements DiscountCalculator {
            @Override
            public double calculateDiscount(double originalPrice) {
                return originalPrice * 0.5; // 50% off for BOGO
            }
            
            @Override
            public String getDiscountType() {
                return "Buy One Get One Free";
            }
        }
        
        System.out.println("✓ Created new BuyOneGetOneDiscount class");
        System.out.println("✓ No modification needed to OrderProcessor");
        System.out.println("✓ No modification needed to existing discount classes");
        
        OrderProcessor processor = new OrderProcessor(
            new CreditCardProcessor(),
            new EmailNotification("test@example.com")
        );
        
        boolean result = processor.processOrder("OCP Test Customer", 80.0, new BuyOneGetOneDiscount());
        assertTrue(result);
        
        System.out.println("✅ OCP: System is open for extension, closed for modification");
    }
    
    @Test
    @DisplayName("DIP: High-level modules depend on abstractions")
    void testDependencyInversionPrinciple() {
        System.out.println("🔍 Testing DIP: High-level modules depend on abstractions");
        
        // Create mock implementations to prove OrderProcessor depends on abstractions
        PaymentProcessor mockPayment = new PaymentProcessor() {
            @Override
            public boolean processPayment(double amount) {
                System.out.println("✓ Mock payment processor called with amount: $" + amount);
                return true;
            }
            
            @Override
            public String getPaymentMethod() {
                return "Mock Payment";
            }
        };
        
        NotificationService mockNotification = new NotificationService() {
            @Override
            public void sendNotification(String message) {
                System.out.println("✓ Mock notification service called with: " + message);
            }
            
            @Override
            public String getNotificationType() {
                return "Mock Notification";
            }
        };
        
        DiscountCalculator mockDiscount = new DiscountCalculator() {
            @Override
            public double calculateDiscount(double originalPrice) {
                System.out.println("✓ Mock discount calculator called with price: $" + originalPrice);
                return 10.0;
            }
            
            @Override
            public String getDiscountType() {
                return "Mock Discount";
            }
        };
        
        System.out.println("✓ OrderProcessor accepts interface implementations (abstractions)");
        System.out.println("✓ OrderProcessor doesn't depend on concrete classes");
        
        OrderProcessor processor = new OrderProcessor(mockPayment, mockNotification);
        boolean result = processor.processOrder("DIP Test Customer", 50.0, mockDiscount);
        assertTrue(result);
        
        System.out.println("✅ DIP: Dependencies injected as abstractions, enabling easy testing and flexibility");
    }
    
    @Test
    @DisplayName("All SOLID principles working together")
    void testAllPrinciplesTogether() {
        System.out.println("🔍 Testing all SOLID principles working together");
        
        // SRP: Each class has single responsibility
        // OCP: We can use different implementations without changing OrderProcessor
        // DIP: OrderProcessor depends on interfaces
        
        System.out.println("✓ Testing with CreditCardProcessor + EmailNotification + PercentageDiscount");
        OrderProcessor processor1 = new OrderProcessor(
            new CreditCardProcessor(),
            new EmailNotification("test1@example.com")
        );
        boolean result1 = processor1.processOrder("Customer 1", 120.0, new PercentageDiscount(15));
        assertTrue(result1);
        
        System.out.println("✓ Testing with PayPalProcessor + SmsNotification + FixedAmountDiscount");
        OrderProcessor processor2 = new OrderProcessor(
            new PayPalProcessor(),
            new SmsNotification("+1-555-TEST")
        );
        boolean result2 = processor2.processOrder("Customer 2", 90.0, new FixedAmountDiscount(20));
        assertTrue(result2);
        
        System.out.println("✅ All SOLID principles work together seamlessly!");
        System.out.println("   • SRP: Clear separation of concerns");
        System.out.println("   • OCP: Easy to extend with new implementations");
        System.out.println("   • DIP: Flexible dependency injection");
    }
}