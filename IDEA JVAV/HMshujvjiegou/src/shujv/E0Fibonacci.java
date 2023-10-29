package shujv;

import java.util.Arrays;

public class E0Fibonacci {
    /*
    * 使用记忆法，也称备忘录法，改进斐波那契数列
    * 让时间复杂度降低
    *
    *
    *
    *
    * */
   public static int list(int index){
       int restun;
       int[] list=new int[index+1];
       //这个是把数组中的每一个元素都初始化成-1
       //返回-1，就是说这个数据不在list中
       //{0,1,-1,-1,-1,-1,-1}
       Arrays.fill(list,-1);

       list[0]=0;
       list[1]=1;

       return sun(index,list);
   }
    public static int sun(int index,int [] list){
       /* if (index==0){
            return 0;
        }
        if (index==1){
            return 1;
        }*/
        if (list[index]!=-1){//取元素
            return list[index];

        }
        int x;
        int y;
        x=sun(index-1,list);
        y=sun(index-2,list);
        list[index]=x+y;//存入值到list

        return x+y;
    }

    public static void main(String[] args) {
        System.out.println(list(3));
    }
}
