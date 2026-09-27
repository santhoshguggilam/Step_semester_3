public class Scorecard {

    private final int totalQuestions;
    private final boolean[] answers;
    private int recordedCount;

    public Scorecard(int totalQuestions) {
        this.totalQuestions = totalQuestions;
        this.answers = new boolean[totalQuestions];
        this.recordedCount = 0;
    }

    public void recordAnswer(boolean isCorrect) {
        if (recordedCount < totalQuestions) {
            answers[recordedCount++] = isCorrect;
        }
    }

    public int getScore() {
        int count = 0;
        for (int i = 0; i < recordedCount; i++) {
            if (answers[i]) {
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        Scorecard sc = new Scorecard(4);
        sc.recordAnswer(true);
        sc.recordAnswer(true);
        sc.recordAnswer(false);
        sc.recordAnswer(true);
        System.out.println("sc.getScore() -> " + sc.getScore());
    }
}
