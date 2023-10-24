package com.itheima;
/*ioc入门
* di入门，基于ioc管理bean，Servcie中需要的dao对象通过 我们提供的方法 进入到Service中
* Service与Dao两个bean之间的关系描述，通过配置实现
* */
import com.itheima.dao.BookDao;
import com.itheima.service.BookService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App2 {
    public static void main(String[] args) {
        //然后说我们想要拿bean，得先拿到ioc容器
        //获取ioc容器,但这是一个接口，不能创建对象，但是可以new一个实现类,并把配置文件内的东西告诉它
        ApplicationContext ctx=new ClassPathXmlApplicationContext("applicationContext.xml");
        //获取bean,并强转成和类一样的类型
//        BookDao bookDao = (BookDao) ctx.getBean("bookDao");
//        bookDao.save();
        BookService bookService = (BookService) ctx.getBean("service");
        bookService.save();

    }
}
