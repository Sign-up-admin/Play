package shujv;


import java.awt.*;

import static shujv.RedBlackTree.Color.RED;//自己导入自己的枚举方法
import static shujv.RedBlackTree.Color.BLACK;

/*家人们
红黑树 */
/*红黑树也是一种自平衡的二叉搜索树,较之AVL,插入和删除时旋转次数更少
红黑树特性
1.所有节点都有两种颜色:红与黑
2. 所有 null 视为黑色
3. 红色节点不能相邻
4.根节点是黑色
5. 从根到任意一个叶于节点,路径中的黑色节点数一样(黑色完美平衡)*/
public class RedBlackTree {
    //枚举类型
    public enum Color {
        RED, BLACK;
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

        // 是否是左孩子，第一个条件代表这个节点是否有孩子，第二个条件看parent.left和当前节点是否相同
        boolean isLeftChild() {
            //判断当前节点是父亲的左孩子。是返回true
            return parent != null && parent.left == this;
        }

        // 找叔叔，返回叔叔
        Node uncle() {
            /*比如说，一个节点，是它爷爷的左孩子的左孩子，那这个节点的叔叔就是爷爷的右孩子。
             *        一个节点，是它爷爷的左孩子的右孩子，那这个节点的叔叔就是爷爷的右孩子。
             *        一个节点，是它爷爷的右孩子的左孩子，那这个节点的叔叔就是爷爷的左孩子
             *        一个节点，是它爷爷的右孩子的右孩子，那这个节点的叔叔就是爷爷的左孩子
             *        就是说，一个节点他的爷爷的孩子，除了当前节点的爸爸，爷爷的孩子节点都是叔叔
             *   */

            //一个节点，没有父亲节点（根节点）一定没有叔叔节点，没有爷爷的节点一定没有叔叔节点
            if (parent == null || parent.parent == null) {
                return null;
            }
            //如果当前节点的父亲是爷爷的左孩子，那么返回爷爷的右孩子
            if (parent.isLeftChild()) {
                return parent.parent.right;
            } else {
                //不然返回爷爷的左孩子（当前节点的父亲是爷爷的右孩子）
                return parent.parent.left;
            }
        }

        // 兄弟
        Node sibling() {
            //根节点没有父亲
            if (parent == null) {
                return null;
            }
            //如果当前节点有父亲，而且是父亲的左孩子
            if (this.isLeftChild()) {
                //直接返回父亲的右孩子（兄弟）
                return parent.right;
            } else {
                //直接返回父亲的左孩子（兄弟）
                return parent.left;
            }
        }
    }
    //工具方法，判断当前节点是红色还是黑色,因为有null值，不用这个方法，去直接访问，会出现空指针

    public boolean isRed(Node node) {
        //所有null节点视为黑色,再判断cloor属性
        return node != null && node.color==RED;
    }
    public boolean isBlack(Node node) {
        //所有null节点视为黑色,再判断cloor属性
        return node == null || node.color==BLACK;
    }
    //右旋 包含1.对parent的处理 2.旋转后新根的父子关系
    //红（粉）色，是在上面，被下位，黄色是上位节点，绿色是换爹的节点

    public void rightRotate(Node pink){
        Node yellow=pink.left;
        Node green =yellow.left;
        //需要在旋转之前把它们的爹处理好
        /*1.旋转之前绿色的爹是指向黄色，旋转之后指向pink
        *
        * */
        yellow.right=pink;//yellow上位，red下来成为yellow的右孩子
        pink.left=green;//换爹，高度不变，
    }
}
