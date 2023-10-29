package LeetCode;

import shujv.MinHeap;

//求数据流中的最大元素
//堆的典型应用场景
/*先使用小顶堆保留前k大的数据
*
*
* */
public class E03Leetcode703 {
    MinHeap heap;
    public E03Leetcode703(int k,int[] nums){
         heap = new MinHeap(k);//第k大的元素，就是堆的大小，容量
        for (int num:nums){
            add(num);
        }
    }
    //会不断调用这个add方法，来模拟数据流中新来的数据
    public int add(int val){
        if (!heap.isFull()){
            heap.offer(val);
        }else if (val>heap.peek()){
            heap.replace(val);
        }
        if (heap.peek()<val){
            heap.replace(val);
        }
        return heap.peek();//返回堆顶，就是数据流第k大的元素
    }
    public static void main(String[] args) {
        E03Leetcode703 test = new E03Leetcode703(3, new int[]{});
        //小顶堆 4 5 8 注意是留最大3个，当新数据比堆顶数据大时，替换
        System.out.println(test.add(3));
        System.out.println(test.add(5));
        System.out.println(test.add(10));
        System.out.println(test.add(9));
        System.out.println(test.add(4));

    }
}
