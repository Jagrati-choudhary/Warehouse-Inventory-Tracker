# Warehouse-Inventory-Tracker

# Overview
A simple Java application to manage warehouse inventory. Add products, receive shipments, fulfill orders, and get low stock alerts. Inventory is saved in a CSV file.

# Features
->Add products with quantity and reorder threshold
->Receive shipments and fulfill orders
->Low stock alerts using Observer pattern
->Save and load inventory from CSV

# How to Run
-->Compile:
javac *.java

-->Run:
java Main

-->Follow console prompts to manage inventory

# Example Workflow:
->Add product: ID 101, name Laptop, quantity 50, threshold 10
->Receive shipment: 20 units for ID 101
->Fulfill order: 30 units for ID 101
->Low stock alert shows automatically if quantity < 10

# Files
-> Main.java – program entry
-> Product.java – product details
-> Warehouse.java – inventory management
-> StockObserver.java – observer interface
-> AlertService.java – low stock alerts
-> inventory.csv – stored inventory
