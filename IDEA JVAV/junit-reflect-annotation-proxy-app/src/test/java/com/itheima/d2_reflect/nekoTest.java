package com.itheima.d2_reflect;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

/*public Field[] getfields() 获取类的全部成员变量（只能获取public修饰的）
 *public Field[] getDeclaredField  获取类的全部成员变量
 *public Field[] getField(String name)  获取类的某个成员变量（只能获取public修饰的）
 * public Field[] getDeclaredField(String name)  获取类的全部成员变量
 *
 * 在反射中获取到成员变量的作用 依然是取值，赋值，用成员变量的 set get 方法实现。
 * 要使用反射对成员变量进行赋值，对象必须先创建出来
 *
 * 这种代码风格是为了将来学习框架时准备的，虽然现在无法理解，平时开发中还是使用new创建对象，对象中的方法准备变量
 * */
public class nekoTest {
    @Test
    public void testGetFileds() throws NoSuchFieldException, IllegalAccessException {
        //反射第一步必须是获取类的class对象
        Class neko = com.itheima.d2_reflect.neko.class;//可以全类名？可以
        //2.获取类的全部成员变量，并且把每一个成员变量封装成Filed成员变量
        Field[] nekoPara = neko.getDeclaredFields();
        System.out.println("neko");
        for (Field field : nekoPara) {//输入nekopara.for 回车就可以快速输入增强for
            System.out.println(field.getName() + "---->"
                    + field.getType());
        }
        //4.定位某个成员变量
        Field fname = neko.getDeclaredField("name");
        System.out.println(fname);

        Field fage = neko.getDeclaredField("age");

        System.out.println(fage);
//        用反射为成员变量赋值
        neko Azuki = new neko();
        fname.setAccessible(true);//设置暴力反射
        fname.set(Azuki, "Azuki");//意思就是，fname是Filed类型的，是neko类中name成员变量的反射，使用java中反射的set方法，可以实现对对象Azuki中成员变量name的修改
        System.out.println(Azuki);
//        用反射为对象中的成员变量取值
        neko Vanilla = new neko();
        fname.setAccessible(true);//设置暴力反射
        fname.set(Vanilla, "Vanilla");
        Object aName = fname.get(Vanilla);//这个方法会返回一个String,接收
        System.out.println("nameIs"+aName);
    }

}
