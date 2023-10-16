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
5. 从根到任意一个叶于节点（叶子节点是null值，黑色）,简单路径（不能出现向上访问）中的黑色节点数一样(黑色完美平衡)*/
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
        return node != null && node.color == RED;
    }

    public boolean isBlack(Node node) {
        //所有null节点视为黑色,再判断cloor属性
        return node == null || node.color == BLACK;
    }
    //右旋 包含1.对parent的处理 2.旋转后新根的父子关系（刷新爷爷节点的孩子们信息）
    //红（粉）色，是在上面，被下位，黄色是上位节点，绿色是换爹的节点

    public void rightRotate(Node pink) {
        Node partent = pink.parent;//拿到爷爷
        Node yellow = pink.left;
        Node green = yellow.left;
        //需要在旋转之前把它们的爹处理好
        /* 1.旋转之前绿色的爹是指向黄色，旋转之后指向pink
         * 2.黄色在旋转之前是pink粉色的，旋转之后如果是成为了根节点，就没有爹，不是成为根节点，就从爷爷节点的孙子变成爷爷的儿子
         * 3.pink在旋转之后，parent变成yellow
         * */
        if (green != null) {
            green.parent = pink;
        }
        yellow.parent = pink.parent;//因为如果yellow是根节点也没事，pink之前的parent是null

        yellow.right = pink;//yellow上位，red下来成为yellow的右孩子
        pink.left = green;//换爹，高度不变，
        pink.parent = yellow;
        //特殊情况
        if (partent == null) {
            root = yellow;

        }
        //如果原来爷爷的左孩子是pink（现在爷爷的left或者right还没更新，是指向pink）
        else if (partent.left == pink) {
            partent.left = yellow;//更新
        } else {
            partent.right = yellow;
        }
    }

    public void leftRotate(Node pink) {
        Node parent = pink.parent;
        Node yellow = pink.right;
        Node green = yellow.left;
        if (green != null) {
            green.parent = pink;
        }
        yellow.left = pink;
        yellow.parent = pink.parent;
        pink.right = green;
        pink.parent = yellow;
        if (parent == null) {
            root = yellow;
        } else if (parent.left == pink) {
            parent.left = yellow;
        } else {
            parent.right = yellow;
        }

    }

    //想必一定听说过，红黑树插入删除复杂和恐怖
    /*新增和更新
     * 正常增，遇到红红不平衡进行调整
     * params：key——键
     * value-值
     * */
    public void put(int key, Object value) {
        Node p = root;//指针
        Node parent = null;//记录父节点
        while (p != null) {
            parent = p;
            if (key < p.key) {
                p = p.left;
            } else if (key > p.key) {
                p = p.right;
            } else {
                p.value = value;//更新的逻辑
                return;
            }

        }
        //等循环结束，还没找到说明没有节点，走新建节点逻辑
        Node inserted = new Node(key, value);
        //是根节点的情况(作为第一个添加的节点)
        if (parent == null) {
            root = inserted;//那就成为根节点
        }
        //如果已经有节点了，需要添加节点，哪需要添加到parent的左孩子还是右孩子？
        else if (key < parent.key) {
            parent.left = inserted;
            inserted.parent = parent;//更新父亲信息
            //到
        } else {
            parent.right = inserted;
            inserted.parent = parent;
        }
        fixRedRed(inserted);

    }

    /*插入节点均视为红色节点--参见黑马红黑树
     * case 1 插入节点是根节点，直接将根节点变黑
     * case 2 插入节点的父亲节点是黑色，树的红黑性质不变，不进行调整
     * 插入节点的父亲为红色，触发红红相邻
     * case 3（通过改变节点颜色就能重新平衡的情况）
     *        叔叔为红色 父亲变为黑色节点，为了保证黑色平衡，连带的叔叔节点也变为黑色节点
     *        爷爷如果是黑色不变，会造成这颗子树黑色过多，因此爷爷节点变为红色节点
     *        爷爷如果变成红色，可能会接着触发红红相邻，因此对爷爷进行递归调整
     * case 4 叔叔为黑色
     *
     * */
    //重新恢复平衡方法，针对父节点和子节点都是红色的情况，
    public void fixRedRed(Node x) {
        //case 1 插入节点是根节点，直接将根节点变黑
        if (x == root) {
            x.color = BLACK;
            return;
        }
        //case 2 插入节点的父亲节点是黑色，树的红黑性质不变，不进行调整
        if (isBlack(x.parent)) {
            return;
        }
        //case3,先拿到父亲，叔叔，爷爷
        Node parent = x.parent;
        Node unlce = x.uncle();
        Node grandParnet = x.parent.parent;
        //解决叔叔是红色的情况，也是递归的条件
        if (isRed(unlce)) {
            parent.color = BLACK;
            unlce.color = BLACK;
            grandParnet.color = RED;
            fixRedRed(grandParnet);
            return;
        }
        /*case4 叔叔为黑色节点（需要旋转和变色）
         *1.父亲为左孩子，插入点也是左孩子，此时是LL不平衡（父亲变黑祖父变红，对爷爷和父亲进行一次右旋）
         *2.父亲为左孩子，插入节点是右孩子，此时LR不平衡（先父亲进行左旋，再）
         *3.父亲为右孩子，插入节点也是右孩子，此时RR不平衡
         *4.父亲为右孩子，插入节点是左孩子，此时RL不平衡
         *
         * */
        //情况1
        if (parent.isLeftChild() && x.isLeftChild()) {
            parent.color = BLACK;//
            grandParnet.color = RED;
            rightRotate(grandParnet);
            //情况2
        } else if (parent.isLeftChild() && !x.isLeftChild()) {
            leftRotate(parent);
            x.color = BLACK;
            grandParnet.color = RED;
            rightRotate(grandParnet);
            //情况3
        } else if (!parent.isLeftChild() && !x.isLeftChild()) {
            parent.color = BLACK;
            grandParnet.color = RED;
            leftRotate(grandParnet);
            //情况4
        } else if (!parent.isLeftChild() && x.isLeftChild()) {
            rightRotate(parent);
            x.color = BLACK;
            grandParnet.color = RED;
            leftRotate(grandParnet);

        }

    }

    /*删除
     * 正常删除，会用到李代桃僵技巧，遇到黑黑不平衡进行调整
     * params：key——键
     * */
    public void remove(int key) {

    }

    //查找节点方法
    public Node find(int key) {
        Node p = root;//指针节点
        //循环查找
        while (p != null) {
            if (p.key < key) {
                p = p.left;//向左查找
            } else if (p.key > key) {
                p = p.right;//向右查找
            } else {
                return p;
            }
        }
        //循环完了没找到，返回null
        return null;
    }

    //查找剩余节点方法，意思就是查找删除剩下的（或者说这个节点的后继节点） 分几种情况 这一个节点没有孩子的时候 还有有两个孩子的时候，
    //哪谁算删除剩下的呢
    Node findReplaced(Node deleted) {
        //第一种情况，这个节点没有孩子（准确的说是红黑树中的nill叶子节点是黑色的null）
        if (deleted.left == null && deleted.right == null) {
            return null;
        }
        //第二种情况，它有一个孩子
        if (deleted.left == null) {
            return deleted.right;
        }
        if (deleted.right == null) {
            return deleted.left;

        }
        //我们要到它的右子树去查找它的后继节点
        //所以它的left
        Node s = deleted.right;//继续向右查找
        while (s.left != null) {
            s = s.left;
        }
        return s;

    }


}
