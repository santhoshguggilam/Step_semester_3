import java.util.Arrays;

public class DutyRosterRotation {

    public static String[] rotateRoster(String[] names, long k) {
        if (names == null || names.length <= 1) {
            return names;
        }

        int n = names.length;
        int shift = (int) (k % n);
        if (shift < 0) {
            shift += n;
        }

        String[] rotated = new String[n];
        for (int i = 0; i < n; i++) {
            rotated[(i + shift) % n] = names[i];
        }

        return rotated;
    }

    public static void main(String[] args) {
        String[] names1 = {"A", "B", "C", "D", "E"};
        System.out.println("Sample 1 Output: " + Arrays.toString(rotateRoster(names1, 2)));

        String[] names2 = {"A", "B", "C", "D", "E"};
        System.out.println("Sample 2 Output: " + Arrays.toString(rotateRoster(names2, 7)));
    }
}
