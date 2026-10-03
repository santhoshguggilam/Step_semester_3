import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

abstract class BorrowedItem {
    protected String title;
    protected int daysLate;

    public BorrowedItem(String title, int daysLate) {
        this.title = title;
        this.daysLate = daysLate;
    }

    public String getTitle() {
        return title;
    }

    public abstract double calculateFine();
}

class BookItem extends BorrowedItem {
    public BookItem(String title, int daysLate) {
        super(title, daysLate);
    }

    @Override
    public double calculateFine() {
        return daysLate * 2.0;
    }
}

class DVDItem extends BorrowedItem {
    public DVDItem(String title, int daysLate) {
        super(title, daysLate);
    }

    @Override
    public double calculateFine() {
        return Math.min(50.0, daysLate * 5.0);
    }
}

class MagazineItem extends BorrowedItem {
    public MagazineItem(String title, int daysLate) {
        super(title, daysLate);
    }

    @Override
    public double calculateFine() {
        return daysLate * 1.0;
    }
}

public class LibraryLateFineCounter {

    public static void main(String[] args) {
        String sampleInput = "3\nBOOK Algebra 4\nDVD Inception 12\nMAGAZINE Sports 3";
        Scanner scanner = new Scanner(sampleInput);

        if (!scanner.hasNextInt()) {
            return;
        }

        int n = scanner.nextInt();
        List<BorrowedItem> items = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            String type = scanner.next();
            String title = scanner.next();
            int daysLate = scanner.nextInt();

            if ("BOOK".equalsIgnoreCase(type)) {
                items.add(new BookItem(title, daysLate));
            } else if ("DVD".equalsIgnoreCase(type)) {
                items.add(new DVDItem(title, daysLate));
            } else if ("MAGAZINE".equalsIgnoreCase(type)) {
                items.add(new MagazineItem(title, daysLate));
            }
        }
        scanner.close();

        double totalFines = 0;
        for (BorrowedItem item : items) {
            double fine = item.calculateFine();
            totalFines += fine;
            System.out.printf("%s: %.2f%n", item.getTitle(), fine);
        }
        System.out.printf("Total Fines: %.2f%n", totalFines);
    }
}
