package com.itheima.d2_reflect;

public class StudentTest {
    /*java反射这些技术极少用到
     *反射第一步：加载类，获取类的的字节码：class对象 （Java认为万物皆是对象，所以字节码文件也是一种对象，所以Java提供了一个class类来代表字节码，我们获取类，实际上获取的是字节码对象，class对象也会封装各种信息）
     *   获取Class 对象的三种方式
     * Class
     * 第二步，获取类的构造器，生成Construtor对象
     * 第三步 获取类的成员变量 Field对象成员变量
     * 第四步 获取类的成员方法对象
     *
     *获取class对象的三种方式
     * 1.  Class c1 = 类名.class
     * 调用Class提供方法:
     *                  public static Class forName(String package);
     * Object提供的方法
public Class getClass(); Class c3=对象.getClass();
     * */
    public static void main(String[] args) throws ClassNotFoundException {
        Class c1 = Student.class;
        System.out.println(c1.getName());//全类名
        System.out.println(c1.getSimpleName());//简名 Student

        //方法2
        Class c2 =Class.forName("com.itheima.d2_reflect.Student");//它担心我们的路径写的有异常
        System.out.println(c1==c2);

//        方法三
        Student student = new Student();
        Class c3=student.getClass();
        System.out.println(c3==c2);
    }
}
