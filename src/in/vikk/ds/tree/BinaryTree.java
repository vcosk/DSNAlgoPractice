package in.vikk.ds.tree;

public class BinaryTree<T extends Comparable<T>> {

    protected static class Node<T extends Comparable<T>> {
        public T data;
        public Node<T> left;
        public Node<T> right;

        public Node(T data) {
            this.data = data;
        }
    }

    protected Node<T> root;

    protected String pretyPrint(Node<T> node, String indent, boolean lastNode) {
        StringBuilder sb = new StringBuilder();
        if (node != null) {
            sb.append(indent);
            if (lastNode) {
                sb.append("R---->");
                indent += "    ";
            } else {
                sb.append("L---->");
                indent += "|    ";
            }
            sb.append(node.data).append("\n");
            sb.append(pretyPrint(node.left, indent, false));
            sb.append(pretyPrint(node.right, indent, true));
        }
        return sb.toString();
    }

    @Override
    public String toString() {
        return pretyPrint(this.root, "", true);
    }
}
