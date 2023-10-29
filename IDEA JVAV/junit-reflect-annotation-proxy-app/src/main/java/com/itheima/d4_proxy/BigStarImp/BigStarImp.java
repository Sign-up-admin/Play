package com.itheima.d4_proxy.BigStarImp;
/*java最核心的原理 动态代理
*
*
* */
public class BigStarImp {
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
