import java.util.LinkedList;
import java.util.Queue;

// Orders queue class
class Orders {
    private final int capacity = 10;
    private static final int maxOrder = 15;
    private Queue<String> orderQueue = new LinkedList<>();

    // Chef class
    class Chef implements Runnable {
        @Override
        public void run() {
            int orderCount = 0;
            while (orderCount < maxOrder) 
            {
                synchronized (orderQueue) 
                {
                    while (orderQueue.size() == capacity)
                     {
                        try 
                        {
                            System.out.println("Chef Queue is Full, waiting...");
                            orderQueue.wait();
                        } 
                        catch (InterruptedException e) 
                        {
                            Thread.currentThread().interrupt();
                        }
                    }

                    // Cooking time
                    try
                    {
                        Thread.sleep((int) (Math.random() * 3000) + 1000);
                    } 
                    catch (InterruptedException e) 
                    {
                        Thread.currentThread().interrupt();
                    }

                    // Order to the queue
                    String order = "Order" + (orderCount + 1);
                    orderQueue.add(order);
                    System.out.println("Chef Prepared " + order);
                    orderCount++;
                    orderQueue.notify();
                }
            }
        }
    }

    // Waiter class
    class Waiter implements Runnable
    {
        @Override
        public void run() 
        {
            int orderCount = 0;
            while (orderCount < maxOrder) 
            {
                synchronized (orderQueue) 
                {
                    // Delivery time
                    try 
                    {
                        System.out.println("Waiter is delivering order...");
                        Thread.sleep((int) (Math.random() * 2000) + 1000);
                    } 
                    catch (InterruptedException e) 
                    {
                        Thread.currentThread().interrupt();
                    }

                    // Waiting for orders
                    while (orderQueue.isEmpty()) 
                    {
                        try 
                        {
                            System.out.println("Waiter: No orders, waiting...");
                            orderQueue.wait();
                        } 
                        catch (InterruptedException e) 
                        {
                            Thread.currentThread().interrupt();
                        }
                    }

                    // Deliver the order
                    String order = orderQueue.poll();
                    System.out.println("Waiter: Delivered " + order);
                    orderCount++;
                    orderQueue.notify();
                }
            }
        }
    }

// Main method to start the restaurant process
    public static void main(String[] args) 
    {
        Orders orders = new Orders();
        Thread chefThread = new Thread(orders.new Chef());
        Thread waiterThread = new Thread(orders.new Waiter());

        chefThread.start();
        waiterThread.start();
    }
}
