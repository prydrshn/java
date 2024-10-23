import java.util.*;

public class TopKFrequentNumbers {
    // Static variable to store the input array
    static int[] numbers;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Input: size of the array
        System.out.print("Enter the size of the array: ");
        int size = scanner.nextInt();
        
        // Input: the numbers in the array
        numbers = new int[size];
        System.out.println("Enter " + size + " numbers:");
        for (int i = 0; i < size; i++) {
            numbers[i] = scanner.nextInt();
        }

        // Input: the value of K
        System.out.print("Enter the value of K: ");
        int K = scanner.nextInt();

        // Output the top K frequent numbers
        System.out.println("Top " + K + " frequent numbers: " + Arrays.toString(getTopKFrequent(K)));
    }

    // Static method to get the top K frequent numbers
    public static int[] getTopKFrequent(int K) {
        // Frequency map to count occurrences
        Map<Integer, Integer> frequencyMap = new HashMap<>();

        // Count the frequency of each number
        for (int num : numbers) {
            frequencyMap.put(num, frequencyMap.getOrDefault(num, 0) + 1);
        }

        // List to hold the entries of frequency map
        List<Map.Entry<Integer, Integer>> entryList = new ArrayList<>(frequencyMap.entrySet());

        // Sort the list by frequency (descending) and by value (descending)
        Collections.sort(entryList, (a, b) -> {
            int freqCompare = b.getValue().compareTo(a.getValue());
            if (freqCompare == 0) {
                return b.getKey().compareTo(a.getKey());
            }
            return freqCompare;
        });

        // Prepare the result array
        int[] result = new int[K];
        for (int i = 0; i < K; i++) {
            result[i] = entryList.get(i).getKey();
        }

        return result;
    }
}
