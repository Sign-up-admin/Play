package shujv;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.function.Consumer;

public class Iterator2Collection {
    /*Collection系列集合三种通用的遍历方式:
1.迭代器遍历
2.增强for遍历:
3.1ambda表达式遍历
迭代器遍历相关的三个方法:
Iterator<E> iterator()获取一个迭代器对象
boolean hasNext()判断当前指向的位置是否有元素
E next()获取当前指向的元素并移动指针
细节注意点:
1,报错NoSuchelementException
2,迭代器遍历完毕,指针不会复位
3,循环中只能用一次next方法
4,迭代器遍历时,不能用集合的方法进行增加或者删除

*/
    public static void main(String[] args) {
        //创建集合并添加元素
        Collection<String> collectioncoll= new ArrayList<>();
        collectioncoll.add("世");
        collectioncoll.add("界");
        collectioncoll.add("你");
        collectioncoll.add("好");
        collectioncoll.add("！");
        //获取迭代器对象
        //迭代器就好比是一个箭头，默认指向集合的0索引处
        Iterator<String> iterrator=collectioncoll.iterator();
        //Consumer<String> consumer=new Consumer;
        //hasNext方法会判断集合是否还会有下一个元素，没有返回false，
        //next方法会做两件事情获取元素，并移动指针
        //通常这两个方法配套使用，一一对应

        while (iterrator.hasNext()){
            String str =iterrator.next();
            System.out.println(str);
        }
        System.out.println(iterrator.hasNext());//false了
        //当上面的循环结束之后，迭代器已经指向最后没有元素了
        //如果我们要进行第二次循环，只能再获取一个新的迭代器
        Iterator<String>iterator2=collectioncoll.iterator();
        while (iterator2.hasNext()){
            String str2=iterator2.next();
            System.out.println(str2);
        }

//增强for遍历
        /*其实底层就是Iterator迭代器,JDK5时加入.
        * for(数据的类型 变量名:数组或集合)
        *集合名.for+回车 可以快速生成增强for循环
        *修改增强for中的变量，是不会改变集合中原本的变量
        * 非常的美滋滋
        * */
        Collection<String>collection3=new ArrayList<>();
        collection3.add("覃");
        collection3.add("朝");
        collection3.add("军");
        collection3.add("帅");
        collection3.add("帅");
        collection3.add("帅");

        for (String s : collection3) {
            System.out.println(s);
        }
/*
2.利用匿名内部类的形式
底层原理:
  其实也会自己遍历集合,依次得到每一个元素
  把得到的每一个元素,传递给下面的accept方法
  s依次表示集合中的每一个数据

*
 */
        Collection<String> collection4=new ArrayList<>();
collection4.forEach(new Consumer<String>() {
    @Override
    public void accept(String s) {
        System.out.println(s);
    }
});
//lambda表达式
        collection4.forEach(s -> System.out.println(s));

/*
*1. Collection是单列集合的顶层接口,所有方法被List和Set系列集合共享
 2. 常见成员方法:
add、clear、remove、contains、isEmpty、size
 3. 三种通用的遍历方式:
迭代器:在遍历的过程中需要删除元素,请使用迭代器。
● 增强for、Lambda:
仅仅想遍历,那么使用增强for或Lambda表达式。
*
* */

    }
}
