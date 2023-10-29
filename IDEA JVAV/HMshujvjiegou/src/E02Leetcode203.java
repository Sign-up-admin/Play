//这题是根据值删除链表节点

import shujv.LeetCodeListNode;
public class E02Leetcode203 {
    //已经有leetcode节点了，写一个带哨兵的
    //方法1
    public LeetCodeListNode removeElement(LeetCodeListNode head,int val){
       LeetCodeListNode s=new LeetCodeListNode(-1,head);//新建的哨兵节点，值无所谓，如果不加哨兵，第一个节点要特殊处理
       LeetCodeListNode p1=s;//指向哨兵的指针节点
       LeetCodeListNode p2=s.next;//指向头指针的指针节点
       while (p2!=null){//执行条件，链表没有循环完null
           if (p2.val==val){
               //删除,直接让p1移动时跳过现在的p2，到p2前，这个节点不会被跳过检查
               p1.next=p2.next;
               p2=p2.next;
               //如果指针p2指向的节点的值，等于目标值，那么就让p1指向的节点的next，指向‘p3’，
               //p2就继续移动

           }else {
               //不是这个值，p1，p2向后平移
               p1=p1.next;
               p2=p2.next;
           }
       }

       return s;
   }
//方法2递归
    /*
    方法2 思路,
    “我”是当前节点的意思
    递归函数负责返回:从当前节点(我)开始,完成删除的的链表
    1. 若我与V相等,应该返回下一个节点递归结果，这个递归结果
    2. 若我与v不等,应该返回我,但我的 next 应该更新（让我能够带上后续删过的节点）就是反转next*/
public LeetCodeListNode removeElement2(LeetCodeListNode p,int val){
        if (p==null){
            return null;
        }//递归中“递”的结束条件，”归“的开始条件
        if (p.val==val){
            return removeElement2(p.next,val);//如果在“归”时发现了目标节点，返回下一个节点递归结果
        }else {
            p.next=removeElement2(p.next,val);
            return p;
        }
}

    public static void main(String[] args) {
        LeetCodeListNode o5=new LeetCodeListNode(5,null);
        LeetCodeListNode o4=new LeetCodeListNode(4,o5);
        LeetCodeListNode o3=new LeetCodeListNode(3,o4);
        LeetCodeListNode o2=new LeetCodeListNode(2,o3);
        LeetCodeListNode o1=new LeetCodeListNode(1,o2);
        System.out.println(o1);
        E02Leetcode203 I1=new E02Leetcode203();
        I1.removeElement2(o1,3);
        System.out.println(o1);

    }
}
