package com.itheima.d2_reflect;

public class cat {
    private String name;
    private  int age;

    public cat() {
        System.out.println("无参数构造器执行了");
    }

    public cat(String name, int age) {
        this.name = name;
        this.age = age;
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

    @Override
    public String toString() {
        return "cat{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
