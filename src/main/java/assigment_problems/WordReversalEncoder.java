public class WordReversalEncoder {

    public static String reverseEachWord(String sentence) {
        if (sentence == null || sentence.isEmpty()) {
            return "";
        }

        String[] words = sentence.split(" ");
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < words.length; i++) {
            StringBuilder wordBuilder = new StringBuilder();
            for (int j = words[i].length() - 1; j >= 0; j--) {
                wordBuilder.append(words[i].charAt(j));
            }

            result.append(wordBuilder.toString());
            if (i < words.length - 1) {
                result.append(" ");
            }
        }

        return result.toString();
    }

    public static void main(String[] args) {
        String input = "hello club";
        String encoded = reverseEachWord(input);
        System.out.println("Input: \"" + input + "\"");
        System.out.println("Output: " + encoded);
    }
}