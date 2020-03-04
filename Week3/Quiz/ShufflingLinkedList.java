import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

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

        //PrintNodes(first);

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

        shuffle(leftHead);
        shuffle(rightHead);

        while (leftHead != null || rightHead != null) {
            if (leftHead == null) {
                addToShuffled(rightHead);
            } else if (rightHead == null) {
                addToShuffled(leftHead);
            } else {
                boolean obverse = flipCoin();
                Node<Integer> winner = obverse ? leftHead : rightHead;
                addToShuffled(winner);
            }
            leftHead = leftHead.next;
            rightHead = rightHead.next;
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
