// SalariedEmployee.java
class SalariedEmployee extends Employee {
    private double monthlySalary;

    public SalariedEmployee(int employeeId, String employeeName, String designation, double monthlySalary) {
        super(employeeId, employeeName, designation);
        setMonthlySalary(monthlySalary);
    }

    // Setter with validation
    public void setMonthlySalary(double monthlySalary) {
        if (monthlySalary <= 0) throw new IllegalArgumentException("Monthly salary must be positive.");
        this.monthlySalary = monthlySalary;
    }

    // Getter
    public double getMonthlySalary() {
        return monthlySalary;
    }

    // Overridden method for calculating weekly salary
    @Override
    public double calculateWeeklySalary() {
        return monthlySalary / 4; // Assuming 4 weeks in a month
    }

    @Override
    public double calculateBonus() {
        return 0.0; // No bonus for salaried employees by default
    }

    // Method to display detailed employee info
    @Override
    public void displayEmployeeInfo() {
        super.displayEmployeeInfo();
        System.out.println("Monthly Salary: " + monthlySalary);
        System.out.println("Weekly Salary: " + calculateWeeklySalary());
    }
}
