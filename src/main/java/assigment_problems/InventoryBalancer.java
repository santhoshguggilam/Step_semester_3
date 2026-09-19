public class InventoryBalancer {

    public static void analyzeInventory(int[] sectionA, int[] sectionB) {
        int totalA = 0;
        int totalB = 0;

        for (int qty : sectionA) {
            totalA += qty;
        }

        for (int qty : sectionB) {
            totalB += qty;
        }

        String status = (totalA == totalB) ? "Balanced" : "Not Balanced";

        int highestQuantity = Integer.MIN_VALUE;
        String highestSection = "Section A";
        int highestItemNumber = 1;

        for (int i = 0; i < sectionA.length; i++) {
            if (sectionA[i] > highestQuantity) {
                highestQuantity = sectionA[i];
                highestSection = "Section A";
                highestItemNumber = i + 1;
            }
        }

        for (int i = 0; i < sectionB.length; i++) {
            if (sectionB[i] > highestQuantity) {
                highestQuantity = sectionB[i];
                highestSection = "Section B";
                highestItemNumber = i + 1;
            }
        }

        System.out.printf(
                "Section A Total: %d | Section B Total: %d | Status: %s | Highest Quantity: %d (%s, Item %d)%n",
                totalA, totalB, status, highestQuantity, highestSection, highestItemNumber
        );
    }

    public static void main(String[] args) {
        int[] sectionA = {20, 15, 30};
        int[] sectionB = {25, 10, 30};

        analyzeInventory(sectionA, sectionB);
    }
}