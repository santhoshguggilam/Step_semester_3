public class DuplicateSeatChecker {

    public static void checkDuplicateSeats(int[] seatNumbers) {
        boolean foundDuplicate = false;

        for (int i = 0; i < seatNumbers.length; i++) {
            for (int j = i + 1; j < seatNumbers.length; j++) {
                if (seatNumbers[i] == seatNumbers[j]) {
                    // Check if seatNumbers[i] was already reported previously
                    boolean alreadyReported = false;
                    for (int k = 0; k < i; k++) {
                        if (seatNumbers[k] == seatNumbers[i]) {
                            alreadyReported = true;
                            break;
                        }
                    }

                    if (!alreadyReported) {
                        System.out.println("Duplicate Seat Number Found: " + seatNumbers[i]);
                        foundDuplicate = true;
                    }
                }
            }
        }

        if (!foundDuplicate) {
            System.out.println("No Duplicate Seats Found");
        }
    }

    public static void main(String[] args) {
        System.out.println("Test Case 1 (Duplicates present):");
        int[] hallA = {101, 102, 103, 102, 105};
        checkDuplicateSeats(hallA);

        System.out.println("\nTest Case 2 (No duplicates):");
        int[] hallB = {101, 102, 103, 104, 105};
        checkDuplicateSeats(hallB);
    }
}