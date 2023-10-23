package shujv;
/*单边快排（lomuto洛穆托分区方案）其它快排算法也只是partiton分区方法不同
 *
 * 叫单边是因为两个i，j指针都是从左边向右边查找
 *
 * 快速排序算法是基于比较的排序算法中速度最快的了，并且数据量越大，越明显
 *
 * 核心思想:每轮找到一个基准点元素，把比它小的放到它的左边，比它大的放到它的右边。这称为分区
 *           1.选择最右元素作为基准点元素
 *             在从左向右前进的时候，i找到比right大的就会停下来，j继续前进，直到j找到比基准点小的元素，j停下
 *           2.指针j负责找到比基准点小的元素，一旦找到则与i进行交换
 *           3.i指针指向大于基准点元素的左边界，也是每次交换的目标索引
 *           4.最好基准点与i交换，i即为分区位置
 *           5.当left==right时候（相遇的时候），说明区间内就剩下一个元素，结束递归，
 *
 * 每一轮进行判断，每个区间都只有一个元素被确定是“在正确的位置”，就算有其它元素偶然在正确位置
 * ，也只能是基准点在“正确的位置”，基准点不需要参与之后的排序
 * */

/*
* 双边快排的要点
* 选择最左侧元素作为基准点
* j找比基准点小的，i找比基准点大大，一旦找到，二者进行交换
*       i从左向右
*       j从右向左
* 最后基准点与i交换，i即为基准点最终索引
*
* */

import java.util.Arrays;

public class QuickSortLomuto {
    public static void sort(int a[]) {
        //传入数组，要排序左右边界，
        quick(a, 0, a.length - 1);
    }

    //方便递归的函数，传入数组，快排左边界，右边界
    private static void quick(int[] a, int left, int right) {
        if (left >= right) {
            return;
        }
        int p = partition(a, left, right);//p代表基准点处元素的索引,等下分区方法返回
        quick(a, left, p-1);//递归调用，分两个区,p-1是因为p不参与下一次排序
        quick(a, p + 1, right);//同理+1
    }

    //分区方法
    private static int partition(int[] a, int left, int right) {
        int pv = a[right];//初始基准点元素值
        int i = left;
        int j = left;
        while (j < right) {//j指针处理
            if (a[j] < pv) {//j 找到比基准点小的了
                if (i != j) {
                    swap(a, i, j);
                }
                i++;//没有找到比基准点大的元素，i自增
            }
            j++;//没有遇到比基准点小的元素时，j前进
        }
        //最后，在循环结束时，把基准点交换到中间
        swap(a,i,right);
        return i;
    }

    //交换方法，传入数组，数组内要交换位置的i，j
    private static void swap(int[] a, int i, int j) {
        int t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    public static void main(String[] args) {
        int[] a = {9, 3, 7, 2, 8, 5, 1, 4};
        System.out.println(Arrays.toString(a));
        sort(a);
        System.out.println(Arrays.toString(a));
    }
}
