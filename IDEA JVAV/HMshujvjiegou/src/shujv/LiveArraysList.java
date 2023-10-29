package shujv;

import java.util.Iterator;
import java.util.function.Consumer;

public class LiveArraysList implements Iterable<Integer> {
    private int size=0;//逻辑大小
    private int capacity=10;//容量



    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int[] getArry() {
        return arry;
    }

    public void setArry(int[] arry) {
        this.arry = arry;
    }

    private int[] arry=new int[capacity];
    public void addLast(int element){//动态从后加入新值
        arry[size]=element;
        size++;
    }
    public void add(int index,int element){//动态从任意位置加入数值,位置，数值
        if (index >=0 && index < size) {//判断index值是否合法
            System.arraycopy(arry, index, arry, index + 1, size - 1);//拷贝复制数组方法，第一个参数是要复制的数组,从哪拷贝，拷贝的目标数组，拷贝目标数组的起始位置，拷贝几个元素

            /*
            * 从arry数组的index位置拷贝到arry(自己)数组的index + 1的起始位置，拷贝size-1个元素
            *就是
            * */
            arry[index] = element;
            size++;
        }else{
            System.out.println("INDEX NOT SUPPORT");
        }

    }
    public int get(int index) {
        if (index>0&&index<=size-1) {
            return arry[index];
        }else {
            return -1;
        }
    }

    //遍历方法
    public void foreach(){
        for (int i=0;i<=size;i++){
            System.out.println(arry[i]);
        }
    }
    //遍历方法1，传入一个函数式接口
    public void foreach(Consumer<Integer> consumer){
        for (int i=0;i<=size;i++){
            System.out.println(arry[i]);
        consumer.accept(arry[i]);
        }//遍历提供数组每一个元素
        //不需要返回值，那么使用Consunmer合适。
    }
//遍历方法2，迭代器遍历
public void foreachD(){
    for (int i=0;i<=size;i++){
        System.out.println(arry[i]);
    }
}


    @Override
    public Iterator<Integer> iterator() {
        return new Iterator<Integer>() {//匿名内部类的写法
            int i=0;
            @Override
            public boolean hasNext() {//有没有下一个元素
                return i<size;
            }
/*
* 在一个循环内不断调用hasnext，如果有下一个元素，返回真，一直循环，直到返回假
*
*
* */
@Override
            public Integer next() {//返回当前元素，并移动到下一个元素
                return arry[i++];
            }
        };
    }
    public int Del(int index){//数组删除，通过copy，把要删除的元素被后面元素覆盖，后面元素下标+1
        int removed= arry[index];
        if (index<size-1){
            System.arraycopy(arry,index+1,arry,index,size-index-1);
        }
        size--;
        return arry[index];

    }
    public void Add(int index,int element){
        //在加数据之前进行容量检查,不够就加
        if (size==capacity){
            //扩 1.5 1.618 2
            capacity+=capacity>>1;//位移操作
            int[] newArray = new int[capacity];
            System.arraycopy (arry,0,newArray,0,size);
            //复制数值到新数组
            arry=newArray;
        }
//添加逻辑
        if (index>=0&&index<size){
            System.arraycopy(arry,index,arry,index+1,size-index);
        }

    }

}
