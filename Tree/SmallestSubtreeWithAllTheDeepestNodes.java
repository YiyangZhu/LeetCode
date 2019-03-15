package Tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

public class SmallestSubtreeWithAllTheDeepestNodes {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    int max;
    List<TreeNode> list;

    public TreeNode subtreeWithAllDeepest(TreeNode root) {
        if (root == null) {
            return null;
        }
        Map<TreeNode, TreeNode> parentMap = new HashMap<>();
        Map<TreeNode, Integer> nodeDepthMap = new HashMap<>();
        max = -1;
        list = new ArrayList<>();
        buildMap(root, null, 0, parentMap, nodeDepthMap);
        if (list.size() == 1) {
            return list.get(0);
        }
        TreeNode p1 = parentMap.get(list.get(0));
        for (TreeNode t : list) {
            TreeNode p2 = parentMap.get(t);
            while (p1 != p2 && p1 != null && p2 != null) {
                int p1Depth = nodeDepthMap.get(p1);
                int p2Depth = nodeDepthMap.get(p2);
                if (p1Depth == p2Depth) {
                    p1 = parentMap.get(p1);
                    p2 = parentMap.get(p2);
                } else if (p1Depth > p2Depth) {
                    p1 = parentMap.get(p1);
                } else {
                    p2 = parentMap.get(p2);
                }
            }
        }
        if (p1 == null) {
            return root;
        } else {
            return p1;
        }
    }

    private void buildMap(TreeNode node, TreeNode parentNode, int depth, Map<TreeNode, TreeNode> parentMap, Map<TreeNode, Integer> nodeDepthMap) {
        if (node == null) {
            return;
        }
        parentMap.put(node, parentNode);
        nodeDepthMap.put(node, depth);
        if (depth >= max) {
            max = depth;
            if (list.size() != 0 && nodeDepthMap.get(list.get(0)) != max) {
                list.clear();
            }
            list.add(node);
        }
        buildMap(node.left, node, depth + 1, parentMap, nodeDepthMap);
        buildMap(node.right, node, depth + 1, parentMap, nodeDepthMap);
    }

    public TreeNode subtreeWithAllDeepestMethod2(TreeNode root) {
        if (root == null) {
            return null;
        }
        Map<TreeNode, Integer> map = new HashMap<>();
        buildMap(root, map);
        return findSubtree(root, map);
    }

    private int buildMap(TreeNode node, Map<TreeNode, Integer> map) {
        if (node == null) {
            return -1;
        }
        int left = buildMap(node.left, map);
        int right = buildMap(node.right, map);
        int distance = Math.max(left, right) + 1;
        map.put(node, distance);
        return distance;
    }

    private TreeNode findSubtree(TreeNode root, Map<TreeNode, Integer> map) {
        if (root.left == null && root.right == null) {
            return root;
        }
        if (root.left == null) {
            return findSubtree(root.right, map);
        }
        if (root.right == null) {
            return findSubtree(root.left, map);
        }
        int left = map.get(root.left);
        int right = map.get(root.right);
        if (left == right) {
            return root;
        } else if (left > right) {
            return findSubtree(root.left, map);
        } else {
            return findSubtree(root.right, map);
        }
    }
}