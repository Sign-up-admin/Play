package com.itheima.service.impl;

import com.itheima.dao.BookDao;
import com.itheima.dao.impl.BookDaoImpl;
import com.itheima.service.BookService;

public class BookServiceImpl implements BookService {
    //5.删除业务层中使用new的方式创建Dao对象，把new 部分干掉后，通过我们提供的set方法把对象给它
//    private BookDao bookDao=new BookDaoImpl();
    private BookDao bookDao;

    public void save(){
        System.out.println("book service save ... ");
        bookDao.save();
    }
    //提供对应的方法
    public void setBookDao(BookDao bookDao) {
        this.bookDao = bookDao;
    }
}
