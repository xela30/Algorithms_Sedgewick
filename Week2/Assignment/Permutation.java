import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Permutation {

    public static void main(String[] args) {
        if (args.length < 1) {
            throw new IllegalArgumentException();
        }
        RandomizedQueue<String> queue = new RandomizedQueue<>();
        int k = Integer.parseInt(args[0]);
        if (k > 0) {
            while (!StdIn.isEmpty()) {
                String input = StdIn.readString();
                queue.enqueue(input);
            }

            for (int i = 0; i < k; i++) {
                StdOut.println(queue.dequeue());
            }
        }
    }
}
