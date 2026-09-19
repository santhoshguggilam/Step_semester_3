public class StudentMember extends LibraryMember {

    protected String course;

    public StudentMember(String memberId, int borrowLimit, String course) {
        super(memberId, borrowLimit);
        this.course = course;
    }

    public String getCourse() {
        return course;
    }

    @Override
    public String displayInfo() {
        return "Student Member | Course: " + course + " | Books Borrowed: " + booksBorrowed;
    }

    @Override
    protected void chargeFine(int amount) {
        super.chargeFine(amount / 2);
    }

    public static void main(String[] args) {
        StudentMember s = new StudentMember("STU10", 3, "CSE");
        s.borrowBook();
        s.borrowBook();
        System.out.println("s.getBooksBorrowed(): " + s.getBooksBorrowed());

        StudentMember s5 = new StudentMember("STU5", 3, "CSE");
        s5.chargeFine(100);
        System.out.println("s5.getTotalFine(): " + s5.getTotalFine());
        System.out.println("s5.getFineHistory(): " + java.util.Arrays.toString(s5.getFineHistory()));

        int[] history = s5.getFineHistory();
        history[0] = 999;
        System.out.println("After tampering with returned array: " + java.util.Arrays.toString(s5.getFineHistory()));
    }
}