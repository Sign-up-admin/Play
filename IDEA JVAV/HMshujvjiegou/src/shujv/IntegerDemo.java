package shujv;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class IntegerDemo {
    private static int getum(ArrayList<Integer> list) {
        int sum=0;
        for (int i=0;i<=list.size();i++){
            int num=list.get(i);
            sum=sum+num;
        }
        return sum;
    }


    public static void main(String[] args) {
        //包装类toBinaryString把整数转化为二进制，八进制，十六进制
        String str1=Integer.toBinaryString(100);//转化为二进制
        String str4=Integer.toOctalString(100);//转化为八进制
        String str2=Integer.toHexString(100);//转化为十六进制
        int str5 = Integer.parseInt("100");//将字符串类型的整数转成int类型的整形
        /*
        *这个Integer.parseInt("");非常重要，java是一种强类型语言:每种数据在Java中都有各自的数据类型
        *在计算的时候，如果不是同一种数据类型，是无法计算的。
        * 所以要大量的用这个转换
        *细节1:
        * 在类型转换的时候，括号中的参数只能是数字不能是其它，否则会报错
        *
        * 细节2:
        * 8种包装类中，除了Character都有对应的parseXXX方法，进行类型转换。
        *
        * */

        String str3=Integer.toString(200);//
        System.out.println("str1:"+str1);
        System.out.println("str2:"+str2);
        System.out.println("str3:"+str3);
        System.out.println("str4:"+str4);
        System.out.println("str5:"+str5);
        //改写键盘录入代码
/*
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入一个整数");
        String str= scanner.next();
        System.out.println(str);
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入一个字符串");
        String str9 = sc.next();
        System.out.println(str);
*/
//弊端:
//当我们在使用next.nextInt, nextDouble在接收数据的时候,遇到空格,回车,制表符的时候就停止了 //键盘录入的是123 123 那么此时只能接收到空格前面的数据
//我想要的是接收一整行数据
//约定:
//以后我们如果想要键盘录入,不管什么类型,统一使用nextLine
//特点遇到回车才停止
        //String line = sc.nextLine(); System.out.println(line);
        //double v = Double.parseDouble(line);
       // System.out.println(v);

//创建一个集合
    ArrayList<Integer> list = new ArrayList<>();

    //键盘录入数据添加到集合

        while (true){
    Scanner sc2=new Scanner(System.in);
        String s = sc2.nextLine();
        int in=Integer.parseInt(s);//转化为int
            if (in < 1 || in > 100){
                System.out.println("数值不和法");
                continue;

            }//判断值在0~200之间
            list.add(in);//添加数值,in是基本数据类型，Integer自动装箱
            int sum = getum(list);
        /*for (int i = 0; i < List.size(); i++) {
            System.out.println(List.get(i)+"  ");
        }*/
        if (sum>200){//判断条件
            System.out.println("数值满足200");
            break;
        }
        }
    }
/*

    private static int getSum(ArrayList<Integer> list) {
        int sum=0;
        for (int i=0;i<=list.size();i++){
            int num=list.get(i);
            sum=sum+num;
        }
        return sum;
    }
*/
//写个求和方法函数
   /* public static int getSum3(ArrayList<Integer> list){
        int sum=0;
        for (int i=0;i<=list.size();i++){
            int p=list.get(i);
            sum=sum+p;
        }
        return sum;*/
    }




