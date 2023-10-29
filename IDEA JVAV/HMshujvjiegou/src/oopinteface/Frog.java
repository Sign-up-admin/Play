package oopinteface;

public class Frog extends Animal implements swin{
    @Override
    public void eat() {
        System.out.println("青蛙吃");
    }

    @Override
    public void swin() {
        System.out.println("青蛙游泳");

    }

    public Frog() {
    }

    public Frog(int age, String name) {
        super(age, name);
    }
}
