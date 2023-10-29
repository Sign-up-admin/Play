package com.itheima.d2_reflect;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
//获取构造器的作用是初始化类的对象返回

public class catTest {
    @Test
    public void Test2Constructor() {
        //反射第一步，必须先得到这个类的Class对象
        Class c = cat.class;
        //反射第二步，获取类的全部构造器
        Constructor[] constructors = c.getConstructors();//获取类内部的全部构造器(只能获取到public修饰的，开发中不常用)
//这个方法会把类内部的每一个构造器的，封装成一个所谓的Constructor对象
//        然后把这些对象放到构造器数组里面，因此我们要给到一个Constructor数组对象来
        for (Constructor constructor : constructors) {
            System.out.println(constructor.getName()
                    + "constructors---------->"
                    + constructor.getParameterCount());//获取构造器中参数的数量
        }

    }

    @Test
    public void Test3Constructor() {
        Class c2 = cat.class;

        Constructor[] declaredConstructors = c2.getDeclaredConstructors();//这个方法获取类中全部构造方法
        for (Constructor declaredConstructor : declaredConstructors) {
            System.out.println(declaredConstructor.getName()
                    + "declaredConstructors---------->"
                    + declaredConstructor.getParameterCount());//获取构造器中参数的数量
        }
    }

    @Test
    public void Test4Constructor() throws NoSuchMethodException {
        Class c3 = cat.class;
        Constructor c3Constructor = c3.getConstructor();//通过类获取其中一个方法（只能public修饰），需要方法名和构造器参数类型，是一个可变参数
        System.out.println(c3Constructor.getName()
                + "c3Constructor--------->"
                + c3Constructor.getParameterCount());
        //通过参数获取构造方法
        Constructor c3Constructor1 = c3.getConstructor(String.class, int.class);
        System.out.println(c3Constructor1.getName()
                + "c3Constructor1--------->"
                + c3Constructor1.getParameterCount());
    }

    /*T newInstance(Obiect...initargs)  调用此构造器对象表示的构造器，并传入参数，完成对象的初始化
     * public viod setAccessible(bollean flag) 设置为true，表示禁止检查访问控制（暴力反射）
     * */
    @Test
    public void testGetConstructor() throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        Class c = cat.class;
//        获取类的某个构造器，无参数构造器
        Constructor declaredConstructor = c.getDeclaredConstructor();//获取无参数构造器
        System.out.println(declaredConstructor.getName()
                + "declaredConstructor--------->"
                + declaredConstructor.getParameterCount());
        cat cat=(cat) c.newInstance();//调用初始化对象，并进行返回
        System.out.println(cat);
//        3，获取有参数构造器
        Constructor declaredConstructor13 = c.getDeclaredConstructor(String.class, int.class);
        System.out.println(declaredConstructor13.getName()
                + "declaredConstructor13--------->"
                + declaredConstructor13.getParameterCount());
    cat cat2=(cat) declaredConstructor13.newInstance("Azuki",4);
        System.out.println(cat2);
    }
}
