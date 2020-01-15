package in.vikk.ds.tree.heap;

public class BinomialHeap<T extends Comparable<T>> {

    private Node<T> head = null;

    public BinomialHeap() {}



    private static class Node<T extends Comparable<T>> {
        private T payload;

        private Node<T> nextSibling;

        private Node<T> leftMostChild;

        private Node<T> parent;

        private int degree = 0;

        private Node(T payload) {
            this.payload = payload;
        }


    }
}
