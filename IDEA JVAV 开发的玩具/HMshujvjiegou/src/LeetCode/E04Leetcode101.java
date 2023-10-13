package LeetCode;

import org.junit.jupiter.api.Test;
import shujv.TreeNode;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
//判断对称二叉树

public class E04Leetcode101 {
    public static void main(String[] args) {

    }
    @Test
    public void test1() {
        TreeNode root = new TreeNode(1,
                new TreeNode(2, new TreeNode(3), new TreeNode(4)),
                new TreeNode(2, new TreeNode(4), new TreeNode(3)));
        assertTrue(new E04Leetcode101().isSymmetric(root));
    }

    boolean isSymmetric(TreeNode root){
        return check(root.Left,root.right);
    }
    public boolean check(TreeNode left,TreeNode right){
        //如果同时左右两个孩子是null，是对称的
        if (left==null&&right==null){
            return true;
        }
        //如果左右两个孩子不能同时是null，是不对称的
        if (left==null||right==null){
            return false;
        }
        if (left.val!= right.val){
            return false;
        }
        //向下递归，内侧和外侧循环
        return check(left.Left,right.right)&&check(left.right,right.Left);
    }
}
