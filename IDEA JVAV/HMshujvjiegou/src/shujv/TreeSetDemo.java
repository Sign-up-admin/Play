package shujv;

import java.util.TreeSet;

public class TreeSetDemo {
    public static void main(String[] args) {
        //创建对象
        Student s1=new Student("zhangsna",19);
        Student s2=new Student("Lisi",20);
        Student s3=new Student("Wangwu",21);



        //创建集合对象
        TreeSet<Student> ts =new TreeSet<>();
        //因为Student对象是自定义对象，没有比较规则，所以无法加入TreeSet，
        //TreeSet的两种比较方式
        /*
        * 1.默认排序/自然排序；JavaBean类实现comparable接口指定比较规则
        *
        * */
        //添加元素
        //年龄一样会覆盖
        ts.add(s1);
        ts.add(s2);
        ts.add(s3);

        //添加元素
        //打印集合

        System.out.println(ts);
        //hasCode和equals方法：哈希表有关的
        //TreeSet 底层是红黑树
    }
}
