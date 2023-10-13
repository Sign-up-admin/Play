import oopinteface.Animal;
import oopinteface.Dog;
import oopinteface.Frog;
import oopinteface.Rabbit;

public class DUO_TAI {
    public static void main(String[] args) {
        Rabbit R=new Rabbit(1,"tt");
        Frog frog=new Frog(2,"FF");
        Dog D=new Dog(1,"DD");
        Show(R);
        Show(frog);
        Show(D);


    }
    public static void Show(Animal animal){
        animal.eat();
        System.out.println(animal.getAge()+animal.getName());


    }
}
