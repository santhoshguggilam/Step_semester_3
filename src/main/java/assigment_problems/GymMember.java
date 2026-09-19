import java.util.Arrays;

public class GymMember {

    protected String memberId;
    protected int monthlyFee;
    protected int sessionsAttended;

    private int[] lateFeeHistory = new int[10];
    private int lateFeeCount = 0;
    private int totalLateFees = 0;

    private int feesPaid = 0;

    private static int membersEnrolled = 0;
    public final String membershipNumber;

    public GymMember(String memberId, int monthlyFee) {
        if (memberId == null || memberId.trim().length() < 4) {
            System.out.println("construction rejected");
            throw new IllegalArgumentException("construction rejected");
        }
        this.memberId = memberId.trim();
        this.monthlyFee = monthlyFee;
        this.sessionsAttended = 0;
        this.membershipNumber = this.memberId;
    }

    public GymMember(int monthlyFee) {
        membersEnrolled++;
        this.membershipNumber = "GYM-" + (2000 + membersEnrolled);
        this.memberId = this.membershipNumber;
        this.monthlyFee = monthlyFee;
        this.sessionsAttended = 0;
    }

    public void attendSession() {
        sessionsAttended++;
    }

    public int getSessionsAttended() {
        return sessionsAttended;
    }

    public String displayInfo() {
        return "Standard Member | Sessions: " + sessionsAttended;
    }

    protected void chargeLateFee(int amount) {
        if (amount > 0 && lateFeeCount < lateFeeHistory.length) {
            lateFeeHistory[lateFeeCount++] = amount;
            totalLateFees += amount;
        }
    }

    public int[] getLateFeeHistory() {
        return Arrays.copyOf(lateFeeHistory, lateFeeCount);
    }

    public int getTotalLateFees() {
        return totalLateFees;
    }

    public void payFee(int amount) {
        feesPaid += amount;
    }

    public void payFee(int amount, String mode) {
        // the two-argument overload only records the mode before delegating to the one-argument version
        payFee(amount);
    }

    public int getFeesPaid() {
        return feesPaid;
    }

    public static int getMembersEnrolled() {
        return membersEnrolled;
    }

    public static boolean isValidReferralCode(String code) {
        if (code == null || code.length() != 4) {
            return false;
        }
        if (code.charAt(0) != 'G') {
            return false;
        }
        if (!Character.isDigit(code.charAt(1)) || !Character.isDigit(code.charAt(2))) {
            return false;
        }
        return Character.isUpperCase(code.charAt(3)) && Character.isLetter(code.charAt(3));
    }

    public static String signUpBatch(String[] memberIds, int monthlyFee) {
        int signedUp = 0;
        int rejected = 0;

        if (memberIds != null) {
            for (String id : memberIds) {
                try {
                    new GymMember(id, monthlyFee);
                    signedUp++;
                } catch (IllegalArgumentException e) {
                    rejected++;
                }
            }
        }

        return "Signed Up: " + signedUp + " | Rejected: " + rejected;
    }

    public static String classifyGeneration(GymMember member) {
        if (member instanceof EliteMember) {
            return "Multilevel descendant (3 generations deep)";
        } else if (member instanceof GroupClassMember) {
            return "Hierarchical sibling (independent branch)";
        } else if (member instanceof PremiumMember) {
            return "Single-level descendant (2 generations deep)";
        } else {
            return "Base class";
        }
    }

    public static int getTotalSessionsAttended(GymMember[] members) {
        int sum = 0;
        if (members != null) {
            for (GymMember m : members) {
                if (m != null) {
                    sum += m.getSessionsAttended();
                }
            }
        }
        return sum;
    }

    public static String batchPrint(GymMember[] members) {
        StringBuilder sb = new StringBuilder();
        if (members != null) {
            for (GymMember m : members) {
                if (m != null) {
                    String info = m.displayInfo().replace(" Member", "");
                    sb.append(info);

                    if (m instanceof PremiumMember) {
                        PremiumMember pm = (PremiumMember) m;
                        sb.append(" [Trainer via downcast: ").append(pm.getTrainerName()).append("]");
                    }
                    sb.append(" | ");
                }
            }
        }
        return sb.toString();
    }

    public static String processWeeklyCheckIn(GymMember[] members) {
        int processed = 0;
        int nullSkipped = 0;
        int group = 0;
        int individual = 0;

        if (members != null) {
            for (GymMember m : members) {
                if (m == null) {
                    nullSkipped++;
                } else {
                    processed++;
                    if (m instanceof GroupClassMember) {
                        group++;
                    } else {
                        individual++;
                    }
                }
            }
        }

        return processed + " processed | " + nullSkipped + " null skipped | " + group + " group | " + individual + " individual";
    }

    public static void main(String[] args) {
        System.out.println("=== Problem 1: Constructor Validation & Batch Sign-up ===");
        try {
            new GymMember("GM1", 1000);
        } catch (IllegalArgumentException e) {
            // expected
        }

        System.out.println(signUpBatch(new String[]{"MEM1", "GM1", "MEM2", " ", "MEM3"}, 1000));

        System.out.println("\n=== Problem 5: Referral Codes & Weekly Check-in ===");
        System.out.println("isValidReferralCode(\"G45B\"): " + isValidReferralCode("G45B"));
        System.out.println("isValidReferralCode(\"G4B\"): " + isValidReferralCode("G4B"));
        System.out.println("isValidReferralCode(\"X45B\"): " + isValidReferralCode("X45B"));

        GymMember m1 = new GymMember(1000);
        System.out.println("m1.membershipNumber: " + m1.membershipNumber);
        System.out.println("GymMember.getMembersEnrolled(): " + GymMember.getMembersEnrolled());

        m1.payFee(500);
        m1.payFee(500, "UPI");
        System.out.println("m1.getFeesPaid(): " + m1.getFeesPaid());

        GymMember[] checkInBatch = {
            new GroupClassMember(1500, "Zumba"),
            null,
            new GymMember(1000)
        };
        System.out.println(processWeeklyCheckIn(checkInBatch));
    }
}