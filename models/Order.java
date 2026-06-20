package models;

import java.util.List;
import strategies.*;
import exceptions.*;

public abstract class Order {
    private static int nextOrderId = 0;

    protected int orderId;
    protected User user;
    protected Restaurant restaurant;
    protected List<MenuItem> items;
    protected PaymentStrategy paymentStrategy;
    protected double total;
    protected String scheduled;

    public Order() {
        this.user = null;
        this.restaurant = null;
        this.paymentStrategy = null;
        this.total = 0.0;
        this.scheduled = "";
        this.orderId = ++nextOrderId;
    }

    /**
     * Validates that the order has all required fields before processing.
     * @throws InvalidOrderException if validation fails
     */
    public void validateOrder() throws InvalidOrderException {
        if (user == null) {
            throw new InvalidOrderException("Order " + orderId + ": User cannot be null");
        }
        if (restaurant == null) {
            throw new InvalidOrderException("Order " + orderId + ": Restaurant cannot be null");
        }
        if (items == null || items.isEmpty()) {
            throw new InvalidOrderException("Order " + orderId + ": Order must contain at least one item");
        }
        if (total <= 0) {
            throw new InvalidOrderException("Order " + orderId + ": Order total must be greater than zero (total=" + total + ")");
        }
        if (paymentStrategy == null) {
            throw new InvalidOrderException("Order " + orderId + ": Payment strategy is required");
        }
    }

    public void processPayment() throws PaymentFailedException {
        if (paymentStrategy == null) {
            throw new PaymentFailedException("Order " + orderId + ": Please choose a payment mode first");
        }
        if (total <= 0) {
            throw new PaymentFailedException("Order " + orderId + ": Cannot process payment for invalid amount (" + total + ")");
        }
        try {
            paymentStrategy.pay(total);
        } catch (Exception e) {
            throw new PaymentFailedException("Order " + orderId + ": Payment processing failed - " + e.getMessage(), e);
        }
    }

    public abstract String getType();

    // Getters and Setters
    public int getOrderId() {
        return orderId;
    }

    public void setUser(User u) {
        user = u;
    }

    public User getUser() {
        return user;
    }

    public void setRestaurant(Restaurant r) {
        restaurant = r;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setItems(List<MenuItem> its) {
        items = its;
        total = 0;
        for (MenuItem i : items) {
            total += i.getPrice();
        }
    }

    public List<MenuItem> getItems() {
        return items;
    }

    public void setPaymentStrategy(PaymentStrategy p) {
        paymentStrategy = p;
    }

    public void setScheduled(String s) {
        scheduled = s;
    }

    public String getScheduled() {
        return scheduled;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}

