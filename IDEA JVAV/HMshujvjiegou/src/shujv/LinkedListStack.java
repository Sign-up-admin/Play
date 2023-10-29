package shujv;

import java.util.Iterator;

//栈，用数组的实现
public class LinkedListStack <E>implements Stack<E>,Iterable<E>{
    /*
    *           底              顶
    *           0 1 2 3 4 5 6 7 8//索引
    *           a b c
    *                |
    *                top指针
    *
    *           以右边作为顶部有个好处，从尾部添加元素也好，性能更高
    *
    * */
    public  E[] array;//数组性能跟好,反正也只是在头部操作
    private  int top;//栈顶指针

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            int p=top;
            //从右到左，从栈顶到栈底遍历
            @Override
            public boolean hasNext() {
                return p>0;//写循环的条件
            }

            @Override
            public E next() {
                E vaule=array[p-1];
                p--;
                return vaule;
            }
        };
    }

    @Override
    public boolean push(E value) {
        if (isFull()){
            return false;
        }
        array[top]=value;
        top++;
        return true;
    }

    @Override
    public E pop() {
        if (isEmpty()){
            return null;
        }
        E value=array[top-1];//top-1才是真正的栈顶元素的位置
        top--;//其实让下一次覆盖就是删除了呀
        return value;
    }

    @Override
    public E peek() {
        return null;
    }

    @Override
    public boolean isEmpty() {
        return top==0;
    }

    @Override
    public boolean isFull() {
        return top==array.length;
    }

}
