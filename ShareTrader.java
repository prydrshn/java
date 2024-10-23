import java.util.Scanner;

public class ShareTrader {
    // Static variable to store the maximum profit
    static int maxProfit;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Input: size of the prices array
        System.out.print("Enter the number of stock prices: ");
        int size = scanner.nextInt();
        
        // Input: the stock prices
        int[] prices = new int[size];
        System.out.println("Enter the stock prices:");
        for (int i = 0; i < size; i++) {
            prices[i] = scanner.nextInt();
        }

        // Calculate and display the maximum profit
        System.out.println("Maximum profit: " + findMaxProfit(prices));
    }

    // Static method to calculate the maximum profit
    public static int findMaxProfit(int[] prices) {
        int n = prices.length;
        if (n < 2) {
            return 0; // Not enough prices to make a transaction
        }

        // Initialize profit arrays
        int[] profit1 = new int[n]; // Profit after the first transaction
        int[] profit2 = new int[n]; // Profit after the second transaction

        // Calculate profit for the first transaction
        int minPrice = prices[0];
        for (int i = 1; i < n; i++) {
            minPrice = Math.min(minPrice, prices[i]);
            profit1[i] = Math.max(profit1[i - 1], prices[i] - minPrice);
        }

        // Calculate profit for the second transaction
        int maxPrice = prices[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            maxPrice = Math.max(maxPrice, prices[i]);
            profit2[i] = Math.max(profit2[i + 1], maxPrice - prices[i]);
        }

        // Find the maximum profit from two transactions
        maxProfit = 0;
        for (int i = 0; i < n; i++) {
            maxProfit = Math.max(maxProfit, profit1[i] + profit2[i]);
        }

        return maxProfit;
    }
}
