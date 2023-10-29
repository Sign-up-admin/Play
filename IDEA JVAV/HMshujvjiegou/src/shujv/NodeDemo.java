package shujv;

import org.w3c.dom.Node;

public class NodeDemo {
    private Node head;
   private class  Node{
       private int value;
       private Node next;
       public Node(int value,Node next){
           this.value=value;
           this.next=next;
       }
   }
   public void addFirst(int value){
       //链表为空
       // Node NewNode=new Node(value,null);
        //head.next=NewNode;
//链表非空
       Node NewNode=new Node(value,head);
       head=NewNode ;
   }
//遍历
    public void loop(){
       Node p=head;
       while (p!=null){
           System.out.println(p.value);
           p=p.next;

       }
    }

}
