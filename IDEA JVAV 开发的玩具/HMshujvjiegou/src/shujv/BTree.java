package shujv;

import java.util.Arrays;

//学点b树
@SuppressWarnings("all")
public class BTree {
    static class Node {
        int[] keys;//关键字
        Node[] children;//孩子
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
        int removeKey() {

        }

    }

}
