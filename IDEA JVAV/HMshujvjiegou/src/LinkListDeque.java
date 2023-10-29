import shujv.Deque;

import java.util.Iterator;

// 双向链表节点类
public class LinkListDeque<E>implements Deque<E>,Iterable<E> {
static class Node<E>{
     Node<E> prev;
     E value;

    public Node(Node<E> prev, E value, Node<E> next) {
        this.prev = prev;
        this.value = value;
        this.next = next;
    }

    Node<E>next;

}
int capecity;
int size;
Node<E> sentinel = new Node<>(null, null, null);//哨兵节点
public LinkListDeque(int capecity){
    //在开始时，哨兵节点的前驱和后继指向自己
    this.capecity=capecity;
    sentinel.next=sentinel;
    sentinel.prev=sentinel;

}

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            Node<E> p= sentinel.next;
            @Override
            public boolean hasNext() {
                return p!=sentinel;
            }

            @Override
            public E next() {
                E value= p.value;
                p=p.next;

                return value;
            }
        };
    }
//a added b
    @Override
    public boolean offerFirst(E e) {
    if (isFull()){
        return false;
    }
    Node<E> a=sentinel;
    Node<E> b=sentinel.next;
    Node<E> added = new Node<>(a, e, b);
    a.next=added;
    b.prev=added;
    size++;

        return true;

    }

    @Override
    public boolean offerLast(E e) {
        if (isFull()){
            return false;
        }
        Node<E> a=sentinel.prev;
        Node<E> b=sentinel;
        Node<E> added = new Node<>(a, e, b);
        a.next=added;
        b.prev=added;
        size++;
        return false;
    }

    @Override
    public E pollFirst() {

        return null;
    }

    @Override
    public E pollLast() {
        return null;
    }

    @Override
    public E peekFirst() {
        return null;
    }

    @Override
    public E peekLast() {
        return null;
    }

    @Override
    public boolean isFull() {

        return capecity==size;
    }

    @Override
    public boolean isEmpty() {
        return size==0;
    }
}
