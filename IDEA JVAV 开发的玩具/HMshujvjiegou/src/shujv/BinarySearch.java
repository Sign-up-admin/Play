package shujv;

public class BinarySearch {
    public static int binaryserch(int[] a,int taget){
        int i=0;//i向右移动
        int j=a.length-1;//j向左移动
        while (i<=j){//当i还在j的左边时，和i=j时都要执行
            int m=(i+j)/2;//java自动向下取整，其它语言一般还有float来取值。
            if (taget < a[m]) {//目标在左边
                j=m-1;


            }
            else if (taget>a[m]) {//当目标在右边
                i=m+1;

            }else {
                return m;
            }
        }

        return -1;
    }//它的次数是log_2(n)=2+1
    public static int xianxing(int[] a,int taget){
        int i;
        for (i=0;i<a.length;i++){
            if (a[i]==taget) {
                return i;
            }

        }
        return -1;
    }
    public static int binaryserchLeftMost(int[] a,int taget){
        int i=0;//i向右移动
        int j=a.length-1;//j向左移动
        int candidate =-1;//记录候选位置

        while (i<=j){//当i还在j的左边时，和i=j时都要执行
            //int m=(i+j)/2;//java自动向下取整，其它语言一般还有float来取值。使用无符号右移运算符来保证大的整数计算不会出现错误，因为Java会在二进制数认为最大位位符号位
            int m=(i+j)>>1;

            if (taget < a[m]) {//目标在左边
                j=m-1;
            }
            else if (taget>a[m]) {//当目标在右边
                i=m+1;

            }else {
                candidate=m;
                j=m-1;

            }
        }

        return candidate;
    }//二分查找中最左重复元素
}//问题1:为什么是i<=j 意味着区间内有未比较的元素，而不是i《j？
//i,j它们指向的元素也会参与比较
//(i+j)/2有问题吗？
// 线性查找
