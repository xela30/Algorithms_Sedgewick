import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

// Not uniformly random (as per tests) but not sure why.
// Randomly swaps data of each 2 nodes being iterated with merge sort algorithm.
// Swapping nodes itself seems to make algorithm cost proportional to N2*logN as on each swapping we need to look through the list from the very head.
public class ShufflingLinkedList2 {

    private static int n = 10;

    public static void main(String[] args) {
        Node<Integer> node = new Node<>(0);
        Node<Integer> head = node;
        for (int i = 1; i < n; i++) {
            node.next = new Node<>(i);
            node = node.next;
        }

        Node<Integer> tail = getTail(head);

        shuffle(head, tail);

        PrintNodes(head);
    }

    private static void PrintNodes(Node<Integer> first) {
        for (Node<Integer> nextNode : first) {
            StdOut.println(nextNode.item);
        }
    }

    private static void shuffle(Node<Integer> head, Node<Integer> tail) {
        if (head == tail) {
            return;
        }

        Node<Integer> middle = getMiddle(head, tail);
        shuffle(head, middle);
        shuffle(middle.next, tail);

        Node<Integer> headNext = head;
        Node<Integer> middleNext = middle;

        while (headNext != middle.next || middleNext != tail.next) {
            if (flipCoin())
                swap(headNext, middleNext);
            if (headNext != middle.next)
                headNext = headNext.next;
            if (middleNext != tail.next)
                middleNext = middleNext.next;
        }
    }

    private static void swap(Node<Integer> node1, Node<Integer> node2) {
        Integer aux = node1.item;
        node1.item = node2.item;
        node2.item = aux;
    }

    private static Node<Integer> getTail(Node<Integer> head) {
        Node<Integer> tail = null;
        for (Node<Integer> node : head) {
            tail = node;
        }
        return tail;
    }


    private static boolean flipCoin() {
        return StdRandom.bernoulli();
    }

    private static Node<Integer> getMiddle(Node<Integer> head, Node<Integer> tail) {
        Node<Integer> slow = head;
        Node<Integer> fast = head;
        while (fast != tail && fast.next != tail) {
            fast = fast.next.next;
            slow = slow.next;
        }

        return slow;
    }
}
