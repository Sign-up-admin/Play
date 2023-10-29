package shujv;

public class Interface {
    public static class Animal{
        Animal(){

        }//空参构造
        Animal(int age,String name){
             this.age=age;
             this.name=name;
        }//有参构造
        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        private int age;
        private String name;

        //行为吃
        public void eat() {

        }
        //不是所有的动物都会游泳，让我们新建一个接口
    }
    public static void main(String[] args) {

        //接口是一种规则，更多的侧重方法行为，
        //类更多的侧重一类事物


        /*
        * 接口使用关键字
        * interface
        * 接口不能实例化
        * */

    }
}
