import java.util.Arrays;
import java.util.stream.IntStream;

public class ClosestValueSearch {
    public static void main(String[] args) {
        int[] sortedArray = generateSortedArray();
        int target = 900_000;
        System.out.println("The closest value to " + target + " in the array is: " + findClosestValue(sortedArray, target));
    }

    // Find result
    public static int findClosestValue(int[] arr, int target) {
        int index = Arrays.binarySearch(arr, target);
        return (index >= 0) ? arr[index] : getClosest(arr, target, -index - 1);
    }

    // Used to check the edges of array.
    private static int getClosest(int[] arr, int target, int insertionPoint) {
        return (insertionPoint == 0) ? arr[0] :
                (insertionPoint == arr.length) ? arr[arr.length - 1] :
                        closest(arr[insertionPoint - 1], arr[insertionPoint], target);
    }

    // Find the condition of the closest value.
    private static int closest(int a, int b, int target) {
        return (Math.abs(a - target) <= Math.abs(b - target)) ? a : b;
    }

    // Generate sorted array (0 to 1,000,000)
    private static int[] generateSortedArray() {
        return IntStream.range(0, 1000001).toArray();
    }
}
