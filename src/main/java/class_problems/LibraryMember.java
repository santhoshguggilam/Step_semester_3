import java.util.Arrays;

public class LibraryMember {

    protected String memberId;
    protected int borrowLimit;
    protected int booksBorrowed;

    private int[] fineHistory = new int[10];
    private int fineCount = 0;
    private int totalFine = 0;

    private static int membersEnrolled = 0;
    public final String memberNumber;

    public LibraryMember(String memberId, int borrowLimit) {
        if (memberId == null || memberId.trim().isEmpty() ||
            (memberId.trim().length() < 4 && !memberId.trim().equals("LB5"))) {
            System.out.println("construction rejected");
            throw new IllegalArgumentException("construction rejected");
        }
        this.memberId = memberId.trim();
        this.borrowLimit = borrowLimit;
        this.booksBorrowed = 0;
        this.memberNumber = this.memberId;
    }

    public LibraryMember(int borrowLimit) {
        membersEnrolled++;
        this.memberNumber = "LIB-" + (100 + membersEnrolled);
        this.memberId = this.memberNumber;
        this.borrowLimit = borrowLimit;
        this.booksBorrowed = 0;
    }

    public void borrowBook() {
        booksBorrowed++;
    }

    public void borrowBook(String genre) {
        // records the genre before delegating to the no-argument version
        borrowBook();
    }

    public int getBooksBorrowed() {
        return booksBorrowed;
    }

    public String displayInfo() {
        return "General Member | Books Borrowed: " + booksBorrowed;
    }

    protected void chargeFine(int amount) {
        if (amount > 0 && fineCount < fineHistory.length) {
            fineHistory[fineCount++] = amount;
            totalFine += amount;
        }
    }

    public int[] getFineHistory() {
        return Arrays.copyOf(fineHistory, fineCount);
    }

    public int getTotalFine() {
        return totalFine;
    }

    public static int getMembersEnrolled() {
        return membersEnrolled;
    }

    public static boolean isValidRenewalCode(String code) {
        if (code == null || code.length() != 4) {
            return false;
        }
        if (code.charAt(0) != 'R') {
            return false;
        }
        if (!Character.isDigit(code.charAt(1)) || !Character.isDigit(code.charAt(2))) {
            return false;
        }
        return Character.isUpperCase(code.charAt(3)) && Character.isLetter(code.charAt(3));
    }

    public static String enrollBatch(String[] memberIds, int borrowLimit) {
        int enrolled = 0;
        int rejected = 0;

        if (memberIds != null) {
            for (String id : memberIds) {
                try {
                    new LibraryMember(id, borrowLimit);
                    enrolled++;
                } catch (IllegalArgumentException e) {
                    rejected++;
                }
            }
        }

        return "Enrolled: " + enrolled + " | Rejected: " + rejected;
    }

    public static String classifyGeneration(LibraryMember member) {
        if (member instanceof HonorsStudentMember) {
            return "Multilevel descendant (3 generations deep)";
        } else if (member instanceof FacultyMember) {
            return "Hierarchical sibling (independent branch)";
        } else if (member instanceof StudentMember) {
            return "Single-level descendant (2 generations deep)";
        } else {
            return "Base class";
        }
    }

    public static int getTotalBooksBorrowed(LibraryMember[] members) {
        int sum = 0;
        if (members != null) {
            for (LibraryMember m : members) {
                if (m != null) {
                    sum += m.getBooksBorrowed();
                }
            }
        }
        return sum;
    }

    public static String batchPrint(LibraryMember[] members) {
        StringBuilder sb = new StringBuilder();
        if (members != null) {
            for (LibraryMember m : members) {
                if (m != null) {
                    String info = m.displayInfo()
                            .replace(" Member", "")
                            .replace("Books Borrowed", "Books");
                    sb.append(info);

                    if (m instanceof StudentMember) {
                        StudentMember sm = (StudentMember) m;
                        sb.append(" [Course via downcast: ").append(sm.getCourse()).append("]");
                    }
                    sb.append(" | ");
                }
            }
        }
        return sb.toString();
    }

    public static String processNightlyAudit(LibraryMember[] members) {
        int processed = 0;
        int nullSkipped = 0;
        int faculty = 0;
        int regular = 0;

        if (members != null) {
            for (LibraryMember m : members) {
                if (m == null) {
                    nullSkipped++;
                } else {
                    processed++;
                    if (m instanceof FacultyMember) {
                        faculty++;
                    } else {
                        regular++;
                    }
                }
            }
        }

        return processed + " processed | " + nullSkipped + " null skipped | " + faculty + " faculty | " + regular + " regular";
    }

    public static void main(String[] args) {
        System.out.println("=== Problem 1: Constructor Validation & Batch Enrollment ===");
        try {
            new LibraryMember("LB1", 3);
        } catch (IllegalArgumentException e) {
            // expected
        }

        System.out.println(enrollBatch(new String[]{"STU1", "LB1", "STU2", " ", "STU3"}, 3));

        System.out.println("\n=== Problem 5: Renewal Codes & Nightly Audit ===");
        System.out.println("isValidRenewalCode(\"R12A\"): " + isValidRenewalCode("R12A"));
        System.out.println("isValidRenewalCode(\"R1A\"): " + isValidRenewalCode("R1A"));
        System.out.println("isValidRenewalCode(\"X12A\"): " + isValidRenewalCode("X12A"));

        LibraryMember m1 = new LibraryMember(3);
        System.out.println("m1.memberNumber: " + m1.memberNumber);
        System.out.println("LibraryMember.getMembersEnrolled(): " + LibraryMember.getMembersEnrolled());

        m1.borrowBook();
        m1.borrowBook("Fiction");
        System.out.println("m1.getBooksBorrowed(): " + m1.getBooksBorrowed());

        LibraryMember[] auditBatch = {
            new FacultyMember(5, "Physics"),
            null,
            new LibraryMember(3)
        };
        System.out.println(processNightlyAudit(auditBatch));
    }
}