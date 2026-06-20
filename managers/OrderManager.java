package managers;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.logging.Level;
import models.*;
import exceptions.*;

public class OrderManager {
    private List<Order> orders = new ArrayList<>();
    private static OrderManager instance = null;
    private static final Logger logger = Logger.getLogger(OrderManager.class.getName());

    private OrderManager() {
        
    }

    public static OrderManager getInstance() {
        if (instance == null) {
            instance = new OrderManager();
        }
        return instance;
    }

    public void addOrder(Order order) throws InvalidOrderException {
        if (order == null) {
            throw new InvalidOrderException("Cannot add null order");
        }
        order.validateOrder();
        orders.add(order);
        if (order.getUser() != null) {
            logger.info("Added order id=" + order.getOrderId() + " user=" + order.getUser().getName() + " total=" + order.getTotal());
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

