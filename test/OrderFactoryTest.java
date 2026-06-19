import factories.*;
import models.*;
import strategies.*;
import java.util.*;

public class OrderFactoryTest {
    public static void main(String[] args) {
        try {
            // Prepare data
            User user = new User(1, "Alice", "123 Main St");
            Restaurant r = new Restaurant(1, "Tasty", "Downtown");
            List<MenuItem> items = Arrays.asList(new MenuItem("m1", "Pizza", 250), new MenuItem("m2", "Soda", 50));
            PaymentStrategy ps = new UpiPaymentStrategy("9999999999");

            // NowOrderFactory - Delivery
            NowOrderFactory nowFactory = new NowOrderFactory();
            Order delivery = nowFactory.createOrder(user, user.getCart(), r, items, ps, 300, "Delivery");
            if (!delivery.getType().equals("Delivery")) throw new RuntimeException("Expected Delivery order");
            if (delivery.getUser() != user) throw new RuntimeException("User not set on order");
            if (delivery.getTotal() != 300.0) throw new RuntimeException("Total incorrect");

            // NowOrderFactory - Pickup
            Order pickup = nowFactory.createOrder(user, user.getCart(), r, items, ps, 300, "Pickup");
            if (!pickup.getType().equals("Pickup")) throw new RuntimeException("Expected Pickup order");

            // ScheduledOrderFactory
            ScheduledOrderFactory schedFactory = new ScheduledOrderFactory("2026-12-31 12:00");
            Order sched = schedFactory.createOrder(user, user.getCart(), r, items, ps, 300, "Delivery");
            if (!sched.getScheduled().equals("2026-12-31 12:00")) throw new RuntimeException("Scheduled time mismatch");

            System.out.println("OrderFactoryTest: ALL TESTS PASSED");
        } catch (Throwable t) {
            System.err.println("OrderFactoryTest: TEST FAILED - " + t.getMessage());
            t.printStackTrace();
            System.exit(1);
        }
    }
}
