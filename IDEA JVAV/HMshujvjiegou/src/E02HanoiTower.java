import java.util.LinkedList;

public class E02HanoiTower {
    //汉诺塔计算步数代码
    //a,b,c表示三个柱子
    //数字大小表示，圆盘的大小
    //n代表盘子的个数
    //a代表源柱子，b代表借用存放盘子的柱子柱子，c代表最终目标的柱子
    static LinkedList<Integer> a=new LinkedList<>();
    static LinkedList<Integer> b=new LinkedList<>();
    static LinkedList<Integer> c=new LinkedList<>();
    /*
    * 迭代器（Iterator）是一种设计模式和数据结构，
    * 用于遍历集合（或容器）中的元素，
    * 而不需要暴露集合的内部结构。
    * 它提供了一种统一的方式来访问不同类型的集合，
    * 使得遍历过程更加抽象和通用。
    * 迭代器是编程语言中常见的概念，
    * 通常用于Java、C++等语言中的集合类。
    * 是的，你的理解基本正确。迭代器是一种封装了的代码，它允许你对集合（如数组、列表、集合等）进行遍历或操作，并提供了一种统一的、抽象的方式来访问集合中的元素，而不需要了解集合的内部实现细节。迭代器提供了一组方法，允许你按顺序访问集合中的元素，通常包括以下几个关键方法：

hasNext()：检查是否还有下一个元素可以遍历。
next()：返回下一个元素，并将迭代器的位置移动到下一个元素。
可能还包括其他方法，如 remove()（删除当前元素，可选）。
通过迭代器，你可以以一种一致的方式遍历不同类型的集合，而不必担心集合的底层实现细节。
* 这种抽象性和一致性使得迭代器成为一种非常有用的工具，特别是在处理不同种类的数据结构时。
* 不同语言和编程环境可能会提供不同类型的迭代器，
* 但它们的基本目标都是提供一种便捷的方式来遍历和操作集合中的元素。
User
我错在哪里了？
ChatGPT
你的理解基本正确，没有明显的错误。我可能没有清晰地表达出来。我再总结一下：
迭代器是一种抽象的编程概念，
* 它提供了一种通用的方式来遍历集合（数组、列表、集合等）中的元素
* ，
* 而无需了解集合的内部结构或底层实现。迭代器封装了遍历集合的代码逻辑，
* 并提供了一组方法，如 hasNext() 和 next()，用于访问集合中的元素。
通过迭代器，你可以以一致的方式访问不同类型的集合，
* 使得代码更具通用性和可维护性。

所以，你的理解是正确的，迭代器确实提供了一种封装了的代码，
* 可以实现集合的遍历或操作，并提供了一种一致的方式来处理不同类型的集合或数组。
* 如果你有任何更具体的问题或需要进一步的解释，请随时提出。

*/

    static void init(int n){
        for (int i=n;i>=1;i--){
            a.addLast(i);
        }
    }
    static void move(int n,LinkedList<Integer> a,LinkedList<Integer> b,LinkedList<Integer> c){
        //结束判断
        if (n==0){
            return;
        }//结束判断
        move(n-1,a,c,b);//递归调用函数自己，实现移动盘子,把n-1个盘子由a借c移至b
        c.addLast(a.removeLast());//中间把最后一个盘子由a移动至c
        extracted();
        move(n-1,b,a,c);//把n-1个盘子由b借a移动至c
        extracted();

    }
    private static void extracted() {
        System.out.println(a);
        System.out.println(b);
        System.out.println(c);
        System.out.println("--------------");
    }
    public static void main(String[] args) {
        init(3);
        extracted();
        //迭代器提供的addLast,removeLast方法返回的值是被移除元素的数值，
        //所以要从a柱子移动到b柱子
        /*
        * a.removeLast是移除a的最后一位元素并返回移除数值
        * b.addLast从后面添加一位数值
        *
        *
        * */
        //b.addLast(a.removeLast());
        move(3,a,b,c);

    }

}
