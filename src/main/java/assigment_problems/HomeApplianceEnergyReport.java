import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

interface SaverModeSupport {
    default double applySaverReduction(double rawUnits) {
        return rawUnits * 0.75;
    }
}

abstract class HomeAppliance {
    protected double hours;
    public static final double COST_PER_UNIT = 8.0;

    public HomeAppliance(double hours) {
        this.hours = hours;
    }

    public abstract String getApplianceName();

    public abstract double getPowerRatingWatts();

    public double calculateRawUnits() {
        return (getPowerRatingWatts() * hours) / 1000.0;
    }
}

class FridgeAppliance extends HomeAppliance {
    public FridgeAppliance(double hours) {
        super(hours);
    }

    @Override
    public String getApplianceName() {
        return "FRIDGE";
    }

    @Override
    public double getPowerRatingWatts() {
        return 150.0;
    }
}

class ACAppliance extends HomeAppliance implements SaverModeSupport {
    public ACAppliance(double hours) {
        super(hours);
    }

    @Override
    public String getApplianceName() {
        return "AC";
    }

    @Override
    public double getPowerRatingWatts() {
        return 1500.0;
    }
}

class TVAppliance extends HomeAppliance {
    public TVAppliance(double hours) {
        super(hours);
    }

    @Override
    public String getApplianceName() {
        return "TV";
    }

    @Override
    public double getPowerRatingWatts() {
        return 100.0;
    }
}

class WasherAppliance extends HomeAppliance implements SaverModeSupport {
    public WasherAppliance(double hours) {
        super(hours);
    }

    @Override
    public String getApplianceName() {
        return "WASHER";
    }

    @Override
    public double getPowerRatingWatts() {
        return 500.0;
    }
}

class ApplianceRequest {
    HomeAppliance appliance;
    boolean saverMode;

    public ApplianceRequest(HomeAppliance appliance, boolean saverMode) {
        this.appliance = appliance;
        this.saverMode = saverMode;
    }
}

public class HomeApplianceEnergyReport {

    public static void main(String[] args) {
        String sampleInput = "4\nFRIDGE 24\nAC 8 SAVER\nTV 5\nWASHER 2 SAVER";
        Scanner scanner = new Scanner(sampleInput);

        if (!scanner.hasNextInt()) {
            return;
        }

        int n = scanner.nextInt();
        List<ApplianceRequest> requests = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            String name = scanner.next();
            double hours = scanner.nextDouble();
            boolean saver = false;
            if (scanner.hasNext("SAVER")) {
                scanner.next();
                saver = true;
            }

            HomeAppliance app = null;
            if ("FRIDGE".equalsIgnoreCase(name)) {
                app = new FridgeAppliance(hours);
            } else if ("AC".equalsIgnoreCase(name)) {
                app = new ACAppliance(hours);
            } else if ("TV".equalsIgnoreCase(name)) {
                app = new TVAppliance(hours);
            } else if ("WASHER".equalsIgnoreCase(name)) {
                app = new WasherAppliance(hours);
            }

            if (app != null) {
                requests.add(new ApplianceRequest(app, saver));
            }
        }
        scanner.close();

        double totalCost = 0;
        for (ApplianceRequest req : requests) {
            HomeAppliance app = req.appliance;
            if (req.saverMode) {
                if (app instanceof SaverModeSupport) {
                    double rawUnits = app.calculateRawUnits();
                    double units = ((SaverModeSupport) app).applySaverReduction(rawUnits);
                    double cost = units * HomeAppliance.COST_PER_UNIT;
                    totalCost += cost;
                    System.out.printf("%s: Units=%.2f Cost=%.2f%n", app.getApplianceName(), units, cost);
                } else {
                    System.out.printf("%s: saver mode not supported%n", app.getApplianceName());
                }
            } else {
                double units = app.calculateRawUnits();
                double cost = units * HomeAppliance.COST_PER_UNIT;
                totalCost += cost;
                System.out.printf("%s: Units=%.2f Cost=%.2f%n", app.getApplianceName(), units, cost);
            }
        }
        System.out.printf("Total Cost: %.2f%n", totalCost);
    }
}
