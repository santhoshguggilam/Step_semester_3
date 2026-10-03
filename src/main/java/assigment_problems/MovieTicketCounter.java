import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

abstract class MovieTicket {
    protected int count;
    public static final double CONVENIENCE_FEE = 20.0;

    public MovieTicket(int count) {
        this.count = count;
    }

    public abstract String getSeatType();

    protected abstract double getBasePrice();

    public double calculateTotal() {
        return (getBasePrice() + CONVENIENCE_FEE) * count;
    }
}

class RegularTicket extends MovieTicket {
    public RegularTicket(int count) {
        super(count);
    }

    @Override
    public String getSeatType() {
        return "REGULAR";
    }

    @Override
    protected double getBasePrice() {
        return 150.0;
    }
}

class PremiumTicket extends MovieTicket {
    public PremiumTicket(int count) {
        super(count);
    }

    @Override
    public String getSeatType() {
        return "PREMIUM";
    }

    @Override
    protected double getBasePrice() {
        return 250.0;
    }
}

class ReclinerTicket extends MovieTicket {
    public ReclinerTicket(int count) {
        super(count);
    }

    @Override
    public String getSeatType() {
        return "RECLINER";
    }

    @Override
    protected double getBasePrice() {
        return 400.0;
    }
}

public class MovieTicketCounter {

    public static void main(String[] args) {
        String sampleInput = "3\nREGULAR 3\nPREMIUM 2\nRECLINER 1";
        Scanner scanner = new Scanner(sampleInput);

        if (!scanner.hasNextInt()) {
            return;
        }

        int n = scanner.nextInt();
        List<MovieTicket> tickets = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            String seat = scanner.next();
            int count = scanner.nextInt();

            if ("REGULAR".equalsIgnoreCase(seat)) {
                tickets.add(new RegularTicket(count));
            } else if ("PREMIUM".equalsIgnoreCase(seat)) {
                tickets.add(new PremiumTicket(count));
            } else if ("RECLINER".equalsIgnoreCase(seat)) {
                tickets.add(new ReclinerTicket(count));
            }
        }
        scanner.close();

        double total = 0;
        for (MovieTicket t : tickets) {
            double amount = t.calculateTotal();
            total += amount;
            System.out.printf("%s: %.2f%n", t.getSeatType(), amount);
        }
        System.out.printf("Total: %.2f%n", total);
    }
}
