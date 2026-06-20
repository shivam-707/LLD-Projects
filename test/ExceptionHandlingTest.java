package test;

import managers.*;
import models.*;
import strategies.*;
import factories.*;
import exceptions.*;
import java.util.*;

/**
 * Test class demonstrating exception handling in the Tomato system.
 * Shows how custom exceptions are used for validation and error handling.
 */
public class ExceptionHandlingTest {
    public static void main(String[] args) {
        System.out.println("=== Exception Handling Test Suite ===\n");

        // Test 1: Invalid order - null user
        testInvalidOrderNullUser();

        // Test 2: Invalid order - empty items
        testInvalidOrderEmptyItems();

        // Test 3: Restaurant not found
        testRestaurantNotFound();

        // Test 4: Valid order processing (should succeed)
        testValidOrderProcessing();

        System.out.println("\n=== All Exception Tests Completed ===");
    }

    private static void testInvalidOrderNullUser() {
        System.out.println("Test 1: Validating order with null user (should fail)");
        try {
            User user = new User(1, "Alice", "123 Main");
            Restaurant r = new Restaurant("TestRest", "Downtown");
            List<MenuItem> items = Arrays.asList(new MenuItem("m1", "Burger", 150));
            PaymentStrategy ps = new CreditCardPaymentStrategy("1111-2222");

            NowOrderFactory factory = new NowOrderFactory();
            Order order = factory.createOrder(user, user.getCart(), r, items, ps, 150, "Delivery");
            
            // Manually set user to null to test validation
            order.setUser(null);
            order.validateOrder(); // Should throw exception

            System.out.println("  ✗ FAILED: Expected InvalidOrderException but validation passed\n");
        } catch (InvalidOrderException e) {
            System.out.println("  ✓ PASSED: Caught expected exception - " + e.getMessage() + "\n");
        }
    }

    private static void testInvalidOrderEmptyItems() {
        System.out.println("Test 2: Adding order with empty items (should fail)");
        try {
            OrderManager manager = OrderManager.getInstance();
            manager.clearOrders();

            User user = new User(1, "Alice", "123 Main");
            Restaurant r = new Restaurant("TestRest", "Downtown");
            PaymentStrategy ps = new UpiPaymentStrategy("9999999999");

            NowOrderFactory factory = new NowOrderFactory();
            Order order = factory.createOrder(user, user.getCart(), r, new ArrayList<>(), ps, 0, "Delivery");
            manager.addOrder(order);

            System.out.println("  ✗ FAILED: Expected InvalidOrderException for empty items\n");
        } catch (InvalidOrderException e) {
            System.out.println("  ✓ PASSED: Caught expected exception - " + e.getMessage() + "\n");
        }
    }

    private static void testRestaurantNotFound() {
        System.out.println("Test 3: Searching for restaurant in non-existent location (should fail)");
        try {
            RestaurantManager manager = RestaurantManager.getInstance();
            manager.searchByLocation("NonExistentCity");

            System.out.println("  ✗ FAILED: Expected RestaurantNotFoundException\n");
        } catch (RestaurantNotFoundException e) {
            System.out.println("  ✓ PASSED: Caught expected exception - " + e.getMessage() + "\n");
        }
    }

    private static void testValidOrderProcessing() {
        System.out.println("Test 4: Processing valid order (should succeed)");
        try {
            OrderManager manager = OrderManager.getInstance();
            manager.clearOrders();

            User user = new User(2, "Bob", "456 Side");
            Restaurant r = new Restaurant("GoodRest", "Uptown");
            List<MenuItem> items = Arrays.asList(new MenuItem("m2", "Pizza", 250));
            PaymentStrategy ps = new CreditCardPaymentStrategy("4444-5555");

            NowOrderFactory factory = new NowOrderFactory();
            Order order = factory.createOrder(user, user.getCart(), r, items, ps, 250, "Delivery");
            
            order.validateOrder(); // Explicit validation
            manager.addOrder(order);

            System.out.println("  ✓ PASSED: Valid order added successfully. Order ID: " + order.getOrderId() + "\n");
        } catch (Exception e) {
            System.out.println("  ✗ FAILED: Unexpected exception - " + e.getMessage() + "\n");
        }
    }
}
