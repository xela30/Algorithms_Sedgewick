import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private Item[] q = (Item[]) new Object[1];
    private int size = 0;

    // construct an empty randomized queue
    public RandomizedQueue() {

    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return size == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return size;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
        if (size == q.length) {
            resize(2 * size);
        }
        q[size++] = item;
    }

    // remove and return a random item
    public Item dequeue() {
        if (isEmpty()) {
            throw new java.util.NoSuchElementException();
        }
        int i = StdRandom.uniform(0, size);
        Item item = q[i];
        q[i] = q[size - 1];
        q[size - 1] = null;
        size--;
        if (size > 0 && size == q.length / 4) {
            resize(q.length / 2);
        }
        return item;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (isEmpty()) {
            throw new java.util.NoSuchElementException();
        }

        int i = StdRandom.uniform(0, size);
        return q[i];
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new Iterator<Item>() {
            private Item[] items = getShuffledCopyOfQueue();
            private int i = 0;

            private Item[] getShuffledCopyOfQueue() {
                items = (Item[]) new Object[size];
                for (int i = 0; i < size; i++) {
                    items[i] = q[i];
                }
                StdRandom.shuffle(items);
                return items;
            }


            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }

            @Override
            public boolean hasNext() {
                return i < items.length;
            }

            @Override
            public Item next() {
                if (hasNext()) {
                    return items[i++];
                }
                throw new java.util.NoSuchElementException();
            }
        };
    }

    // unit testing (required)
    public static void main(String[] args) {
        RandomizedQueue<String> queue = new RandomizedQueue<>();
        for (int i = 0; i < 10; i++) {
            queue.enqueue(String.valueOf(i));
        }
        StdOut.printf("size: %s\n", queue.size());
        StdOut.printf("dequeue: %s\n", queue.dequeue());
        StdOut.printf("dequeue: %s\n", queue.dequeue());
        StdOut.printf("sample: %s\n", queue.sample());
        StdOut.printf("isEmpty: %s\n", queue.isEmpty());
        for (String item : queue) {
            StdOut.printf("item: %s\n", item);
        }
    }

    private void resize(int capacity) {
        Item[] copy = (Item[]) new Object[capacity];
        for (int i = 0; i < size; i++) {
            copy[i] = q[i];
        }
        q = copy;
    }
}