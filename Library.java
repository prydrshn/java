import java.util.*;

public class Library {
    private Map<Integer, Book> bookCollection;

    public Library() {
        bookCollection = new HashMap<>();
    }

    public void addBook(Book book) {
        bookCollection.put(book.getBookId(), book);
    }

    public void borrowBook(int bookId) {
        Book book = bookCollection.get(bookId);
        if (book != null) {
            book.borrowBook();
        } else {
            System.out.println("Book not found!");
        }
    }

    public void returnBook(int bookId) {
        Book book = bookCollection.get(bookId);
        if (book != null) {
            book.returnBook();
        } else {
            System.out.println("Book not found!");
        }
    }

    public void displayAllBooks() {
        for (Book book : bookCollection.values()) {
            book.displayBookInfo();
        }
    }
}
