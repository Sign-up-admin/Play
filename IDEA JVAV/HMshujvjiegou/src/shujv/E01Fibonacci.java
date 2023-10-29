package shujv;

import java.util.Arrays;

public class E01Fibonacci {
    public static int fibonacci (int sun){
        int[] cache=new int[sun+1];//设置数组的长度为要计算数的+1
        Arrays.fill(cache,-1);//用这个函数，初始化数组，让数组中的每一位都是-1，-1会方便区分错误-1，-1.-1
        cache[0]=0;
        cache[1]=1;


        return sun(sun,cache);

    }
    public static int sun(int sun,int[] cache){
        if (cache[sun]!=-1){
            return cache[sun];
        }
        int x=sun(sun-1,cache);
        int y=sun(sun-2,cache);
        cache[sun]=x+y;

        return cache[sun];//存入数组
    }

    public static void main(String[] args) {

    }
}
