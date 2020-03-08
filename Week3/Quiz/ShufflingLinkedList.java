import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

// This one is based on one-time random merging thus obviously doesn't shuffle uniformly.
// Seems to be similar to https://web.stanford.edu/class/cs9/sample_probs/ListShuffling.pdf
public class ShufflingLinkedList {

    private static int n = 8;
    private static Node<Integer> shuffled;

    public static void main(String[] args) {
        Node<Integer> node = new Node<>(0);
        Node<Integer> first = node;
        for (int i = 1; i < n; i++) {
            node.next = new Node<>(i);
            node = node.next;
        }

        shuffle(first);

        PrintNodes(shuffled);
    }

    private static void PrintNodes(Node<Integer> first) {
        for (Node<Integer> nextNode : first) {
            StdOut.println(nextNode.item);
        }
    }

    private static void shuffle(Node<Integer> node) {
        if (node == null) {
            return;
        }
        Node<Integer> leftHead = node;
        Node<Integer> middle = getMiddle(node);
        Node<Integer> rightHead = middle.next;
        middle.next = null;

        if (rightHead == null) {
            return;
        }

        while (leftHead != null || rightHead != null) {
            if (leftHead == null) {
                addToShuffled(rightHead);
                rightHead = rightHead.next;
            } else if (rightHead == null) {
                addToShuffled(leftHead);
                leftHead = leftHead.next;
            } else {
                boolean obverse = flipCoin();
                Node<Integer> winner;
                if (obverse) {
                    winner = leftHead;
                    leftHead = leftHead.next;
                } else {
                    winner = rightHead;
                    rightHead = rightHead.next;
                }
                addToShuffled(winner);
            }
        }
    }

    private static void addToShuffled(Node<Integer> winner) {
        Node<Integer> aux = shuffled;
        shuffled = new Node<>(winner.item);
        shuffled.next = aux;
    }

    private static boolean flipCoin() {
        return StdRandom.bernoulli();
    }

    private static Node<Integer> getMiddle(Node<Integer> node) {
        Node<Integer> slow = node;
        Node<Integer> fast = node;
        while (fast != null && fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }

        return slow;
    }
}
