// Employee.java
public class Employee {
    private int employeeId;
    private String employeeName;
    private String designation;

    public Employee(int employeeId, String employeeName, String designation) {
        setEmployeeId(employeeId);
        setEmployeeName(employeeName);
        setDesignation(designation);
    }

    // Setters with validation
    public void setEmployeeId(int employeeId) {
        if (employeeId <= 0) throw new IllegalArgumentException("Employee ID must be positive.");
        this.employeeId = employeeId;
    }

    public void setEmployeeName(String employeeName) {
        if (employeeName == null || employeeName.trim().isEmpty()) {
            throw new IllegalArgumentException("Employee name cannot be empty.");
        }
        this.employeeName = employeeName;
    }

    public void setDesignation(String designation) {
        if (designation == null || designation.trim().isEmpty()) {
            throw new IllegalArgumentException("Designation cannot be empty.");
        }
        this.designation = designation;
    }

    // Getters
    public int getEmployeeId() {
        return employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public String getDesignation() {
        return designation;
    }

    // Abstract method for calculating weekly salary
    public double calculateWeeklySalary() {
        return 0.0; // To be implemented by derived classes
    }

    // Method for calculating bonus (to be overridden)
    public double calculateBonus() {
        return 0.0; // Default bonus is 0, override in derived classes
    }

    // Method to display employee info
    public void displayEmployeeInfo() {
        System.out.println("Employee ID: " + employeeId);
        System.out.println("Employee Name: " + employeeName);
        System.out.println("Designation: " + designation);
    }
}
