package in.vikk.ds.tree;

import java.util.List;
import java.util.Queue;
import java.util.ArrayList;
import java.util.LinkedList;

public class BST {

    private class TreeNode {
        private int val;

        private TreeNode left;

        private TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder("Node = [");
//            if (left != null)
//                sb.append("\n\tLeft : [").append(left).append("], ");
            sb.append("\n\tData : [").append(val).append("]");
//            if (right != null)
//                sb.append(",\n\tRight : [").append(right).append("]");
            sb.append("]\n\t");
//
            return sb.toString();
        }

    }
    private TreeNode root;

    public void add(int val) {
        TreeNode newNode = new TreeNode(val);
        if (root == null) {
            root = newNode;
        }
        else {
            TreeNode current = root;
            while (true) {
                if (val < current.val) {
                    if (current.left == null) {
                        current.left = newNode;
                        break;
                    }
                    else {
                        current = current.left;
                    }
                }
                else if (val > current.val) {
                    if (current.right == null) {
                        current.right = newNode;
                        break;
                    }
                    else {
                        current = current.right;
                    }
                }
                else {
                    break;
                }
            }
        }
    }

    public List<List<Integer>> breadthFirstSearch() {
        List<List<Integer>> dataListList = new ArrayList<>();
        Queue<Queue<TreeNode>> nodesQueue = new LinkedList<>();

        Queue<TreeNode> nodeQueue = new LinkedList<>();
        nodeQueue.add(root);
        nodesQueue.add(nodeQueue);
        while (!nodesQueue.isEmpty()) {
            Queue<TreeNode> currentNodeQueue = nodesQueue.poll();
            Queue<TreeNode> nextNodeQueue = new LinkedList<>();
            List<Integer> dataList = new ArrayList<>();
            while (!currentNodeQueue.isEmpty()) {
                TreeNode node = currentNodeQueue.poll();

                dataList.add(node.val);

                if (node.left != null) {
                    nextNodeQueue.add(node.left);
                }

                if (node.right != null) {
                    nextNodeQueue.add(node.right);
                }
            }
            dataListList.add(dataList);
            if (!nextNodeQueue.isEmpty()) {
                nodesQueue.add(nextNodeQueue);
            }
        }

        return dataListList;
    }

    public int maxDepth() {
        return maxDepth(root);
    }

    public boolean isBalanced() {
        return isBalanced(root);
    }

    private int maxDepth(TreeNode node) {
        int depth = 0;
        if (node != null) {
            int leftDepth = maxDepth(node.left);
            int rightDepth = maxDepth(node.right);
            depth = Math.max(leftDepth, rightDepth) + 1;
        }
        return depth;
    }

    private boolean isBalanced(TreeNode node) {
        boolean isBalanced = true;

        if (node != null &&
                (node.left != null || node.right != null)) {
            isBalanced = (node.left != null && node.right != null)
                    && isBalanced(node.left)
                    && isBalanced(node.right);
        }

        return isBalanced;
    }

    private boolean isSymmetric(TreeNode node) {
        boolean isSymmetric = true;

        if (node != null &&
                (node.left != null || node.right != null)) {

        }

        return isSymmetric;
    }

    public String printHelper(TreeNode currPtr, String indent, boolean last) {
        StringBuilder sb = new StringBuilder();
        // print the tree structure on the screen
        if (currPtr != null) {
            sb.append(indent);
            if (last) {
                sb.append("R----");
                indent += "     ";
            } else {
                sb.append("L----");
                indent += "|    ";
            }
            sb.append(currPtr.val + "\n");

            sb.append(printHelper(currPtr.left, indent, false));
            sb.append(printHelper(currPtr.right, indent, true));

        }
        return sb.toString();
    }

    @Override
    public String toString() {
        return this.printHelper(this.root, "", true);
    }

    public static void main(String args[]) {
        BST bst = new BST();
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
        bst.add(0);
        System.out.println(bst);
        System.out.println(bst.breadthFirstSearch());
        System.out.println(bst.maxDepth());
        System.out.println(bst.isBalanced());


        bst = new BST();
        System.out.println(bst.isBalanced());
        bst.add(7);
        System.out.println(bst.isBalanced());
        bst.add(11);
        bst.add(4);
        bst.add(3);
        bst.add(5);
        bst.add(10);
        bst.add(12);

        System.out.println(bst.isBalanced());
        System.out.println(bst);



    }
}