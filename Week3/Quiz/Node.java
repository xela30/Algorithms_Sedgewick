import java.util.Iterator;

public class Node<T> implements Iterable<Node<T>> {

    public Node(T i) {
        this.item = i;
    }

    T item;
    Node<T> next;

    @Override
    public Iterator<Node<T>> iterator() {
        return new NodeIterator<T>(this);
    }
}