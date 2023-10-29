package shujv;

/*
* 归并爱上插入（排序）
* */

/*归并排序已经很快了
* 但是还是比不上jdk的算法
* 速度的突破主要有两个方向
*  不使用基于比较的排序算法
*  把之前学习的排序算法组合起来，实现性能突破
*   经验表明，归并排序适合数据量非常大时的排序运算
*   而插入排序适合数据量很少的适合
*
*   在归并排序中的主要性能瓶颈是“治”的部分，因为它（之前）认为只有把大量数据分割到区间内只剩一个元素的时候（left==right）
*   但是其实，我们不需要一直分分分到到left==right，区间内只剩下一个数据才认为达到了“治”的效果
*
*   这个算法比jdk快1ms
*
* */

import java.util.Arrays;

public class MergeInsertionSort {
    public static void insertion(int[] a, int left, int right) {//改为指定一个区间进行插入排序
        for (int low = left + 1; low <= right; low++) {
            int t = a[low];
            int i = low - 1;
            // 自右向左找插入位置，如果比待插入元素大，则不断右移，空出插入位置
            while (i >= left && t < a[i]) {
                a[i + 1] = a[i];
                i--;
            }
            // 找到插入位置
            if (i != low - 1) {
                a[i + 1] = t;
            }
        }
    }

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
        int[] a2 = new int[a1.length];
        split(a1, 0, a1.length - 1, a2);
    }

    private static void split(int[] a1, int left, int right, int[] a2) {
        // 2. 治
        if (right - left <= 32) {//在这里修改的意思是当区间小于32长度时，就可以开始插入排序了
            // 插入排序
            insertion(a1, left, right);
            return;
        }
        // 1. 分
        int m = (left + right) >>> 1;
        split(a1, left, m, a2);
        split(a1, m + 1, right, a2);
        // 3. 合
        merge(a1, left, m, m + 1, right, a2);
        System.arraycopy(a2, left, a1, left, right - left + 1);
    }

    public static void main(String[] args) {
        int[] a = {9, 3, 7, 2, 8, 5, 1, 4};
        System.out.println(Arrays.toString(a));
        sort(a);
        System.out.println(Arrays.toString(a));
    }
}
