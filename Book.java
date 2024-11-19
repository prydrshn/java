public class Book {
    private int bookId;
    private String title;
    private String author;
    private boolean isAvailable;

    public Book(int bookId, String title, String author) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.isAvailable = true; // Default to available
    }

    public int getBookId() {
        return bookId;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void borrowBook() {
        if (isAvailable) {
            isAvailable = false;
            System.out.println("You have successfully borrowed the book: " + title);
        } else {
            System.out.println("The book is currently not available.");
        }
    }

    public void returnBook() {
        if (!isAvailable) {
            isAvailable = true;
            System.out.println("You have successfully returned the book: " + title);
        } else {
            System.out.println("This book was not borrowed.");
        }
    }

    public void displayBookInfo() {
        System.out.println("Book ID: " + bookId + ", Title: " + title + ", Author: " + author + ", Available: " + (isAvailable ? "Yes" : "No"));
    }
}
