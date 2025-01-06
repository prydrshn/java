import java.util.ArrayList;
import java.util.HashMap;

public class CustomerRepository {
    private HashMap<String, Customer> customerMap;

    public CustomerRepository() {
        customerMap = new HashMap<>();
    }

    // Add a customer to the repository
    public void addCustomer(Customer customer) {
        customerMap.put(customer.getCustomerId(), customer);
    }

    // Get customer by ID
    public Customer getCustomerById(String customerId) {
        return customerMap.get(customerId);
    }

    // Remove customer by ID
    public void removeCustomer(String customerId) {
        customerMap.remove(customerId);
    }

    // Get all customers as a list
    public ArrayList<Customer> getAllCustomers() {
        return new ArrayList<>(customerMap.values());
    }

    // Get the total number of customers
    public int getTotalCustomers() {
        return customerMap.size();
    }
}
