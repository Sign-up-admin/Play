package shujv;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.function.Consumer;

public class collectionList {
    /*
    *List系列集合独有的方法:
    * void add(int index,E element)
    * E remove(int index)
    * E set(int index)
    * E get(int index)
    * 在此集合中的指定位置插入指定的元素
    * 刪除指定索引处的元素,返回被刪除的元素
    * 修改指定索引处的元素,返回被修改的元素
    * 返回指定索引处的元素
E set(int index,E element)
    *list删除方法
    * 1直接删除
    * 2通过索引删除.remove(index)
    *
    * 因为在调用方法的时候，如果方法出现了重载现象
    * 优先调用，实参跟形参完全一致的
    *
    *
    * 五种遍历方式对比
      迭代器遍历
       在遍历的过程中需要删除元素,请使用迭代器。
      列表迭代器
       在遍历的过程中需要添加元素,请使用列表迭代器。
      增强for遍历
       仅仅想遍历,那么使用增强for或Lambda表达式。
       Lambda表达式
      普通for
       如果遍历的时候想操作索引,可以用普通for。
    * */
    public static void main(String[] args) {
        //创建集合
        List<String>list=new ArrayList<>();
        list.add("N");
        list.add("E");
        list.add("K");
        list.add("O");
        list.add(" ");
        list.add("P");
        list.add("A");
        list.add("R");
        list.add("A");
        //list的五种遍历方法
        //迭代器方法
        Iterator<String> integer =list.iterator();
        while (integer.hasNext()){
            System.out.println(integer.next());

        }
        //增强for(快速，集合或数组名字.for  不是fori，fori是普通for)
        for (String s : list) {

            System.out.printf(s);

        }
//lanba表达式
        list.forEach(s -> System.out.println(s));
        //普通for
        for (int i = 0; i < list.size(); i++) {
            System.out.print(list.get(i));
        }
        //列表迭代器
        ListIterator<String>listIterator=list.listIterator();
       /* while (listIterator.hasNext()){


        }*/




    }
}
