package shujv;

public class E06Sum {
public static long sum(long l){
    if (l==1){
        return 1;
    }
    return sum(l-1)+l;
}

    public static void main(String[] args) {
    //因为每次求时，会保留上一层，
        //会爆栈哦System.out.println(sum(100000));使用尾循环解决
        //方法太多，层级太深，栈内存耗尽
        //如果在函数的最后一步是调用一个函数，那么称为尾调用，例如
        /*function a(){
            return b();

        }*/

        /*下面三段伪代码不能叫做尾调用
                funtion a(){
            const c= b()
                return c
        }*/
        //上面代码因为最后一步并非调用函数


        /*function a(){
            return b()+1
        }*/
        //最后一步虽然调用了函数，但又用到了外层函数的数值1
        //一些语言能对尾调用进行优化比如c++scala

    }
}
