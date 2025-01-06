import java.util.HashSet;

public class Customer {
    private String customerId;
    private String name;
    private int loyaltyPoints;
    private HashSet<Product> productsPurchased;

    public Customer(String customerId, String name, int loyaltyPoints) {
        this.customerId = customerId;
        this.name = name;
        this.loyaltyPoints = loyaltyPoints;
        this.productsPurchased = new HashSet<>();
    }

    // Getters and Setters
    public String getCustomerId() {
        return customerId;
    }

    public String getName() {
        return name;
    }

    public int getLoyaltyPoints() {
        return loyaltyPoints;
    }

    public void addProduct(Product product) {
        productsPurchased.add(product);
    }

    public HashSet<Product> getProductsPurchased() {
        return productsPurchased;
    }

    public void updateLoyaltyPoints(int points) {
        this.loyaltyPoints += points;
    }
}
