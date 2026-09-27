import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

abstract class HostelRoom {
    protected int units;

    public HostelRoom(int units) {
        this.units = units;
    }

    public abstract String getType();

    public abstract double calculateBill();
}

class SingleRoom extends HostelRoom {
    public SingleRoom(int units) {
        super(units);
    }

    @Override
    public String getType() {
        return "SINGLE";
    }

    @Override
    public double calculateBill() {
        return units * 8.0;
    }
}

class SharedRoom extends HostelRoom {
    private int occupants;

    public SharedRoom(int units, int occupants) {
        super(units);
        this.occupants = occupants;
    }

    @Override
    public String getType() {
        return "SHARED";
    }

    @Override
    public double calculateBill() {
        if (occupants <= 0) {
            return 0.0;
        }
        return (units * 6.0) / occupants;
    }
}

class ACRoom extends HostelRoom {
    public ACRoom(int units) {
        super(units);
    }

    @Override
    public String getType() {
        return "AC";
    }

    @Override
    public double calculateBill() {
        return (units * 10.0) + 200.0;
    }
}

public class HostelElectricityBill {

    public static void main(String[] args) {
        String sampleInput = "3\nSINGLE 120\nSHARED 150 3\nAC 100";
        Scanner scanner = new Scanner(sampleInput);

        if (!scanner.hasNextInt()) {
            return;
        }

        int n = scanner.nextInt();
        List<HostelRoom> rooms = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            String type = scanner.next();
            int units = scanner.nextInt();

            if ("SINGLE".equalsIgnoreCase(type)) {
                rooms.add(new SingleRoom(units));
            } else if ("SHARED".equalsIgnoreCase(type)) {
                int occupants = scanner.nextInt();
                rooms.add(new SharedRoom(units, occupants));
            } else if ("AC".equalsIgnoreCase(type)) {
                rooms.add(new ACRoom(units));
            }
        }
        scanner.close();

        double grandTotal = 0;
        for (HostelRoom r : rooms) {
            double bill = r.calculateBill();
            grandTotal += bill;
            System.out.printf("%s: %.2f%n", r.getType(), bill);
        }
        System.out.printf("Total: %.2f%n", grandTotal);
    }
}
