public class FactorialCalculator {

    public static long calculateFactorial(int n) {
        if (n < 0) {
            return -1;
        }
        long fact = 1;
        for (int i = 2; i <= n; i++) {
            fact *= i;
        }
        return fact;
    }

    public static void main(String[] args) {
        System.out.println(calculateFactorial(5));
        System.out.println(calculateFactorial(0));
    }
}
