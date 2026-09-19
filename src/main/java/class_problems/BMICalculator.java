import java.io.IOException;
import java.util.Scanner;

public class BMICalculator {

    public static String getBmiStatus(double bmi) {
        if (bmi < 18.5) {
            return "Underweight";
        } else if (bmi <= 24.9) {
            return "Normal";
        } else if (bmi <= 29.9) {
            return "Overweight";
        } else {
            return "Obese";
        }
    }

    public static void printWellnessReport(double[] heights, double[] weights) {
        System.out.println("==========================================================================");
        System.out.printf("%-10s | %-12s | %-12s | %-10s | %-15s%n", "Person", "Height (m)", "Weight (kg)", "BMI", "Status");
        System.out.println("==========================================================================");

        for (int i = 0; i < heights.length; i++) {
            double bmi = weights[i] / (heights[i] * heights[i]);
            String status = getBmiStatus(bmi);
            System.out.printf("Person %-3d | %-12.2f | %-12.2f | %-10.2f | %-15s%n", (i + 1), heights[i], weights[i], bmi, status);
        }
        System.out.println("==========================================================================");
    }

    public static void main(String[] args) {
        double[] heights = {1.75, 1.60, 1.82, 1.68, 1.55, 1.78, 1.65, 1.90, 1.72, 1.58};
        double[] weights = {70.0, 90.0, 68.0, 82.0, 43.0, 75.0, 64.0, 95.0, 52.0, 80.0};

        try {
            if (System.in.available() > 0) {
                Scanner scanner = new Scanner(System.in);
                if (scanner.hasNextInt()) {
                    int n = scanner.nextInt();
                    if (n > 0) {
                        heights = new double[n];
                        weights = new double[n];
                        for (int i = 0; i < n; i++) {
                            heights[i] = scanner.nextDouble();
                            weights[i] = scanner.nextDouble();
                        }
                    }
                }
                scanner.close();
            }
        } catch (IOException e) {
            // Default demo data used
        }

        System.out.println("Sample Individual Outputs:");
        for (int i = 0; i < Math.min(2, heights.length); i++) {
            double bmi = weights[i] / (heights[i] * heights[i]);
            System.out.printf("Person %d - Height: %.2f m, Weight: %.2f kg -> BMI: %.2f | Status: %s%n",
                    (i + 1), heights[i], weights[i], bmi, getBmiStatus(bmi));
        }

        System.out.println("\nFull Corporate Wellness Report (Team of " + heights.length + "):");
        printWellnessReport(heights, weights);
    }
}