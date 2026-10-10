public class WordReverserPalindrome {

    public static void checkPalindrome(String word) {
        if (word == null) {
            return;
        }

        StringBuilder sb = new StringBuilder(word);
        String reversed = sb.reverse().toString();

        if (word.equalsIgnoreCase(reversed)) {
            System.out.println(word + " - palindrome");
        } else {
            System.out.println(reversed + " - not a palindrome");
        }
    }

    public static void main(String[] args) {
        checkPalindrome("level");
        checkPalindrome("java");
    }
}
