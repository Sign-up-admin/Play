package com.itheima.service.impl;

import com.itheima.dao.BookDao;
import com.itheima.service.BookService;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

public class BookServiceImpl implements BookService, InitializingBean, DisposableBean {
//    BookService，InitializingBean这两个接口各对应一个方法，使用接口控制bean生命周期，就是按照spring的标准来进行开发，不用去配置那里写init 和destorsy了
    private BookDao bookDao;

    public void setBookDao(BookDao bookDao) {
        System.out.println("set .....");
        this.bookDao = bookDao;
    }

    public void save() {
        System.out.println("book service save ...");
        bookDao.save();
    }

    public void destroy() throws Exception {
        System.out.println("service destroy");
    }
    //方法名 属性设置之后，椅子就是当属性名设置完以后才去运行这个操作（了解即可）
    public void afterPropertiesSet() throws Exception {
        System.out.println("service init");
    }
}
