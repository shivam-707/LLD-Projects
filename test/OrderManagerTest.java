

import managers.*;
import models.*;
import strategies.*;
import factories.*;
import java.util.*;

public class OrderManagerTest {
    public static void main(String[] args) {
        try {
            OrderManager manager = OrderManager.getInstance();
            manager.clearOrders();

            User user = new User(2, "Bob", "456 Side St");
            Restaurant r = new Restaurant("Yummy", "Uptown");
            List<MenuItem> items = Arrays.asList(new MenuItem("m3", "Burger", 150));
            PaymentStrategy ps = new CreditCardPaymentStrategy("1111-2222-3333-4444");

            NowOrderFactory nowFactory = new NowOrderFactory();
            Order o1 = nowFactory.createOrder(user, user.getCart(), r, items, ps, 150, "Delivery");
            manager.addOrder(o1);

            if (manager.getOrderCount() != 1) throw new RuntimeException("Expected 1 order after add");

            manager.clearOrders();
            if (manager.getOrderCount() != 0) throw new RuntimeException("Expected 0 orders after clear");

            System.out.println("OrderManagerTest: ALL TESTS PASSED");
        } catch (Throwable t) {
            System.err.println("OrderManagerTest: TEST FAILED - " + t.getMessage());
            t.printStackTrace();
            System.exit(1);
        }
    }
}
