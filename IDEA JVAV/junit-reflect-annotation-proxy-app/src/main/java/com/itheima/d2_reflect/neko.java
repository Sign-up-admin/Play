package com.itheima.d2_reflect;

public class neko {
    public static int a;
    public static final String COUNTRY="中国";
    private String name;
    private int age;

    public neko() {
    }

    public neko(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "neko{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    public static int getA() {
        return a;
    }

    public static void setA(int a) {
        neko.a = a;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
