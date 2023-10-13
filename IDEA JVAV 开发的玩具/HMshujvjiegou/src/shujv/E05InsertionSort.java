package shujv;

import java.util.Arrays;

public class E05InsertionSort {
    public static void sort(int[] a){
        int low=0;
        for (int i=0;a[i]<a[i+1];i++){
            if (a[i]>a[i+1]){
                low=a[i];
            }
        }

        insertion(a,low);
    }
    private static void insertion(int[] a,int low){
        //这个low是未排序的边界
        int p=a[low];//用于和未排序区域进行比较的指针
        int i=low-1;//已经排序指针区域
        if (low==a.length){
            return;
        }

        while (-1<a[i]&&a[i]>p){//用于查找需要插入的位置
            a[i+1]=a[i];//空出插入位置
            i--;
        }//结束时返回需要插入的位置，需要插入的位置是已经排序区域中的第一个比小
        a[i+1]=p;//插入
        insertion(a,low+1);

    }

    public static void main(String[] args) {
        int[] b={4,5,6,9,8,7,66};
        sort(b);
        System.out.println(Arrays.toString(b));

    }
}
