import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Library library = new Library();

        // Menu for interacting with the library system
        while (true) {
            System.out.println("Library Management System");
            System.out.println("1. Add a Book");
            System.out.println("2. Borrow a Book");
            System.out.println("3. Return a Book");
            System.out.println("4. Display All Books");
            System.out.println("5. Exit");
            System.out.print("Please enter your choice: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume the newline character after nextInt()
            
            switch (choice) {
                case 1:  // Add a Book
                    addBook(scanner, library);
                    break;
                case 2:  // Borrow a Book
                    borrowBook(scanner, library);
                    break;
                case 3:  // Return a Book
                    returnBook(scanner, library);
                    break;
                case 4:  // Display All Books
                    library.displayAllBooks();
                    break;
                case 5:  // Exit
                    System.out.println("Exiting the Library Management System.");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice, please try again.");
            }
        }
    }

    // Method to add a book to the library based on user input
    private static void addBook(Scanner scanner, Library library) {
        // Collect input from the user for book details
        System.out.print("Enter Book ID: ");
        int bookId = scanner.nextInt();
        scanner.nextLine();  // Consume the newline character
        
        System.out.print("Enter Title: ");
        String title = scanner.nextLine();
        
        System.out.print("Enter Author: ");
        String author = scanner.nextLine();

        System.out.println("Select Book Type: ");
        System.out.println("1. General Book");
        System.out.println("2. Reference Book");
        System.out.println("3. Fiction Book");
        System.out.println("4. Periodical");
        System.out.print("Please enter your choice: ");
        int bookType = scanner.nextInt();
        scanner.nextLine();  // Consume the newline character

        switch (bookType) {
            case 1:  // General Book
                library.addBook(new Book(bookId, title, author));
                break;
            case 2:  // Reference Book
                System.out.print("Enter Edition Number: ");
                int edition = scanner.nextInt();
                scanner.nextLine();  // Consume the newline character
                library.addBook(new ReferenceBook(bookId, title, author, edition));
                break;
            case 3:  // Fiction Book
                System.out.print("Enter Genre: ");
                String genre = scanner.nextLine();
                library.addBook(new FictionBook(bookId, title, author, genre));
                break;
            case 4:  // Periodical
                System.out.print("Enter Edition Number: ");
                int periodicalEdition = scanner.nextInt();
                scanner.nextLine();  // Consume the newline character
                System.out.print("Enter Issue Frequency (e.g., Monthly, Weekly): ");
                String frequency = scanner.nextLine();
                library.addBook(new Periodical(bookId, title, author, periodicalEdition, frequency));
                break;
            default:
                System.out.println("Invalid choice.");
        }
    }

    // Borrow Book method
    private static void borrowBook(Scanner scanner, Library library) {
        System.out.print("Enter Book ID to borrow: ");
        int bookId = scanner.nextInt();
        library.borrowBook(bookId);
    }

    // Return Book method
    private static void returnBook(Scanner scanner, Library library) {
        System.out.print("Enter Book ID to return: ");
        int bookId = scanner.nextInt();
        library.returnBook(bookId);
    }
}
