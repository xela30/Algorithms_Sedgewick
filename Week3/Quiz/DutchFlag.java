import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import java.awt.*;

public class DutchFlag {
    private static int n = 100;
    private static int[] _buckets = new int[n];

    public static void main(String[] args) {
        InitBucketsWithRandomDutchColors();

        PutWhitePebbleToHeadOfArray();

        Perform3WayPartition();

        PrintArray();
    }

    private static void Perform3WayPartition() {
        int lt = 0;
        int gt = _buckets.length - 1;
        int i = 0;

        while (i <= gt) {
            if (_buckets[i] < _buckets[lt]) {
                swap(lt++, i++);
            } else if (_buckets[i] > _buckets[lt]) {
                swap(i, gt--);
            } else {
                i++;
            }
        }
    }

    private static void InitBucketsWithRandomDutchColors() {
        for (int i = 0; i < n; i++) {
            // 0 - red
            // 1 = white
            // 2 - blue
            _buckets[i] = StdRandom.uniform(3);
        }
    }

    private static Color Color(int i){
        switch (_buckets[i]){
            case 0: return Color.red;
            case 1: return Color.white;
            case 2: return Color.blue;
        }
        throw new IllegalStateException();
    }

    private static void PutWhitePebbleToHeadOfArray() {
        int i = 0;
        while (Color(i) != Color.white) {
            i++;
        }

        if (i != 0) {
            swap(0, i);
        }
    }

    private static void PrintArray() {
        for (int i = 0; i < _buckets.length; i++)
            StdOut.println(_buckets[i]);
    }


    private static void swap(int i, int j) {
        int aux = _buckets[i];
        _buckets[i] = _buckets[j];
        _buckets[j] = aux;
    }
}
