public class DigitSumReversal {

    public static void processNumber(int number) {
        int temp = Math.abs(number);
        int sum = 0;
        int reversed = 0;

        while (temp > 0) {
            int digit = temp % 10;
            sum += digit;
            reversed = reversed * 10 + digit;
            temp /= 10;
        }

        System.out.println("Sum of digits: " + sum);
        System.out.println("Reverse: " + reversed);
    }

    public static void main(String[] args) {
        processNumber(4721);
    }
}
