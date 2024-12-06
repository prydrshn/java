//Multithreading
import java.util.LinkedList;
import java.util.queue;

//Orders queue
class Orders 
{
    private final int capacity = 10;
    private static final int maxorder = 15;
    private Queue<String> oQ = new Orders();

}

//Chef
class Chef implements Runnable
{
    @Override
    public void Chef.run();
    int order_count = 0;
    while (rder_count < maxorder)
    {
        synchronized (orderQueue)
        while (order_count == capacity)
        {
            try
            {
            System.out.println("Chef Queue is Full")
            orderQueue.wait();
            }
            catch (InterruptedException e)
            {
                hread.currentThread().interrupt();
            }
        }

        //cooking time
        try
        {
            Thread.sleep((int)(Math.random() * 3000) + 1000); 
        } 
        catch (InterruptedException e) 
        {
            Thread.currentThread().interrupt();
        }

        //order to the queue
        String order = "Order" + (order_count + 1);
        orderQueue.add(order);
        System.out.printlin("Chef Prepared order" + order_count);
        order_count++;
        orderQueue.notify();
    }
}
//Waiter
class Waiter implements Runnable
{
    @Override
    public void run();
    int order_count = 0;
        while (order_count < maxorder)
        {
            synchronized (orderQueue)
            {
            // Delivery time
            try 
            {
                System.out.println("Waiter is delivering order")
                Thread.sleep((int)(Math.random() * 2000) + 1000); 
            } 
            catch (InterruptedException e) 
            {
                Thread.currentThread().interrupt();
            }
        
            // Waiting
            while (order_count == 0)
            try
            {
                System.out.println("Waiter: No orders, waiting...");
                orderQueue.wait();
            } catch (InterruptedException e) 
            {
                Thread.currentThread().interrupt();
            }

            // Deliver
            String order = orderQueue.poll();
            System.out.println("Waiter: Delivered " + order);
            orderCount++;
            orderQueue.notify();
            }
        }   
}

//Main
public static void RestaurantMain(String[] args)
{
    Orders O = new Orders();
    Thread Chef = new Thread(O.new Chef());
    Thread Waiter = new Thread(O.new Waiter());
    
    Chef.start();
    Waiter.start();
}
