import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

abstract class ElectricityConnection {
    protected double units;

    public ElectricityConnection(double units) {
        this.units = units;
    }

    public abstract String getType();

    public abstract double calculateBill();
}

class HomeConnection extends ElectricityConnection {
    public HomeConnection(double units) {
        super(units);
    }

    @Override
    public String getType() {
        return "HOME";
    }

    @Override
    public double calculateBill() {
        if (units <= 100) {
            return units * 5.0;
        } else {
            return (100 * 5.0) + ((units - 100) * 7.0);
        }
    }
}

class ShopConnection extends ElectricityConnection {
    public ShopConnection(double units) {
        super(units);
    }

    @Override
    public String getType() {
        return "SHOP";
    }

    @Override
    public double calculateBill() {
        return (units * 8.0) + 100.0;
    }
}

class FactoryConnection extends ElectricityConnection {
    public FactoryConnection(double units) {
        super(units);
    }

    @Override
    public String getType() {
        return "FACTORY";
    }

    @Override
    public double calculateBill() {
        return Math.max(1000.0, units * 6.0);
    }
}

public class ElectricityConnectionBilling {

    public static void main(String[] args) {
        String sampleInput = "3\nHOME 150\nSHOP 90\nFACTORY 120";
        Scanner scanner = new Scanner(sampleInput);

        if (!scanner.hasNextInt()) {
            return;
        }

        int n = scanner.nextInt();
        List<ElectricityConnection> connections = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            String type = scanner.next();
            double units = scanner.nextDouble();

            if ("HOME".equalsIgnoreCase(type)) {
                connections.add(new HomeConnection(units));
            } else if ("SHOP".equalsIgnoreCase(type)) {
                connections.add(new ShopConnection(units));
            } else if ("FACTORY".equalsIgnoreCase(type)) {
                connections.add(new FactoryConnection(units));
            }
        }
        scanner.close();

        double totalBilled = 0;
        for (ElectricityConnection conn : connections) {
            double bill = conn.calculateBill();
            totalBilled += bill;
            System.out.printf("%s: %.2f%n", conn.getType(), bill);
        }
        System.out.printf("Total: %.2f%n", totalBilled);
    }
}
