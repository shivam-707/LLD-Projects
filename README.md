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

## Key Classes
Order: Abstract base for all order types
DeliveryOrder/PickupOrder: Specific order implementations
OrderManager: Handles order operations
RestaurantManager: Manages restaurant data
PaymentStrategy: Interface for payment processing

## Requirements
Java 8 or higher

## Learning Outcomes
This project demonstrates:
Object-oriented design principles
Factory pattern implementation
Strategy pattern for flexible payment processing
Separation of concerns
Inheritance and polymorphism

## Summary
This repository implements a modular food ordering system that supports delivery and pickup workflows, multiple payment strategies, and order tracking through manager classes. Recent changes added logging for service operations and lightweight test helpers for order management.
