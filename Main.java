#Interface
import java.util.*;
import java.util.HashSet;
import java.util.Set;

public class TransactionAnalyzerImpl implements TransactionAnalyzer {

    @Override
    public int countPairs(int[] arr, int target) {
        Set<Integer> seen = new HashSet<>();
        Set<String> uniquePairs = new HashSet<>();
        int count = 0;

        for (int price : arr) {
            int complement = target - price;

            if (seen.contains(complement)) {
                String pair = Math.min(price, complement) + "," + Math.max(price, complement);
                if (!uniquePairs.contains(pair)) {
                    uniquePairs.add(pair);
                    count++;
                }
            }
            seen.add(price);
        }

        return count;
    }
}
public class Main {
    public static void InterfaceMain(String[] args) {
        TransactionAnalyzer analyzer = new TransactionAnalyzerImpl();

        int[] arr1 = {1, 5, 7, -1, 5};
        int target1 = 6;
        System.out.println("Output: " + analyzer.countPairs(arr1, target1)); 

        int[] arr2 = {2, 4, 3, 5, 6};
        int target2 = 8;
        System.out.println("Output: " + analyzer.countPairs(arr2, target2)); 
    }
}
