public class AlertService implements StockObserver {
    @Override
    public void update(Product product) {
        if (product.getQuantity() < product.getReorderThreshold()) {
            System.out.println("ALERT: Low stock for " + product.getName() +
                               " – only " + product.getQuantity() + " left!");
        }
    }
}
