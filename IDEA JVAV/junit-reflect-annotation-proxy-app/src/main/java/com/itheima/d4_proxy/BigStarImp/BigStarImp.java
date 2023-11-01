package com.itheima.d4_proxy.BigStarImp;

import com.itheima.d4_proxy.BigStar;

/*java最核心的原理 动态代理
*面向切面编程之 动态代理 （AOP）
* 1.对象如果嫌身上干的事太多的话，可以通过代理来转移部分职责
* 2.在不变动原有的代码基础上，为代码实现功能的追加
*
* 代理对象如何知道，被代理对象中的方法？ Java通过接口
* */
public class BigStarImp implements BigStar {
    private String name;

    public BigStarImp(String name) {
        this.name = name;
    }

    public String sing(String name){
        System.out.println(this.name+"正在唱的歌："+name);
        return "再见，鼠鼠们~~~";
    }
    public void dance(){
        System.out.println(this.name+"热舞中~~~");
    }
}
