public class MatchDayGridAnalyzer {

    private static double rowAverage(int[] row) {
        if (row == null || row.length == 0) {
            return 0.0;
        }
        double sum = 0;
        for (int val : row) {
            sum += val;
        }
        return sum / row.length;
    }

    public static String classifyMatches(int[][] runsPerOver, int threshold) {
        if (runsPerOver == null || runsPerOver.length == 0) {
            return "";
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < runsPerOver.length; i++) {
            double avg = rowAverage(runsPerOver[i]);
            String status = (avg >= threshold) ? "Power Surge" : "Normal";

            if (i > 0) {
                sb.append(" | ");
            }
            sb.append("Match ").append(i).append(": ").append(status);
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        int[][] runsPerOver = {
            {4, 6, 8},
            {10, 12, 14},
            {2, 3, 1}
        };
        int threshold = 8;

        System.out.println("Threshold: " + threshold);
        System.out.println("Output: " + classifyMatches(runsPerOver, threshold));
    }
}
