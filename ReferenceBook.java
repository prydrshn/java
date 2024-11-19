public class ReferenceBook extends Book {
    private int edition;

    public ReferenceBook(int bookId, String title, String author, int edition) {
        super(bookId, title, author);
        this.edition = edition;
    }

    @Override
    public void displayBookInfo() {
        super.displayBookInfo();
        System.out.println("Edition: " + edition);
    }
}
