package shujv;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class BlockingQueue1<E> implements BlockingQueue<E>{
    private final E[] array;
    private int head;
    private int tail;
    private int size;
    ReentrantLock lock=new ReentrantLock();
    private Condition headWaits=lock.newCondition();
    private Condition tailWaits=lock.newCondition();

    public BlockingQueue1(int capercity) {//添加一个元素之后，poll 等待队列非空
        array = (E[]) new Object[capercity];//新建一个Object数组，容量为capercity，做类型转换
    }


    @Override
    public void offer(E e) throws InterruptedException {

            lock.lockInterruptibly();
            try {
                while (isFull()){//当数组不满时反复检测是否为虚假唤醒，是就继续等待
                    //当数组满时，我们让线程进入为offer方法提供的Condition对象，现在我们让它等待
                    tailWaits.await();
                }
                array[tail]=e;
                if (++tail==array.length){
                    tail=0;
                }
                size++;
                headWaits.signal();

            }finally {
                lock.unlock();

            }

    }

    @Override
    public boolean offer(E e, long timeout) throws InterruptedException {

        lock.lockInterruptibly();
        try {
            long t = TimeUnit.MILLISECONDS.toNanos(timeout);
            while (isFull()){//当数组不满时反复检测是否为虚假唤醒，是就继续等待
                //当数组满时，我们让线程进入为offer方法提供的Condition对象，现在我们让它等待
                if (t<=0){
                    return false;
                }
                t=tailWaits.awaitNanos(t);//最多等待多少纳秒
            }
            array[tail]=e;
            if (++tail==array.length){
                tail=0;
            }
            size++;
            headWaits.signal();

        }finally {
            lock.unlock();

        }

        return false;
    }

    @Override
    public E Epoll() throws InterruptedException {
        lock.lockInterruptibly();
        try {
            while (isEmpty()){//当数组不满时反复检测是否为虚假唤醒，是就继续等待
                //当数组满时，我们让线程进入为offer方法提供的Condition对象，现在我们让它等待
                headWaits.await();
            }
            E e = array[head];
            array[head]=null;
            if (++head==array.length){
                head=0;
            }
            size--;
            tailWaits.signal();

        return  e;
        }finally {
            lock.unlock();

        }
    }
    public boolean isFull(){

        return size==array.length;

    }
    public boolean isEmpty(){
        return size==0;
    }
}
