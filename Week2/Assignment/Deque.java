import java.util.Iterator;

public class Deque<Item> implements Iterable<Item> {

    private Node<Item> first;
    private Node<Item> last;

    // construct an empty deque
    public Deque() {
    }

    // is the deque empty?
    public boolean isEmpty() {
        return first == null;
    }

    // return the number of items on the deque
    public int size() {
        return 0;
    }

    // add the item to the front
    public void addFirst(Item item) {
        Node<Item> oldFirst = first;
        first = new Node<Item>();
        first.item = item;
        first.next = oldFirst;
    }

    // add the item to the back
    public void addLast(Item item) {
        Node<Item> oldLast = last;
        last = new Node<Item>();
        last.item = item;
        oldLast.next = last;
    }

    public void resize(int capacity) {
    }

    // remove and return the item from the front
    public Item removeFirst() {
        return null;
    }

    // remove and return the item from the back
    public Item removeLast() {
        return null;
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new Iterator<Item>() {
            @Override
            public boolean hasNext() {
                return false;
            }

            @Override
            public Item next() {
                return null;
            }
        };
    }

    // unit testing (required)
    public static void main(String[] args) {
    }
}
