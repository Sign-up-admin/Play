package shujv;
//使用递归实现二叉树的
/*前，中，后序遍历
*
*
* */
public class TreeTraversal {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1,
                new TreeNode(2, new TreeNode(4), null),
                new TreeNode(3, new TreeNode(5), new TreeNode(6))
        );
        preOrder(root);
        System.out.println();
        inOrder(root);
        System.out.println();
        postOrder(root);
        System.out.println();
    }
//后序遍历方法
    private static void postOrder(TreeNode root) {
        if (root==null){
            return;
        }
        postOrder(root.Left);
        postOrder(root.right);
        System.out.print(root.val+"\t");
    }
//中序遍历方法
    private static void inOrder(TreeNode root) {
        if (root==null){
            return;
        }
        inOrder(root.Left);
        System.out.print(root.val+"\t");
        inOrder(root.right);
    }
//前序遍历方法
    private static void preOrder(TreeNode root) {
        if (root==null){
            return;
        }
        System.out.print(root.val+"\t");
        preOrder(root.Left);
        preOrder(root.right);
    }
}
