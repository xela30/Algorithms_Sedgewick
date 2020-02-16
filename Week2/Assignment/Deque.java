import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;

public class Deque<Item> implements Iterable<Item> {

    private class Node<T> {
        T item;
        Node<T> next;
        Node<T> previous;
    }

    private Node<Item> first, last;
    private int size = 0;

    // construct an empty deque
    public Deque() {
    }

    // is the deque empty?
    public boolean isEmpty() {
        return size == 0;
    }

    // return the number of items on the deque
    public int size() {
        return size;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("item");
        }

        Node<Item> newNode = new Node<>();
        newNode.item = item;
        if (isEmpty()) {
            first = last = newNode;
        } else {
            newNode.next = first;
            first.previous = newNode;
            first = newNode;
        }

        size++;
    }

    // remove and return the item from the back
    public Item removeLast() {
        if (isEmpty())
            throw new java.util.NoSuchElementException();

        Item item = last.item;

        if (size == 1) {
            first = last = null;
        } else {
            last = last.previous;
            last.next = null;
        }

        size--;
        return item;
    }

    // add the item to the back
    public void addLast(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("item");
        }
        Node<Item> newNode = new Node<>();
        newNode.item = item;
        if (isEmpty()) {
            first = last = newNode;
        } else {
            newNode.previous = last;
            last.next = newNode;
            last = newNode;
        }

        size++;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (isEmpty())
            throw new java.util.NoSuchElementException();

        Item item = first.item;

        if (size == 1) {
            first = last = null;
        } else {
            first = first.next;
            first.previous = null;
        }

        size--;
        return item;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new Iterator<Item>() {
            private Node<Item> current = first;

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public Item next() {
                if (hasNext()) {
                    Item result = current.item;
                    current = current.next;
                    return result;
                }
                throw new java.util.NoSuchElementException();
            }
        };
    }

    // unit testing (required)
    public static void main(String[] args) {
        Deque<String> deque = new Deque<>();
        deque.addFirst("first");
        deque.addLast("last");
        for (String item :
                deque) {
            StdOut.println(item);
        }
        StdOut.printf("size: %s\n", deque.size());
        StdOut.printf("first: %s\n", deque.removeFirst());
        StdOut.printf("last: %s\n", deque.removeLast());
        StdOut.printf("isEmpty: %s\n", deque.isEmpty());
    }
}
