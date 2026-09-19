import java.util.Arrays;

public class MergeTwoSortedArrays {

    public static int[] mergeSortedArrays(int[] arr1, int[] arr2) {
        int n1 = arr1.length;
        int n2 = arr2.length;
        int[] result = new int[n1 + n2];

        int i = 0;
        int j = 0;
        int k = 0;

        while (i < n1 && j < n2) {
            if (arr1[i] <= arr2[j]) {
                result[k++] = arr1[i++];
            } else {
                result[k++] = arr2[j++];
            }
        }

        while (i < n1) {
            result[k++] = arr1[i++];
        }

        while (j < n2) {
            result[k++] = arr2[j++];
        }

        return result;
    }

    public static void main(String[] args) {
        int[] arr1 = {1, 3, 5};
        int[] arr2 = {2, 4, 6};
        System.out.println("Input: arr1 = [1, 3, 5], arr2 = [2, 4, 6]");
        System.out.println("Output: " + Arrays.toString(mergeSortedArrays(arr1, arr2)));

        int[] arr3 = {};
        int[] arr4 = {1, 2, 3};
        System.out.println("\nInput: arr1 = [], arr2 = [1, 2, 3]");
        System.out.println("Output: " + Arrays.toString(mergeSortedArrays(arr3, arr4)));
    }
}
