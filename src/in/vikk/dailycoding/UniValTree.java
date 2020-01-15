package in.vikk.dailycoding;

import in.vikk.ds.tree.BinaryTree;

//A unival tree (which stands for "universal value") is a tree where all nodes under it have the same value.
//Given the root to a binary tree, count the number of unival subtrees.

public class UniValTree<T extends Comparable<T>> extends BinaryTree<T> {

    private static class Counter {
        private int counter = 0;

        synchronized public void incrementCounter() {
            counter += 1;
        }

        public int getCounter() {
            return counter;
        }
    }

    public int countUnival() {
        Counter counter = new Counter();
        countUnival(this.root, counter);
        return counter.getCounter();
    }

    private boolean countUnival(Node<T> node, Counter counter) {

        if (node != null) {
            boolean leftCompare = true;
            boolean rightCompare = true;
            if (node.left != null && node.left.data.compareTo(node.data) != 0) {
                leftCompare = false;
            }

            if (node.right != null && node.right.data.compareTo(node.data) != 0) {
                rightCompare = false;
            }

            leftCompare &= countUnival(node.left, counter);
            rightCompare &= countUnival(node.right, counter);

            if (leftCompare && rightCompare) {
                counter.incrementCounter();
            }
            else {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        UniValTree<Integer> binaryTree1 = new UniValTree<>();
        binaryTree1.root = new Node<>(5);
        binaryTree1.root.left = new Node<>(4);
        binaryTree1.root.right = new Node<>(5);
        binaryTree1.root.left.left = new Node<>(4);
        binaryTree1.root.left.right = new Node<>(4);
        binaryTree1.root.right.right = new Node<>(5);
        System.out.println(binaryTree1);
        System.out.println(binaryTree1.countUnival());

        UniValTree<Integer> binaryTree2 = new UniValTree<>();
        binaryTree2.root = new Node<>(5);
        binaryTree2.root.left = new Node<>(1);
        binaryTree2.root.right = new Node<>(5);
        binaryTree2.root.left.left = new Node<>(5);
        binaryTree2.root.left.right = new Node<>(5);
        binaryTree2.root.right.right  = new Node<>(5);
        System.out.println(binaryTree2);
        System.out.println(binaryTree2.countUnival());

        UniValTree<Character> binaryTree3 = new UniValTree<>();
        binaryTree3.root = new Node<>('a');
        binaryTree3.root.left = new Node<>('a');
        binaryTree3.root.right = new Node<>('a');
        binaryTree3.root.right.left = new Node<>('a');
        binaryTree3.root.right.right = new Node<>('a');
        binaryTree3.root.right.right.right = new Node<>('A');
        System.out.println(binaryTree3);
        System.out.println(binaryTree3.countUnival());

        UniValTree<Character> binaryTree4 = new UniValTree<>();
        binaryTree4.root = new Node<>('a');
        binaryTree4.root.left = new Node<>('c');
        binaryTree4.root.right = new Node<>('b');
        binaryTree4.root.right.left = new Node<>('b');
        binaryTree4.root.right.right = new Node<>('b');
        binaryTree4.root.right.right.right = new Node<>('b');
        System.out.println(binaryTree4);
        System.out.println(binaryTree4.countUnival());
    }
}
