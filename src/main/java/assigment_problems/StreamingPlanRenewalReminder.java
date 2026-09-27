import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

abstract class StreamingPlan {
    protected String name;
    protected LocalDate startDate;

    public StreamingPlan(String name, String startDate) {
        this.name = name;
        this.startDate = LocalDate.parse(startDate);
    }

    public String getName() {
        return name;
    }

    public abstract LocalDate calculateRenewalDate();
}

class BasicPlan extends StreamingPlan {
    public BasicPlan(String name, String startDate) {
        super(name, startDate);
    }

    @Override
    public LocalDate calculateRenewalDate() {
        return startDate.plusDays(30);
    }
}

class StandardPlan extends StreamingPlan {
    public StandardPlan(String name, String startDate) {
        super(name, startDate);
    }

    @Override
    public LocalDate calculateRenewalDate() {
        return startDate.plusDays(90);
    }
}

class PremiumPlan extends StreamingPlan {
    public PremiumPlan(String name, String startDate) {
        super(name, startDate);
    }

    @Override
    public LocalDate calculateRenewalDate() {
        return startDate.plusDays(365);
    }
}

public class StreamingPlanRenewalReminder {

    public static void main(String[] args) {
        String sampleInput = "4\nBASIC Asha 2024-01-15\nSTANDARD Ravi 2024-02-01\nPREMIUM Neha 2024-03-10\nBASIC Kiran 2024-12-20";
        Scanner scanner = new Scanner(sampleInput);

        if (!scanner.hasNextInt()) {
            return;
        }

        int n = scanner.nextInt();
        List<StreamingPlan> subscribers = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            String planType = scanner.next();
            String name = scanner.next();
            String startDate = scanner.next();

            if ("BASIC".equalsIgnoreCase(planType)) {
                subscribers.add(new BasicPlan(name, startDate));
            } else if ("STANDARD".equalsIgnoreCase(planType)) {
                subscribers.add(new StandardPlan(name, startDate));
            } else if ("PREMIUM".equalsIgnoreCase(planType)) {
                subscribers.add(new PremiumPlan(name, startDate));
            }
        }
        scanner.close();

        for (StreamingPlan sub : subscribers) {
            LocalDate renewalDate = sub.calculateRenewalDate();
            System.out.println(sub.getName() + ": " + renewalDate);
        }
    }
}
