import java.util.Collection;
import java.util.Comparator;
import java.util.TreeSet;
import java.util.function.Supplier;

public class Tree<T extends Comparable<T>> {
    private final Node<T> root;
    private final Supplier<Collection<Node<T>>> collectionSupplier;

    public Tree(Supplier<Collection<Node<T>>> collectionSupplier) {
        this.collectionSupplier = collectionSupplier;
        this.root = createNode();
    }

    public Node<T> getRoot() {
        return root;
    }

    public Node<T> createNode() {
        return new Node<>(null, null, collectionSupplier);
    }

    public static class Node<T extends Comparable<T>> {
        private T data;
        private final Node<T> parent;
        private final Collection<Node<T>> children;
        private final Supplier<Collection<Node<T>>> collectionSupplier;

        private Node(T data, Node<T> parent, Supplier<Collection<Node<T>>> collectionSupplier) {
            this.data = data;
            this.parent = parent;
            this.collectionSupplier = collectionSupplier;
            this.children = collectionSupplier.get();
        }

        public T getData() {
            return data;
        }

        public void setData(T data) {
            this.data = data;
        }

        public Node<T> getParent() {
            return parent;
        }

        private void removeFromParent() {
            if(null != this.parent) {
                this.parent.children.remove(this);
            }
        }

        public Node<T> withParent(Node<T> parent) {
            removeFromParent();
            Node<T> node = new Node<>(data, parent, collectionSupplier);
            node.children.add(node);
            return node;
        }

        public Node<T> withCollection(Supplier<Collection<Node<T>>> collectionSupplier) {
            removeFromParent();
            Node<T> node = new Node<>(data, parent, collectionSupplier);
            node.children.addAll(this.children);
            return node;
        }

        public Collection<Node<T>> getChildren() {
            return children;
        }

        public Node<T> createNode(T data) {
            Node<T> node = new Node<>(data, this, collectionSupplier);
            this.children.add(node);
            return node;
        }
    }
}