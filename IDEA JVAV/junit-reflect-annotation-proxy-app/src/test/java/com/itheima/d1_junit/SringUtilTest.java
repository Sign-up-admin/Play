package com.itheima.d1_junit;

import org.junit.jupiter.api.*;
import org.springframework.test.context.event.annotation.BeforeTestClass;
import org.springframework.test.context.event.annotation.BeforeTestMethod;
import org.springframework.util.Assert;

//import static org.junit.Assert.assertEquals;


/*将junit框架的jar包导入到项目中（注意idea集成了，不需要我们自己导入）
 * 为需要测试的业务类，定义对应的测试类，并为每一个业务方法，编写对应的测试方法（必须公共，无参数，没有返回值）
 *测试方法上必须声明@Test方法注解，然后在测试方法中，编写代码调用被测试的业务方法进行测试
 * */
//测试类
public class SringUtilTest {//记得测试类的话要，同包同名。后面加Test
    @BeforeEach
    public void test1(){
        System.out.println("test----> BeforeEach 执行了————————");
    }
    @BeforeAll
    public static void test11(){
        System.out.println("test11 BeforeClass 执行了————————");
    }
    @AfterEach
    public void test22(){
        System.out.println("test22-----> AfterEach 执行了");
    }
    @AfterAll
    public static void test2(){
        System.out.println("test2-----> AfterAll 执行了");
    }

    @Test
//    测试类的方法名，test加上被测试方法名
    public void testPrintNumber() {
        SringUtil.printNumber("hello world");
        //SringUtil.printNumber(null);//通过这个测试出代码有空指针报错，说明写得有水平哟
//        如何写得更有水平？就是业务方法没有出现异常，但也可能出现bug?
//        核心功能之一断言机制
        //Assert.assertEquals();
    }
  @Test
    public void getMaxIndex(){
        int maxIndex = SringUtil.getMaxIndex(null);
        System.out.println(maxIndex);
        int admin = SringUtil.getMaxIndex("admin");
        System.out.println(admin);
        Assertions.assertEquals(5,admin);//有毒品吧？断言
      /*@Test测试类中方法必须用它修饰才能成为测试方法，才能启动运行
      * @Before用来修饰一个实例方法，该方法会在每一个测试方法执行前执行一次
      * @Afeter 用来修饰一个实例方法，该方法会在每一个测试方法执行后执行一次
      * @BeforeClass 用来修饰一个静态方法，该方法会在所有测试方法之前只执行一次
      * @AfterClass 用来修饰一个静态方法，该方法会在所有测试方法之后执行一次
      * 在测试方法执行前执行方法，常用于 初始化资源
      * 在测试方法执行完后在执行方法 常用于 释放资源,以上这些过时了，都是junit4，这个框架是junit5
JUnit 5中有很多注解可以用来编写和组织测试用例，例如：

@Test：表示一个普通的测试方法，不能有返回值，不能被private或static修饰2。
@RepeatedTest：表示一个重复执行多次的测试方法，可以替代@Test注解2。
@Nested：表示一个非静态内部类是一个测试类，需要执行其中的测试方法2。
@BeforeEach：表示一个方法会在每个测试方法（除了动态测试方法）执行之前执行2。
@AfterEach：表示一个方法会在每个测试方法（除了动态测试方法）执行之后执行2。
@BeforeAll：表示一个静态方法会在整个测试类中只执行一次，且在所有测试方法之前执行2。
@AfterAll：表示一个静态方法会在整个测试类中只执行一次，且在所有测试方法之后执行2。
这些注解只是JUnit 5中的一部分，还有很多其他的注解可以用来实现更多的功能，例如参数化测试，动态测试，分组测试等等
      * */

      /*java反射这些技术极少用到
      *反射第一步：加载类，获取类的的字节码：class对象 （Java认为万物皆是对象，所以字节码文件也是一种对象，所以Java提供了一个class类来代表字节码，我们获取类，实际上获取的是字节码对象，class对象也会封装各种信息）
      *   获取Class 对象的三种方式
      * Class
      * 第二步，获取类的构造器，生成Construtor对象
      * 第三步 获取类的成员变量 Field对象成员变量
      * 第四步 获取类的成员方法对象
      *
      * */
    }
}
