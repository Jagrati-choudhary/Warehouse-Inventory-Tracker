import java.io.*;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class Warehouse {
    private final Map<Integer, Product> inventory = new ConcurrentHashMap<>();
    private final List<StockObserver> observers = new ArrayList<>();

    public void registerObserver(StockObserver observer) {
        observers.add(observer);
    }

    public synchronized void addProduct(Product product) {
        if (inventory.containsKey(product.getId())) {
            throw new IllegalArgumentException("Product already exists!");
        }
        inventory.put(product.getId(), product);
    }

    public synchronized void receiveShipment(int productId, int qty) {
        Product p = inventory.get(productId);
        if (p == null) {
            System.out.println("Invalid Product ID: " + productId);
            return;
        }
        p.addStock(qty);
        System.out.println("Received " + qty + " units of " + p.getName());
        notifyObservers(p);
    }

    public synchronized void fulfillOrder(int productId, int qty) {
        Product p = inventory.get(productId);
        if (p == null) {
            System.out.println("Invalid Product ID: " + productId);
            return;
        }
        if (p.getQuantity() < qty) {
            System.out.println("Insufficient stock for " + p.getName());
            return;
        }
        p.removeStock(qty);
        System.out.println("Fulfilled order of " + qty + " units of " + p.getName());
        notifyObservers(p);
    }

    public void printInventory() {
        System.out.println("\n--- Current Inventory ---");
        for (Product p : inventory.values()) {
            System.out.println(p);
        }
    }

    private void notifyObservers(Product product) {
        for (StockObserver observer : observers) {
            observer.update(product);
        }
    }

    public void loadFromFile(String filePath) {
        File file = new File(filePath);
        if (!file.exists()) return;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 4) {
                    int id = Integer.parseInt(parts[0]);
                    String name = parts[1];
                    int quantity = Integer.parseInt(parts[2]);
                    int threshold = Integer.parseInt(parts[3]);
                    inventory.put(id, new Product(id, name, quantity, threshold));
                }
            }
            System.out.println("Inventory loaded from file: " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveToFile(String filePath) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(filePath))) {
            for (Product p : inventory.values()) {
                pw.println(p.getId() + "," + p.getName() + "," +
                           p.getQuantity() + "," + p.getReorderThreshold());
            }
            System.out.println("Inventory saved to file: " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
