package shujv;
public class LeetCodeListNode {
    //LeetCode很多题目需要的链表类
    //LeetCode只提供了每个链表的节点类，实际上还需要一个类似集合的链表类，
    public int val;
    public LeetCodeListNode next;
    public LeetCodeListNode(int val,LeetCodeListNode next){
        this.val=val;
        this.next=next;

    }//构造方法
 
    @Override
    public String toString() {
        StringBuilder sb=new StringBuilder(64);
        sb.append("[");
        LeetCodeListNode p=this;
        while (p!=null){
            sb.append(p.val);
            if (p.next!=null){
                sb.append(",");

            }
            p=p.next;
        }
        sb.append("]");
        return sb.toString();
    }

    /*public static LeetCodeListNode back(LeetCodeListNode p){
        if (p==null){
            return p;
        }
        if (p.next==null){
            return p;
        }
        //这里，p已经指向5了，我们想要让链表逆转，也就是让链表中的相邻元素之间中的next指向它的前一位，例如
        //在原来的单链表中，4元素的next指向为5，也就是p是4，p.next是5，现在我们希望在back(4)这一层递归中把5指向4
        //5在这一层递归中是p.next，对5的指向进行操作，就是对p.next.next进行操作，所以有，p.next.next=p;
        p.next.next=p;
        // 现在5虽然指向了4，可是4还是指向了5，那么这是一个死循环，现在我们让4指向null，随后的back(3)中3，4指向3
        //3又指向null.....
        //最后1指向null，完成反转
        p.next=null;
        LeetCodeListNode last=back(p.next);
        return last;
    }*/
}
