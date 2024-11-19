//Inheritance
public class Periodical extends ReferenceBook {
    private String issueFrequency;

    // Constructor
    public Periodical(int bookId, String title, String author, int edition, String issueFrequency) {
        super(bookId, title, author, edition);
        setIssueFrequency(issueFrequency);
    }

    // Getter and Setter for Issue Frequency
    public String getIssueFrequency() {
        return issueFrequency;
    }

    public void setIssueFrequency(String issueFrequency) {
        if (issueFrequency != null && !issueFrequency.trim().isEmpty()) {
            this.issueFrequency = issueFrequency;
        } else {
            System.out.println("Issue frequency cannot be empty.");
        }
    }

    @Override
    public void displayBookInfo() {
        super.displayBookInfo();
        System.out.println("Issue Frequency: " + issueFrequency);
    }
}

