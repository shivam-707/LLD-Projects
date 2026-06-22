# LLD-Project 1

# Tomato - Food Delivery System (Low-Level Design)

## Overview
Tomato is a low-level design implementation of a food delivery application. It demonstrates core design patterns and principles for building a scalable order management system with support for both immediate and scheduled deliveries.

## Summary
This repository implements a modular food ordering system that supports delivery and pickup workflows, multiple payment strategies, and order tracking through manager classes. Recent changes added logging for service operations, lightweight test helpers, and production-grade exception handling with custom exception classes for validation and error management.

## Features
- **Multiple Order Types**: Support for instant (NOW) and scheduled delivery orders
- **Payment Strategies**: Multiple payment methods (Credit Card, UPI)
- **Order Management**: Complete order lifecycle from creation to notification
- **Restaurant Management**: Manage multiple restaurants and their menus
- **User Profiles**: Support for different user types with cart management
- **Pickup & Delivery**: Support for both pickup and delivery order types
- **Notifications**: Notification service for order updates

## Design Patterns Used
- **Factory Pattern**: `OrderFactory`, `NowOrderFactory`, `ScheduledOrderFactory`
- **Strategy Pattern**: Payment strategy implementations
- **Manager Pattern**: Business logic orchestration

## Error Handling & Validation
The system now includes robust exception handling with custom exceptions:
- **InvalidOrderException**: Thrown when order validation fails (null user, missing items, invalid total, missing payment strategy)
- **PaymentFailedException**: Thrown when payment processing fails
- **RestaurantNotFoundException**: Thrown when restaurant search returns no results

All core operations validate inputs and throw appropriate exceptions with descriptive error messages for better debugging and production monitoring.



The `OrderManager` provides methods for transitioning orders through these states, with automatic notifications at each step.

## Key Classes
Order: Abstract base for all order types
DeliveryOrder/PickupOrder: Specific order implementations
OrderManager: Handles order operations
RestaurantManager: Manages restaurant data
PaymentStrategy: Interface for payment processing

## Testing
### Unit Tests
- **OrderFactoryTest**: Validates factory pattern for NOW and SCHEDULED orders
- **OrderManagerTest**: Tests order manager add/clear/count operations  
- **ExceptionHandlingTest**: Comprehensive exception validation with 4 test cases
### Run Tests
```bash
javac -d out $(find . -name "*.java")
java -cp out OrderFactoryTest
java -cp out OrderManagerTest
java -cp out ExceptionHandlingTest
```

## Requirements
- Java 8 or higher
- No external dependencies (uses java.util.logging)

## Learning Outcomes
This project demonstrates:
Object-oriented design principles
Factory pattern implementation
Strategy pattern for flexible payment processing
Separation of concerns
Inheritance and polymorphism

## Summary
This repository implements a modular food ordering system that supports delivery and pickup workflows, multiple payment strategies, and order tracking through manager classes. Recent changes added logging for service operations and lightweight test helpers for order management.
