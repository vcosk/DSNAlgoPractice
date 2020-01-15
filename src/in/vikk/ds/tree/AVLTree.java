
package in.vikk.ds.tree;
public class AVLTree <T extends Comparable<T>> {

    protected static class Node<T extends Comparable<T>> {

        protected int height;
        protected Node<T> left;
        protected Node<T> right;
        protected Node<T> parent;
        protected boolean isLeft;

        protected T data;

        public Node(T data) {
            this.data = data;
            this.height = 0;
        }

        public int getBalanceFactor() {
            return (right == null ? 0 : right.height) - (left == null ? 0 : left.height);
        }

        public Node<T> getLeft() {
            return left;
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

        public void setLeft(Node<T> left) {
            this.left = left;
        }

        public boolean isLeft() {
            return parent == null ? false : data.compareTo(parent.getData()) < 0;
        }

        public T getData() {
            return data;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            if (data != null)
                sb.append("Data : [").append(getData()).append("] [")
                        .append(getBalanceFactor()).append("] [").append(height);
            sb.append("]");

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

    protected Node<T> root;

    public int height() {
        return height(root);
    }

    private int height(Node node) {
        if (node != null) {
            int leftHeight = height(node.left);
            int rightHeight = height(node.right);
            node.height = Math.max(leftHeight, rightHeight) + 1;
            return node.height;
        }
        return 0;
    }

    public void add(T data) {
        Node<T> newNode = new Node<>(data);
        if (root == null) {
            root = newNode;
        }
        else {
            add(root, newNode);
        }
        updateHeight(newNode);
    }

    private void updateHeight(Node<T> node) {
        if (node != null) {
            int leftHeight = 0;
            int rightHeight = 0;

            if (node.left != null) {
                leftHeight = node.left.height;
            }

            if (node.right != null) {
                rightHeight = node.right.height;
            }

            node.height = Math.max(leftHeight, rightHeight) + 1;

            if (Math.abs(node.getBalanceFactor()) > 1) {
                balanceTree(node);
            }
            else {
                updateHeight(node.parent);
            }
        }
    }

    public boolean delete(T data) {
        Node<T> node = find(data, this.root);
        if (node == null) {
            return false;
        }
        Node<T> parent = node.getParent();
        Node<T> predecessor = getPredecessor(node);

        Node<T> right = node.getRight();
        Node<T> left = node.getLeft();

        if (predecessor == null) {
            if (parent == null) {
                this.root = node.getRight();
                this.root.setParent(null);
            } else {
                if (node.isLeft()) {
                    parent.setLeft(right);
                } else {
                    parent.setRight(right);
                }

                if (right != null) {
                    right.setParent(parent);
                }
                updateHeight(parent);
            }
        } else {
            Node<T> predLeft = predecessor.getLeft();
            Node<T> predParent = predecessor.getParent();

            if (node != predParent) {
                predecessor.setLeft(left);
                left.setParent(predecessor);
                predParent.setRight(predLeft);
            }

            predecessor.setRight(right);
            if (node.getRight() != null) {
                node.getRight().setParent(predecessor);
            }

            if (parent == null) {
                this.root = predecessor;
                predecessor.setParent(null);
            } else {
                predecessor.setParent(node.getParent());
                if (node.isLeft()) {
                    node.getParent().setLeft(predecessor);
                } else {
                    node.getParent().setRight(predecessor);
                }
                if (right != null) {
                    right.setParent(predecessor);
                }
            }

            height(predParent);
        }
        return true;
    }

    public boolean find(T data) {
        boolean found = find(data, root) != null;
        return found;
    }

    private Node<T> find(T data, Node<T> node) {
        if (node != null) {
            int comparision = node.getData().compareTo(data);
            if (comparision == 0) {
                return node;
            }
            else if (comparision > 0) {
                return find(data, node.getLeft());
            }
            else {
                return find(data, node.getRight());
            }
        }
        return null;
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
            }
            else {
                add(testNode.getLeft(), newNode);
            }
        }
    }

    private void balanceTree(Node<T> node) {
        int balanceFactor = node.getBalanceFactor();
        if (balanceFactor > 1) {
            if (node.getRight().getBalanceFactor() < 0) {
                rightRotate(node.getRight());
            }
            leftRotate(node);
        }
        else if (balanceFactor < -1) {
            if (node.getLeft().getBalanceFactor() > 0) {
                leftRotate(node.getLeft());
            }
            rightRotate(node);
        }
        if (node.equals(this.root)) {
            this.root = node.getParent();
        }
    }

    private void leftRotate(Node<T> node) {
        if (node != null) {
            Node<T> parent = node.getParent();
            Node<T> right = node.getRight();
            Node<T> left = node.getLeft();

            if (parent != null) {
                if (node.isLeft()) {
                    parent.setLeft(right);
                }
                else {
                    parent.setRight(right);
                }
            }

            right.setParent(parent);
            node.setRight(right.getLeft());
            if (right.getLeft() != null) {
                right.getLeft().setParent(node);
            }
            right.setLeft(node);
            node.setParent(right);

            updateHeight(node);
        }
    }

    private void rightRotate(Node<T> node) {
        if (node != null) {
            Node<T> parent = node.getParent();
            Node<T> left = node.getLeft();
            Node<T> right = node.getRight();

            if (parent != null) {
                if (node.isLeft()) {
                    parent.setLeft(left);
                }
                else {
                    parent.setRight(left);
                }
            }

            left.setParent(parent);
            node.setParent(left);
            node.setLeft(left.getRight());
            if (left.getRight() != null) {
                left.getRight().setParent(node);
            }
            left.setRight(node);

            updateHeight(node);
        }
    }

    private static <T extends Comparable<T>> String printHelper(Node<T> node, String indent, boolean rightNode) {
        StringBuilder sb = new StringBuilder();

        if (node != null) {
            sb.append(indent);
            if (rightNode) {
                sb.append("R---->");
                indent += "     ";
            }
            else {
                sb.append("L---->");
                indent += "|    ";
            }
            sb.append(node.getData()).append("[" + node.height+"]").append("[" + node.getBalanceFactor()+"]");
            sb.append("\n");
            sb.append(printHelper(node.getLeft(), indent, false));
            sb.append(printHelper(node.getRight(), indent, true));
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        AVLTree<Integer> bst = new AVLTree<>();
//        bst.add(4);
//        bst.add(6);
//        bst.add(7);
//        bst.add(5);
//        bst.add(2);
//        bst.add(3);
//        bst.add(1);
//
//        System.out.println(bst);
//        bst.delete(2);
//        System.out.println(bst);

        bst = new AVLTree<>();
        bst.add(6);
        bst.add(8);
        System.out.println(bst);
        bst.add((10));
        System.out.println(bst);
        bst.add((4));
        System.out.println(bst);
        bst.add((3));
        System.out.println(bst);
        bst.add((2));
        System.out.println(bst);
        bst.add((1));
        System.out.println(bst);
        bst.add(15);
        System.out.println(bst);
        bst.add(20);
        System.out.println(bst);
        bst.add(25);
        System.out.println(bst);
        bst.add(30);
        System.out.println(bst);
        bst.add(23);
        System.out.println(bst);
        bst.add(22);
        System.out.println(bst);
        bst.add(27);
        System.out.println(bst);
        bst.add(28);
        System.out.println(bst);
        System.out.println("***DELETE***");
        bst.delete(15);
        System.out.println(bst);
        bst.delete(28);
        bst.delete(22);
        bst.delete(25);
        bst.delete(20);
        System.out.println(bst);
        bst.delete(8);
        System.out.println(bst);
        bst.delete(6);
        System.out.println(bst);
        bst.delete(1);
        System.out.println(bst);

        bst = new AVLTree<>();
        bst.add(50);
        bst.add(30);
        bst.add(70);
        bst.add(80);
        bst.add(60);
        System.out.println(bst);
//        bst.add(55);
        System.out.println(bst);
    }
}
