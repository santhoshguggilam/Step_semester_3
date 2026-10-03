import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

abstract class StaffMember {
    protected String name;

    public StaffMember(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public abstract double calculatePay();
}

class FullTimeStaff extends StaffMember {
    private double weeklySalary;

    public FullTimeStaff(String name, double weeklySalary) {
        super(name);
        this.weeklySalary = weeklySalary;
    }

    @Override
    public double calculatePay() {
        return weeklySalary;
    }
}

class HourlyStaff extends StaffMember {
    private double hours;
    private double rate;

    public HourlyStaff(String name, double hours, double rate) {
        super(name);
        this.hours = hours;
        this.rate = rate;
    }

    @Override
    public double calculatePay() {
        if (hours <= 40) {
            return hours * rate;
        } else {
            return (40 * rate) + ((hours - 40) * rate * 1.5);
        }
    }
}

class InternStaff extends StaffMember {
    private double stipend;

    public InternStaff(String name, double stipend) {
        super(name);
        this.stipend = stipend;
    }

    @Override
    public double calculatePay() {
        return stipend;
    }
}

public class WeeklyStaffPay {

    public static void main(String[] args) {
        String sampleInput = "3\nFULLTIME Asha 12000\nHOURLY Ravi 45 200\nINTERN Neha 5000";
        Scanner scanner = new Scanner(sampleInput);

        if (!scanner.hasNextInt()) {
            return;
        }

        int n = scanner.nextInt();
        List<StaffMember> staffList = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            String type = scanner.next();
            String name = scanner.next();

            if ("FULLTIME".equalsIgnoreCase(type)) {
                double salary = scanner.nextDouble();
                staffList.add(new FullTimeStaff(name, salary));
            } else if ("HOURLY".equalsIgnoreCase(type)) {
                double hours = scanner.nextDouble();
                double rate = scanner.nextDouble();
                staffList.add(new HourlyStaff(name, hours, rate));
            } else if ("INTERN".equalsIgnoreCase(type)) {
                double stipend = scanner.nextDouble();
                staffList.add(new InternStaff(name, stipend));
            }
        }
        scanner.close();

        double totalPayroll = 0;
        for (StaffMember s : staffList) {
            double pay = s.calculatePay();
            totalPayroll += pay;
            System.out.printf("%s: %.2f%n", s.getName(), pay);
        }
        System.out.printf("Total Payroll: %.2f%n", totalPayroll);
    }
}
