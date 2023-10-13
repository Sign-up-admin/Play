package LeetCode;
//翻转二叉树

//import javax.swing.tree.TreeNode;

import shujv.TreeNode;

public class E07Leetcode226 {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1,
                new TreeNode(2,
                        new TreeNode(4),
                        new TreeNode(5,
                                new TreeNode(7), new TreeNode(6))),
                new TreeNode(3));
        new E07Leetcode226().invertTree(root);
        System.out.println(root);
    }
    public TreeNode invertTree(TreeNode root){
        fn(root);
        return root;
    }
    public static void fn(TreeNode node){
        if (node==null){
            return;

        }
        TreeNode t=node;
        node.Left=node.right;
        node.right=t.Left;
        fn(node.right);
        fn(node.Left);

    }
}
