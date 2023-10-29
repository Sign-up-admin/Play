package LeetCode;
//求数据流中的中位数
//使用堆来实现
//数据流就是堆最好
/*用两个堆，一个最大堆，一个最小堆
*最大堆存较小的数，堆顶最大数，最小堆存较大的数，堆顶是最小的数
*
*        大顶堆    堆顶                堆顶      小顶堆
*            1,2,3                            6,7,8
*
*
* 把两个堆的堆顶，求和除以2，就是中位数
* 两个堆元素个数应该相等，就算不相等，也不能个数相差超过1个
*
*为了保证两边数据量的平衡
？？？两边个数一样时,左边个数加一（元素加在左边最大堆）
？？？两边个数不一样时,右边个数加一（元素加在右边最小堆）
 但是,随便一个数能直接加入吗?
？？？左边个数加一时,应该挑右边最小的加入
？？？个数加一时,应该挑左边最大的加入
* */

import shujv.Heap;

import java.util.Arrays;

public class E04Leetcode295 {
    public void addNum(int num){
        if (left.size()==right.size()){
            right.offer(num);
            left.offer(right.poll());//就是先把元素加到右边，再弹出右边的最小元素，加到左边
        }else {
            left.offer(num);
            right.offer(left.poll());//就是先把元素加到左边，再弹出右边的最大元素，加到右边
        }

    }
    public int findMedian(){
        if (right.size==left.size){
            return (right.peek()+ left.peek())>>1;
        }else {
            return right.peek();//总是左边多一个元素，左边堆顶就是中位数
        }
    }
    public Heap left =new Heap(10,true);
    public Heap right =new Heap(10,false);

    public static void main(String[] args) {
        E04Leetcode295 test = new E04Leetcode295();
        test.addNum(1);
        test.addNum(2);
        test.addNum(3);
        test.addNum(7);
        test.addNum(8);
        test.addNum(9);
        System.out.println(test);
        test.addNum(10);
        System.out.println(test);
        test.addNum(4);
        System.out.println(test);
        System.out.println(test.findMedian());

    }
    @Override
    public String toString() {
        int size = left.size;
        int[] left = new int[size];
        for (int i = 0; i < left.length; i++) {
            left[size - 1 - i] = this.left.array[i];
        }
        int[] right = Arrays.copyOf(this.right.array, this.right.size);
        return Arrays.toString(left) + " <-> " + Arrays.toString(right);
    }
}
