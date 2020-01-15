package in.vikk.ds.tree;

public class BinarySearchTree<T extends Comparable<T>> {

    protected Node<T> root;

    public BinarySearchTree() {
    }

    public void add(T data) {
        Node<T> newNode = new Node<>(data);
        if (root == null) {
            root = newNode;
        }
        else {
            add(root, newNode);
        }
    }

    public boolean delete(T data) {
        return delete(this.root, data);
    }

    public boolean find(T data) {
        boolean found = false;
        Node<T> current = root;
        while (current != null) {
            int comparision = data.compareTo(current.data);
            if (comparision < 0) {
                current = current.getLeft();
            } else if (comparision > 0) {
                current = current.getRight();
            } else {
                found = true;
                break;
            }
        }
        return found;
    }

    @Override
    public String toString() {
        return printHelper(this.root, "", true);
    }

    protected Node<T> getSuccessor(Node<T> node) {
        Node<T> successor = null;
        if (node != null) {
            successor = node.getRight();
            while (successor != null && successor.getLeft() !=null) {
                successor = successor.getLeft();
            }
        }
        return successor;
    }

    protected Node<T> getPredecessor(Node<T> node) {
        Node<T> predecessor = null;
        if (node != null) {
            predecessor = node.getLeft();
            while (predecessor != null && predecessor.getRight() != null) {
                predecessor = predecessor.getRight();
            }
        }
        return predecessor;
    }

    protected void add(Node<T> testNode, Node<T> newNode) {
        int comparision = testNode.getData().compareTo(newNode.getData());
        // If the value is greater thank the current node, go right.
        if (comparision < 0) {
            if (testNode.getRight() == null) {
                testNode.setRight(newNode);
                newNode.setParent(testNode);
                newNode.setLeft(false);
            }
            else {
                add(testNode.getRight(), newNode);
            }
        }
        // Else go left
        else if (comparision > 0) {
            if (testNode.getLeft() == null) {
                testNode.setLeft(newNode);
                newNode.setParent(testNode);
                newNode.setLeft(true);
            }
            else {
                add(testNode.getLeft(), newNode);
            }
        }
    }

    protected boolean delete(Node<T> node, T data) {
        if (node != null) {
            int comparision = node.getData().compareTo(data);
            if (comparision > 0) {
                return delete(node.getLeft(), data);
            }
            else if (comparision < 0) {
                return delete(node.getRight(), data);
            }
            else {
                Node<T> successor = getSuccessor(node);
                Node<T> parent = node.getParent();
                if (successor == null) {
                    if (parent == null) {
                        this.root = node.getLeft();
                    }
                    else {
                        if (node.isLeft()) {
                            parent.setLeft(node.getLeft());
                        }
                        else {
                            parent.setRight(node.getLeft());
                        }
                        node.getLeft().setParent(parent);
                    }
                }
                else {
                    successor.setLeft(node.getLeft());
                    successor.getLeft().setParent(successor);
                    if (!successor.getParent().equals(node)) {
                        Node<T> successorRight = successor.getRight();
                        if (successorRight != null) {
                            successor.getParent().setLeft(successorRight);
                            successorRight.setParent(successor.getParent());
                            successorRight.setLeft(true);
                        }
                        successor.setRight(node.getRight());
                    }
                    successor.setParent(parent);

                    // Root node
                    if (parent == null) {
                        this.root = successor;
                    }
                    else {
                        successor.setParent(node.getParent());
                        if (node.isLeft()) {
                            successor.getParent().setLeft(successor);
                            successor.setLeft(true);
                        }
                        else {
                            successor.getParent().setRight(successor);
                        }
                    }
                }

                return true;
            }
        }

        return false;
    }

    private static <T> String printHelper(Node<T> node, String indent, boolean rightNode) {
        StringBuilder sb = new StringBuilder();

        if (node != null) {
            sb.append(indent);
            if (rightNode) {
                sb.append("R----");
                indent += "     ";
            }
            else {
                sb.append("L----");
                indent += "|    ";
            }
            sb.append(node.getData());
            sb.append("\n");
            sb.append(printHelper(node.getLeft(), indent, false));
            sb.append(printHelper(node.getRight(), indent, true));
        }

        return sb.toString();
    }
    protected static class Node<T> {
        protected Node<T> left;
        protected Node<T> right;
        protected Node<T> parent;
        protected boolean isLeft;
        protected T data;


        public Node(T data) {
            this.data = data;
        }

        public Node<T> getLeft() {
            return left;
        }

        public void setLeft(Node<T> left) {
            this.left = left;
        }

        public Node<T> getRight() {
            return right;
        }

        public void setRight(Node<T> right) {
            this.right = right;
        }

        public Node<T> getParent() {
            return parent;
        }

        public void setParent(Node<T> parent) {
            this.parent = parent;
        }

        public void setLeft(boolean left) {
            isLeft = left;
        }

        public boolean isLeft() {
            return isLeft;
        }

        public T getData() {
            return data;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder("Node = [");
//            if (left != null)
//                sb.append("\n\tLeft : [").append(getLeft()).append("], ");
            if (data != null)
                sb.append("Data : [").append(getData()).append("]");
//            if (right != null)
//                sb.append(", Right : [").append(getRight()).append("]");
            sb.append("]\n\t");

            return sb.toString();
        }

        @Override
        public int hashCode() {
            return data.hashCode();
        }

        @Override
        public boolean equals(Object obj) {
            if (obj != null && obj instanceof Node) {
                return data.equals(((Node)obj).getData());
            }
            return false;
        }
    }
    public static void main(String[] args) {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();

        bst.add(7);
        bst.add(11);
        bst.add(4);
        bst.add(3);
        bst.add(5);
        bst.add(2);
        bst.add(1);
        bst.add(10);
        bst.add(12);
        bst.add(8);
        bst.add(9);
        bst.add(6);

//        System.out.println(bst);
//        System.out.println(bst.delete(7));
//        System.out.println(bst);
//        System.out.println(bst.delete(3));
        System.out.println(bst);
        System.out.println(bst.getPredecessor(bst.root));
        System.out.println(bst.delete(4));
        System.out.println(bst);
        System.out.println(bst.delete(7));
        System.out.println(bst);
        System.out.println(bst.getPredecessor(bst.root.getRight()));
    }
}
