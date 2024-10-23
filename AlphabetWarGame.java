import java.util.HashMap;
import java.util.Map;

public class AlphabetWarGame {
    private Map<Character, Integer> leftStrengths;
    private Map<Character, Integer> rightStrengths;

    // Default constructor with predefined strengths
    public AlphabetWarGame() {
        leftStrengths = new HashMap<>();
        rightStrengths = new HashMap<>();
        setDefaultStrengths();
    }

    // Constructor allowing custom strengths
    public AlphabetWarGame(Map<Character, Integer> left, Map<Character, Integer> right) {
        this.leftStrengths = left;
        this.rightStrengths = right;
    }

    // Method to set default strengths
    private void setDefaultStrengths() {
        leftStrengths.put('w', 4);
        leftStrengths.put('p', 3);
        leftStrengths.put('b', 2);
        leftStrengths.put('s', 1);
        
        rightStrengths.put('m', 4);
        rightStrengths.put('q', 3);
        rightStrengths.put('d', 2);
        rightStrengths.put('z', 1);
    }

    // Method to determine the winner from a single word
    public String alphabetWar(String word) {
        return determineWinner(calculateStrength(word));
    }

    // Method to determine the winner from separate left and right words
    public String alphabetWar(String leftWord, String rightWord) {
        int leftStrength = calculateStrength(leftWord).get("left");
        int rightStrength = calculateStrength(rightWord).get("right");
        return determineWinner(leftStrength, rightStrength);
    }

    // Calculate total strengths for a given word
    private Map<String, Integer> calculateStrength(String word) {
        int leftScore = 0;
        int rightScore = 0;

        for (char letter : word.toCharArray()) {
            if (leftStrengths.containsKey(letter)) {
                leftScore += leftStrengths.get(letter);
            } else if (rightStrengths.containsKey(letter)) {
                rightScore += rightStrengths.get(letter);
            }
        }

        Map<String, Integer> scores = new HashMap<>();
        scores.put("left", leftScore);
        scores.put("right", rightScore);
        return scores;
    }

    // Determine the winner based on the strengths
    private String determineWinner(int leftStrength, int rightStrength) {
        if (leftStrength > rightStrength) {
            return "Left side wins!";
        } else if (rightStrength > leftStrength) {
            return "Right side wins!";
        } else {
            return "Let's fight again!";
        }
    }

    // Overloaded method to determine the winner based on calculated strengths
    private String determineWinner(Map<String, Integer> scores) {
        return determineWinner(scores.get("left"), scores.get("right"));
    }

    // Main method to test the game
    public static void main(String[] args) {
        AlphabetWarGame game = new AlphabetWarGame();

        // Test cases
        System.out.println(game.alphabetWar("z")); // Right side wins!
        System.out.println(game.alphabetWar("zdqmwpbs")); // Let's fight again!
        System.out.println(game.alphabetWar("wwwwwwz")); // Left side wins!

        // Example of separate left and right words
        System.out.println(game.alphabetWar("wwww", "zz")); // Left side wins!
        
        // Custom strengths example
        Map<Character, Integer> customLeft = new HashMap<>();
        customLeft.put('w', 5);
        customLeft.put('p', 4);
        customLeft.put('b', 3);
        customLeft.put('s', 2);

        Map<Character, Integer> customRight = new HashMap<>();
        customRight.put('m', 5);
        customRight.put('q', 4);
        customRight.put('d', 3);
        customRight.put('z', 2);

        AlphabetWarGame customGame = new AlphabetWarGame(customLeft, customRight);
        System.out.println(customGame.alphabetWar("wwpp", "zz")); // Left side wins!
    }
}
