import java.util.Date;
import java.util.HashSet;  // Add this import statement

public class Order {
    private String orderId;
    private Customer customer;
    private Date deliveryDate;
    private HashSet<Product> orderedProducts;

    public Order(String orderId, Customer customer, Date deliveryDate) {
        this.orderId = orderId;
        this.customer = customer;
        this.deliveryDate = deliveryDate;
        this.orderedProducts = new HashSet<>();
    }

    // Getters and Setters
    public String getOrderId() {
        return orderId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public HashSet<Product> getOrderedProducts() {
        return orderedProducts;
    }

    public void addProduct(Product product) {
        orderedProducts.add(product);
    }
}
