package shujv;



import java.awt.*;
import static shujv.RedBlackTree.Color.RED;//自己导入自己的枚举方法
import static shujv.RedBlackTree.Color.BLACK;
/*家人们
红黑树
*
* */
public class RedBlackTree {
    //枚举类型
    public enum Color{
        RED,BLACK;
    }
    Node root;
    static class Node {
        int key;
        Object value;
        Node left;
        Node right;
        Node parent;        // 父节点
        Color color = RED;  // 颜色

        public Node(int key, Object value) {
            this.key = key;
            this.value = value;
        }

        public Node(int key) {
            this.key = key;
        }

        public Node(int key, Color color) {
            this.key = key;
            this.color = color;
        }

        public Node(int key, Color color, Node left, Node right) {
            this.key = key;
            this.color = color;
            this.left = left;
            this.right = right;
            if (left != null) {
                left.parent = this;
            }
            if (right != null) {
                right.parent = this;
            }
        }

        // 是否是左孩子，第一个条件代表这个节点是否有孩子，第二二个条件看parent.left和当前节点是否相同
        boolean isLeftChild() {
            return parent != null && parent.left == this;
        }

        // 找叔叔，返回叔叔
        Node uncle() {
            //一个节点，没有父亲节点（根节点）一定没有叔叔节点，没有爷爷的节点一定没有叔叔节点
            if (parent == null || parent.parent == null) {
                return null;
            }
            if (parent.isLeftChild()) {
                return parent.parent.right;
            } else {
                return parent.parent.left;
            }
        }

        // 兄弟
        Node sibling() {
            if (parent == null) {
                return null;
            }
            if (this.isLeftChild()) {
                return parent.right;
            } else {
                return parent.left;
            }
        }
    }
}
