package managers;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import models.*;

public class OrderManager {
    private List<Order> orders = new ArrayList<>();
    private static OrderManager instance = null;
    private static final Logger logger = Logger.getLogger(OrderManager.class.getName());

    private OrderManager() {
        // Private Constructor
    }

    public static OrderManager getInstance() {
        if (instance == null) {
            instance = new OrderManager();
        }
        return instance;
    }

    public void addOrder(Order order) {
        orders.add(order);
        if (order != null && order.getUser() != null) {
            logger.info("Added order id=" + order.getOrderId() + " user=" + order.getUser().getName() + " total=" + order.getTotal());
        } else {
            logger.info("Added order (null details)");
        }
    }

    public void listOrders() {
        logger.info("Listing all orders. count=" + orders.size());
        System.out.println("\n--- All Orders ---");
        for (Order order : orders) {
            String summary = order.getType() + " order for " + order.getUser().getName()
                    + " | Total: ₹" + order.getTotal()
                    + " | At: " + order.getScheduled();
            logger.fine(summary);
            System.out.println(summary);
        }
    }

    public int getOrderCount() {
        return orders.size();
    }

    public void clearOrders() {
        orders.clear();
    }
}

