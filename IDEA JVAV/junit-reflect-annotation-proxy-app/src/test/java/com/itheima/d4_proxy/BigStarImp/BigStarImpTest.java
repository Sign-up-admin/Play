package com.itheima.d4_proxy.BigStarImp;

import com.itheima.d4_proxy.BigStar;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/*java最核心的原理 动态代理
 *面向切面编程之 动态代理 （AOP）
 * 1.对象如果嫌身上干的事太多的话，可以通过代理来转移部分职责
 * 2.在不变动原有的代码基础上，为代码实现功能的追加
 *
 * 代理对象如何知道，被代理对象中的方法？ Java通过接口
 * */
public class BigStarImpTest {
    //返回值是一个接口
    public static BigStar createProxyBigStarImp(BigStarImp bigStarImp) {
        /*newProxyInstance(ClassLoader loader,
                                          Class<?>[] interfaces,
                                          InvocationHandler h)
                                          参数ClassLoader 1.用于指定一个类加载器，到时候用这个类加载器生成加载的代理类
                                          这个类的写法是固定的，开发中都是使用 当前类里 的 类加载器，BigStarImpTest.class.getClassLoader()

                                          参数2 Class<?>[] interfaces.指定生成的代理长什么样子，也就是有哪些方法，将类中方法包装成一个数组，给过去就行了
                                          参数3 用来指定生成的代理对象要干什么事情
                                          */
        BigStar BigStarProxy = (BigStar) Proxy.newProxyInstance(BigStarImpTest.class.getClassLoader()
                , new Class[]{BigStar.class}, new InvocationHandler() {
                    @Override//回调方法,第一个参数，java会把当前代理对象当作一个Object 传给invoke方法，
                    //第二个参数，他会把当前调用的方法传入invoke
                    //第三个参数会把方法的参数作为Object数组，传入invoke
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        //代理对象要做的事情
                        if (method.getName().equals("sing")) {
                            System.out.println("打钱");
                        } else if (method.getName().equals("dance")) {
                            System.out.println("场地");
                        }
                        return method.invoke(bigStarImp, args);//返回方法
                    }
                });
        return BigStarProxy;

    }

}
