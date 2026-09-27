import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

abstract class ParkedVehicle {
    protected int hours;

    public ParkedVehicle(int hours) {
        this.hours = hours;
    }

    public abstract String getType();

    public abstract double calculateCharge();
}

class BikeVehicle extends ParkedVehicle {
    public BikeVehicle(int hours) {
        super(hours);
    }

    @Override
    public String getType() {
        return "BIKE";
    }

    @Override
    public double calculateCharge() {
        return hours * 10.0;
    }
}

class CarVehicle extends ParkedVehicle {
    public CarVehicle(int hours) {
        super(hours);
    }

    @Override
    public String getType() {
        return "CAR";
    }

    @Override
    public double calculateCharge() {
        if (hours <= 0) {
            return 0.0;
        }
        return 30.0 + (hours - 1) * 20.0;
    }
}

class TruckVehicle extends ParkedVehicle {
    public TruckVehicle(int hours) {
        super(hours);
    }

    @Override
    public String getType() {
        return "TRUCK";
    }

    @Override
    public double calculateCharge() {
        double charge = hours * 50.0;
        return Math.max(100.0, charge);
    }
}

public class CampusParkingChargeCalculator {

    public static void main(String[] args) {
        String sampleInput = "4\nBIKE 3\nCAR 4\nTRUCK 1\nCAR 1";
        Scanner scanner = new Scanner(sampleInput);

        if (!scanner.hasNextInt()) {
            return;
        }

        int n = scanner.nextInt();
        List<ParkedVehicle> vehicles = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            String type = scanner.next();
            int hours = scanner.nextInt();

            if ("BIKE".equalsIgnoreCase(type)) {
                vehicles.add(new BikeVehicle(hours));
            } else if ("CAR".equalsIgnoreCase(type)) {
                vehicles.add(new CarVehicle(hours));
            } else if ("TRUCK".equalsIgnoreCase(type)) {
                vehicles.add(new TruckVehicle(hours));
            }
        }
        scanner.close();

        double grandTotal = 0;
        for (ParkedVehicle v : vehicles) {
            double charge = v.calculateCharge();
            grandTotal += charge;
            System.out.printf("%s: %.2f%n", v.getType(), charge);
        }
        System.out.printf("Total: %.2f%n", grandTotal);
    }
}
