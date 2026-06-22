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

    public Order getOrderById(int orderId) {
        for (Order order : orders) {
            if (order.getOrderId() == orderId) {
                return order;
            }
        }
        return null;
    }

    public void confirmOrder(int orderId) throws InvalidOrderException {
        Order order = getOrderById(orderId);
        if (order == null) {
            throw new InvalidOrderException("Order not found: " + orderId);
        }
        order.setStatus(OrderStatus.CONFIRMED);
        logger.info("Order " + orderId + " status changed to CONFIRMED");
    }

    public void markPreparing(int orderId) throws InvalidOrderException {
        Order order = getOrderById(orderId);
        if (order == null) {
            throw new InvalidOrderException("Order not found: " + orderId);
        }
        order.setStatus(OrderStatus.PREPARING);
        logger.info("Order " + orderId + " status changed to PREPARING");
    }

    public void markOutForDelivery(int orderId) throws InvalidOrderException {
        Order order = getOrderById(orderId);
        if (order == null) {
            throw new InvalidOrderException("Order not found: " + orderId);
        }
        order.setStatus(OrderStatus.OUT_FOR_DELIVERY);
        logger.info("Order " + orderId + " status changed to OUT_FOR_DELIVERY");
    }

    public void markDelivered(int orderId) throws InvalidOrderException {
        Order order = getOrderById(orderId);
        if (order == null) {
            throw new InvalidOrderException("Order not found: " + orderId);
        }
        order.setStatus(OrderStatus.DELIVERED);
        logger.info("Order " + orderId + " status changed to DELIVERED");
    }

    public void cancelOrder(int orderId) throws InvalidOrderException {
        Order order = getOrderById(orderId);
        if (order == null) {
            throw new InvalidOrderException("Order not found: " + orderId);
        }
        order.setStatus(OrderStatus.CANCELLED);
        logger.warning("Order " + orderId + " has been CANCELLED");
    }

    public void clearOrders() {
        orders.clear();
    }
}

