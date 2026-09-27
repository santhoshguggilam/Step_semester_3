import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

abstract class DeliveryRequest {
    protected double weight;
    protected double distance;

    public DeliveryRequest(double weight, double distance) {
        this.weight = weight;
        this.distance = distance;
    }

    public abstract String getType();

    public abstract double calculateFee();
}

class StandardDelivery extends DeliveryRequest {
    public StandardDelivery(double weight, double distance) {
        super(weight, distance);
    }

    @Override
    public String getType() {
        return "STANDARD";
    }

    @Override
    public double calculateFee() {
        return 5.0 + (0.50 * weight) + (0.10 * distance);
    }
}

class ExpressDelivery extends DeliveryRequest {
    public ExpressDelivery(double weight, double distance) {
        super(weight, distance);
    }

    @Override
    public String getType() {
        return "EXPRESS";
    }

    @Override
    public double calculateFee() {
        return 15.0 + (1.00 * weight) + (0.20 * distance);
    }
}

class InternationalDelivery extends DeliveryRequest {
    private double customsFee;

    public InternationalDelivery(double weight, double distance, double customsFee) {
        super(weight, distance);
        this.customsFee = customsFee;
    }

    @Override
    public String getType() {
        return "INTERNATIONAL";
    }

    @Override
    public double calculateFee() {
        return 25.0 + (2.00 * weight) + (0.50 * distance) + customsFee;
    }
}

public class DeliveryFeeCalculator {

    public static void main(String[] args) {
        String sampleInput = "3\nSTANDARD 10 50\nEXPRESS 5 20\nINTERNATIONAL 20 100 30";
        Scanner scanner = new Scanner(sampleInput);

        if (!scanner.hasNextInt()) {
            return;
        }

        int n = scanner.nextInt();
        List<DeliveryRequest> requests = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            String type = scanner.next();
            double weight = scanner.nextDouble();
            double distance = scanner.nextDouble();

            if ("STANDARD".equalsIgnoreCase(type)) {
                requests.add(new StandardDelivery(weight, distance));
            } else if ("EXPRESS".equalsIgnoreCase(type)) {
                requests.add(new ExpressDelivery(weight, distance));
            } else if ("INTERNATIONAL".equalsIgnoreCase(type)) {
                double customsFee = scanner.nextDouble();
                requests.add(new InternationalDelivery(weight, distance, customsFee));
            }
        }
        scanner.close();

        double grandTotal = 0;
        for (DeliveryRequest r : requests) {
            double fee = r.calculateFee();
            grandTotal += fee;
            System.out.printf("%s: %.2f%n", r.getType(), fee);
        }
        System.out.printf("Total: %.2f%n", grandTotal);
    }
}
