package shujv;

import queue.Queue;

import java.util.Iterator;

public class ArryQueue1<E> implements Queue<E>,Iterable<E> {
    private E[] array;//泛型类型的数组叫array
    /*这行代码声明了一个泛型数组变量 array，
    其中 E 是一个泛型类型参数。这表示 array 可以存储任何类型为 E 的元素。
然而，需要注意的是，在 Java 中直接创建泛型数组是受限制的，
因为 Java 不允许创建具有泛型类型参数的数组。编译器会发出警告或错误，
因为泛型数组可能会导致类型安全性问题。
通常，如果需要创建一个泛型数组，可以使用以下方法之一：
使用类型转换：可以创建一个 Object 数组，然后将其转换为泛型数组。
这会引入类型转换的不安全性，因此要小心使用。*/
    int head=0;//头尾指针是将来数组的索引位置，用整型代表
    int tail=0;//尾指针
@SuppressWarnings("all")//这是Java中的注解，抑制警告的产生
    public ArryQueue1(int capacity) {
        array= (E[]) new Object[capacity+1];//类型转换
    }
    //一个有参构造，参数是容量，环形数组得专门留一格容量来实现对环形数组容量满还是不满的检测
    //使用代码上得写你希望的容量capacity，和实际为capacity+1

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            //
            // 创建一个迭代器对象，设置一个指针p，用来遍历数组
            //循环结束的条件是当这个p指针和tail（尾指针相等时）
            int p=head;
            //当
            @Override
            public boolean hasNext() {
                return p!=tail;
                /*匿名内部类中，这里写再循环的条件,当p!=tail条件成立时，
                这个函数返回true通常情况下，
                在 Java 中，hasNext 方法返回 true 表示仍然有下一个元素可供迭代，
                而返回 false 表示迭代已经结束，没有更多的元素可供访问*/
            }

            @Override
            public E next() {
                //每次循环的时候，在这里通过p指针拿到数组的值
                E value=array[p];
                //每次取值完后还要p指针向前移动，并保证取值合法，参见上面模以5
                p=(p+1)%array.length;
                return value;//返回值
            }
        };
    }

    @Override
    public boolean offer(E value) {
    if (isFull()){
        return false;
    }
    //判断，满了的不加，返回false
        array[tail]=value;//从队列的尾部新增元素
        tail=(tail+1)%array.length;//加完数据之后，tail指针向移动一格，注意要模以数组的长度
        //控制这个索引是正确的索引范围，不超过它的有效范围
        return true;

    }

    @Override
    public E poll() {
    if (isEmpty()){
        return null;
    }
    //从队头提取并移除一个元素，首先判断数组是否为空
        E value=array[head];//创建一个临时泛型，存储数组头指针中的值
    head=(head+1)%array.length;//头指针向前移动，并模以5来保证索引是正确的范围
        return value;//返回头指针中的值
    }

    @Override
    public E peek() {
    //取头部的值
        if (isEmpty()){
            return null;
        }
        return array[head];
    }

    @Override
    public boolean isEmpty() {
    if (head==tail){
        return true;
    }
        return false;
    }
    //当头尾指针相等，指向相同，队列就是空的

    @Override
    public boolean isFull() {

        return (tail+1)%array.length==head;//条件表达式的意思是：如果 (tail + 1) % array.length 等于 head，
        // 则返回 true，否则返回 false。
    }
    /*Java 中的布尔表达式方法（通常被称为布尔方法或谓词方法）的典型实现方式是在 return 语句处写判断条件，
    这种方法通常用于返回 true 或 false 的结果，
    具体取决于条件是否成立。*/
    //（tail+1）%“这个叫模”（数组的长度）==head，时环形数组是满的
}
