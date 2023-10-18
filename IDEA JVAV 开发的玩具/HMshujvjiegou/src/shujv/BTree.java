package shujv;

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
    public void put(int key){
        doPut(root,key);
    }
    public void doPut(Node node,int key){
        int i=0;
        while (i<node.keyNumber){
            //更新的逻辑
            if (node.keys[key]==key){
                return;
            }
            //找到了插入位置，即为此时的 i
            if (node.keys[key]>key){
            break;
            }
        }
        i++;
        if (node.leaf){
            node.insertKey(key,i);
        }else {
            doPut(node.children[i],key);
        }
        /*分裂节点时数据一分为三，大的key出来到新建节点，中间的key返回到父亲节点，小的key留给自己
        *新建节点为原来父亲节点的新孩子
        * */
        /** 创建 right 节点(分裂后大于当前 left节点的),
         * 把t以后的key 和child 都拷贝过去 。
         * t-1 处的key 插入到parent 的index,
         * index 指 left 作为孩子时的索引
         * right节点作为parent的孩子插入到index+1处
        * */
    }
    //分裂方法
    private void split(Node left,Node parent,int index){
        Node right=new Node(t);
        right.leaf=left.leaf;//新节点的叶子属性和原来没分裂的节点叶子属性是一样的，它们应该都是left的父亲的叶子节点
        //把left里一部分key放入right这个新节点中，就是两个数组之间拷贝
        //System.arraycopy 从哪个数组拷贝，从哪里开始拷贝，拷贝到哪个数组，从这个数组的哪里放入拷贝数据，放入数据的长度
        System.arraycopy(left.keys,t,right.keys,0,t-1);
        left.keyNumber=t-1;//新节点的keyNumber更新
        right.keyNumber=t-1;
        left.keyNumber=t-1;
        //2.中间的key们 （t-1）处插入到父亲节点
        int min=left.keys[t-1];

        //3.right节点作为父亲节点的新孩子


    }
    
    //3.删除

}
