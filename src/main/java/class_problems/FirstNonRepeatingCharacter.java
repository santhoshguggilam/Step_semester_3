import java.io.IOException;
import java.util.Scanner;

public class FirstNonRepeatingCharacter {

    public static char findFirstNonRepeatingChar(String text) {
        int[] freq = new int[256];

        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);
            if (ch < 256) {
                freq[ch]++;
            }
        }

        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);
            if (ch < 256 && freq[ch] == 1) {
                return ch;
            }
        }

        return '\0';
    }

    public static void processAndPrint(String text) {
        char result = findFirstNonRepeatingChar(text);
        if (result == '\0') {
            System.out.println("No Non-Repeating Character Found");
        } else {
            System.out.println("First Non-Repeating Character: '" + result + "'");
        }
    }

    public static void main(String[] args) {
        boolean hasInput = false;
        String text = "";
        try {
            if (System.in.available() > 0) {
                Scanner scanner = new Scanner(System.in);
                if (scanner.hasNextLine()) {
                    text = scanner.nextLine().trim();
                    hasInput = !text.isEmpty();
                }
                scanner.close();
            }
        } catch (IOException e) {
            hasInput = false;
        }

        if (hasInput) {
            System.out.println("Input: \"" + text + "\"");
            processAndPrint(text);
        } else {
            System.out.println("Input: \"swiss\"");
            processAndPrint("swiss");

            System.out.println("\nInput: \"aabbcc\"");
            processAndPrint("aabbcc");
        }
    }
}