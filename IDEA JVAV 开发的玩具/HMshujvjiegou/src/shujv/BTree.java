package shujv;

import java.lang.module.FindException;
import java.util.Arrays;

//学点b树
@SuppressWarnings("all")
public class BTree {
    //节点类
    static class Node {
        int[] keys;//关键字
        Node[] children;//孩子,是一个节点数组，里面存的是Node
        int keyNumber;// 有效关键字数目(就是给上面keys数组赋值，因为是静态数组,也是记录一个节点中有多少数据)
        boolean leaf = true;// 是否是叶子节点
        int t;// 最小度数 (最小孩子数)

        public Node(int t) { // t>=2
            this.t = t;
            this.children = new Node[2 * t];//一个节点能连最多2*t个节点
            this.keys = new int[2 * t - 1];//一个节点最多容纳2 * t - 1个数据（key）
        }

        public Node(int[] keys) {
            this.keys = keys;
        }

        @Override
        public String toString() {
            return Arrays.toString(Arrays.copyOfRange(keys, 0, keyNumber));
        }

        //多路查找
        /*1.先对当前节点进行变量 决定向左找啊还是向右查找
         *
         * */
        //key是找到这个数据的关键字
        Node get(int key) {
            int i = 0;
            //遍历节点keys,数量是keyNumber
            while (i < keyNumber) {
                if (keys[i] == key) {
                    return this;//返回当期节点
                }
                // 执行到此时 keys[i]>key 或 i==keyNumber（要查找的数据key超过有效范围）
                if (keys[i] > key) {
                    break;
                }
                i++;
            }
            // 非叶子情况
            return children[i].get(key);//递归调用get方法继续，去第i个children那里遍历节点，查找数据
            //这个i是两个key之间的节点，i的大小代表是第几大的孩子
        }

        /*把index处后面的元素全部向后移动一格，空出一个插入key
         * [1,2,3,4,5]
         * [1,2, ,3,4,5]
         * */
        // 向 keys 指定索引处（index）插入 key
        void insertKey(int key, int index) {
            //从keys数组index处拷贝到keys数组处的index+1位置，拷贝长度是length
            System.arraycopy(keys, index, keys, index + 1, keyNumber - index);
            /*for (int i = keyNumber-1; i >=index; i--) {
                int p=keys[index];
                keys[index+1]=p;
            }*/
            keys[index] = key;//插入key
            keyNumber++;//有效索引++
        }

        // 向 children 指定索引处插入 child
        void insertChild(Node child, int index) {
            System.arraycopy(children, index, children, index + 1, keyNumber - index);
            children[index] = child;//插入key
        }
        //下面是九个工具方法，方便实现remove

        //移除指定 index 处的 key
        int removeKey(int index) {
            int t = keys[index];
            //利用数组剪切，从后向前覆盖要删除的那一个节点，从而实现“删除”
            System.arraycopy(keys, index + 1, keys, index, --keyNumber - index);
            return t;
        }

        //移除最左边的key
        int removeLeftmostKey() {
            return removeKey(0);
        }

        //移除最右边的key
        int removeRightmostKey() {
            return removeKey(keyNumber - 1);
        }

        //移除指定位置的child
        Node removeChild(int index) {
            Node t = children[index];
            //利用数组剪切，从后向前覆盖要删除的那一个node节点，实现删除
            System.arraycopy(children, index + 1, children, index, keyNumber - index);
            children[keyNumber] = null;//help GC
            return t;
        }

        // 移除最左边child
        Node removeLeftmostChild() {
            return removeChild(0);
        }

        // 移除最右边child
        Node removeRightmostChild() {
            return removeChild(keyNumber);
        }

        //index 孩子处左边的兄弟
        Node childLeftSibling(int index) {
            return index > 0 ? children[index - 1] : null;//index大于零？，是返回孩子处左边的兄弟，不是返回null（没有）
            //如果这个节点已经是最左边的节点了返回null
        }

        //index 孩子处右边的兄弟
        Node childRightSibling(int index) {
            return index == keyNumber ? null : children[index + 1];//如果这个节点自己已经是最右边的节点了，返回null
        }

        // 复制当前节点的所有key 和child到 target
        void moveToTarget(Node tatget) {
            int start = tatget.keyNumber;
            //如果不是叶子节点
            if (!leaf) {
                for (int i = 0; i <= keyNumber; i++) {
                    tatget.children[start + i] = children[i];
                }
            }
            for (int i = 0; i < keyNumber; i++) {
                tatget.keys[tatget.keyNumber++] = keys[i];
            }
        }
    }

    Node root;
    int t;//树中所有节点的最小度数
    //因为它俩呢会在我们将来数组的分裂和合并时用到
    final int MIN_KEY_NUMBER; // 最小key数目
    final int MAX_KEY_NUMBER; // 最大key数目

    //无参构造的默认度数为2
    public BTree() {
        this(2);
    }

    public BTree(int t) {
        this.t = t;
        root = new Node(t);
        MAX_KEY_NUMBER = 2 * t - 1;
        MIN_KEY_NUMBER = t - 1;
    }

    //1.是否存在
    public boolean contains(int key) {
        return root.get(key) != null;
    }

    //2.新增
    /*要新增一个key，如果当前节点是叶子节点，就直接加入keys[]
    *但是一个节点的key数目不是越多越好，
    * 当key的数目超过一个最大值时（MAX_KEY_NUMBER），
    * 就要对这个节点做一个分裂处理
    *
    * 首先查找本节点中的插入位置i,如果没有空位(key被找到),应该走更新的逻辑,目前什么没做
      接下来分两种情况
    。如果节点是叶于节点,可以直接插入了
    。如果节点是非叶子节点,蒂要继续在 children[i]处继续递归插入
      无论哪种情况,插入完成后都可能超过节点 keys 数目限制,此时应当执行节点分裂
    * */
    public void put(int key) {
        doPut(root, key, null, 0);
    }

    public void doPut(Node node, int key, Node parent, int index) {
        int i = 0;
        while (i < node.keyNumber) {
            //更新的逻辑
            if (node.keys[key] == key) {
                return;
            }
            //找到了插入位置，即为此时的 i
            if (node.keys[key] > key) {
                break;
            }
        }
        i++;
        if (node.leaf) {
            node.insertKey(key, i);
            //可能达到上限
        } else {
            doPut(node.children[i], key, parent, i);//在这个节点不是叶子节点的情况中，我们要去处理的节点是node.children[i]
            //当前节点的第二个孩子对吧》，所以父亲节点就是它自己
            //可能达到上限
        }
        if (node.keyNumber == MAX_KEY_NUMBER) {
            split(node, parent, index);
        }
        /*分裂节点时数据一分为三，大的key出来到新建节点，中间的key返回到父亲节点，小的key留给自己
         *新建节点为原来父亲节点的新孩子
         * */
        /** 创建 right 节点(分裂后大于当前 left节点的),
         * 把t以后的key 和child 都拷贝过去 。
         * ○ t-1 处的key 插入到parent 的index,index 指 left 作为孩子时的索引
         * ○ right节点作为parent的孩子插入到index+1处
         *
         * 如果 parent == null表示要分裂的是根节点,此时需要创建 新根,原来的根节点作为新根的孩于
         * • 否则
         * 。创建right节点(分裂后大于当前left节点的),把t 以后的 key 和child 都拷贝过去
         * t-1 处的 key 插入到parent 的index处,index 指 left
         * ,作为孩子时的索引
         * right节点作为parent 的孩子插入到index+1处
         *
         * */

        /*当我们是叶子节点时，新插入一个key，插入的key是不是可能让这个节点key的有效
         *数目被超过，
         * 还有一种情况就是当我们是父节点的时候，叶子节点在进行分裂操作时，
         * 把节点从叶子节点返回到父节点时，这个父节点就可能会超过key的最大值
         * */
    }

    //分裂方法（要分裂的节点，要分裂节点的父亲，）
    void split(Node left, Node parent, int index) {
        //分裂的是根节点
        if (parent == null) {
            Node newRoot = new Node(t);
            newRoot.leaf = false;
            newRoot.insertChild(left, 0);
            this.root = newRoot;
            parent = newRoot;
        }
        Node right = new Node(t);
        right.leaf = left.leaf;//新节点的叶子属性和原来没分裂的节点叶子属性是一样的，它们应该都是left的父亲的叶子节点
        //把left里一部分key放入right这个新节点中，就是两个数组之间拷贝
        //System.arraycopy 从哪个数组拷贝，从哪里开始拷贝，拷贝到哪个数组，从这个数组的哪里放入拷贝数据，放入数据的长度
        System.arraycopy(left.keys, t, right.keys, 0, t - 1);
        //一种比较特殊的情况，非叶子节点的分裂
        //如果当前要分裂的节点不是叶子节点,那么需要创建新的两个叶子节点，
        if (left.leaf == false) {
            System.arraycopy(left.children, t, right.children, 0, t);
        }
        //如果分裂的是，根节点的情况，那么要把根节点数据分三分，最大分到新right，最小分到新left节点

        left.keyNumber = t - 1;//新节点的keyNumber更新
        right.keyNumber = t - 1;
        left.keyNumber = t - 1;
        //2.中间的key们 （t-1）处插入到父亲节点
        int min = left.keys[t - 1];
        parent.insertKey(min, index);
        //3.right节点作为父亲节点的新孩子
        parent.insertChild(right, index + 1);


    }

    //3.删除
    //删除某个节点的中的key
    /*case1 当前节点是叶子节点，没找到，直接返回
     *case2 当前节点是叶子节点，找到了，
     * 困难
     *case3 当前节点不是叶子节点，没找到
     *case4 当前节点不是叶子节点，找到了
     *case5 删除后key数目小于下限，下限就是t-1，上限是keynumber（不平衡）
     *case6 根节点*/
    public void remove(int key) {
        doRemove(root, key);
    }

    public void doRemove(Node node, int key) {
        int i = 0;
        while (node.keyNumber > i) {//索引还在有效范围之内
            //找到的情况,找的循环
            if (node.keys[i] == key) {
                break;
            }
            i++;
            //只要遇到比待删除key大的值，我们就停下循环，当停下来时，它的索引i就是我们要进入的下一个节点的那个孩子
            //i 找到；代表删除key 的索引
            //i 没找到 代表到第i个孩子继续查找
            //我想在循环外重新把这两种情况区分开了，是应该在做一次条件判断
            //为了让代码的可读性更好些，可以让刚才这个判断条件给他抽成一个方法 ,选中条件，CTRL+alt+m
            if (node.leaf) {//如果是叶子节点的话

                if (found(node, key, i)) {//case1
                    return;
                } else {//case2
                    node.removeKey(i);//i处就是key的索引，用方法删除;
                    //删除完了，可能整个这个k的数目小于下限了
                }
            } else {//如果节点不是叶子节点的话
                if (!found(node, key, i)) {//case3
                    doRemove(node.children[i], key);
                } else {//case4
                    //和其它平衡类的什么红黑树，avl树一样，非叶子节点，找到了的话不能直接删除，我们要把它先替换成后继的key
                    /*怎么找这个后继的key？就从它的右侧孩子一直向左走
                     * 然后一直向左走，走到头，就能找到它的后继节点
                     * 向左走就是child[index+1]
                     * 每次都是找0号孩子，实现一直向左走
                     * 走到头的判断，就是走到叶子节点了，那就表示找到这个后继节点key所在的节点了，
                     * 然后这个节点中可能有多个key，我们再找他第零个索引位置key，就是在最左侧的key了
                     * 这个就是我们的后继key*/
                    // 1. 找到后继 key
                    Node s = node.children[i + 1];//定义一个节点s，代表存在后继key的后继节点
                    // 它的起点是我们当前节点中需要删除的key，它的索引加一的孩子作为起点
                    //就是得到比他key值要大的那个孩子处，作为起点进行查找
                    while (!s.leaf) {//不是叶子节点，继续向左走，循环结束，那它就是叶子节点了
                        s = s.children[0];
                    }
                    //最左侧的key
                    int skey = s.keys[0];
                    // 2. 替换待删除 key
                    node.keys[i] = skey;
                    // 3. 删除后继 key
                    doRemove(node.children[i+1],skey);

                }
            }
            //一旦小于下限，就进行平衡调整
            if (node.keyNumber < MIN_KEY_NUMBER) {
                //调整平衡，根节点和其他节点调整平衡的方式有所不同，这是case5，6搞的事情
            }
        }
    }

    //那个方法
    private static boolean found(Node node, int key, int i) {
        return i < node.keyNumber && node.keys[i] == key;
    }


}
