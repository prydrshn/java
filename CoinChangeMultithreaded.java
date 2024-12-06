import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

// Callable task for calculating combinations
class CoinChangeTask implements Callable<Integer> {
    private int[] coins;
    private int sum;

    public CoinChangeTask(int[] coins, int sum) {
        this.coins = coins;
        this.sum = sum;
    }

    @Override
    public Integer call() throws Exception {
        int[] dp = new int[sum + 1];
        dp[0] = 1; // Base case: only one way to make sum 0

        for (int coin : coins) {
            for (int j = coin; j <= sum; j++) {
                dp[j] += dp[j - coin];
            }
        }

        return dp[sum];
    }
}

// Main class for multithreaded coin change
public class CoinChangeMultithreaded {
    public static void main(String[] args) throws Exception {
        int[] coins1 = {1, 2, 3};
        int sum1 = 4;

        int[] coins2 = {2, 5, 3, 6};
        int sum2 = 10;

        // Use a thread pool for parallel computation
        ExecutorService executor = Executors.newFixedThreadPool(2);

        // Create tasks
        CoinChangeTask task1 = new CoinChangeTask(coins1, sum1);
        CoinChangeTask task2 = new CoinChangeTask(coins2, sum2);

        // Submit tasks
        Future<Integer> result1 = executor.submit(task1);
        Future<Integer> result2 = executor.submit(task2);

        // Retrieve results
        System.out.println("Number of ways to make sum " + sum1 + ": " + result1.get());
        System.out.println("Number of ways to make sum " + sum2 + ": " + result2.get());

        // Shutdown executor
        executor.shutdown();
    }
}
