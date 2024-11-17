// Abstract class Robber
abstract class Robber {

    // Abstract method that prints "MScAI&ML"
    abstract void RobbingClass();

    // Abstract methods for different types of houses
    abstract int RowHouses(int[] money);
    abstract int RoundHouses(int[] money);
    abstract int SquareHouse(int[] money);
    abstract int MuliHouseBuilding(int[] money);

    // Default method that prints "I love MachineLearning."
    public void MachineLearning() {
        System.out.println("I love MachineLearning.");
    }
}

// JAVAProfessionalRobber class inherits from Robber and implements abstract methods
class JAVAProfessionalRobber extends Robber {

    // Implementing the abstract method RobbingClass
    @Override
    void RobbingClass() {
        System.out.println("MScAI&ML");
    }

    // Implementing the RowHouses method
    @Override
    int RowHouses(int[] money) {
        if (money == null || money.length == 0) {
            return 0;
        }
        if (money.length == 1) {
            return money[0];
        }

        // Dynamic programming approach for house robbery problem
        int prev1 = 0, prev2 = 0;
        for (int amount : money) {
            int temp = prev1;
            prev1 = Math.max(prev2 + amount, prev1);
            prev2 = temp;
        }
        return prev1;
    }

    // Implementing the RoundHouses method
    @Override
    int RoundHouses(int[] money) {
        if (money == null || money.length == 0) {
            return 0;
        }
        if (money.length == 1) {
            return money[0];
        }

        // Helper function for non-circular (normal) rowhouses
        int simpleRobbery = robSimple(money, 0, money.length - 2); // Exclude the last house
        int circularRobbery = robSimple(money, 1, money.length - 1); // Exclude the first house
        return Math.max(simpleRobbery, circularRobbery);
    }

    // Helper method for robbing non-circular houses (simple rowhouses)
    private int robSimple(int[] money, int start, int end) {
        int prev1 = 0, prev2 = 0;
        for (int i = start; i <= end; i++) {
            int temp = prev1;
            prev1 = Math.max(prev2 + money[i], prev1);
            prev2 = temp;
        }
        return prev1;
    }

    // Implementing the SquareHouse method
    @Override
    int SquareHouse(int[] money) {
        return RowHouses(money);  // Since SquareHouse problem is the same as RowHouses
    }

    // Implementing the MuliHouseBuilding method
    @Override
    int MuliHouseBuilding(int[] money) {
        if (money == null || money.length == 0) {
            return 0;
        }

        // Dynamic programming approach, considering alternate houses
        int prev1 = 0, prev2 = 0;
        for (int amount : money) {
            int temp = prev1;
            prev1 = Math.max(prev2 + amount, prev1);
            prev2 = temp;
        }
        return prev1;
    }
}

// Main class with a test case
public class Main {
    public static void main(String[] args) {
        // Create an instance of JAVAProfessionalRobber
        JAVAProfessionalRobber robber = new JAVAProfessionalRobber();
        
        // Call the RobbingClass method
        robber.RobbingClass();

        // Test RowHouses
        int[] rowHousesMoney = {2, 7, 9, 3, 1};
        System.out.println("Max money that can be robbed from RowHouses: " + robber.RowHouses(rowHousesMoney));

        // Test RoundHouses
        int[] roundHousesMoney = {2, 3, 2};
        System.out.println("Max money that can be robbed from RoundHouses: " + robber.RoundHouses(roundHousesMoney));

        // Test SquareHouse (same logic as RowHouses)
        int[] squareHousesMoney = {5, 1, 1, 5};
        System.out.println("Max money that can be robbed from SquareHouses: " + robber.SquareHouse(squareHousesMoney));

        // Test MuliHouseBuilding
        int[] muliHousesMoney = {1, 2, 3, 1};
        System.out.println("Max money that can be robbed from MuliHouseBuilding: " + robber.MuliHouseBuilding(muliHousesMoney));

        // Call the MachineLearning method
        robber.MachineLearning();
    }
}
