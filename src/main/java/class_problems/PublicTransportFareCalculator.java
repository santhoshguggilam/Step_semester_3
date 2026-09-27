import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

abstract class TransportJourney {
    protected double distance;

    public TransportJourney(double distance) {
        this.distance = distance;
    }

    public abstract String getType();

    public abstract double calculateFare();
}

class BusJourney extends TransportJourney {
    public BusJourney(double distance) {
        super(distance);
    }

    @Override
    public String getType() {
        return "BUS";
    }

    @Override
    public double calculateFare() {
        double fare = 2.0 + (0.10 * distance);
        return Math.min(10.0, fare);
    }
}

class TrainJourney extends TransportJourney {
    public TrainJourney(double distance) {
        super(distance);
    }

    @Override
    public String getType() {
        return "TRAIN";
    }

    @Override
    public double calculateFare() {
        return 3.0 + (0.15 * distance);
    }
}

class MetroJourney extends TransportJourney {
    private double peakHourFactor;

    public MetroJourney(double distance, double peakHourFactor) {
        super(distance);
        this.peakHourFactor = peakHourFactor;
    }

    @Override
    public String getType() {
        return "METRO";
    }

    @Override
    public double calculateFare() {
        return (1.50 + 0.20 * distance) * peakHourFactor;
    }
}

public class PublicTransportFareCalculator {

    public static void main(String[] args) {
        String sampleInput = "3\nBUS 15\nTRAIN 50\nMETRO 10 1.5";
        Scanner scanner = new Scanner(sampleInput);

        if (!scanner.hasNextInt()) {
            return;
        }

        int n = scanner.nextInt();
        List<TransportJourney> journeys = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            String type = scanner.next();
            double distance = scanner.nextDouble();

            if ("BUS".equalsIgnoreCase(type)) {
                journeys.add(new BusJourney(distance));
            } else if ("TRAIN".equalsIgnoreCase(type)) {
                journeys.add(new TrainJourney(distance));
            } else if ("METRO".equalsIgnoreCase(type)) {
                double peakFactor = scanner.nextDouble();
                journeys.add(new MetroJourney(distance, peakFactor));
            }
        }
        scanner.close();

        double grandTotal = 0;
        for (TransportJourney j : journeys) {
            double fare = j.calculateFare();
            grandTotal += fare;
            System.out.printf("%s: %.2f%n", j.getType(), fare);
        }
        System.out.printf("Total: %.2f%n", grandTotal);
    }
}
