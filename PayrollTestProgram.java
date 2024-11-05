// PayrollTestProgram.java

public class PayrollTestProgram {
    public static void main(String[] args) {
        // Instantiate HourlyEmployee objects
        HourlyEmployee hourlyEmployee1 = new HourlyEmployee(1, "John Doe", "Lab Assistant", 20.50, 40);
        HourlyEmployee hourlyEmployee2 = new HourlyEmployee(2, "Alice Brown", "Research Assistant", 22.00, 35);

        // Instantiate SalariedEmployee objects
        SalariedEmployee salariedEmployee1 = new SalariedEmployee(3, "Bob Smith", "Professor", 5000);
        SalariedEmployee salariedEmployee2 = new SalariedEmployee(4, "Emma White", "Lecturer", 4000);

        // Instantiate ExecutiveEmployee objects
        ExecutiveEmployee executiveEmployee1 = new ExecutiveEmployee(5, "Michael Johnson", "Dean", 12000, 15);
        ExecutiveEmployee executiveEmployee2 = new ExecutiveEmployee(6, "Sophia Green", "Vice Chancellor", 15000, 20);

        // Displaying information for HourlyEmployees
        System.out.println("Hourly Employee 1:");
        hourlyEmployee1.displayEmployeeInfo();
        System.out.println("-----");
        System.out.println("Hourly Employee 2:");
        hourlyEmployee2.displayEmployeeInfo();
        System.out.println("-----");

        // Displaying information for SalariedEmployees
        System.out.println("Salaried Employee 1:");
        salariedEmployee1.displayEmployeeInfo();
        System.out.println("-----");
        System.out.println("Salaried Employee 2:");
        salariedEmployee2.displayEmployeeInfo();
        System.out.println("-----");

        // Displaying information for ExecutiveEmployees
        System.out.println("Executive Employee 1:");
        executiveEmployee1.displayEmployeeInfo();
        System.out.println("-----");
        System.out.println("Executive Employee 2:");
        executiveEmployee2.displayEmployeeInfo();
        System.out.println("-----");

        // Calculate and display total payroll
        double totalPayroll = hourlyEmployee1.calculateWeeklySalary() + hourlyEmployee2.calculateWeeklySalary() +
                salariedEmployee1.calculateWeeklySalary() + salariedEmployee2.calculateWeeklySalary() +
                executiveEmployee1.calculateWeeklySalary() + executiveEmployee2.calculateWeeklySalary();

        System.out.println("Total Payroll: " + totalPayroll);
    }
}
