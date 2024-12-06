// Interface Declaration
interface WaterConservationSystem {
    int calculateTrappedWater(int[] blockHeights);
}

// Abstract Class Implementation
abstract class RainySeasonConservation implements WaterConservationSystem {}

// Implementation of CityBlockConservation
class CityBlockConservation extends RainySeasonConservation {

    @Override
    public int calculateTrappedWater(int[] blockHeights) {
        int n = blockHeights.length;
        if (n == 0) return 0;

        int[] leftMax = new int[n];
        int[] rightMax = new int[n];
        int waterTrapped = 0;

        // Fill left max array
        leftMax[0] = blockHeights[0];
        for (int i = 1; i < n; i++) {
            leftMax[i] = Math.max(leftMax[i - 1], blockHeights[i]);
        }

        // Fill right max array
        rightMax[n - 1] = blockHeights[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            rightMax[i] = Math.max(rightMax[i + 1], blockHeights[i]);
        }

        // Calculate trapped water
        for (int i = 0; i < n; i++) {
            waterTrapped += Math.min(leftMax[i], rightMax[i]) - blockHeights[i];
        }

        return waterTrapped;
    }
}

// Main Class
public class WaterConservationMain {
    public static void main(String[] args) {
        CityBlockConservation conservation = new CityBlockConservation();

        // Test Case 1
        int[] blocks1 = {3, 0, 2, 0, 4};
        System.out.println("Inout " + int[] blocks1);
        System.out.println("Test Case 1 - Trapped Water: " + conservation.calculateTrappedWater(blocks1));

        // Test Case 2
        int[] blocks2 = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        System.out.println("Inout " + int[] blocks2);
        System.out.println("Test Case 2 - Trapped Water: " + conservation.calculateTrappedWater(blocks2));
    }
}
