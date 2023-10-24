package com.itheima;

import com.itheima.dao.BookDao;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AppForLifeCycle {
    public static void main( String[] args ) {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        //ApplicationContext ctx=new ClassPathXmlApplicationContext("applicationContext.xml")
        //上面这个ctx是ApplicationContext这个接口，但是很遗憾，这个接口不具有close这个方法，换成ClassPathXmlApplicationContext这个类就可以了（这个是前者的子类）
        BookDao bookDao = (BookDao) ctx.getBean("bookDao");
        bookDao.save();
        //注册关闭钩子函数，在虚拟机退出之前回调此函数，关闭容器
        //ctx.registerShutdownHook();
        //关闭容器
        ctx.close();
    }
}
