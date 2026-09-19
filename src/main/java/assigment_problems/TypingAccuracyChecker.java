public class TypingAccuracyChecker {

    public static void checkTypingAccuracy(String original, String typed) {
        if (original == null || typed == null) {
            System.out.println("Invalid input strings.");
            return;
        }

        int total = original.length();
        int matched = 0;
        int firstMismatch = -1;

        int len = Math.min(original.length(), typed.length());
        for (int i = 0; i < len; i++) {
            if (original.charAt(i) == typed.charAt(i)) {
                matched++;
            } else if (firstMismatch == -1) {
                firstMismatch = i;
            }
        }

        double accuracy = total == 0 ? 100.0 : ((double) matched / total) * 100.0;

        System.out.printf("Matched: %d/%d | Accuracy: %.2f%%", matched, total, accuracy);

        if (firstMismatch == -1 && original.length() == typed.length()) {
            System.out.println(" | No Mismatches");
        } else if (firstMismatch != -1) {
            System.out.println(" | First Mismatch at position " + (firstMismatch + 1)
                    + " ('" + original.charAt(firstMismatch) + "' vs '" + typed.charAt(firstMismatch) + "')");
        } else {
            System.out.println(" | Length mismatch at position " + (len + 1));
        }
    }

    public static void main(String[] args) {
        System.out.println("Test Case 1:");
        String original1 = "hello world";
        String typed1 = "hello worlt";
        checkTypingAccuracy(original1, typed1);

        System.out.println("\nTest Case 2:");
        String original2 = "coding";
        String typed2 = "coding";
        checkTypingAccuracy(original2, typed2);
    }
}