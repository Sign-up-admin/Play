package shujv;

import java.util.Arrays;
/*
 * 非常好的归并排序，使我的%￥自下而上起来
 * 你最好是在说数据
 *
 * */
/*归并排序中的规律
      不难看出，自上而下其实最后结果就是两个相邻的元素排序，然后合并数组
*     自下而上，宽度从一开始是一，每一次是上一次的两倍
*
* */

public class MergeSortBottomUp {
    public static void merge(int[] a1, int i, int iEnd, int j, int jEnd, int[] a2) {
        int k = i;
        while (i <= iEnd && j <= jEnd) {
            if (a1[i] < a1[j]) {
                a2[k] = a1[i];
                i++;
            } else {
                a2[k] = a1[j];
                j++;
            }
            k++;
        }
        if (i > iEnd) {
            System.arraycopy(a1, j, a2, k, jEnd - j + 1);
        }
        if (j > jEnd) {
            System.arraycopy(a1, i, a2, k, iEnd - i + 1);
        }
    }

    public static void sort(int[] a1) {
        int n = a1.length;
        int[] a2 = new int[n];
        //width 代表有序区间的宽度，取值依次为1，2，4，8.......
        for (int width = 1; width < n; width *= 2) {
            //left,right分别代表待合并区间的左右边界
            //left是左边界，数值一开始是零，在width是1时，每次循环left加2
            for (int left = 0; left < n; left += 2 * width) {
                int right = Math.min(left + 2 * width-1, n - 1);//在width是1时，right边界是left-1，所以
                //为了防止right数值超过数组，用Math.min方法在n-1之间取一个较小值，作为我们的右边界
                int m = Math.min(left + width - 1, n - 1);//求中间值并防止出数组越界
                merge(a1,left,m,m+1,right,a2);
            }
            System.arraycopy(a2,0,a1,0,n);
        }
    }

    public static void main(String[] args) {
        int[] a = {9, 3, 7, 2, 8, 5, 1, 4};
        sort(a);
        System.out.println(Arrays.toString(a));
    }
}
