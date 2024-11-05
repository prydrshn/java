// PayrollSystem.java
public class PayrollSystem {
    public static void main(String[] args) {
        // Creating employee objects
        HourlyEmployee hourlyEmployee = new HourlyEmployee(1, "John Doe", "Lab Assistant", 15.50, 40);
        SalariedEmployee salariedEmployee = new SalariedEmployee(2, "Jane Smith", "Professor", 5000);
        ExecutiveEmployee executiveEmployee = new ExecutiveEmployee(3, "Michael Brown", "Dean", 12000, 10);

        // Displaying employee details
        hourlyEmployee.displayEmployeeInfo();
        System.out.println("-----");
        salariedEmployee.displayEmployeeInfo();
        System.out.println("-----");
        executiveEmployee.displayEmployeeInfo();
    }
}
