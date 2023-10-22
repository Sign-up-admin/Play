package shujv;

import java.util.Arrays;
/*
* 冒泡排序
* */

public class BubblesSort {
    private static void bubble(int[] a) {
        int j = a.length-1;
        while (j != 0) {
            int x = 0;
            for (int i = 0; i < j; i++) {
                if (a[i] > a[i + 1]) {
                    int t = a[i];
                    a[i] = a[i + 1];
                    a[i + 1] = t;
                    x = i;//这个x是最后一次交换位置，下一次冒泡就不用在这个边界的后面比较了，减少冒泡次数
                }
            }
            j = x;
        }
    }

    public static void main(String[] args) {
        int[] a = {6, 5, 4, 3, 2, 1};
        System.out.println(Arrays.toString(a));
        bubble(a);
        System.out.println(Arrays.toString(a));
    }
}
