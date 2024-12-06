import java.util.LinkedList;

// Custom exception for an empty counter
class CounterEmptyException extends Exception {
    public CounterEmptyException(String message) {
        super(message);
    }
}

// Coffee counter class for synchronization
class CoffeeCounter {
    private final int capacity = 3;
    private LinkedList<String> counter = new LinkedList<>();

    public synchronized void produce(String coffee) throws InterruptedException {
        while (counter.size() == capacity) {
            wait(); // Wait if counter is full
        }
        counter.add(coffee);
        System.out.println("Barista prepared coffee. Counter: " + counter.size());
        notifyAll();
    }

    public synchronized String consume() throws InterruptedException, CounterEmptyException {
        while (counter.isEmpty()) {
            wait(); // Wait if counter is empty
        }
        String coffee = counter.removeFirst();
        System.out.println("Customer picked up coffee. Counter: " + counter.size());
        notifyAll();
        return coffee;
    }

    public synchronized String review() throws InterruptedException, CounterEmptyException {
        if (counter.isEmpty()) throw new CounterEmptyException("Counter is empty. Cannot review.");
        String coffee = counter.removeFirst();
        System.out.println("Reviewer sampled coffee. Counter: " + counter.size());
        notifyAll();
        return coffee;
    }
}

// Producer thread (Barista)
class Barista extends Thread {
    private CoffeeCounter counter;
    private int coffeeCount;

    public Barista(CoffeeCounter counter, int coffeeCount) {
        this.counter = counter;
        this.coffeeCount = coffeeCount;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < coffeeCount; i++) {
                counter.produce("Coffee " + (i + 1));
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

// Consumer thread (Customer)
class Customer extends Thread {
    private CoffeeCounter counter;
    private int coffeeCount;

    public Customer(CoffeeCounter counter, int coffeeCount) {
        this.counter = counter;
        this.coffeeCount = coffeeCount;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < coffeeCount; i++) {
                counter.consume();
            }
        } catch (InterruptedException | CounterEmptyException e) {
            e.printStackTrace();
        }
    }
}

// Observer thread (Reviewer)
class Reviewer extends Thread {
    private CoffeeCounter counter;

    public Reviewer(CoffeeCounter counter) {
        this.counter = counter;
    }

    @Override
    public void run() {
        try {
            counter.review();
        } catch (InterruptedException | CounterEmptyException e) {
            e.printStackTrace();
        }
    }
}

// Main simulation class
public class CoffeeShopSimulation {
    public static void main(String[] args) {
        CoffeeCounter counter = new CoffeeCounter();

        // Create baristas, customers, and a reviewer
        Barista barista1 = new Barista(counter, 2);
        Barista barista2 = new Barista(counter, 3);
        Customer customer1 = new Customer(counter, 1);
        Customer customer2 = new Customer(counter, 2);
        Customer customer3 = new Customer(counter, 1);
        Reviewer reviewer = new Reviewer(counter);

        // Start threads
        barista1.start();
        barista2.start();
        customer1.start();
        customer2.start();
        customer3.start();
        reviewer.start();
    }
}
