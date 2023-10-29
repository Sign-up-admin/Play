package shujv;

import java.util.ArrayList;

public class GenericsDemo1 {
    public static void main(String[] args) {
        //没有泛型时如何创建对象
        /*
        *作为原始类型 'java.util.ArrayList' 的成员对 'add(E)' 的未检查的调用
        * 泛型的格式:<>

        *泛型的细节
● 泛型中不能写基本数据类型
● 指定泛型的具体类型后,传递数据时,可以传入该类类型或者其子类类型
● 如果不写泛型,类型默认是Object
        *
        * */
        //结论:
//如果我们没有给集合指定类型,默认认为所有的数据类型都是Object类型
//此时可以往集合添加任意的数据类型。
//带来一个坏处:我们在获取数据的时候,无法使用他的特有行为。
// 此时推出了泛型,可以在添加数据的时候就把类型进行统一。
        // 而且我们在获取数据的时候, 也省的强转了,非常的方便。
        //多态的弊端是不能访问子类的特有功能
//obj.length();
//str.length();
       /* ArrayList arrayList =new ArrayList();
        arrayList.add(123);
        arrayList.add("avc");
        arrayList.add(new Dog() {
            @Override
            public void guagua() {

            }

            @Override
            public void zhauba() {

            }

            @Override
            public void method() {

            }
        });
    }*/
    }
}
