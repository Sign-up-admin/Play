package shujv;

import java.util.List;
import java.util.ArrayList;


//删除链表倒数第n个节点
public class E03Leetcode19 {
public int recursion(LeetCodeListNode p,int n){
    if (p==null){
        return 0;
    }
    int nth =recursion(p.next,n);//下个节点的倒数位置，记录一下

    System.out.println(p.val+" "+nth);

    return recursion(p.next,n);//n是下一个节点的倒数位置
}

    public static void main(String[] args) {
    //ListNode ni=new ListNode.of(1,2,3,4);
    LeetCodeListNode o5=new LeetCodeListNode(5,null);
        LeetCodeListNode o4=new LeetCodeListNode(4,o5);
        LeetCodeListNode o3=new LeetCodeListNode(3,o4);
        LeetCodeListNode o2=new LeetCodeListNode(2,o3);
        LeetCodeListNode o1=new LeetCodeListNode(1,o2);
        //缺of方法，没写完
        //弄不出来，看样子这个of方法不过是数组转换成链表的一个快捷方法

    }
}
