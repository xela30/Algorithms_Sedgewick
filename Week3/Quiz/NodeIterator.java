import java.util.Iterator;

public class NodeIterator<T> implements Iterator<Node<T>> {
    private final Node<T> _node;
    private Node<T> current;

    public NodeIterator(Node<T> node) {
        _node = node;
        current = _node;
    }


    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean hasNext() {
        return current != null;
    }

    @Override
    public Node<T> next() {
        if (hasNext()) {
            Node<T> result = current;
            current = current.next;
            return result;
        }
        throw new java.util.NoSuchElementException();
    }
}
