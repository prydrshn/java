import java.util.*;

public class AmazonApp {

    // Data structures
    private static ArrayList<Product> products = new ArrayList<>();
    private static ArrayList<Order> orders = new ArrayList<>();
    private static CustomerRepository customerRepository = new CustomerRepository();
    private static HashMap<String, Product> productMap = new HashMap<>();
    private static TreeSet<Product> sortedProductsByPrice = new TreeSet<>(new ProductPriceComparator());
    private static TreeSet<Customer> sortedCustomersByLoyaltyPoints = new TreeSet<>(new CustomerLoyaltyComparator());

    // Main function to simulate the app
    public static void main(String[] args) {
        // Adding sample customers
        Customer c1 = new Customer("C001", "John Doe", 100);
        Customer c2 = new Customer("C002", "Jane Smith", 200);
        customerRepository.addCustomer(c1);
        customerRepository.addCustomer(c2);

        // Adding products
        Product p1 = new Product("P001", "Laptop", 1000.00);
        Product p2 = new Product("P002", "Smartphone", 500.00);
        products.add(p1);
        products.add(p2);

        // Adding products to TreeSet for sorting by price
        sortedProductsByPrice.add(p1);
        sortedProductsByPrice.add(p2);

        // Adding customers to TreeSet for sorting by loyalty points
        sortedCustomersByLoyaltyPoints.add(c1);
        sortedCustomersByLoyaltyPoints.add(c2);

        // Display sorted products by price
        System.out.println("Sorted Products by Price:");
        for (Product product : sortedProductsByPrice) {
            System.out.println(product.getName() + ": $" + product.getPrice());
        }

        // Display sorted customers by loyalty points
        System.out.println("\nSorted Customers by Loyalty Points:");
        for (Customer customer : sortedCustomersByLoyaltyPoints) {
            System.out.println(customer.getName() + ": " + customer.getLoyaltyPoints() + " points");
        }

        // Retrieve all customers using the repository
        System.out.println("\nAll Customers in Repository:");
        ArrayList<Customer> allCustomers = customerRepository.getAllCustomers();
        for (Customer customer : allCustomers) {
            System.out.println(customer.getName() + " (ID: " + customer.getCustomerId() + ")");
        }
    }

    // Comparator for sorting Products by Price
    static class ProductPriceComparator implements Comparator<Product> {
        @Override
        public int compare(Product p1, Product p2) {
            return Double.compare(p1.getPrice(), p2.getPrice());
        }
    }

    // Comparator for sorting Customers by Loyalty Points
    static class CustomerLoyaltyComparator implements Comparator<Customer> {
        @Override
        public int compare(Customer c1, Customer c2) {
            return Integer.compare(c1.getLoyaltyPoints(), c2.getLoyaltyPoints());
        }
    }
}
