import java.util.Arrays;

public class TopThreePodiumFinder {

    public static int[] findTopThreeScores(int[] scores) {
        if (scores == null || scores.length < 3) {
            return new int[0];
        }

        int first = Integer.MIN_VALUE;
        int second = Integer.MIN_VALUE;
        int third = Integer.MIN_VALUE;

        for (int score : scores) {
            if (score >= first) {
                third = second;
                second = first;
                first = score;
            } else if (score >= second) {
                third = second;
                second = score;
            } else if (score >= third) {
                third = score;
            }
        }

        return new int[]{first, second, third};
    }

    public static void main(String[] args) {
        int[] scores = {45, 82, 79, 90, 33, 90, 61};
        System.out.println("Input: " + Arrays.toString(scores));
        System.out.println("Output: " + Arrays.toString(findTopThreeScores(scores)));
    }
}
