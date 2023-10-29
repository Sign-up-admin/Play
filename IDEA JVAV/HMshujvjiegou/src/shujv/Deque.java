package shujv;
//queue
// deque double ended
//
public interface Deque <E>{
    //向队头添加元素
    boolean offerFirst(E e);
    //向队尾添加元素
    boolean offerLast(E e);
    //向队列头部添加元素
    E pollFirst();
    //向队列尾部添加元素
    E pollLast();
    //从头部删除元素
    E peekFirst();
    //从尾部删除元素
    E peekLast();
    //判空
    boolean isFull();
    //判满

    boolean isEmpty();



}
