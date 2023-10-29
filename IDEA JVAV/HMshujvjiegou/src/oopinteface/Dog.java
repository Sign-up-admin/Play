package oopinteface;

public class Dog extends Animal implements swin{
    public Dog() {
    }

    public Dog(int age, String name) {
        super(age, name);
    }

    @Override
    public void eat() {
        System.out.println("dog eat");
    }

    @Override
    public void swin() {

        System.out.println("dog swin");
    }
}
