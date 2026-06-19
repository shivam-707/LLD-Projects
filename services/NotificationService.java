package services;

import java.util.List;
import java.util.logging.Logger;
import models.*;

public class NotificationService {
    private static final Logger logger = Logger.getLogger(NotificationService.class.getName());

    public static void notify(Order order) {
        if (order == null) {
            logger.warning("Attempted to notify null order");
            return;
        }

        logger.info("Notifying new order id=" + order.getOrderId() + " type=" + order.getType());

        System.out.println("\nNotification: New " + order.getType() + " order placed!");
        System.out.println("---------------------------------------------");
        System.out.println("Order ID: " + order.getOrderId());
        System.out.println("Customer: " + order.getUser().getName());
        System.out.println("Restaurant: " + order.getRestaurant().getName());
        System.out.println("Items Ordered:");

        List<MenuItem> items = order.getItems();
        if (items == null || items.isEmpty()) {
            logger.info("Order " + order.getOrderId() + " contains no items");
        } else {
            for (MenuItem item : items) {
                logger.fine("Order " + order.getOrderId() + " item=" + item.getName() + " price=" + item.getPrice());
                System.out.println("   - " + item.getName() + " (₹" + item.getPrice() + ")");
            }
        }

        System.out.println("Total: ₹" + order.getTotal());
        System.out.println("Scheduled For: " + order.getScheduled());
        System.out.println("Payment: Done");
        System.out.println("---------------------------------------------");
    }
}

