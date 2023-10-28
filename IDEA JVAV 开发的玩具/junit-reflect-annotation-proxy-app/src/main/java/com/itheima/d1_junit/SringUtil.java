package com.itheima.d1_junit;
/*将junit框架的jar包导入到项目中（注意idea集成了，不需要我们自己导入）
* 为需要测试的业务类，定义对应的测试类，并为每一个业务方法，编写对应的测试方法（必须公共，无参数，没有返回值）
*测试方法上必须声明@Test方法注解，然后在测试方法中，编写代码调用被测试的业务方法进行测试
* */
//字符串工具类
public class SringUtil {
    public static void printNumber(String name){
        System.out.println("名字的长度是"+name.length());
    }
    /*
    * 求字符串的最大索引
    * */
    public static int getMaxIndex(String data){
        if (data==null){
            return -1;

        }
        return data.length();
    }
}
