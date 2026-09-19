public class FacultyMember extends LibraryMember {

    private String department;

    public FacultyMember(String memberId, int borrowLimit, String department) {
        super(memberId, borrowLimit);
        this.department = department;
    }

    public FacultyMember(int borrowLimit, String department) {
        super(borrowLimit);
        this.department = department;
    }

    public String getDepartment() {
        return department;
    }

    @Override
    public String displayInfo() {
        return "Faculty Member | Department: " + department + " | Books Borrowed: " + booksBorrowed;
    }

    public static void main(String[] args) {
        System.out.println(new LibraryMember("STU1", 3).displayInfo());
        System.out.println(new StudentMember("STU2", 3, "CSE").displayInfo());
        System.out.println(new HonorsStudentMember("STU3", 3, "ECE", 2).displayInfo());
        System.out.println(new FacultyMember("STU4", 5, "Physics").displayInfo());

        HonorsStudentMember honorsMember = new HonorsStudentMember("STU3", 3, "ECE", 2);
        FacultyMember facultyMember = new FacultyMember("STU4", 5, "Physics");

        System.out.println(LibraryMember.classifyGeneration(honorsMember));
        System.out.println(LibraryMember.classifyGeneration(facultyMember));

        StudentMember studentMember = new StudentMember("STU2", 3, "CSE");
        studentMember.borrowBook();
        studentMember.borrowBook(); // 2
        honorsMember.borrowBook(); // 1
        facultyMember.borrowBook();
        facultyMember.borrowBook();
        facultyMember.borrowBook(); // 3

        LibraryMember[] members = {studentMember, honorsMember, facultyMember};
        System.out.println("Total books borrowed: " + LibraryMember.getTotalBooksBorrowed(members));

        LibraryMember[] batch = {
            new LibraryMember("LB5", 3),
            new StudentMember("STU6", 3, "ECE")
        };
        System.out.println("batchPrint: \"" + LibraryMember.batchPrint(batch) + "\"");
    }
}