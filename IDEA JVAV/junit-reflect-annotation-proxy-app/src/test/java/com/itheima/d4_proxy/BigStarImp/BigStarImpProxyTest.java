package com.itheima.d4_proxy.BigStarImp;

import com.itheima.d4_proxy.BigStar;

public class BigStarImpProxyTest {
    public static void main(String[] args) {
        BigStarImp mapper = new BigStarImp("mapper");
        BigStar proxyBigStar = BigStarImpTest.createProxyBigStarImp(mapper);
        proxyBigStar.dance();


    }
}
