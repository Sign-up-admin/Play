package LeetCode;
/*
*求数组中第K大的元素
*
解体思路
1. 向小顶堆放入前k个元素
2. 剩余元素
• 若 <= 堆顶元素,则略过
• 若>堆顶元素,则替换堆顶元素
3. 这样小顶堆始终保留的是到目前为止,前K大的元素
4. 循环结束, 堆顶元素即为第K大元素
*/

import shujv.MinHeap;

public class E02Leetcode215 {
    public int findKthLargest(int[] numbers,int k){
            MinHeap heap=new MinHeap(k);
        for (int i = 0; i < k; i++) {
            heap.offer(numbers[i]);
        }
        for (int i = 0; i < numbers.length; i++) {
            if (numbers[i]<heap.peek()){
                heap.replace(numbers[i]);
            }
            return heap.peek();

        }

        return heap.peek();
    }

    public static void main(String[] args) {
        System.out.println(new E02Leetcode215().findKthLargest(new int[]{3,2,1,5,6,4},2));
        System.out.println(new E02Leetcode215().findKthLargest(new int[]{3,2,3,1,2,4,5,5,6},4));

    }

}
