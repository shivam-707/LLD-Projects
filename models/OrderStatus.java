package models;

/**
 * Enum representing the lifecycle states of an order.
 * 
 * Workflow: PENDING → CONFIRMED → PREPARING → OUT_FOR_DELIVERY → DELIVERED
 */
public enum OrderStatus {
    PENDING("Order placed, awaiting confirmation"),
    CONFIRMED("Order confirmed by restaurant"),
    PREPARING("Restaurant is preparing the order"),
    OUT_FOR_DELIVERY("Order is out for delivery"),
    DELIVERED("Order delivered successfully"),
    CANCELLED("Order was cancelled");

    private final String description;

    OrderStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
