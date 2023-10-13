package shujv;

import org.w3c.dom.Node;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.function.Consumer;

public class test {
    public static void main(String[] args) {
        //二分查找测试
        /*int[] a={7,13,21,30,30,30,38,44,52,53};
        int bs =BinarySearch.binaryserch(a,54);
        System.out.println(bs);
        int xx=BinarySearch.xianxing(a,53);
        System.out.println(xx);
        int ab=Arrays.binarySearch(a,22);//返回负数的含义，-插入点-1.就是按升序排列，找不到这个数字在数组中，可以在哪里插入，负数表示没找到，减1表示 可以插入在0位置。
        System.out.println(ab);
        int cn=BinarySearch.binaryserchLeftMost(a,30);
        System.out.println(cn);*/

        //动态数组测试
      /*  LiveArraysList LA=new LiveArraysList();
        LA.setCapacity(15);
        System.out.println(LA.getCapacity());
        LA.addLast(1);
        LA.addLast(2);
        LA.addLast(3);
        LA.addLast(4);
        LA.addLast(5);
        LA.get(2);
        //LA.forEach();


        LA.addLast(13);
        LA.addLast(15);
        LA.addLast(78);
        for(Integer integer:LA){//增强for底层就是iterator
            System.out.println(integer);
        }
        System.out.println("索引值为：");
        System.out.println();
        */


        //链表实验
        SingLinkList LI=new SingLinkList();
        LI.addFirst(1);
        LI.addFirst(2);
        LI.addFirst(3);
        LI.addFirst(4);
        LI.addLast(5);
        LI.addLast(78);
        LI.addLast(12);
        LI.addLast(10);
        /*LI.loop2(value->{
            System.out.println(value);
        });*/
        System.out.println("索引值是:"+LI.findSuoYin(12));
        /*LI.loop(value->{
            System.out.println(value);
        });*/

            DobiyLinkedListSentinel AA1=new DobiyLinkedListSentinel();
            AA1.addFirst(12);
            AA1.addFirst(13);
            AA1.addFirst(15);
            AA1.addFirst(18);
            AA1.addFirst(16);
            AA1.addFirst(18);
            for (int i=-1;AA1.findNode(i)==null;i++) {
                System.out.println(AA1.findNode(i));
            }




    }


}
