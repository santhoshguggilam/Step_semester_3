import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

abstract class Transaction {
    protected double amount;

    public Transaction(double amount) {
        this.amount = amount;
    }

    public abstract String getType();

    public abstract double calculateFinalAmount();
}

class CardTransaction extends Transaction {
    public CardTransaction(double amount) {
        super(amount);
    }

    @Override
    public String getType() {
        return "CARD";
    }

    @Override
    public double calculateFinalAmount() {
        return amount * 1.02;
    }
}

class WalletTransaction extends Transaction {
    public WalletTransaction(double amount) {
        super(amount);
    }

    @Override
    public String getType() {
        return "WALLET";
    }

    @Override
    public double calculateFinalAmount() {
        return amount * 1.01;
    }
}

class BankTransferTransaction extends Transaction {
    public BankTransferTransaction(double amount) {
        super(amount);
    }

    @Override
    public String getType() {
        return "BANKTRANSFER";
    }

    @Override
    public double calculateFinalAmount() {
        return amount;
    }
}

public class PaymentSystemFeeCalculator {

    public static void main(String[] args) {
        String sampleInput = "3\nCARD 1000\nWALLET 500\nBANKTRANSFER 2000";
        Scanner scanner = new Scanner(sampleInput);

        if (!scanner.hasNextInt()) {
            return;
        }

        int n = scanner.nextInt();
        List<Transaction> transactions = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            String type = scanner.next();
            double amount = scanner.nextDouble();

            if ("CARD".equalsIgnoreCase(type)) {
                transactions.add(new CardTransaction(amount));
            } else if ("WALLET".equalsIgnoreCase(type)) {
                transactions.add(new WalletTransaction(amount));
            } else if ("BANKTRANSFER".equalsIgnoreCase(type)) {
                transactions.add(new BankTransferTransaction(amount));
            }
        }
        scanner.close();

        double grandTotal = 0;
        for (Transaction t : transactions) {
            double finalAmount = t.calculateFinalAmount();
            grandTotal += finalAmount;
            System.out.printf("%s: %.2f%n", t.getType(), finalAmount);
        }
        System.out.printf("Total: %.2f%n", grandTotal);
    }
}
