import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.util.regex.Pattern;
import javax.swing.border.*;

public class SwingConferenceManager {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/ConferenceDB";
    private static final String USER = "root";
    private static final String PASSWORD = "1234";
    
    // Regular expressions for validation
    private static final Pattern CONTACT_PATTERN = Pattern.compile("\\d{10}");
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[A-Za-z0-9+_.-]+@(.+)$");

    private JFrame frame;
    private JPanel panel;
    private JTextArea outputArea;

    public SwingConferenceManager() {
        initializeUI();
    }

    private void initializeUI() {
        frame = new JFrame("Conference Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);  // Center the window

        // Main panel with BorderLayout
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        // Button panel with better styling
        panel = new JPanel(new GridLayout(3, 2, 10, 10));
        panel.setBorder(new CompoundBorder(
            new TitledBorder("Actions"),
            new EmptyBorder(10, 10, 10, 10)
        ));

        // Create styled buttons
        JButton addButton = createStyledButton("Add Attendee", "add.png");
        JButton editButton = createStyledButton("Edit Attendee", "edit.png");
        JButton deleteButton = createStyledButton("Delete Attendee", "delete.png");
        JButton searchButton = createStyledButton("Search Attendee", "search.png");
        JButton statsButton = createStyledButton("Generate Statistics", "stats.png");
        JButton refreshButton = createStyledButton("Refresh", "refresh.png");

        // Add action listeners
        addButton.addActionListener(e -> addAttendee());
        editButton.addActionListener(e -> editAttendee());
        deleteButton.addActionListener(e -> deleteAttendee());
        searchButton.addActionListener(e -> searchAttendee());
        statsButton.addActionListener(e -> generateStatistics());
        refreshButton.addActionListener(e -> refreshDisplay());

        // Add buttons to panel
        panel.add(addButton);
        panel.add(editButton);
        panel.add(deleteButton);
        panel.add(searchButton);
        panel.add(statsButton);
        panel.add(refreshButton);

        // Output area with styling
        outputArea = new JTextArea();
        outputArea.setEditable(false);
        outputArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        outputArea.setMargin(new Insets(5, 5, 5, 5));
        
        JScrollPane scrollPane = new JScrollPane(outputArea);
        scrollPane.setBorder(new CompoundBorder(
            new TitledBorder("Results"),
            new EmptyBorder(5, 5, 5, 5)
        ));

        // Add components to main panel
        mainPanel.add(panel, BorderLayout.NORTH);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        frame.add(mainPanel);
        frame.setVisible(true);
    }

    private JButton createStyledButton(String text, String iconName) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 12));
        button.setFocusPainted(false);
        button.setBackground(new Color(240, 240, 240));
        button.setBorder(new CompoundBorder(
            new LineBorder(Color.GRAY),
            new EmptyBorder(5, 10, 5, 10)
        ));
        return button;
    }

    private void addAttendee() {
        JPanel inputPanel = new JPanel(new GridLayout(4, 2, 5, 5));
        
        JTextField nameField = new JTextField();
        JTextField emailField = new JTextField();
        JTextField contactField = new JTextField();
        JTextField countryField = new JTextField();

        inputPanel.add(new JLabel("Full Name:"));
        inputPanel.add(nameField);
        inputPanel.add(new JLabel("Email:"));
        inputPanel.add(emailField);
        inputPanel.add(new JLabel("Contact Number (10 digits):"));
        inputPanel.add(contactField);
        inputPanel.add(new JLabel("Country:"));
        inputPanel.add(countryField);

        int result = JOptionPane.showConfirmDialog(frame, inputPanel, 
            "Add New Attendee", JOptionPane.OK_CANCEL_OPTION);

        if (result == JOptionPane.OK_OPTION) {
            String fullName = nameField.getText().trim();
            String email = emailField.getText().trim();
            String contactNumber = contactField.getText().trim();
            String country = countryField.getText().trim();

            // Validate inputs
            if (!validateInputs(fullName, email, contactNumber, country)) {
                return;
            }

            insertAttendee(fullName, email, contactNumber, country);
        }
    }

    private boolean validateInputs(String fullName, String email, String contactNumber, String country) {
        if (fullName.isEmpty() || email.isEmpty() || contactNumber.isEmpty() || country.isEmpty()) {
            showError("All fields are required!");
            return false;
        }

        if (!EMAIL_PATTERN.matcher(email).matches()) {
            showError("Invalid email format!");
            return false;
        }

        if (!CONTACT_PATTERN.matcher(contactNumber).matches()) {
            showError("Contact number must be exactly 10 digits!");
            return false;
        }

        return true;
    }

    private void showError(String message) {
        JOptionPane.showMessageDialog(frame, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    private void insertAttendee(String fullName, String email, String contactNumber, String country) {
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD)) {
            String sql = "INSERT INTO Attendees (FullName, Email, ContactNumber, Country) VALUES (?, ?, ?, ?)";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, fullName);
                pstmt.setString(2, email);
                pstmt.setString(3, contactNumber);
                pstmt.setString(4, country);
                int rows = pstmt.executeUpdate();
                if (rows > 0) {
                    outputArea.setText("Attendee added successfully!");
                    refreshDisplay();
                } else {
                    outputArea.setText("Failed to add attendee.");
                }
            }
        } catch (SQLException e) {
            showError("Database error: " + e.getMessage());
        }
    }

    private void editAttendee() {
        String idStr = JOptionPane.showInputDialog(frame, "Enter Attendee ID to Edit:", 
            "Edit Attendee", JOptionPane.QUESTION_MESSAGE);
        if (idStr == null || idStr.trim().isEmpty()) return;

        try {
            int attendeeID = Integer.parseInt(idStr);
            
            JPanel inputPanel = new JPanel(new GridLayout(2, 2, 5, 5));
            JTextField emailField = new JTextField();
            JTextField contactField = new JTextField();

            inputPanel.add(new JLabel("New Email:"));
            inputPanel.add(emailField);
            inputPanel.add(new JLabel("New Contact Number (10 digits):"));
            inputPanel.add(contactField);

            int result = JOptionPane.showConfirmDialog(frame, inputPanel, 
                "Edit Attendee", JOptionPane.OK_CANCEL_OPTION);

            if (result == JOptionPane.OK_OPTION) {
                String email = emailField.getText().trim();
                String contactNumber = contactField.getText().trim();

                if (!EMAIL_PATTERN.matcher(email).matches()) {
                    showError("Invalid email format!");
                    return;
                }

                if (!CONTACT_PATTERN.matcher(contactNumber).matches()) {
                    showError("Contact number must be exactly 10 digits!");
                    return;
                }

                updateAttendee(attendeeID, email, contactNumber);
            }
        } catch (NumberFormatException e) {
            showError("Invalid ID format!");
        }
    }

    private void updateAttendee(int attendeeID, String email, String contactNumber) {
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD)) {
            String sql = "UPDATE Attendees SET Email = ?, ContactNumber = ? WHERE AttendeeID = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, email);
                pstmt.setString(2, contactNumber);
                pstmt.setInt(3, attendeeID);
                int rows = pstmt.executeUpdate();
                if (rows > 0) {
                    outputArea.setText("Attendee updated successfully!");
                    refreshDisplay();
                } else {
                    outputArea.setText("Attendee not found.");
                }
            }
        } catch (SQLException e) {
            showError("Database error: " + e.getMessage());
        }
    }

    private void deleteAttendee() {
        String idStr = JOptionPane.showInputDialog(frame, "Enter Attendee ID to Delete:",
            "Delete Attendee", JOptionPane.WARNING_MESSAGE);
        if (idStr == null || idStr.trim().isEmpty()) return;

        try {
            int attendeeID = Integer.parseInt(idStr);
            int confirm = JOptionPane.showConfirmDialog(frame,
                "Are you sure you want to delete this attendee?",
                "Confirm Deletion",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE);

            if (confirm == JOptionPane.YES_OPTION) {
                try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD)) {
                    String sql = "DELETE FROM Attendees WHERE AttendeeID = ?";
                    try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                        pstmt.setInt(1, attendeeID);
                        int rows = pstmt.executeUpdate();
                        if (rows > 0) {
                            outputArea.setText("Attendee deleted successfully!");
                            refreshDisplay();
                        } else {
                            outputArea.setText("Attendee not found.");
                        }
                    }
                } catch (SQLException e) {
                    showError("Database error: " + e.getMessage());
                }
            }
        } catch (NumberFormatException e) {
            showError("Invalid ID format!");
        }
    }

    private void searchAttendee() {
        String search = JOptionPane.showInputDialog(frame, 
            "Enter ID, Full Name, or Country to Search:",
            "Search Attendee",
            JOptionPane.QUESTION_MESSAGE);

        if (search == null || search.trim().isEmpty()) return;

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD)) {
            String sql = "SELECT * FROM Attendees WHERE AttendeeID = ? OR FullName LIKE ? OR Country LIKE ?";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, search);
                pstmt.setString(2, "%" + search + "%");
                pstmt.setString(3, "%" + search + "%");

                try (ResultSet rs = pstmt.executeQuery()) {
                    StringBuilder result = new StringBuilder();
                    result.append(String.format("%-5s %-20s %-25s %-15s %-15s%n",
                        "ID", "Name", "Email", "Contact", "Country"));
                    result.append("-".repeat(80)).append("\n");

                    while (rs.next()) {
                        result.append(String.format("%-5d %-20s %-25s %-15s %-15s%n",
                            rs.getInt("AttendeeID"),
                            rs.getString("FullName"),
                            rs.getString("Email"),
                            rs.getString("ContactNumber"),
                            rs.getString("Country")));
                    }
                    outputArea.setText(result.length() > 80 ? result.toString() : "No matching records found.");
                }
            }
        } catch (SQLException e) {
            showError("Database error: " + e.getMessage());
        }
    }

    private void generateStatistics() {
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD)) {
            String call = "{CALL GetAttendeeStatistics()}";
            try (CallableStatement cstmt = conn.prepareCall(call)) {
                try (ResultSet rs = cstmt.executeQuery()) {
                    StringBuilder result = new StringBuilder();
                    result.append("Attendee Statistics by Country\n");
                    result.append("-".repeat(40)).append("\n");
                    result.append(String.format("%-20s %-10s%n", "Country", "Count"));
                    result.append("-".repeat(40)).append("\n");

                    while (rs.next()) {
                        result.append(String.format("%-20s %-10d%n",
                            rs.getString("Country"),
                            rs.getInt("NumberOfAttendees")));
                    }
                    outputArea.setText(result.toString());
                }
            }
        } catch (SQLException e) {
            showError("Database error: " + e.getMessage());
        }
    }

    private void refreshDisplay() {
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD)) {
            String sql = "SELECT * FROM Attendees ORDER BY AttendeeID";
            try (Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery(sql)) {
                
                StringBuilder result = new StringBuilder();
                result.append("Current Attendees List\n");
                result.append("-".repeat(80)).append("\n");
                result.append(String.format("%-5s %-20s %-25s %-15s %-15s%n",
                    "ID", "Name", "Email", "Contact", "Country"));
                result.append("-".repeat(80)).append("\n");

                while (rs.next()) {
                    result.append(String.format("%-5d %-20s %-25s %-15s %-15s%n",
                        rs.getInt("AttendeeID"),
                        rs.getString("FullName"),
                        rs.getString("Email"),
                        rs.getString("ContactNumber"),
                        rs.getString("Country")));
                }
                outputArea.setText(result.toString());
            }
        } catch (SQLException e) {
            showError("Database error: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        try {
            String systemLookAndFeel = UIManager.getSystemLookAndFeelClassName();

            UIManager.setLookAndFeel(systemLookAndFeel);

        } catch (Exception e) {
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(SwingConferenceManager::new);
    }
}