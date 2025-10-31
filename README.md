# Warehouse-Inventory-Tracker

# Overview
Warehouse Inventory Tracker is a simple Java console application for managing inventory across multiple warehouses.
Users can add products, receive shipments, fulfill orders, and receive automatic low stock alerts.
Each warehouse (A, B, or C) maintains its own separate inventory file in CSV format.

# Features
->Manage multiple warehouses (A, B, C)
->Add products with quantity and reorder threshold
->Receive shipments and update stock levels
->Fulfill customer orders and adjust stock accordingly
->Automatic low stock alerts implemented using the Observer design pattern
->Save and load inventory data from CSV files for each warehouse

# How to Run
-->Compile:
javac *.java

-->Run:
java Main

-->Follow console prompts to manage inventory

# Example Workflow:
Enter Warehouse (A/B/C): 
C
Do you want to add new products? (yes/no)
yes
Enter Product ID: 101
Enter Product Name: Laptop
Enter Quantity: 50
Enter Reorder Threshold: 2
Product added successfully!
Do you want to add another product? (yes/no)
no

Choose action: 1-Receive Shipment  2-Fulfill Order  3-Print Inventory  4-Exit
4

Do you want to add products in another warehouse? (yes/no)
no
Exiting program...


# Files
-> Main.java – program entry
-> Product.java – product details
-> Warehouse.java – inventory management
-> StockObserver.java – observer interface
-> AlertService.java – low stock alerts
->warehouse_A_inventory.csv	CSV file for Warehouse A inventory
->warehouse_B_inventory.csv	CSV file for Warehouse B inventory
->warehouse_C_inventory.csv	CSV file for Warehouse C inventory
