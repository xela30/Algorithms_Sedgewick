import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Permutation {
    private static RandomizedQueue<String> queue = new RandomizedQueue<>();

    public static void main(String[] args) {

        if (args.length < 1) {
            throw new IllegalArgumentException();
        }
        int k = Integer.parseInt(args[0]);
        int i = 0;
        while (!StdIn.isEmpty()) {
            String input = StdIn.readString();
            if (i >= k) {
                queue.dequeue();
            }
            queue.enqueue(input);
            i++;
        }

        for (String item : queue) {
            StdOut.println(item);
        }
    }
}
