package oopinteface;

public class Rabbit extends Animal {

    @Override
    public void eat() {
        System.out.println("兔子吃");
    }

    public Rabbit() {
    }

    public Rabbit(int age, String name) {
        super(age, name);
    }
}
