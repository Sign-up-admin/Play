package com.itheima.d2_reflect;

public class neko {
    public static int a;
    public static final String COUNTRY = "中国";
    private String name;
    private int age;

    public neko() {
        System.out.println("有参构造器执行~~~");
    }

    private void run() {
        System.out.println("跑的贼快~~~");
    }

    public void eat() {
        System.out.println("主人做的真好吃~~~");
    }

    public String eat(String name,String Food) {
        return name + "最爱吃主人做的"+Food+"了~~~";
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
