package shujv;
//反转链表
import java.util.List;

//反转链表的方法
//1.构造一个新代码，从旧链表中依次拿到每个节点，创建新节点添加至新链表的头部
public class LeetCode206 {
    //方法1反转链表
    public LeetCodeListNode reverseList(LeetCodeListNode o1){
        LeetCodeListNode n1=null;//创建一个新链表 n1，初始为空。
        LeetCodeListNode p=o1;//创建一个指针节点 p，指向输入链表 o1 的头节点。
        while (p!=null){
            n1=new LeetCodeListNode(p.val,n1);
            p=p.next;//指向下一个节点
        }
        return n1;

    }
    //方法2
    /*
    * 方法2
与方法1类似,构造一个新链表,
* 从旧链表头部移除节点,添加到新链表头部,
* 成后 新链表即是倒序的,
* 区别在于原题目未提供节点外层的容器类,
* 这里提供一个,另外一个区别是并不去构造新节点
*/
    //外层容器类
static class List{
    LeetCodeListNode head;
    public List(LeetCodeListNode head){
        this.head=head;
    }//提供一个有参构造方法，传入头节点
    public LeetCodeListNode removeFirst(){
        LeetCodeListNode first=head;
        if (first!=null){
            head=first.next;
        }
        //防止head是null，防止空指针,将原本的第二个节点赋值给first
        return first;//返回头节点


    }
    public void addFirst(LeetCodeListNode first){
        first.next=head;
        head=first;

    }
    }
    //要做的事情就是把list1的头节点添加到到list2中，然后把list1头节点删掉
    public LeetCodeListNode reverseList2(LeetCodeListNode head){
    List list1=new List(head);
    List list2=new List(null);
    while (true){
        LeetCodeListNode first=list1.removeFirst();
        if (first==null){
            break;
        }
        list2.addFirst(first);
    }

return list2.head;
    }
//方法3 递归
    public LeetCodeListNode reverseList3(LeetCodeListNode p){
    if (p==null||p.next==null){
        return p;
    }
//整个节点记录一下它一层层返回时的值
        LeetCodeListNode last = reverseList3(p.next);
    //在递归调用结束时，让链表反转，就是让next反转
        //这里，p已经指向5了，我们想要让链表逆转，也就是让链表中的相邻元素之间中的next指向它的前一位，例如
        //在原来的单链表中，4元素的next指向为5，也就是p是4，p.next是5，现在我们希望在back(4)这一层递归中把5指向4
        //5在这一层递归中是p.next，对5的指向进行操作，就是对p.next.next进行操作，所以有，p.next.next=p;
        p.next.next=p;
        // 现在5虽然指向了4，可是4还是指向了5，那么这是一个死循环，
        // 现在我们让4指向null，
        p.next=null;
        // 随后的back(3)中3，4指向3
        //3又指向null.....
        //最后1指向null，完成反转
    return last;
    }
    //方法4
    //public LeetCodeListNode reverseList4(LeetCodeListNode P);
    //方法5
    public static void main(String[] args) {
       LeetCodeListNode o5=new LeetCodeListNode(5,null);
        LeetCodeListNode o4=new LeetCodeListNode(4,o5);
        LeetCodeListNode o3=new LeetCodeListNode(3,o4);
        LeetCodeListNode o2=new LeetCodeListNode(2,o3);
        LeetCodeListNode o1=new LeetCodeListNode(1,o2);
        System.out.println(o1);
        //LeetCodeListNode n1=new LeetCode206().reverseList(o1);
        //LeetCodeListNode n1=new LeetCode206().reverseList2(o1);
        LeetCodeListNode n1=new LeetCode206().reverseList3(o1);

        System.out.println(n1);



    }
}
