public class BusiestBusRow {

    public static String busiestRow(int[][] grid) {
        if (grid == null || grid.length == 0) {
            return "Row 0, Total 0";
        }

        int bestRow = 0;
        int maxTotal = -1;

        for (int i = 0; i < grid.length; i++) {
            int rowSum = 0;
            if (grid[i] != null) {
                for (int seat : grid[i]) {
                    rowSum += seat;
                }
            }

            if (rowSum > maxTotal) {
                maxTotal = rowSum;
                bestRow = i;
            }
        }

        return "Row " + bestRow + ", Total " + maxTotal;
    }

    public static void main(String[] args) {
        int[][] grid = {
            {2, 0, 1},
            {3, 3, 1},
            {1, 1, 1}
        };

        System.out.println("Expected Output: " + busiestRow(grid));
    }
}
