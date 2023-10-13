package shujv;

import java.util.Iterator;
import java.util.function.Consumer;

public class  SingLinkList implements Iterator<Integer>{//整体
    private Node head=null;//头指针

    @Override
    public boolean hasNext() {
        return false;
    }

    @Override
    public Integer next() {
        return null;
    }


    private static class Node{//节点类，内部类，对外暴露的东西越少越好
    int value;//值
    Node next;//java中的指针就是引用，Node类
    public Node(){};
    public Node(int value,Node next){//javaBEN有参构造

         this.value=value;
         this.next=next;
    }
}//加入节点方法
    public void addFirst(int value){
    //1,链表为空
    //head=new Node(value,null);
    //链表非空/*
        // 为了防治旧节点的丢失，应该先把新节点指向旧的节点
        //
        // 然后再，让旧节点的上一个节点，指向新节点
        // */
        new Node(value,head);//新节点指向的位置就是head指向的位置
        head=new Node(value,head);

    }

    //链表遍历
    public void loop(Consumer<Integer> consumer) {
        Node P = head;
        while (P != null) {//做一个P指针，从head依次指向尾巴
            consumer.accept(P.value);
            P = P.next;//指向下一个的操作方法

        }

    }
    //whlie循环遍历法
    public void loop2(Consumer<Integer> consumer){
    for (Node P=head;P!=null;P=P.next){
        consumer.accept(P.value);
    }
    }
    //for循环遍历法,其实差不多
    public void loo3(Consumer<Integer>consumer){
        Node P=head;
        while (P!=null){
            P=P.next;

        }
    }
    //泛型就是将来要遍历出值的类型

    //Iterrator迭代器遍历法
    public Iterator<Integer> iterator(){
    return new Iterator<Integer>() {
        Node p=head;

        @Override
        public boolean hasNext() {
            return p!=null;//这个return位置要填写的是当链表（数据内部之类）为空时返回false的条件，这里
            //意思是当p非null时，返回为真
        }


        @Override
        public Integer next() {
            int v= p.value;
            p=p.next;//重写方法了，也重新写指向下一个元素的代码
            return v;//方法提供返回值
        }
    };

    }
    //从尾部添加节点
    /*
    * 首先遍历链表找到最后一个节点
    *然后把最后一个节点的next指针指向新加入的节点
    *
    * */
    public void addLast2(Node addLsatNode){
        Node P=head;
        while (P!=null){
            P=P.next;
        }
        P.next=addLsatNode;
    }
    public Node findLast(){
        Node P;
        //当链表为空时
        if (head==null){
            return null;

        }//当链表不为空时
        for (P=head;P!=null;P=P.next){

        }
        return P;
    }//封装思想找最后一个节点写一个方法，从最后一个节点加入又写一个方法
    public void addLast(int value){
        Node last=findLast();
        if (last==null){
            addFirst(value);
            return;
        }

        last.next=new Node(value,null);//从后面添加新节点指向null

    }
    public int findSuoYin(int value){
        int i=0;
        for (Node P=head;P.next!=null;P=P.next,i++){
            if (value== P.value){

            }
        }
        return i;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
