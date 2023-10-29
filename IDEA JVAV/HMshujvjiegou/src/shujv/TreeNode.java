package shujv;
//这是用链表实现的
public class TreeNode {
 public int val;//值
public TreeNode Left;//是树节点的左孩子

public TreeNode right;//是树节点的右孩子

    public TreeNode(int val) {
        this.val = val;
    }

    public TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        Left = left;
        this.right = right;
    }
//两种带参数的构造方法
    @Override
    public String toString() {
        return String.valueOf(this.val);
    }
}
