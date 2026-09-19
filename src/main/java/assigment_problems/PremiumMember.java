public class PremiumMember extends GymMember {

    protected String trainerName;

    public PremiumMember(String memberId, int monthlyFee, String trainerName) {
        super(memberId, monthlyFee);
        this.trainerName = trainerName;
    }

    public String getTrainerName() {
        return trainerName;
    }

    @Override
    public String displayInfo() {
        return "Premium Member | Trainer: " + trainerName + " | Sessions: " + sessionsAttended;
    }

    @Override
    protected void chargeLateFee(int amount) {
        super.chargeLateFee(amount / 2);
    }

    public static void main(String[] args) {
        PremiumMember p = new PremiumMember("MEM01", 2000, "Coach Riya");
        p.attendSession();
        p.attendSession();
        System.out.println("p.getSessionsAttended(): " + p.getSessionsAttended());

        PremiumMember p5 = new PremiumMember("MEM5", 2000, "Coach Riya");
        p5.chargeLateFee(200);
        System.out.println("p5.getTotalLateFees(): " + p5.getTotalLateFees());
        System.out.println("p5.getLateFeeHistory(): " + java.util.Arrays.toString(p5.getLateFeeHistory()));

        int[] history = p5.getLateFeeHistory();
        history[0] = 999;
        System.out.println("After tampering: " + java.util.Arrays.toString(p5.getLateFeeHistory()));
    }
}