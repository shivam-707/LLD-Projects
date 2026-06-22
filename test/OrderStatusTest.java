import managers.*;
import models.*;
import strategies.*;
import factories.*;
import services.NotificationService;
import exceptions.*;
import java.util.*;

/**
 * Test class demonstrating order status tracking and lifecycle management.
 */
public class OrderStatusTest {
    public static void main(String[] args) {
        System.out.println("=== Order Status Tracking Test ===\n");

        try {
            OrderManager manager = OrderManager.getInstance();
            manager.clearOrders();

            // Create test data
            User user = new User(1, "John Doe", "789 Oak St");
            Restaurant restaurant = new Restaurant("Pizza Palace", "Downtown");
            List<MenuItem> items = Arrays.asList(
                new MenuItem("P1", "Margherita Pizza", 300),
                new MenuItem("D1", "Iced Tea", 50)
            );
            PaymentStrategy payment = new CreditCardPaymentStrategy("5555-6666");

            // Create and add order
            NowOrderFactory factory = new NowOrderFactory();
            Order order = factory.createOrder(user, user.getCart(), restaurant, items, payment, 350, "Delivery");
            manager.addOrder(order);

            System.out.println("Initial Status: " + order.getStatus() + "\n");

            // Simulate order lifecycle
            simulateOrderLifecycle(manager, order);

            System.out.println("\n=== Test Completed Successfully ===");
        } catch (Exception e) {
            System.err.println("Test Failed: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void simulateOrderLifecycle(OrderManager manager, Order order) throws InvalidOrderException {
        int orderId = order.getOrderId();

        // Step 1: Confirm order
        System.out.println("Step 1: Confirming order...");
        manager.confirmOrder(orderId);
        NotificationService.notifyStatusChange(order);

        // Step 2: Mark as preparing
        System.out.println("Step 2: Restaurant is preparing...");
        manager.markPreparing(orderId);
        NotificationService.notifyStatusChange(order);

        // Step 3: Mark as out for delivery
        System.out.println("Step 3: Out for delivery...");
        manager.markOutForDelivery(orderId);
        NotificationService.notifyStatusChange(order);

        // Step 4: Mark as delivered
        System.out.println("Step 4: Delivered...");
        manager.markDelivered(orderId);
        NotificationService.notifyStatusChange(order);

        System.out.println("Final Status: " + order.getStatus());
    }
}
