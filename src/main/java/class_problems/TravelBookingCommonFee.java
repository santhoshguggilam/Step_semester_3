import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

abstract class TravelBooking {
    protected double distanceKm;
    public static final double BOOKING_FEE = 50.0;

    public TravelBooking(double distanceKm) {
        this.distanceKm = distanceKm;
    }

    public abstract String getMode();

    protected abstract double calculateBaseFare();

    public double calculateTotalFare() {
        return calculateBaseFare() + BOOKING_FEE;
    }
}

class BusTravel extends TravelBooking {
    public BusTravel(double distanceKm) {
        super(distanceKm);
    }

    @Override
    public String getMode() {
        return "BUS";
    }

    @Override
    protected double calculateBaseFare() {
        return distanceKm * 2.0;
    }
}

class TrainTravel extends TravelBooking {
    public TrainTravel(double distanceKm) {
        super(distanceKm);
    }

    @Override
    public String getMode() {
        return "TRAIN";
    }

    @Override
    protected double calculateBaseFare() {
        return distanceKm * 1.5;
    }
}

class FlightTravel extends TravelBooking {
    public FlightTravel(double distanceKm) {
        super(distanceKm);
    }

    @Override
    public String getMode() {
        return "FLIGHT";
    }

    @Override
    protected double calculateBaseFare() {
        return 2500.0 + (distanceKm * 4.0);
    }
}

public class TravelBookingCommonFee {

    public static void main(String[] args) {
        String sampleInput = "3\nBUS 200\nTRAIN 300\nFLIGHT 500";
        Scanner scanner = new Scanner(sampleInput);

        if (!scanner.hasNextInt()) {
            return;
        }

        int n = scanner.nextInt();
        List<TravelBooking> bookings = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            String mode = scanner.next();
            double distance = scanner.nextDouble();

            if ("BUS".equalsIgnoreCase(mode)) {
                bookings.add(new BusTravel(distance));
            } else if ("TRAIN".equalsIgnoreCase(mode)) {
                bookings.add(new TrainTravel(distance));
            } else if ("FLIGHT".equalsIgnoreCase(mode)) {
                bookings.add(new FlightTravel(distance));
            }
        }
        scanner.close();

        for (TravelBooking b : bookings) {
            System.out.printf("%s: %.2f%n", b.getMode(), b.calculateTotalFare());
        }
    }
}
