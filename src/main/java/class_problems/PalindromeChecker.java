import java.util.Scanner;

public class PalindromeChecker {

    public static boolean isPalindromeIterative(String text) {
        int left = 0;
        int right = text.length() - 1;

        while (left < right) {
            if (text.charAt(left) != text.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    public static boolean isPalindromeRecursive(String text) {
        if (text.length() <= 1) {
            return true;
        }
        if (text.charAt(0) != text.charAt(text.length() - 1)) {
            return false;
        }
        return isPalindromeRecursive(text.substring(1, text.length() - 1));
    }

    public static boolean isPalindromeArrayReversal(String text) {
        char[] original = text.toCharArray();
        char[] reversed = new char[original.length];

        for (int i = 0; i < original.length; i++) {
            reversed[i] = original[original.length - 1 - i];
        }

        for (int i = 0; i < original.length; i++) {
            if (original[i] != reversed[i]) {
                return false;
            }
        }
        return true;
    }

    public static void displayResult(String text) {
        boolean iterative = isPalindromeIterative(text);
        boolean recursive = isPalindromeRecursive(text);
        boolean arrayReversal = isPalindromeArrayReversal(text);

        String iterStr = iterative ? "Palindrome" : "Not Palindrome";
        String recurStr = recursive ? "Palindrome" : "Not Palindrome";
        String arrayStr = arrayReversal ? "Palindrome" : "Not Palindrome";

        System.out.println("Iterative: " + iterStr + " | Recursive: " + recurStr + " | Array Reversal: " + arrayStr);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String text;

        if (scanner.hasNextLine()) {
            text = scanner.nextLine().trim();
        } else {
            text = "madam";
        }

        System.out.println("Input: \"" + text + "\"");
        displayResult(text);

        // Also demonstrate with alternate sample case if default was run
        if (text.equals("madam")) {
            System.out.println("\nInput: \"hello\"");
            displayResult("hello");
        }

        scanner.close();
    }
}
