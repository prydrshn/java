// Interface Declaration
interface BankInterface {
    double getBalance();
    double getInterestRate();
}

// Implementation of BankA
class BankA implements BankInterface {
    private double balance;

    public BankA(double deposit) {
        this.balance = deposit;
    }

    @Override
    public double getBalance() {
        return balance;
    }

    @Override
    public double getInterestRate() {
        return 7.0;
    }
}

// Implementation of BankB
class BankB implements BankInterface {
    private double balance;

    public BankB(double deposit) {
        this.balance = deposit;
    }

    @Override
    public double getBalance() {
        return balance;
    }

    @Override
    public double getInterestRate() {
        return 7.4;
    }
}

// Implementation of BankC
class BankC implements BankInterface {
    private double balance;

    public BankC(double deposit) {
        this.balance = deposit;
    }

    @Override
    public double getBalance() {
        return balance;
    }

    @Override
    public double getInterestRate() {
        return 7.9;
    }
}

// Main Class
public class BankMain {
    public static void main(String[] args) {
        // Create objects for each bank
        BankA bankA = new BankA(10000);
        BankB bankB = new BankB(150000);
        BankC bankC = new BankC(200000);

        // Display the details
        System.out.println("Bank A - Balance: " + bankA.getBalance() + ", Interest Rate: " + bankA.getInterestRate() + "%");
        System.out.println("Bank B - Balance: " + bankB.getBalance() + ", Interest Rate: " + bankB.getInterestRate() + "%");
        System.out.println("Bank C - Balance: " + bankC.getBalance() + ", Interest Rate: " + bankC.getInterestRate() + "%");
    }
}
