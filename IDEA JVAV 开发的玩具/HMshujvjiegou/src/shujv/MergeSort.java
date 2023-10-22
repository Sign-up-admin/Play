package shujv;
/*
 * 归并排序
 * 自顶向下版本基于递归方式实现
 * 分而治之
 * 分治和递归
 *       分 - 每次从中间切一刀，处理的数据少一半
 *       治 - 当数据仅剩一个时可以认为有序
 *       合 - 两个有序的结果，可以进行合并（参见数组练习E01.合并有序数组）
 *
 * */

import java.util.Arrays;
/*
        a1 原始数组
        i~iEnd 第一个有序范围
        j~jEnd 第二个有序范围
        a2 临时数组
     */

public class MergeSort {
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
        int[] a2 = new int[a1.length];//定义临时数组
        //从零开始，一直到数组长度减一
        split(a1, 0, a1.length - 1, a2);
    }

    //把数据的主键一分为二，传入参数 待排序数组，要处理数据的一个边界范围，left和right,最左侧索引和最右侧索引
    private static void split(int[] a1, int left, int right, int[] a2) {
        int[] array = Arrays.copyOfRange(a1, left, right + 1);//打印一个范围内的数据
        System.out.println(Arrays.toString(array));
        //2治
        if (left == right) {
            return;
        }
        //————————————————————————分—————————————————————————
        //每次递归调用，数据量减半
        int m = (left + right) >>> 1;
        //划分范围是，左边这个范围是left到中间点，右边这个范围是中间点+1到right
        split(a1, left, m, a2);
        split(a1, m + 1, right, a2);
        //当left和right相同时，说明分割到头了，表示这个范围内只有一个数据了
        //3.合
        //代表的是第一个有序的范围从哪里到哪里,然后是临时数组
        merge(a1, left, m, m + 1, right, a2);
        //最终将临时数组的变量拷贝到旧数组中
        System.arraycopy(a2, left, a1, left, right - left + 1);
        //他俩差值加1，就是元素个数
    }

    public static void main(String[] args) {
        int[] a = {9, 3, 7, 2, 8, 5, 1, 4};
        sort(a);
        System.out.println(Arrays.toString(a));
    }
}
/*归并排序（Merge Sort）是一种基于分治策略的排序算法，它将一个未排序的数组分成若干子数组，然后逐步将这些子数组合并为一个有序数组。
归并排序通常被认为是一种非常高效和稳定的排序算法，其时间复杂度为O(n log n)，这使得它在大规模数据集上具有良好的性能。*/