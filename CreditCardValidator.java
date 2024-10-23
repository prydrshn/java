import java.util.Scanner;

public class CreditCardValidator {
    private String ccNumber;

    // Constructor to initialize the credit card number
    public CreditCardValidator(String ccNumber) {
        this.ccNumber = ccNumber;
    }

    // Method to validate the credit card
    public void validateCard() {
        // Step 1: Validate length (only 8 digits allowed)
        if (!validateLength()) {
            System.out.println("Invalid credit card number");
            return;
        }

        // Step 2: Remove the last digit
        int lastDigit = Character.getNumericValue(ccNumber.charAt(ccNumber.length() - 1));
        String remainingDigits = ccNumber.substring(0, ccNumber.length() - 1);

        // Step 3: Reverse the remaining digits
        String reversedDigits = new StringBuilder(remainingDigits).reverse().toString();

        // Step 4: Double the digits in odd-numbered positions (1st, 3rd, 5th, etc.)
        int sum = 0;
        for (int i = 0; i < reversedDigits.length(); i++) {
            int digit = Character.getNumericValue(reversedDigits.charAt(i));
            // Check for 1-based odd positions (0-based even index)
            if (i % 2 == 0) {
                digit *= 2;
                // If the result is a double-digit number, add the digits
                if (digit > 9) {
                    digit = digit - 9; // Same as adding the digits
                }
            }
            sum += digit;
        }

        // Step 5: Subtract the last digit from 10
        int resultE = (10 - lastDigit) % 10; // Ensure we wrap around to stay within 0-9

        // Step 6: Compare the result of step 5 with the last digit
        if (resultE == lastDigit) {
            System.out.println("Valid credit card number");
        } else {
            System.out.println("Invalid credit card number");
        }
    }

    // Method to validate the length of the credit card number
    private boolean validateLength() {
        return ccNumber.length() == 8; // Only 8 digits allowed
    }

    // Main method to run the program
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter an 8-digit credit card number: ");
        String ccNumber = scanner.nextLine();
        CreditCardValidator validator = new CreditCardValidator(ccNumber);
        validator.validateCard();
        scanner.close();
    }
}
