// HourlyEmployee.java
class HourlyEmployee extends Employee {
    private double hourlyRate;
    private int hoursWorked;

    public HourlyEmployee(int employeeId, String employeeName, String designation, double hourlyRate, int hoursWorked) {
        super(employeeId, employeeName, designation);
        setHourlyRate(hourlyRate);
        setHoursWorked(hoursWorked);
    }

    // Setters with validation
    public void setHourlyRate(double hourlyRate) {
        if (hourlyRate <= 0) throw new IllegalArgumentException("Hourly rate must be positive.");
        this.hourlyRate = hourlyRate;
    }

    public void setHoursWorked(int hoursWorked) {
        if (hoursWorked < 0 || hoursWorked > 168) throw new IllegalArgumentException("Invalid number of hours worked.");
        this.hoursWorked = hoursWorked;
    }

    // Getters
    public double getHourlyRate() {
        return hourlyRate;
    }

    public int getHoursWorked() {
        return hoursWorked;
    }

    // Overridden method for calculating weekly salary
    @Override
    public double calculateWeeklySalary() {
        return hourlyRate * hoursWorked;
    }

    @Override
    public double calculateBonus() {
        return 0.0; // No bonus for hourly employees by default
    }

    // Method to display detailed employee info
    @Override
    public void displayEmployeeInfo() {
        super.displayEmployeeInfo();
        System.out.println("Hourly Rate: " + hourlyRate);
        System.out.println("Hours Worked: " + hoursWorked);
        System.out.println("Weekly Salary: " + calculateWeeklySalary());
    }
}
