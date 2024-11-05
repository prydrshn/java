// ExecutiveEmployee.java
class ExecutiveEmployee extends SalariedEmployee {
    private double bonusPercentage;

    public ExecutiveEmployee(int employeeId, String employeeName, String designation, double monthlySalary, double bonusPercentage) {
        super(employeeId, employeeName, designation, monthlySalary);
        setBonusPercentage(bonusPercentage);
    }

    // Setter with validation
    public void setBonusPercentage(double bonusPercentage) {
        if (bonusPercentage < 0 || bonusPercentage > 100) throw new IllegalArgumentException("Bonus percentage must be between 0 and 100.");
        this.bonusPercentage = bonusPercentage;
    }

    // Getter
    public double getBonusPercentage() {
        return bonusPercentage;
    }

    // Overridden method for calculating bonus
    @Override
    public double calculateBonus() {
        // Calculating bonus as a percentage of annual salary (assuming 12 months)
        double annualSalary = getMonthlySalary() * 12;
        return annualSalary * (bonusPercentage / 100);
    }

    // Method to display detailed employee info
    @Override
    public void displayEmployeeInfo() {
        super.displayEmployeeInfo();
        System.out.println("Bonus Percentage: " + bonusPercentage);
        System.out.println("Annual Salary: " + getMonthlySalary() * 12);
        System.out.println("Calculated Bonus: " + calculateBonus());
    }
}
