import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

abstract class GardenPlot {
    protected String owner;

    public GardenPlot(String owner) {
        this.owner = owner;
    }

    public String getOwner() {
        return owner;
    }

    public abstract String getShape();

    public abstract double calculateArea();
}

class CirclePlot extends GardenPlot {
    private double radius;

    public CirclePlot(String owner, double radius) {
        super(owner);
        this.radius = radius;
    }

    @Override
    public String getShape() {
        return "CIRCLE";
    }

    @Override
    public double calculateArea() {
        return Math.PI * radius * radius;
    }
}

class RectanglePlot extends GardenPlot {
    private double length;
    private double width;

    public RectanglePlot(String owner, double length, double width) {
        super(owner);
        this.length = length;
        this.width = width;
    }

    @Override
    public String getShape() {
        return "RECTANGLE";
    }

    @Override
    public double calculateArea() {
        return length * width;
    }
}

class TrianglePlot extends GardenPlot {
    private double base;
    private double height;

    public TrianglePlot(String owner, double base, double height) {
        super(owner);
        this.base = base;
        this.height = height;
    }

    @Override
    public String getShape() {
        return "TRIANGLE";
    }

    @Override
    public double calculateArea() {
        return 0.5 * base * height;
    }
}

public class GardenPlotAreaReport {

    public static void main(String[] args) {
        String sampleInput = "3\nCIRCLE Asha 5\nRECTANGLE Ravi 4 6\nTRIANGLE Neha 10 3";
        Scanner scanner = new Scanner(sampleInput);

        if (!scanner.hasNextInt()) {
            return;
        }

        int n = scanner.nextInt();
        List<GardenPlot> plots = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            String shape = scanner.next();
            String owner = scanner.next();

            if ("CIRCLE".equalsIgnoreCase(shape)) {
                double radius = scanner.nextDouble();
                plots.add(new CirclePlot(owner, radius));
            } else if ("RECTANGLE".equalsIgnoreCase(shape)) {
                double length = scanner.nextDouble();
                double width = scanner.nextDouble();
                plots.add(new RectanglePlot(owner, length, width));
            } else if ("TRIANGLE".equalsIgnoreCase(shape)) {
                double base = scanner.nextDouble();
                double height = scanner.nextDouble();
                plots.add(new TrianglePlot(owner, base, height));
            }
        }
        scanner.close();

        double totalArea = 0;
        for (GardenPlot p : plots) {
            double area = p.calculateArea();
            totalArea += area;
            System.out.printf("%s (%s): %.2f%n", p.getOwner(), p.getShape(), area);
        }
        System.out.printf("Total Area: %.2f%n", totalArea);
    }
}
