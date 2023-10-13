package shujv;
/*
* Java中要防止这种代码交错执行带来的线程安全
*我们x需要锁，
* 1.synchronized 关键字，功能少
* 2.ReentrantLock 可重入锁，功能多
* 锁的特性是同一线程都来加锁
* 那只有一个线程能够成功加锁，其它线程加锁失败。
* 一旦某一个线程在获得锁时，陷入了这种阻塞，那么只能在将来持有锁的线程解锁时
* 才能被唤醒
* */

import java.util.Arrays;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class TestThreadUnsafe {
    private final String[] array =new  String[10];
    private int tail=0;
    private int size=0;
    ReentrantLock lock=new ReentrantLock();//创建Java自带的锁对象
    //让当前线程的offer进入阻塞，就不能使用锁对象的相关方法
    Condition tailWaits=lock.newCondition();//这个对象不是直接new出来的
    //是通过lock对象的newCondition方法创建出来的，叫条件变量对象，像一个集合，这个集合内可以存储多个需要阻塞的线程
    //
    /*
    * 在需要保护共享资源的代码块中，
    * 使用 lock() 方法获取锁。
    * 如果锁当前没有被其他线程持有，
    * 那么当前线程将获取到锁，
    * 则当前线程将被阻塞，直到锁可用为止。
    * tryLock() 方法尝试获取锁，如果锁当前可用，
    * 它会立即返回 true 并获取锁；如果锁被其他线程持有，
    * 它会立即返回 false，而不会阻塞当前线程。
    * */
    public void offer(String e) throws InterruptedException {
        //这是以后使用ReentrantLock的套路
        //首先创建对象ReentrantLock
        /*一般加锁写在try块的外面
        *
        * */
        //lock.lock();//在需要保护的代码之前，调用锁对象中的锁方法lock
        lock.lockInterruptibly();//也是加锁的一个方法，都是可以在阻塞状态随时打断
        //可以保护代码

        try {
            if (isFull()){
                //以前呢我们是让它直接退出返回一个布尔类型的变量
                //现在我们希望换一个逻辑，让线程在队列满时，这里等着，直到什么时候由空位了
                //可以继续运行，继续向下添加
                tailWaits.await();//当前线程加入 taliWaits集合，并且让次线程阻塞
                tailWaits.signal();//让集合中某个阻塞的线程唤醒，让其恢复运行

                }
                 array[tail]=e;
            if (++tail==array.length)
            {
                tail=0;
            }
            size++;
        //tail++;

        }finally {
        lock.unlock();//解锁代码

        }
        //为了保证解锁代码一定被执行，我们可以使用Java中的 try finally的语法
        //这个是Java报错的代码finally是一定被执行的代码，所以把unlock代码放在里面


    }

    @Override
    public String toString() {
        return Arrays.toString(array);
    }
    public boolean isFull(){
        return size==array.length;
    }

    public static void main(String[] args) throws InterruptedException{
        TestThreadUnsafe queue = new TestThreadUnsafe();
        for (int i = 0; i < 10; i++) {
            queue.offer("e"+i);

        }
        new Thread(()->{
            try {
                System.out.println(Thread.currentThread().getName()+"添加元素之前");
                queue.offer("e10");
                System.out.println(Thread.currentThread().getName()+"添加元素之后");
            }
            catch (InterruptedException e){
                throw new RuntimeException(e);
            }
        },"t1").start();

        new Thread(()->{
            System.out.println("开始唤醒");
            try {
                queue.lock.lockInterruptibly();
                queue.tailWaits.signal();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                queue.lock.unlock();
            }
        }, "t2").start();
    }

}
