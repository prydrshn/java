public class FictionBook extends Book {
    private String genre;

    public FictionBook(int bookId, String title, String author, String genre) {
        super(bookId, title, author);
        this.genre = genre;
    }

    @Override
    public void displayBookInfo() {
        super.displayBookInfo();
        System.out.println("Genre: " + genre);
    }
}
