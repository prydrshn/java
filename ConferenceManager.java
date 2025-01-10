import java.sql.*;
import java.util.Scanner;

public class ConferenceManager {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/ConferenceDB";
    private static final String USER = "root";
    private static final String PASSWORD = "1234";

    public static void main(String[] args) {
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD)) {
            Scanner scanner = new Scanner(System.in);
            int choice;

            do {
                System.out.println("\n--- Conference Management System ---");
                System.out.println("1. Add Attendee");
                System.out.println("2. Edit Attendee");
                System.out.println("3. Delete Attendee");
                System.out.println("4. Search Attendee");
                System.out.println("5. Generate Attendee Statistics");
                System.out.println("6. Exit");
                System.out.print("Enter your choice: ");
                choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                switch (choice) {
                    case 1:
                        addAttendee(conn, scanner);
                        break;
                    case 2:
                        editAttendee(conn, scanner);
                        break;
                    case 3:
                        deleteAttendee(conn, scanner);
                        break;
                    case 4:
                        searchAttendee(conn, scanner);
                        break;
                    case 5:
                        generateStatistics(conn);
                        break;
                    case 6:
                        System.out.println("Exiting...");
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } while (choice != 6);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void addAttendee(Connection conn, Scanner scanner) throws SQLException {
        System.out.println("\n--- Add Attendee ---");
        System.out.print("Full Name: ");
        String fullName = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Contact Number: ");
        String contactNumber = scanner.nextLine();
        System.out.print("Country: ");
        String country = scanner.nextLine();

        String sql = "INSERT INTO Attendees (FullName, Email, ContactNumber, Country) VALUES (?, ?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, fullName);
            pstmt.setString(2, email);
            pstmt.setString(3, contactNumber);
            pstmt.setString(4, country);
            int rows = pstmt.executeUpdate();
            System.out.println(rows > 0 ? "Attendee added successfully!" : "Failed to add attendee.");
        }
    }

    private static void editAttendee(Connection conn, Scanner scanner) throws SQLException {
        System.out.println("\n--- Edit Attendee ---");
        System.out.print("Enter Attendee ID to edit: ");
        int attendeeID = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        System.out.print("New Email: ");
        String email = scanner.nextLine();
        System.out.print("New Contact Number: ");
        String contactNumber = scanner.nextLine();

        String sql = "UPDATE Attendees SET Email = ?, ContactNumber = ? WHERE AttendeeID = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, email);
            pstmt.setString(2, contactNumber);
            pstmt.setInt(3, attendeeID);
            int rows = pstmt.executeUpdate();
            System.out.println(rows > 0 ? "Attendee updated successfully!" : "Attendee not found.");
        }
    }

    private static void deleteAttendee(Connection conn, Scanner scanner) throws SQLException {
        System.out.println("\n--- Delete Attendee ---");
        System.out.print("Enter Attendee ID to delete: ");
        int attendeeID = scanner.nextInt();

        String sql = "DELETE FROM Attendees WHERE AttendeeID = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, attendeeID);
            int rows = pstmt.executeUpdate();
            System.out.println(rows > 0 ? "Attendee deleted successfully!" : "Attendee not found.");
        }
    }

    private static void searchAttendee(Connection conn, Scanner scanner) throws SQLException {
        System.out.println("\n--- Search Attendee ---");
        System.out.print("Enter search criteria (ID, Full Name, or Country): ");
        String search = scanner.nextLine();

        String sql = "SELECT * FROM Attendees WHERE AttendeeID = ? OR FullName LIKE ? OR Country LIKE ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, search);
            pstmt.setString(2, "%" + search + "%");
            pstmt.setString(3, "%" + search + "%");

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    System.out.printf("ID: %d, Name: %s, Email: %s, Contact: %s, Country: %s%n",
                            rs.getInt("AttendeeID"), rs.getString("FullName"), rs.getString("Email"),
                            rs.getString("ContactNumber"), rs.getString("Country"));
                }
            }
        }
    }

    private static void generateStatistics(Connection conn) throws SQLException {
        System.out.println("\n--- Attendee Statistics ---");

        String call = "{CALL GetAttendeeStatistics()}";
        try (CallableStatement cstmt = conn.prepareCall(call)) {
            try (ResultSet rs = cstmt.executeQuery()) {
                while (rs.next()) {
                    System.out.printf("Country: %s, Number of Attendees: %d%n",
                            rs.getString("Country"), rs.getInt("NumberOfAttendees"));
                }
            }
        }
    }
}