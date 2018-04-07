import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

public class ThreeSum {
    private int[] data;

    public ThreeSum(int[] data) {
        this.data = data;
        Arrays.sort(this.data);
    }

    public void Find(int target) {
        for (int i = 0; i < data.length - 2; i++) {
            int j = i+1;
            int k = data.length - 1;
            while (j < k) {
                int sum = data[i] + data[j] + data[k];
                if (sum == target) {
                    StdOut.printf("%d %d %d", data[j], data[k], data[i]);
                    StdOut.println();
                    k--;
                    j++;
                } else if (sum > target) {
                    k--;
                } else if (sum < target) {
                    j++;
                }
            }
        }
    }

    public static void main(String[] args) {
        ThreeSum threeSum = new ThreeSum(new int[]{-2, 4, 8, 1, 2, 12, 5, 0, 9, -3});
        threeSum.Find(0);
    }
}
