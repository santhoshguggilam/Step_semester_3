public class GroupClassMember extends GymMember {

    private String className;

    public GroupClassMember(String memberId, int monthlyFee, String className) {
        super(memberId, monthlyFee);
        this.className = className;
    }

    public GroupClassMember(int monthlyFee, String className) {
        super(monthlyFee);
        this.className = className;
    }

    public String getClassName() {
        return className;
    }

    @Override
    public String displayInfo() {
        return "Group Class Member | Class: " + className + " | Sessions: " + sessionsAttended;
    }

    public static void main(String[] args) {
        System.out.println(new GymMember("MEM1", 1000).displayInfo());
        System.out.println(new PremiumMember("MEM2", 2000, "Coach Riya").displayInfo());
        System.out.println(new EliteMember("MEM3", 3000, "Coach Arjun", "L12").displayInfo());
        System.out.println(new GroupClassMember("MEM4", 1500, "Zumba").displayInfo());

        EliteMember eliteMember = new EliteMember("MEM3", 3000, "Coach Arjun", "L12");
        GroupClassMember groupClassMember = new GroupClassMember("MEM4", 1500, "Zumba");

        System.out.println(GymMember.classifyGeneration(eliteMember));
        System.out.println(GymMember.classifyGeneration(groupClassMember));

        PremiumMember premiumMember = new PremiumMember("MEM2", 2000, "Coach Riya");
        premiumMember.attendSession();
        premiumMember.attendSession();
        premiumMember.attendSession(); // 3
        eliteMember.attendSession();
        eliteMember.attendSession(); // 2
        groupClassMember.attendSession();
        groupClassMember.attendSession();
        groupClassMember.attendSession();
        groupClassMember.attendSession(); // 4

        GymMember[] members = {premiumMember, eliteMember, groupClassMember};
        System.out.println("Total sessions attended: " + GymMember.getTotalSessionsAttended(members));

        GymMember[] batch = {
            new GymMember("MEM6", 1000),
            new PremiumMember("MEM7", 2000, "Coach Riya")
        };
        System.out.println("batchPrint: \"" + GymMember.batchPrint(batch) + "\"");
    }
}