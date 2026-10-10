public class LeapYearDetector {

    public static void checkLeapYear(int year) {
        boolean isLeap = (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
        if (isLeap) {
            System.out.println("Leap year");
        } else {
            System.out.println("Not a leap year");
        }
    }

    public static void main(String[] args) {
        checkLeapYear(2024);
        checkLeapYear(1900);
        checkLeapYear(2000);
    }
}
