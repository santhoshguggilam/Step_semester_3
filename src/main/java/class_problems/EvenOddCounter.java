public class EvenOddCounter {

    public static void countEvenOdd(int[] numbers) {
        if (numbers == null) {
            return;
        }

        int even = 0;
        int odd = 0;

        for (int num : numbers) {
            if (num % 2 == 0) {
                even++;
            } else {
                odd++;
            }
        }

        System.out.println("Even: " + even);
        System.out.println("Odd: " + odd);
    }

    public static void main(String[] args) {
        int[] numbers = {3, 8, 12, 5, 7, 10};
        countEvenOdd(numbers);
    }
}
