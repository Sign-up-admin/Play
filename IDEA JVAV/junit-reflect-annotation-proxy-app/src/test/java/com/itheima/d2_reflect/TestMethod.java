package com.itheima.d2_reflect;

import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/*
 * 目标；掌握获取类的成员方法，并对其进行操作。
 *
 * 通过反射获取的成员方法，和普通方法一样，依然是执行
 * public Object invoke(Object obj ,Object ... args)触发某个对象的方法执行
 * public void setAccessible(boolean flag)设置为 true 表示禁止检查访问控制（暴力反射）
 *
 * nekoClass.getMethods();//获取类的全部成员方法（只能获取public修饰的方法）
 * nekoClass.getMethod();
 * nekoClass.getDeclaredMethods();
 * nekoClass.getDeclaredMethod(String name<Class?>paramterType);//获取某个成员方法，要方法名和参数类型
 * */
public class TestMethod {
    //    反射第一步，得到class对象，相当于得到对象
    @Test
    public void testGetMethods() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Class<neko> nekoClass = neko.class;
        //获取全部成员方法
        Method[] methods = nekoClass.getDeclaredMethods();//获取类的全部成员方法（只能获取public修饰的方法）,并把类中每一个成员方法封装成一个Method对象，然后返回这个对象，我们创建一个Method数组接收
        //遍历
        for (Method method : methods) {
            System.out.println(method.getName()
                    + "--->"
                    + method.getParameterCount()//方法参数个数
                    + "--->"
                    + method.getReturnType());//方法返回值类型
        }
        //4.获取某个方法
        Method runClass = nekoClass.getDeclaredMethod("run");//无参数的
        System.out.println(runClass.getName()
                + "--->"
                + runClass.getParameterCount()//方法参数个数
                + "--->"
                + runClass.getReturnType());//方法返回值类型
        Method eatCalss = nekoClass.getDeclaredMethod("eat", String.class);
        System.out.println(eatCalss.getName()
                + "--->"
                + eatCalss.getParameterCount()//方法参数个数
                + "--->"
                + eatCalss.getReturnType());//方法返回值类型
        neko fraise = new neko("Fraise", 4);
        eatCalss.setAccessible(true);//暴力反射
        Object invokeat = eatCalss.invoke(fraise, "Fraise", "蛋糕");//传入一个对象,和方法反射需要的变量（无参数方法，不需要传入参数）
        System.out.println(invokeat);
    }


}
