import java.util.ArrayList;
import java.util.List;

abstract class ExamQuestion {
    protected String questionText;
    protected int points;

    public ExamQuestion(String questionText, int points) {
        this.questionText = questionText;
        this.points = points;
    }

    public abstract String getType();

    public abstract double grade(String studentAnswer);
}

class MCQQuestion extends ExamQuestion {
    private String correctAnswer;

    public MCQQuestion(String questionText, String correctAnswer, int points) {
        super(questionText, points);
        this.correctAnswer = correctAnswer;
    }

    @Override
    public String getType() {
        return "MCQ";
    }

    @Override
    public double grade(String studentAnswer) {
        if (correctAnswer != null && correctAnswer.equalsIgnoreCase(studentAnswer)) {
            return points;
        }
        return 0.0;
    }
}

class TFQuestion extends ExamQuestion {
    private String correctAnswer;

    public TFQuestion(String questionText, String correctAnswer, int points) {
        super(questionText, points);
        this.correctAnswer = correctAnswer;
    }

    @Override
    public String getType() {
        return "TF";
    }

    @Override
    public double grade(String studentAnswer) {
        if (correctAnswer != null && correctAnswer.equalsIgnoreCase(studentAnswer)) {
            return points;
        }
        return 0.0;
    }
}

class EssayQuestion extends ExamQuestion {
    private String keywordsCsv;

    public EssayQuestion(String questionText, String keywordsCsv, int points) {
        super(questionText, points);
        this.keywordsCsv = keywordsCsv;
    }

    @Override
    public String getType() {
        return "ESSAY";
    }

    @Override
    public double grade(String studentAnswer) {
        if (studentAnswer == null || keywordsCsv == null) {
            return 0.0;
        }

        String[] keywords = keywordsCsv.split(",");
        String lowerAnswer = studentAnswer.toLowerCase();
        int matches = 0;

        for (String kw : keywords) {
            String trimmedKw = kw.trim().toLowerCase();
            if (!trimmedKw.isEmpty() && lowerAnswer.contains(trimmedKw)) {
                matches++;
            }
        }

        if (matches >= 2) {
            return points * 0.75;
        } else if (matches == 1) {
            return points * 0.50;
        } else {
            return 0.0;
        }
    }
}

public class ExaminationQuestionGrader {

    public static void main(String[] args) {
        List<ExamQuestion> questions = new ArrayList<>();
        List<String> answers = new ArrayList<>();

        questions.add(new MCQQuestion("What is the capital of France?", "Paris", 10));
        answers.add("Paris");

        questions.add(new TFQuestion("The Earth is flat?", "False", 5));
        answers.add("True");

        questions.add(new EssayQuestion("Name two primary OOP principles.", "Inheritance, Polymorphism, Encapsulation", 20));
        answers.add("Polymorphism is one.");

        questions.add(new EssayQuestion("Describe abstraction and composition.", "Abstraction, Composition", 15));
        answers.add("I talked about abstraction.");

        double totalScore = 0;
        for (int i = 0; i < questions.size(); i++) {
            ExamQuestion q = questions.get(i);
            double score = q.grade(answers.get(i));
            totalScore += score;
            System.out.printf("%s: %.2f%n", q.getType(), score);
        }
        System.out.printf("Total Score: %.2f%n", totalScore);
    }
}
