public class StudentGradeAssignment {

    public static void assignGrade(int marks) {
        if (marks >= 90) {
            System.out.println("Grade A");
        } else if (marks >= 75) {
            System.out.println("Grade B");
        } else if (marks >= 60) {
            System.out.println("Grade C");
        } else if (marks >= 40) {
            System.out.println("Grade D");
        } else {
            System.out.println("Grade F");
        }
    }

    public static void main(String[] args) {
        assignGrade(72);
        assignGrade(95);
        assignGrade(35);
    }
}
