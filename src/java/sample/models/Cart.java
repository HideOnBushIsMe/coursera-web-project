package sample.models;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Cart implements Serializable {
    private Map<String, CartItem> items;

    public Cart() {
        items = new HashMap<>();
    }

    public Map<String, CartItem> getItems() {
        return items;
    }

    public void add(Product product) {
        if (items.containsKey(product.getProductId())) {
            CartItem item = items.get(product.getProductId());
            item.setQuantity(item.getQuantity() + 1);
        } else {
            items.put(product.getProductId(), new CartItem(product, 1));
        }
    }

    public void remove(String productId) {
        items.remove(productId);
    }

    public void update(String productId, int quantity) {
        if (items.containsKey(productId)) {
            if (quantity <= 0) {
                items.remove(productId);
            } else {
                items.get(productId).setQuantity(quantity);
            }
        }
    }

    public double getTotal() {
        double total = 0;
        for (CartItem item : items.values()) {
            total += (item.getProduct().getPrice() * (100 - item.getProduct().getDiscount()) / 100.0) * item.getQuantity();
        }
        return total;
    }
}
