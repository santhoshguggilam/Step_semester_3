public class PiggyBank {

    private final String id;
    private double savings;

    public PiggyBank(String id) {
        this.id = id;
        this.savings = 0;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            savings += amount;
        }
    }

    public void withdraw(double amount) {
        if (amount > savings) {
            System.out.println("rejected, savings stays " + (int) savings);
        } else {
            savings -= amount;
        }
    }

    public double getSavings() {
        return savings;
    }

    public String getId() {
        return id;
    }

    public static void main(String[] args) {
        PiggyBank pb = new PiggyBank("PB-1");
        pb.deposit(100);
        System.out.println("pb.deposit(100) -> savings = " + (int) pb.getSavings());
        pb.withdraw(30);
        System.out.println("pb.withdraw(30) -> savings = " + (int) pb.getSavings());
        System.out.print("pb.withdraw(500) -> ");
        pb.withdraw(500);
    }
}
