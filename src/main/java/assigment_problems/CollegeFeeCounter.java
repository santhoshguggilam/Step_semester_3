import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

interface BusTransportUser {
    default double getTransportFee() {
        return 12000.0;
    }
}

abstract class StudentFee {
    protected String name;
    public static final double NORMAL_TUITION = 40000.0;

    public StudentFee(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public abstract double calculateTotalFee();
}

class DayScholarStudent extends StudentFee implements BusTransportUser {
    public DayScholarStudent(String name) {
        super(name);
    }

    @Override
    public double calculateTotalFee() {
        return NORMAL_TUITION + getTransportFee();
    }
}

class HostellerStudent extends StudentFee {
    private static final double HOSTEL_FEE = 60000.0;

    public HostellerStudent(String name) {
        super(name);
    }

    @Override
    public double calculateTotalFee() {
        return NORMAL_TUITION + HOSTEL_FEE;
    }
}

class ScholarshipStudent extends StudentFee implements BusTransportUser {
    public ScholarshipStudent(String name) {
        super(name);
    }

    @Override
    public double calculateTotalFee() {
        return (NORMAL_TUITION / 2.0) + getTransportFee();
    }
}

public class CollegeFeeCounter {

    public static void main(String[] args) {
        String sampleInput = "3\nDAY_SCHOLAR Asha\nHOSTELLER Ravi\nSCHOLAR Neha";
        Scanner scanner = new Scanner(sampleInput);

        if (!scanner.hasNextInt()) {
            return;
        }

        int n = scanner.nextInt();
        List<StudentFee> students = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            String type = scanner.next();
            String name = scanner.next();

            if ("DAY_SCHOLAR".equalsIgnoreCase(type)) {
                students.add(new DayScholarStudent(name));
            } else if ("HOSTELLER".equalsIgnoreCase(type)) {
                students.add(new HostellerStudent(name));
            } else if ("SCHOLAR".equalsIgnoreCase(type)) {
                students.add(new ScholarshipStudent(name));
            }
        }
        scanner.close();

        double totalCollected = 0;
        for (StudentFee s : students) {
            double fee = s.calculateTotalFee();
            totalCollected += fee;
            System.out.printf("%s: %.2f%n", s.getName(), fee);
        }
        System.out.printf("Total Collected: %.2f%n", totalCollected);
    }
}
