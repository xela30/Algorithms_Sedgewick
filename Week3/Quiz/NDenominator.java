import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import java.util.ArrayList;

// Quiz Question #3
// Decimal dominants.
// Given an array with n keys, design an algorithm to find all values that occur more than n/10 times.
// The expected running time of your algorithm should be linear.
public class NDenominator {
    public static void main(String[] args) {
        int size = 10;
        Integer[] a = new Integer[size];
        int denominator = 2;

        for (int i = 0; i < size; i++) {
            a[i] = StdRandom.uniform(0, denominator);
        }

        StdRandom.shuffle(a);

        for (int i = 0; i < a.length; i++) {
            StdOut.print(a[i]);
            StdOut.print(" ");
        }

        StdOut.println();

        ArrayList<Comparable> result = find(a, denominator);

        for (Comparable item : result) {
            StdOut.print(item);
            StdOut.print(" ");
        }

        StdOut.println();
    }

    private static ArrayList<Comparable> find(Comparable[] a, int denominator) {
        ArrayList<Comparable> result = new ArrayList<>();
        for (int k = a.length / denominator; k < a.length; k += a.length / denominator) {
            int lo = 0;
            int hi = a.length - 1;
            while (lo < hi) {

                int lt = lo;
                int i = lo;
                int gt = hi;

                Comparable v = a[lo];

                while (i <= gt) {
                    if (a[i].compareTo(v) < 0) {
                        swap(a, i++, lt++);
                    } else if (a[i].compareTo(v) > 0) {
                        swap(a, i, gt--);
                    } else {
                        i++;
                    }
                }

                if (gt < k) {
                    lo = gt + 1;
                } else if (lt > k) {
                    hi = lt - 1;
                } else if (gt - lt >= a.length/denominator) {
                    result.add(a[lt]);
                    break;
                } else {
                    break;
                }
            }
        }
        return result;
    }

    private static void swap(Comparable[] a, int i, int j) {
        Comparable aux = a[i];
        a[i] = a[j];
        a[j] = aux;
    }
}
