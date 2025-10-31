import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Warehouse warehouse = new Warehouse();
        AlertService alertService = new AlertService();
        warehouse.registerObserver(alertService);

        Scanner sc = new Scanner(System.in);

        // CSV file path
        String savePath = "inventory.csv";

        //  Load previous inventory (if file exists)
        warehouse.loadFromFile(savePath);

        //  User input for new products
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

        //  Example workflow for receiving shipment / fulfilling order
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

        //  Save current inventory to CSV file
        warehouse.saveToFile(savePath);
        System.out.println("\n✅ Inventory saved to " + savePath);

        sc.close();
    }
}

