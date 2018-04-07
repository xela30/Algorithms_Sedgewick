import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

public class BitonicSearch {
    private int[] data;

    public BitonicSearch(int[] data) {
        this.data = data;
    }

    public void Find(int target) {
        int l = 0;
        int r = data.length - 1;
        while (l < r) {
            int m_index = (r + l) >>> 1;
            int m = data[m_index];
            if (m == target) {
                StdOut.println(m_index);
                return;
            } else if (data[m_index - 1] < m && m > data[m_index + 1]) {
                if (target > m)
                    return;

                int x = ascendingBinSearch(data, l, m_index, target);
                if (x < 0) {
                    x = descendingBinSearch(data, m_index, r, target);
                }

                if (x >= 0) {
                    StdOut.println(x);
                    return;
                }
            } else if (data[m_index - 1] < m && m < data[m_index + 1]) {
                if (m > target) {
                    int x = ascendingBinSearch(data, l, m_index, target);
                    if (x >= 0) {
                        StdOut.println(x);
                        return;
                    }
                }
                l = m_index;
            } else if (data[m_index - 1] > m && m > data[m_index + 1]) {
                if (m > target) {
                    int x = descendingBinSearch(data, m_index, r, target);
                    if (x >= 0) {
                        StdOut.println(x);
                        return;
                    }
                }
                r = m_index;
            }
        }
    }

    public int ascendingBinSearch(int[] data, int left, int right, int target) {
        return Arrays.binarySearch(data, left, right, target);
    }

    private static int descendingBinSearch(int[] a, int fromIndex, int toIndex, int key) {
        int low = fromIndex;
        int high = toIndex;

        while (low <= high) {
            int mid = (low + high) >>> 1;
            int midVal = a[mid];

            if (midVal < key)
                high = mid - 1;
            else if (midVal > key)
                low = mid + 1;
            else
                return mid; // key found
        }
        return -(low + 1);  // key not found.
    }

    public static void main(String[] args) {
        BitonicSearch bitonicSearch = new BitonicSearch(new int[]{-5, -2, 0, 1, 3, 5, 7, 8, 12, 9, 6, 2, -3});
        bitonicSearch.Find(-123);
//        bitonicSearch.Find(-5);
//        bitonicSearch.Find(-3);
//        bitonicSearch.Find(12);
    }
}
