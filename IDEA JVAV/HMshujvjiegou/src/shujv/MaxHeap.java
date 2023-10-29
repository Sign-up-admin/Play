package shujv;
//大顶堆
//建堆代码步骤
//1，找到最后一个叶子节点
//2.从前往后，对每一个节点执行下潜（同自己的最小值的孩子进行交换）
//我们希望调用heap时传入一个普通数组，我们就在构造方法里来
//将数组转化为符号堆特性的数组
//建议背下来，因为面试一定会考,特别是建堆，下沉，上浮，比较和交换的方法

import java.util.Arrays;

public class MaxHeap {
    int[] array;
    int size;
    public MaxHeap(int[] array){
        this.array=array;
        this.size=array.length;
        heapify();
    }

    //创建一个用来存放堆的数组，容量是capcity，
    public MaxHeap (int capcity){
        this.array=new int[capcity];
    }
    //获取堆顶方法
    //return堆顶元素

    public int peek(){
        if (isEmpt()){
            return -1;
        }

        return array[0];
    }
    //pool方法删除堆顶元素，return堆顶元素
    //复习回忆一下，怎么从堆顶移除元素呢？
    /*从索引零处直接移除的效率不高，先把它跟数组，堆中最后一个元素进行交换
    *交换后就相当于从尾部移除了，从尾部移除效率就高了
    * 然后移除，
    * 移除之后可能导致刚刚交换到堆顶的元素，不符合最大顶堆的特性了
    *因此需要进行调整，就是把刚刚交换上堆顶的元素进行一个下潜，就可以了
    *
    * */

    public int pool(){
        if (isEmpt()){
            return -1;
        }
        int top=array[0];
        swap(0,size-1);//size-1是最后一个元素,交换步骤
        size--;//删除了
        down(0);//对栈顶元素重新调整
        return top;
    }
    public boolean isFull(){
        return size==array.length;
    }
    public boolean isEmpt(){
        return size==0;
    }
    //删除指定位置的元素，传入index是要删除指定元素的索引号，思路是一样的
    public int pool(int index){
        //判断是否为空，空返回-1
        if (isEmpt()){
            return -1;
        }
        int deleted=array[index];
        swap(index,size-1);
        size--;
        down(0);//重新调整
        return deleted;
    }
    //替换堆顶元素,params:replace-新元素
    public void replace(int replace){
        array[0]=replace;
        down(0);//替换元素之后，可能会破坏最大堆的特性，所以下潜水
    }
    //如何实现添加元素？向堆的尾部添加元素，将offered元素上浮；直至 offered 小于父亲元素或到堆顶
    //先往堆的尾部添加元素添加元素
    /*把它和父元素进行比较，如果比父元素大，就要继续上浮，一直到小于父元素或者到达堆顶
    *为止
    * */
    public void up(int offered){
        int child=size;
        while (child>0){
        int parent=(child-1)/2;//父亲节点的索引是这样的公式
            if (offered>array[parent]){
                array[child]=array[parent];
            }else {
                break;
            }
            child=parent;
        }
        array[child] =offered;
    }
    public boolean offer(int offered){
        if (isFull()){
            return false;
        }
        up(offered);
        size++;
        return  true;
    }

    //建堆方法，每次添加数据时都会，进行下潜
    public void heapify(){
        for (int i = size/2-1; i >=0; i--) {
            down(i);
        }
        //如何找到最后一个叶子节点？ 公式size/2 -1，就是最后一个叶子节点
    }
    //将parent 索引处的元素下潜，与两个孩子中较大的进行交换，直至没孩子或孩子没它大
    public void down(int parent){
        int left=parent*2+1;//左孩子的索引
        int right=parent*2+2;//右孩子的索引
        int max=parent;//父亲左，右，它们更大的那个索引值？
        if (left<size&&array[left]>array[max]){//判断为有效索引，并且左孩子处比父亲大，交换
            max=left;//这里意思就是对两个节点进行交换，在由数组存贮的堆中
            //两个子节点和父节点进行交换，实际上就是索引的值进行交换就可以了
            //这个max是代表，临时在这个小树中最大的值的索引
            //假如这个if成立了，等下还会拿这个从left变成max，的max进行与right的比较
        }
        if (right<size&&array[right]>array[max]){//判断为有效索引，并且右孩子处比父亲大，交换
            max=right;
        }
        //到这里，就可以在父亲，左孩子，右孩子之间挑出一个最大的，

        //这里是在判断上面的代码是否影响到了parent，就是有没有找到，
        if (max!=parent){
            //找到一个更大的孩子，进行交换
            swap(max,parent);
            //parent处的元素就是当前最后一个非叶子节点
            down(max);//递归，进行下潜
        }
    }
    //这个swap是交换两个元素的方法
    public void swap(int i,int j){
       int t=array[i];
       array[i]=array[j];
       array[j]=t;
    }

    public static void main(String[] args) {
        int [] array={1,2,3,4,5,6,7};
        int [] array2={2,3,1,7,6,4,5};
        MaxHeap maxHeap=new MaxHeap(array);
        MaxHeap heap=new MaxHeap(array2);
        System.out.println(Arrays.toString(heap.array));
        System.out.println(Arrays.toString(maxHeap.array));
        //现在让我们来玩玩堆排序
        /*算法描述
        * 1.heapify建立大顶堆
        * 2.将堆顶与堆底交换（最大元素被交换到堆底）,删除堆底元素，缩小堆并下潜，调整堆
        * 3.重复第二步直至堆里剩一个元素
        *
        * */
        while (heap.size>1){
            heap.swap(0, heap.size-1);//将堆顶与堆底交换（最大元素被交换到堆底）
            heap.size--;//删除堆底元素，准确来说是把存储在数组中的堆结构，一步一步删除堆结构，恢复成正常数组的数据结构
            heap.down(0);//下潜堆顶
             }
        System.out.println(Arrays.toString(heap.array));
    }
}
