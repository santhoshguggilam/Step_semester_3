import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

abstract class CompanyEmployee {
    protected String name;
    protected double monthlySalary;

    public CompanyEmployee(String name, double monthlySalary) {
        this.name = name;
        this.monthlySalary = monthlySalary;
    }

    public String getName() {
        return name;
    }

    public abstract double calculateBonus();
}

class FullTimeEmployee extends CompanyEmployee {
    public FullTimeEmployee(String name, double monthlySalary) {
        super(name, monthlySalary);
    }

    @Override
    public double calculateBonus() {
        return monthlySalary * 0.10;
    }
}

class PartTimeEmployee extends CompanyEmployee {
    public PartTimeEmployee(String name, double monthlySalary) {
        super(name, monthlySalary);
    }

    @Override
    public double calculateBonus() {
        return monthlySalary * 0.05;
    }
}

class InternEmployee extends CompanyEmployee {
    public InternEmployee(String name, double monthlySalary) {
        super(name, monthlySalary);
    }

    @Override
    public double calculateBonus() {
        return 2000.0;
    }
}

public class FestivalBonusCalculator {

    public static void main(String[] args) {
        String sampleInput = "3\nFULLTIME Asha 50000\nPARTTIME Ravi 30000\nINTERN Neha 15000";
        Scanner scanner = new Scanner(sampleInput);

        if (!scanner.hasNextInt()) {
            return;
        }

        int n = scanner.nextInt();
        List<CompanyEmployee> employees = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            String type = scanner.next();
            String name = scanner.next();
            double salary = scanner.nextDouble();

            if ("FULLTIME".equalsIgnoreCase(type)) {
                employees.add(new FullTimeEmployee(name, salary));
            } else if ("PARTTIME".equalsIgnoreCase(type)) {
                employees.add(new PartTimeEmployee(name, salary));
            } else if ("INTERN".equalsIgnoreCase(type)) {
                employees.add(new InternEmployee(name, salary));
            }
        }
        scanner.close();

        double grandTotal = 0;
        for (CompanyEmployee e : employees) {
            double bonus = e.calculateBonus();
            grandTotal += bonus;
            System.out.printf("%s: %.2f%n", e.getName(), bonus);
        }
        System.out.printf("Total Bonus: %.2f%n", grandTotal);
    }
}
