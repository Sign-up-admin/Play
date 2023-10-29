package shujv;


import java.util.Iterator;
//import java.util.Queue;妈的，这里居然引用了java.util包里的queue

//以单向环形带哨兵链表实现队列
//https://www.bilibili.com/video/BV1Lv4y1e7HL?p=92&vd_source=4f97a20f3af2a3ce66c46c34251d9109
//特殊情况，当队列中只有一个节点时.让tail指向哨兵节点
//很多时候，这些代码的本身意义和希望操作，意思不是一样的，但是通过操作
//达到了目标，比如用tail=head，意思不是让tail指向head，是通过赋值让
//tail和head指向同一个节点，哨兵节点
public class LinkListQueue <E>
    implements Queue<E>,Iterable<E>{
    //先写一个静态内部类实现节点
    public static class  Node<E>{
        public Node(E value, Node<E> next) {
            this.value = value;
            this.next = next;
        }
        //有参构造函数
        E value;//这个value可以是任意类型
        Node<E> next;//带next的，指向下一节点


    }
    Node<E> head=new Node<>(null,null);//一开始head节点指向哨兵
    Node<E> tail=head;//一开始tail节点指向哨兵,现在指向同一对象
    //tail的next指针最后要指向头，现在没有节点，就是tail自己指向自己
    //一开始没法让tail自己指向自己，因为对象没有被创建出来，
    // 所以呢我们应该在它的构造方法里
    //让tail的next指向head

    int size;//当前节点数，
    int capacity=Integer.MAX_VALUE;//容量,默认最大，用一个带参构造方法，
    // 来实现在创建对象时，由使用者设定capacity
    //在这个带参构造里还是得实现tail指向哨兵节点
    public LinkListQueue(){
        tail.next=head;//让自己指向自己
    }
    //上面是一个不带参数的构造方法

    public LinkListQueue(int capacity){
        this.capacity=capacity;
        tail.next=head;
    }
    @Override
    public boolean isFull() {
        //这个方法是检查队列是否已满
        if (size==capacity){
            return true;
        }
        return false;
    }
    @Override
    public boolean isEmpty() {
        /*用来检查队列是否为空的方法，空返回true，否则返回false*/
        //如果队列是空的情况下head和tail都指向哨兵节点，就是head==tail
        //
        return head==tail;
    }


    @Override
    public Iterator<E> iterator() {
        /*在Java中，Iterator（迭代器）是一种接口，
        它用于遍历集合（例如列表、集合、映射等）中的元素，
        而不需要了解集合的内部实现细节
        Iterator 提供了一种统一的方式来遍历不同类型的集合，使得代码更具通用性和可维护性。
        Iterator 接口包含以下主要方法：
        boolean hasNext()：用于检查是否还有下一个元素可以遍历。
        如果还有下一个元素，则返回 true；否则，返回 false。
        E next()：返回下一个元素，并将迭代器的位置移动到下一个元素。
        如果没有下一个元素可遍历，通常会抛出 NoSuchElementException 异常。
        void remove()（可选）：用于从集合中删除迭代器最后返回的元素。
        并不是所有的集合都支持这个方法，一些集合可能会抛出 UnsupportedOperationException 异常。*/
        return new Iterator<E>() {
            //声明一个指针
            Node<E> p= head.next;//哨兵本身不用迭代，就从哨兵的下一个开始迭代
            @Override
            public boolean hasNext() {
                /*走到头的标志，对于环形链表来讲，这个指针指向的又是哨兵又是这个头自己了，说明走了一圈了
                *
                * p!=head时，说明还没走到头*/
                return p!=head;
            }

            @Override
            public E next() {
                E value =p.value;//每次应该先拿到指针的值，先记录一下
                p=p.next;//每次这个指针向后走一个值
                return value;//最终返回这个值
            }
        };
    }
    /*@Override
    public void clear() {

    }*/

    @Override
    public boolean offer(E value) {
        if (isFull()){
            return false;
        }
        //队列满了
        //实现队列插入功能的方法
        Node<E> added =new Node<>(value,head);//创建新节点，会改动3个指针，value值从参数传来
        /*1.让原来的head指向新节点
        * 2.新节点的next指向头（新节点是队尾，队头是head，哨兵）
        * 3.新节点做为尾巴了，就让tail指向新节点
        * */
        tail.next=added;//让tail指向新节点
        tail=added;//给tail赋值，让新节点等于tail
        size++;
        return true;//返回真，插入成功
    }

    /*@Override
    public E remove() {
        return null;
    }*/

    @Override
    public E poll() {
        //这是出队的方法，要求获取第一个节点的值，并移除它
        //如果队列非空返回队列头节点的值，否则返回null
        //怎么删除呢？让哨兵节点的指向跳过第一个节点，就意味着它被删除了
        if (isEmpty()){
            return  null;
        }
        Node<E> first=head.next;//创建一个临时节点接收第一个节点的值，然后删掉第一个节点
        head.next=first.next;
        if (first==tail){
            tail=head;
        }
        //特殊情况，当队列中只有一个节点时.让tail指向哨兵节点
        //很多时候，这些代码的本身意义和希望操作，意思不是一样的，但是通过操作
        //达到了目标，比如用tail=head，意思不是让tail指向head，是通过赋值让
        //tail和head指向同一个节点，哨兵节点
        size--;
        return first.value;
    }

    /*@Override
    public E element() {
        return null;
    }*/

    @Override
    public E peek() {
        //从队列的头部去获取值，不移除，如果对列非空返回队头值，否则返回null
        //用isEmpty方法判断队列是否为空，空返回null
        if (isEmpty()){
            return null;
        }
       return head.next.value;//head.next是哨兵
    }

    /*@Override
    public void forEach(Consumer<? super E> action) {
        Iterable.super.forEach(action);
    }*/

    /*@Override
    public int size() {
        return 0;
    }


    @Override
    public boolean contains(Object o) {
        return false;
    }


    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return null;
    }

    @Override
    public boolean add(E e) {
        return false;
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }*/

}
