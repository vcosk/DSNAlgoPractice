package in.vikk.ds.tree;

import java.util.Queue;
import java.util.List;
import java.util.LinkedList;
import java.util.ArrayList;

public class GraphTest {

    public static void main(String[] args) {
//        Tree nodeSet1 = new Tree(1,2,3);
//
//        System.out.println(isSymmetric(nodeSet1[0]));
//
//        TreeNode[] nodeSet2 = generateTreeNodes(1,2,2,3,4,4,3);
//        nodeSet2[0].left = nodeSet2[1];
//        nodeSet2[0].right= nodeSet2[2];
//
//        nodeSet2[1].left = nodeSet2[3];
//        nodeSet2[1].right= nodeSet2[4];
//
//        nodeSet2[2].left = nodeSet2[5];
//        nodeSet2[2].right= nodeSet2[6];
//
//        System.out.println(isSymmetric(nodeSet2[0]));
//
//        TreeNode[] nodeSet3 = generateTreeNodes(1,0);
//        nodeSet3[0] = nodeSet3[1];
//        System.out.println(isSymmetric(nodeSet3[0]));
//
//        TreeNode[] nodeSet4 = generateTreeNodes(1,2,2,3,3);

    }

//    public static boolean isSymmetric(TreeNode node) {
//
//        if (node != null && !(node.left == null && node.right == null)) {
//            if ((node.left != null && node.right == null) || (node.left == null && node.right != null)) {
//                return false;
//            }
//            Queue<Queue<TreeNode>> nodesQueue = new LinkedList<>();
//
//            Queue<TreeNode> nodeQueue = new LinkedList<>();
//            nodeQueue.add(node.left);
//            nodeQueue.add(node.right);
//            nodesQueue.add(nodeQueue);
//
//            while (!nodesQueue.isEmpty()) {
//                Queue<TreeNode> currentNodeQueue = nodesQueue.poll();
//                Queue<TreeNode> nextNodeQueue = new LinkedList<>();
//                List<Integer> dataList = new ArrayList<>();
//                while (!currentNodeQueue.isEmpty()) {
//                    TreeNode currentNode = currentNodeQueue.poll();
//
//                    if (currentNode == null) {
//                        dataList.add(null);
//                    }
//                    else {
//                        if (!(currentNode.left == null && currentNode.right == null)) {
//                            nextNodeQueue.add(currentNode.right);
//                            nextNodeQueue.add(currentNode.left);
//                        }
//
//                        dataList.add(currentNode.val);
//                    }
//                }
//
//                if (dataList.size() % 2 == 0) {
//                    for (int headIndex = 0, tailIndex = dataList.size() - 1; headIndex < tailIndex; headIndex++, tailIndex--) {
//                        if (dataList.get(headIndex) != dataList.get(tailIndex)) {
//                            return false;
//                        }
//                    }
//
//                    if (!nextNodeQueue.isEmpty()) {
//                        nodesQueue.add(nextNodeQueue);
//                    }
//                }
//                else {
//                    return false;
//                }
//            }
//        }
//
//        return true;
//    }

}
