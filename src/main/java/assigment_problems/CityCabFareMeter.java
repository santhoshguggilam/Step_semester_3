import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

interface NightServiceable {
    default double applyNightSurcharge(double fare) {
        return fare * 1.20;
    }
}

abstract class CityCab {
    protected double distanceKm;
    public static final double MINIMUM_FARE = 100.0;

    public CityCab(double distanceKm) {
        this.distanceKm = distanceKm;
    }

    public abstract String getCabType();

    protected abstract double getRatePerKm();

    public double calculateBaseFare() {
        double rawFare = distanceKm * getRatePerKm();
        return Math.max(MINIMUM_FARE, rawFare);
    }
}

class MiniCab extends CityCab {
    public MiniCab(double distanceKm) {
        super(distanceKm);
    }

    @Override
    public String getCabType() {
        return "MINI";
    }

    @Override
    protected double getRatePerKm() {
        return 10.0;
    }
}

class SedanCab extends CityCab implements NightServiceable {
    public SedanCab(double distanceKm) {
        super(distanceKm);
    }

    @Override
    public String getCabType() {
        return "SEDAN";
    }

    @Override
    protected double getRatePerKm() {
        return 14.0;
    }
}

class SUVCab extends CityCab implements NightServiceable {
    public SUVCab(double distanceKm) {
        super(distanceKm);
    }

    @Override
    public String getCabType() {
        return "SUV";
    }

    @Override
    protected double getRatePerKm() {
        return 18.0;
    }
}

class CabTrip {
    CityCab cab;
    boolean isNight;

    public CabTrip(CityCab cab, boolean isNight) {
        this.cab = cab;
        this.isNight = isNight;
    }
}

public class CityCabFareMeter {

    public static void main(String[] args) {
        String sampleInput = "4\nMINI 8 DAY\nSEDAN 10 NIGHT\nSUV 20 DAY\nMINI 5 NIGHT";
        Scanner scanner = new Scanner(sampleInput);

        if (!scanner.hasNextInt()) {
            return;
        }

        int n = scanner.nextInt();
        List<CabTrip> trips = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            String cabType = scanner.next();
            double km = scanner.nextDouble();
            String time = scanner.next();

            CityCab cab = null;
            if ("MINI".equalsIgnoreCase(cabType)) {
                cab = new MiniCab(km);
            } else if ("SEDAN".equalsIgnoreCase(cabType)) {
                cab = new SedanCab(km);
            } else if ("SUV".equalsIgnoreCase(cabType)) {
                cab = new SUVCab(km);
            }

            if (cab != null) {
                trips.add(new CabTrip(cab, "NIGHT".equalsIgnoreCase(time)));
            }
        }
        scanner.close();

        double totalFare = 0;
        for (CabTrip trip : trips) {
            CityCab cab = trip.cab;
            if (trip.isNight) {
                if (cab instanceof NightServiceable) {
                    double baseFare = cab.calculateBaseFare();
                    double finalFare = ((NightServiceable) cab).applyNightSurcharge(baseFare);
                    totalFare += finalFare;
                    System.out.printf("%s: %.2f%n", cab.getCabType(), finalFare);
                } else {
                    System.out.printf("%s: night service not available%n", cab.getCabType());
                }
            } else {
                double fare = cab.calculateBaseFare();
                totalFare += fare;
                System.out.printf("%s: %.2f%n", cab.getCabType(), fare);
            }
        }
        System.out.printf("Total: %.2f%n", totalFare);
    }
}
