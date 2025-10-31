import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        boolean continueWarehouses = true;

        while (continueWarehouses) {

            System.out.println("Enter Warehouse (A/B/C): ");
            String warehouseChoice = sc.nextLine().trim().toUpperCase();

            String savePath = switch (warehouseChoice) {
                case "A" -> "warehouse_A_inventory.csv";
                case "B" -> "warehouse_B_inventory.csv";
                case "C" -> "warehouse_C_inventory.csv";
                default -> {
                    System.out.println("Invalid choice! Defaulting to Warehouse A.");
                    yield "warehouse_A_inventory.csv";
                }
            };

            Warehouse warehouse = new Warehouse();
            AlertService alertService = new AlertService();
            warehouse.registerObserver(alertService);

            // Load existing data
            warehouse.loadFromFile(savePath);

            // Add new products
            System.out.println("Do you want to add new products? (yes/no)");
            String choice = sc.nextLine().trim().toLowerCase();

            while (choice.equals("yes")) {
                try {
                    System.out.print("Enter Product ID: ");
                    int id = Integer.parseInt(sc.nextLine());

                    System.out.print("Enter Product Name: ");
                    String name = sc.nextLine();

                    System.out.print("Enter Quantity: ");
                    int quantity = Integer.parseInt(sc.nextLine());

                    System.out.print("Enter Reorder Threshold: ");
                    int threshold = Integer.parseInt(sc.nextLine());

                    warehouse.addProduct(new Product(id, name, quantity, threshold));
                    System.out.println("Product added successfully!");
                } catch (IllegalArgumentException e) {
                    System.out.println("Error: " + e.getMessage());
                }

                System.out.println("Do you want to add another product? (yes/no)");
                choice = sc.nextLine().trim().toLowerCase();
            }

            // Operations
            boolean continueWorkflow = true;
            while (continueWorkflow) {
                System.out.println("\nChoose action: 1-Receive Shipment  2-Fulfill Order  3-Print Inventory  4-Exit");
                int action = Integer.parseInt(sc.nextLine());

                switch (action) {
                    case 1:
                        System.out.print("Enter Product ID: ");
                        int rId = Integer.parseInt(sc.nextLine());
                        System.out.print("Enter quantity to receive: ");
                        int rQty = Integer.parseInt(sc.nextLine());
                        warehouse.receiveShipment(rId, rQty);
                        break;
                    case 2:
                        System.out.print("Enter Product ID: ");
                        int fId = Integer.parseInt(sc.nextLine());
                        System.out.print("Enter quantity to fulfill: ");
                        int fQty = Integer.parseInt(sc.nextLine());
                        warehouse.fulfillOrder(fId, fQty);
                        break;
                    case 3:
                        warehouse.printInventory();
                        break;
                    case 4:
                        continueWorkflow = false;
                        break;
                    default:
                        System.out.println("Invalid option!");
                }
            }

            // Save data
            warehouse.saveToFile(savePath);
            System.out.println("\nInventory saved to " + savePath);

            // Ask if user wants another warehouse
            System.out.println("\nDo you want to add products in another warehouse? (yes/no)");
            String again = sc.nextLine().trim().toLowerCase();

            if (!again.equals("yes")) {
                continueWarehouses = false;
            } else {
                System.out.println("\n---------------------------------------------");
                System.out.println("Switching to another warehouse...\n");
            }
        }

        System.out.println("\n All warehouses updated successfully. Exiting program...");
        sc.close();
    }
}

