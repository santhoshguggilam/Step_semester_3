class Student {
    private String name;
    private int[] marks;

    public Student(String name, int[] marks) {
        this.name = name;
        this.marks = marks;
    }

    public double calculateAverage() {
        if (marks == null || marks.length == 0) {
            return 0.0;
        }
        double sum = 0;
        for (int mark : marks) {
            sum += mark;
        }
        return sum / marks.length;
    }

    public String assignGrade(double avg) {
        if (avg >= 75) {
            return "Grade B";
        } else if (avg >= 40) {
            return "Grade D";
        } else {
            return "Grade F";
        }
    }

    public void printResultCard() {
        double avg = calculateAverage();
        String grade = assignGrade(avg);
        System.out.printf("%s: Average %.1f, %s%n", name.toUpperCase(), avg, grade);
    }
}

public class StudentResultCardGenerator {

    public static void main(String[] args) {
        Student s1 = new Student("Asha", new int[]{80, 90, 70});
        Student s2 = new Student("Ravi", new int[]{60, 55, 50});

        s1.printResultCard();
        s2.printResultCard();
    }
}
