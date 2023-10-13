package shujv;

//Avl树
/*二叉搜索树在插入和删除时，节点可能失衡
 * 如果在插入和删除时通过旋转，始终让二叉搜索树保持平衡，称为自平衡二叉搜索树
 * AVL是自平衡二叉搜索树的实现之一
 * */
public class AVLTree {
    static class AVLNode {
        int key;//这个是，用于进行比较大小变量
        Object value;//存储的值
        AVLNode left;
        AVLNode right;
        int height = 1; // 高度

        public AVLNode(int key, Object value) {
            this.key = key;
            this.value = value;
        }

        public AVLNode(int key) {
            this.key = key;
        }

        public AVLNode(int key, Object value, AVLNode left, AVLNode right) {
            this.key = key;
            this.value = value;
            this.left = left;
            this.right = right;
        }
    }

    //写一个方法方便null值的高度处理，求节点高度
    public int height(AVLNode node) {
        return node == null ? 0 : node.height;
        //这个节点是否没有孩子？如果是，这个节点高度为0，不是的话高度为计算高度
    }

    //更新节点的高度方法(新增、删除、旋转)
    //和之前求树的最大深度是一回事
    public void updateHeight(AVLNode node) {
        node.height = Integer.max(height(node.left), height(node.right)) + 1;
    }

    //平衡因子，把一个节点的左子树高度，减去这个节点右子树的高度的值
    //这个方法返回值如果是0，1，-1，说明是平衡的
    //当大于1，小于-1 不平衡
    // 平衡因子 (balance factor) = 左子树高度-右子树高度  1 -1 0
    public int bf(AVLNode node) {
        return height(node.left) - height(node.right);
    }
    /*
* LL
- 失衡节点(图中 5 红色) 的bf> 1,即左边更高
- 失衡节点的左孩子(图中3黄色)的bf >= 即左孩子这边也是左边更高或等形
* LR
- 失衡节点(图6)的bf> 1.即左边更高
- 失衡节点的左孩子(图中2 红色)的bf<日即左孩子这边是右边更高
*RL
- 失衡节点(图中 2)的bf<-1,即右边更高
- 失衡节点的右孩子(图中6红色)的bf>日,即右孩子这边左边更高
*RR
- 失衡节点(图中 2 红色)的bf<-1.即右边更高
- 失衡节点的右孩子(图中6 黄色)的bf<=日,即右孩子这边右边更高或等高
* */
    // 参数：要旋转的节点, 返回值：新的根节点
    //左旋方法，red是在上要向下的节点（目标节点），yellow是在下要向上的节点（成为下一个目标节点的节点），green是换爹的（成为下一个目标节点的节点的孩子）

    public AVLNode rightRotate(AVLNode red) {
        AVLNode yellow = red.left;//右旋的话，是目标节点的左孩子，这里yellow是目标节点的左孩子
        //考虑到yellow可能原来也有，自己的孩子green，左孩子，直接操作会让red覆盖yellow.right
        AVLNode green = yellow.right;
        yellow.right = red;//让目标节点变成，目标节点左孩子的，右孩子，右旋，yellow是新根
        red.left = green;//换爹
        //让目标节点变成，目标节点左孩子的，右孩子，右旋，yellow是新根
        updateHeight(red);
        updateHeight(yellow);//更新节点高度
        return yellow;
    }

    //右旋方法
    public AVLNode leftRotate(AVLNode red) {
        AVLNode yellow = red.right;
        AVLNode green = yellow.left;
        yellow.left = red;//左旋
        red.right = green;//换爹
        updateHeight(red);
        updateHeight(yellow);
        return yellow;
    }

    //先左旋左子树(小左旋)，后右旋根节点（大右旋）
    public AVLNode leftRightRotate(AVLNode red) {
        red.left = leftRotate(red.left);//让小左旋后的新根节点为现在red的左孩子
        AVLNode yellow = rightRotate(red);
        return yellow;
    }

    //先右旋右子树，后左旋根节点
    public AVLNode rightLeftRotate(AVLNode red) {
        red.right = rightRotate(red.right);
        AVLNode yellow = rightRotate(red);
        return yellow;
    }

    //检查节点是否失衡，重新平衡代码
    public AVLNode balance(AVLNode node) {
        if (node == null) {
            return null;
        }
        int bf = bf(node);//bf是1,-1,0时，是平衡的，下面条件是不平衡的
        if (bf > 1 && bf(node.left) >= 0) {//LL red的左孩子重，red左孩子的左孩子也重，一次右旋
            return rightRotate(node);

        } else if (bf > 1 && bf(node.left) < 0) {//LR
            return rightLeftRotate(node);

        } else if (bf < -1 && bf(node.right) > 0) { //RL
            return leftRightRotate(node);

        } else if (bf < -1 && bf(node.right) <= 0) { //RR
            return leftRotate(node);
        }
        return node;
    }

    //新增节点方法
    AVLNode root;

    public void put(int key, Object value) {
        root = doput(root, key, value);
    }

    //递归方法，就是找空位，找到空位了创建新的节点
    public AVLNode doput(AVLNode node, int key, Object value) {
        //1.找到空位，创建新节点
        if (node == null) {
            return new AVLNode(key, value);
        }
        //2.key已存在，更新
        if (node.key == key) {
            node.value = value;
            return node;
        }
        //3.继续查找,一种当现在的key小于已存在节点的key，继续向左查找
        if (key < node.key) {
            doput(node.left, key, value);
        } else doput(node.right, key, value);//向右
        updateHeight(node);
        return balance(node);
    }

    public void remove(int key) {
        root = doRemove(root, key);
    }

    public AVLNode doRemove(AVLNode node, int key) {
        /*1 node==null
         * 2没找到key
         * 3找到key，没有孩子，只有一个孩子，两个孩子都有
         * 4更新高度
         * 5重新平衡，balance
         * */
        if (node == null) {
            return null;
        }
        if (key < node.key) {
            node.left = doRemove(node.left, key);//继续向左查找,注意一下doremote返回的是删除后，剩下的那些节点，
            //所以更新一下
        } else if (key > node.key) {
            node.right = doRemove(node.left, key);
        } else {
            if (node.left == null && node.right == null) {
                //找到key了，节点既没有左孩子夜没有右孩子
                return null;
            } else if (node.left == null) {
                node = node.right;
            } else if (node.right == null) {
                node = node.left;
            } else {
                AVLNode s = node.right;
                while (s.left != null) {
                    s = s.left;
                }
                // s 后继节点
                s.right = doRemove(node.right, s.key);
                s.left = node.left;
                node = s;
            }
        }
        // 4. 更新高度
        updateHeight(node);
        // 5. balance
        return balance(node);
    }
}
