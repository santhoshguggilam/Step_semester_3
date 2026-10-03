import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

interface Insurable {
    double calculateInsurance();
}

abstract class Parcel {
    protected double weightKg;
    protected double declaredValue;

    public Parcel(double weightKg, double declaredValue) {
        this.weightKg = weightKg;
        this.declaredValue = declaredValue;
    }

    public abstract String getType();

    public abstract double calculateShippingCharge();
}

class StandardParcel extends Parcel {
    public StandardParcel(double weightKg, double declaredValue) {
        super(weightKg, declaredValue);
    }

    @Override
    public String getType() {
        return "STANDARD";
    }

    @Override
    public double calculateShippingCharge() {
        return 40.0 + (10.0 * weightKg);
    }
}

class ExpressParcel extends Parcel implements Insurable {
    public ExpressParcel(double weightKg, double declaredValue) {
        super(weightKg, declaredValue);
    }

    @Override
    public String getType() {
        return "EXPRESS";
    }

    @Override
    public double calculateShippingCharge() {
        return 80.0 + (15.0 * weightKg);
    }

    @Override
    public double calculateInsurance() {
        return 0.02 * declaredValue;
    }
}

class FragileParcel extends Parcel implements Insurable {
    public FragileParcel(double weightKg, double declaredValue) {
        super(weightKg, declaredValue);
    }

    @Override
    public String getType() {
        return "FRAGILE";
    }

    @Override
    public double calculateShippingCharge() {
        return 40.0 + (10.0 * weightKg) + 50.0;
    }

    @Override
    public double calculateInsurance() {
        return 0.02 * declaredValue;
    }
}

public class ParcelShippingDesk {

    public static void main(String[] args) {
        String sampleInput = "3\nSTANDARD 3 500\nEXPRESS 2 1000\nFRAGILE 4 2000";
        Scanner scanner = new Scanner(sampleInput);

        if (!scanner.hasNextInt()) {
            return;
        }

        int n = scanner.nextInt();
        List<Parcel> parcels = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            String type = scanner.next();
            double weight = scanner.nextDouble();
            double declaredVal = scanner.nextDouble();

            if ("STANDARD".equalsIgnoreCase(type)) {
                parcels.add(new StandardParcel(weight, declaredVal));
            } else if ("EXPRESS".equalsIgnoreCase(type)) {
                parcels.add(new ExpressParcel(weight, declaredVal));
            } else if ("FRAGILE".equalsIgnoreCase(type)) {
                parcels.add(new FragileParcel(weight, declaredVal));
            }
        }
        scanner.close();

        double grandTotal = 0;
        for (Parcel p : parcels) {
            double charge = p.calculateShippingCharge();
            double insurance = 0.0;
            if (p instanceof Insurable) {
                insurance = ((Insurable) p).calculateInsurance();
            }
            double total = charge + insurance;
            grandTotal += total;

            System.out.printf("%s: Charge=%.2f Insurance=%.2f Total=%.2f%n",
                    p.getType(), charge, insurance, total);
        }
        System.out.printf("Grand Total: %.2f%n", grandTotal);
    }
}
