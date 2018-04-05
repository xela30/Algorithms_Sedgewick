import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

public class ThreeSum {
    private int[] data;

    public ThreeSum(int[] data) {
        this.data = data;
        Arrays.sort(this.data);
        StdOut.println(data.length);
    }

    public void Find(int target) {
        int iteration = 0;
        for (int i = 0; i < data.length - 2; i++) {
            iteration++;
            int j = i+1;
            int k = data.length - 1;
            while (j < k) {
                iteration++;
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
        StdOut.println(iteration++);
    }

    public static void main(String[] args) {
        ThreeSum threeSum = new ThreeSum(new int[]{-2, 4, 8, 1, 2, 12, 5, 0, 9, -3});
        threeSum.Find(0);
    }
}
