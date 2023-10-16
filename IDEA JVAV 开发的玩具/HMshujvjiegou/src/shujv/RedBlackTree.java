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

    public Node root;

    public static class Node {

        public int key;
        Object value;
        public Node left;
        public Node right;
        Node parent;        // 父节点
        public Color color = RED;  // 颜色

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

    // 右旋 1. parent 的处理 2. 旋转后新根的父子关系
    private void rightRotate(Node pink) {
        Node parent = pink.parent;//拿到爷爷
        Node yellow = pink.left;
        //需要在旋转之前把它们的爹处理好
        /* 1.旋转之前绿色的爹是指向黄色，旋转之后指向pink
         * 2.黄色在旋转之前是pink粉色的，旋转之后如果是成为了根节点，就没有爹，不是成为根节点，就从爷爷节点的孙子变成爷爷的儿子
         * 3.pink在旋转之后，parent变成yellow
         * */
        Node green = yellow.right;
        if (green != null) {
            green.parent = pink;
        }
        yellow.right = pink;//因为如果yellow是根节点也没事，pink之前的parent是null(bug)
        yellow.parent = parent;//yellow上位，red下来成为yellow的右孩子
        pink.left = green;//换爹，高度不变，
        pink.parent = yellow;
        //特殊情况
        if (parent == null) {
            root = yellow;
            //如果原来爷爷的左孩子是pink（现在爷爷的left或者right还没更新，是指向pink）
        } else if (parent.left == pink) {
            parent.left = yellow;
        } else {
            parent.right = yellow;
        }
    }

    // 左旋
    private void leftRotate(Node pink) {
        Node parent = pink.parent;
        Node yellow = pink.right;
        Node green = yellow.left;
        if (green != null) {
            green.parent = pink;
        }
        yellow.left = pink;
        yellow.parent = parent;
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
    void fixRedRed(Node x) {
        // case 1 插入节点是根节点，变黑即可
        if (x == root) {
            x.color = BLACK;
            return;
        }
        // case 2 插入节点父亲是黑色，无需调整
        if (isBlack(x.parent)) {
            return;
        }
        /* case 3 当红红相邻，叔叔为红时
            需要将父亲、叔叔变黑、祖父变红，然后对祖父做递归处理
        */
        Node parent = x.parent;
        Node uncle = x.uncle();
        Node grandparent = parent.parent;
        //解决叔叔是红色的情况，也是递归的条件
        if (isRed(uncle)) {
            parent.color = BLACK;
            uncle.color = BLACK;
            grandparent.color = RED;
            fixRedRed(grandparent);
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
        if (parent.isLeftChild() && x.isLeftChild()) { // LL
            parent.color = BLACK;
            grandparent.color = RED;
            rightRotate(grandparent);
            //情况2
        } else if (parent.isLeftChild()) { // LR
            leftRotate(parent);
            x.color = BLACK;
            grandparent.color = RED;
            rightRotate(grandparent);
            //情况3
        } else if (!x.isLeftChild()) { // RR
            parent.color = BLACK;
            grandparent.color = RED;
            leftRotate(grandparent);
            //情况4
        } else { // RL
            rightRotate(parent);
            x.color = BLACK;
            grandparent.color = RED;
            leftRotate(grandparent);
        }
    }

    //写个具体的删除操作doRemove，一个递归调用的方法
    /*case0 如果节点有两个节点，
     *
     * 只要是删除黑色节点，就一定会涉及重新平衡，因为简单路径上的黑色节点少了
     *重新恢复平衡的办法就是让它的兄弟节点变成红色
     * 删除黑色有一个特别简单的情况，删除的是黑色节点，repalaced节点变成黑色
     *
     * 红色节点的删除，实际上不需要重新平衡，因为红色节点是只有一个孩子的情况下，
     * 删除了不影响平衡，有两个孩子的情况下，删除节点实际上会转化成删除只有一个孩子的情况
     *________________________________________________________下是双黑方法介绍
     *
     * 删除节点和剩下节点都是黑),触发双黑,双黑意思是,少了一个黑
        case 3: 删除节点或剩余节点的兄弟为红,此时两个侄于定为黑
        case 4:删除节点或剩余节点的兄弟、和兄弟孩子都为黑
        case 5: 删除节点的兄弟为黑,至少一个红 侄于
        case 5:被调整节点的兄弟为黑,至少一个红 侄子
        如果兄弟是左孩子,左侄于是红,LL不平衡
        如果兄弟是左孩子,右侄于是红,LR 不平衡
        如果兄弟是右孩子,右侄于是红,RR不平衡
        如果兄弟是右孩子,左侄于是红,RL 不平衡
        *
        * */
    //deleted和repalaced都是黑色节点的情况，我们抽象成一个方法
    public void remove(int key) {
        Node deleted = find(key);
        if (deleted == null) {
            return;
        }
        doRemove(deleted);
    }

    public boolean contains(int key) {
        return find(key) != null;
    }

    // 查找删除节点
    private Node find(int key) {
        Node p = root;
        while (p != null) {
            if (key < p.key) {//向左查找
                p = p.left;
            } else if (p.key < key) {//向右查找
                p = p.right;
            } else {
                return p;
            }
        }
        //循环完了没找到，返回null
        return null;
    }

    //查找剩余节点方法，意思就是查找删除剩下的（或者说这个节点的后继节点） 分几种情况 这一个节点没有孩子的时候 还有有两个孩子的时候，
    //哪谁算删除剩下的呢
    //这个后继节点意思是代替删除节点的节点，将来它的left指向被删除节点的left，right指向被删除节点的right
    /*删除
     * 正常删除，会用到李代桃僵技巧(findReplaced方法)，遇到黑黑不平衡进行调整
     * 这个李代桃僵技巧就是说把后继节点和被删除节点，交换一下key值和数值，这样就可以避免进入有两个孩子的情况
     *
     * params：key——键
     * */
    private Node findReplaced(Node deleted) {
        //第一种情况，这个节点没有孩子（准确的说是红黑树中的nill叶子节点是黑色的null）
        if (deleted.left == null && deleted.right == null) {
            return null;
        }
        //第二种情况，它有一个孩子，优先右孩子替代，
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

    // 处理双黑 (case3、case4、case5)
    private void fixDoubleBlack(Node x) {
        //结束递归的条件，代表访问到了树的顶部
        if (x == root) {
            return;
        }
        Node parent = x.parent;
        Node sibling = x.sibling();
        // case 3 兄弟节点是红色
        if (isRed(sibling)) {
            if (x.isLeftChild()) {
                leftRotate(parent);
            } else {
                rightRotate(parent);
            }
            parent.color = RED;
            sibling.color = BLACK;
            fixDoubleBlack(x);
            return;
        }
        if (sibling != null) {
            // case 4 兄弟是黑色, 两个侄子也是黑色
            if (isBlack(sibling.left) && isBlack(sibling.right)) {
                sibling.color = RED;
                if (isRed(parent)) {
                    parent.color = BLACK;
                } else {
                    fixDoubleBlack(parent);
                }
            }
            // case 5 兄弟是黑色, 侄子有红色
            else {
                // LL
                if (sibling.isLeftChild() && isRed(sibling.left)) {
                    rightRotate(parent);
                    sibling.left.color = BLACK;
                    sibling.color = parent.color;
                }
                // LR
                else if (sibling.isLeftChild() && isRed(sibling.right)) {
                    sibling.right.color = parent.color;
                    leftRotate(sibling);
                    rightRotate(parent);
                }
                // RL
                else if (!sibling.isLeftChild() && isRed(sibling.left)) {
                    sibling.left.color = parent.color;
                    rightRotate(sibling);
                    leftRotate(parent);
                }
                // RR
                else {
                    leftRotate(parent);
                    sibling.right.color = BLACK;
                    sibling.color = parent.color;
                }
                parent.color = BLACK;
            }
        } else {
            // @TODO 实际也不会出现，触发双黑后，兄弟节点不会为 null
            fixDoubleBlack(parent);
        }
    }

    //写个具体的删除操作doRemove，一个递归调用的方法
    private void doRemove(Node deleted) {
        //拿到删剩下的节点
        Node replaced = findReplaced(deleted);
        Node parent = deleted.parent;//拿到临时父亲节点
        //表示没有孩子这种情况，要删除的节点是根节点，删完了，直接root=null，
        if (replaced == null) {
            // case 1 删除的是根节点//这是判断只有root节点的情况，
            if (deleted == root) {
                root = null;
            }//在真正的删除操作之前，进行节点颜色的判断，来判断是否进行平衡调整
            else {
                if (isBlack(deleted)) {
                    //复杂调整，deleted和repalaced都是黑色节点的情况
                    fixDoubleBlack(deleted);
                } else {
                    // 红色叶子, 无需任何处理
                }
                if (deleted.isLeftChild()) {
                    parent.left = null;//删除叶子节点左孩子
                } else {
                    parent.right = null;
                }
                deleted.parent = null;
            }
            return;
        }
        // //有一个孩子,那么这个孩子一定是红色的，要删除它，恢复红黑树平衡，
        if (deleted.left == null || deleted.right == null) {
            // case 1 删除的是根节点
            if (deleted == root) {
                //交换，然后删除
                root.key = replaced.key;
                root.value = replaced.value;
                root.left = root.right = null;//让节点的左右孩子等于null
                //如果被删除节点是他父亲的左孩子
            } else {
                if (deleted.isLeftChild()) {
                    parent.left = replaced;
                } else {
                    parent.right = replaced;
                }
                replaced.parent = parent;
                deleted.left = deleted.right = deleted.parent = null;
                if (isBlack(deleted) && isBlack(replaced)) {
                    // 复杂处理 @TODO 实际不会有这种情况 因为只有一个孩子时 被删除节点是黑色 那么剩余节点只能是红色不会触发双黑
                    fixDoubleBlack(replaced);
                } else {
                    //删除的是黑色节点，repalaced节点变成黑色
                    // case 2 删除是黑，剩下是红
                    replaced.color = BLACK;
                }
            }
            return;
        }
        //有两个孩子=>有一个孩子 或没有孩子
        //李带桃僵，就是把要删除节点替换成repalaced节点
        // case 0 有两个孩子 => 有一个孩子 或 没有孩子
        //交换键值
        int t = deleted.key;
        deleted.key = replaced.key;
        replaced.key = t;
        //交换value
        Object v = deleted.value;
        deleted.value = replaced.value;
        replaced.value = v;
        doRemove(replaced);
    }
}