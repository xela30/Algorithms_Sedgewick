import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class Merge {
    public static void main(String[] args){
        Integer[] a = new Integer[8];
        for (int i = 0; i < 8; i++)
            a[i] = StdRandom.uniform(8);

        sort(a);

        for (int i = 0; i < 8; i++)
        {
            StdOut.println(a[i]);
        }
    }

    private static void merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi) {
        for (int k = lo; k <= hi; k++) {
            aux[k] = a[k];
        }

        int i = lo;
        int j = mid + 1;
        for (int k = lo; k <= hi; k++) {
            if (i > mid)
                a[k] = aux[j++];
            else if (j > hi)
                a[k] = aux[i++];
            else if (aux[j].compareTo(aux[i]) == -1)
                a[k] = aux[j++];
            else
                a[k] = aux[i++];
        }
    }

    private static void sort(Comparable[] a, Comparable[] aux, int lo, int hi) {
        if (hi <= lo) return;
        int mid = lo + (hi - lo)/2;
        sort (a, aux, lo, mid);
        sort(a, aux, mid+1, hi);
        merge(a,aux, lo, mid, hi);
    }

    public static void sort(Comparable[] a){
        Comparable[] aux = new Comparable[a.length];
        sort(a, aux, 0, a.length - 1);
    }
}
