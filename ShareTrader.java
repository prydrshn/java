import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ShareTrader {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Input: size of the prices array
        System.out.print("Enter the number of stock prices: ");
        int size = scanner.nextInt();
        
        if (size < 2) {
            System.out.println("Not enough prices to make a transaction.");
            return;
        }

        // Input: the stock prices
        int[] prices = new int[size];
        System.out.println("Enter the stock prices:");
        for (int i = 0; i < size; i++) {
            prices[i] = scanner.nextInt();
            if (prices[i] < 0) {
                System.out.println("Stock prices must be non-negative.");
                return;
            }
        }

        // Calculate and display the maximum profit and transactions
        int maxProfit = findMaxProfit(prices);
        System.out.println("Maximum profit: " + maxProfit);
    }

    public static int findMaxProfit(int[] prices) {
        int n = prices.length;
        if (n < 2) {
            return 0; // Not enough prices to make a transaction
        }

        List<String> transactions = new ArrayList<>();
        int totalProfit = 0;

        for (int i = 1; i < n; i++) {
            // If the price is going up, consider it a buying and selling opportunity
            if (prices[i] > prices[i - 1]) {
                int buyPrice = prices[i - 1];
                int sellPrice = prices[i];
                totalProfit += (sellPrice - buyPrice);
                transactions.add("Buy at " + buyPrice + ", sell at " + sellPrice);
            }
        }

        // Output the transactions
        for (String transaction : transactions) {
            System.out.println(transaction);
        }

        return totalProfit;
    }
}
