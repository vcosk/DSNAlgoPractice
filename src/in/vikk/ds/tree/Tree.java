package in.vikk.ds.tree;

import java.util.*;

public class Tree {

    private TreeNode root;

    public Tree (Integer... nodeValues) {
        this.root = buildTree(nodeValues);
    }

    public boolean isSymmetric() {
        return isSymmetric(root, root);
    }
    public List<Integer> inorder() {
        List<Integer> collector = new ArrayList<>();
        inorder(root, collector);
        return collector;
    }

    private TreeNode buildTree (Integer[] nodeValues) {
        TreeNode rootNode = null;

        if (nodeValues != null && nodeValues.length > 0) {
            TreeNode[] nodes = new TreeNode[nodeValues.length];
            for (int index = 0; index < nodeValues.length; index++) {
                nodes[index] = nodeValues[index] == null ? null : new TreeNode(nodeValues[index]);
            }
//            for (int index = 0; index < nodes.length && ((2*index+1) < nodes.length); index++) {
            int index = 0;
            for (TreeNode node : nodes) {
                int childNodeIndex = (2 * index + 1);
                if (node != null && childNodeIndex < nodes.length) {
                    node.left = nodes[childNodeIndex];
                    if (childNodeIndex + 1 < nodes.length) {
                        node.right = nodes[childNodeIndex + 1];
                    }
                    index += 1;
                }
            }
            rootNode = nodes[0];
        }
        return rootNode;
    }

//    private static boolean isSymmetric(TreeNode node) {
//        boolean isSymmetric = true;
//        List<Integer> inorder = new ArrayList<>();
//        inorder(node, inorder);
//
//        for (int headIndex = 0, tailIndex = inorder.size() - 1; headIndex < tailIndex; headIndex++, tailIndex--) {
//            if (inorder.get(headIndex) != inorder.get(tailIndex)) {
//                isSymmetric = false;
//                break;
//            }
//        }
//
//        return isSymmetric;
//    }

    private static void inorder(TreeNode node, List<Integer> colletor) {
        if (node != null) {
            if (node.left == null) {
                if (node.right != null) {
                    colletor.add(null);
                }
            }
            else {
                inorder(node.left, colletor);
            }
            colletor.add(node.val);

            if (node.right == null) {
                if (node.left != null) {
                    colletor.add(null);
                }
            }
            else {
                inorder(node.right, colletor);
            }
        }
    }

    private static boolean isSymmetric(TreeNode node1, TreeNode node2) {
        if (node1 == null && node2 == null) {
            return true;
        }

        if (node1 != null && node2 != null && node1.val == node2.val) {
            return isSymmetric(node1.left, node2.right) && isSymmetric(node1.right, node2.left);
        }

        return false;
    }

    public boolean hasPathSum(int sum) {
        return (root == null ? false : hasPathSum(root, 0, sum));
    }

    private static boolean hasPathSum(TreeNode node, int cummulative, int sum) {
        boolean hasPathSum = false;
        if (node != null) {
            if (node.left == null && node.right == null) {
                cummulative += node.val;
                hasPathSum = cummulative == sum;
            }
            else {
                boolean leftPathSum = false;
                boolean rightPatSum = false;

                cummulative += node.val;

                if (node.left != null) {
                    leftPathSum = hasPathSum(node.left, cummulative, sum);
                }

                if (node.right != null) {
                    rightPatSum = hasPathSum(node.right, cummulative, sum);
                }
                hasPathSum = (leftPathSum || rightPatSum);
            }
        }
        else {
            hasPathSum = cummulative == sum;
        }
        return hasPathSum;
    }

    public static void main(String[] args) {
        Integer[][] treeDataSet = {
//                {1,2,2,null,3,null,3},
//                {1,2,2,3,4,4,3},
//                {1,2,2},
//                {1,2,2,3,4,4,null},
//                {2,3,3,4,5,5,4,null,null,8,9,null,null,9,8},
//                {1,2,2,2,null,2},
//                {5,4,1,null,1,null,4,2,null,2,null},
//                {5,1,1,null,1,null,1,2,null,2,null},
//                {5,4,8,11,null,13,4,7,2,null,null,null,1},
//                {},
                {1,2},
                {1,1,null,2, null},
                {5,4,8,11,null,13,4,7,2,null,null,null,1}
        };

        for (Integer[] treeData : treeDataSet) {
            Tree tree = new Tree(treeData);
            System.out.println(Arrays.toString(treeData) + " "
                    + tree.inorder() + " " + tree.isSymmetric()
                    + " " + tree.hasPathSum(1) + " " + tree.hasPathSum(2) + " " + tree.hasPathSum(22));
        }
    }
}
