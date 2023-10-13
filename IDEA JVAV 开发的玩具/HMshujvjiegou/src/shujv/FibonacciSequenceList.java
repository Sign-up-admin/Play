package shujv;

public class FibonacciSequenceList {

    public static int f(int n){
        if (n==0){
            return 0;
        }
        if (n==1){
            return 1;
        }
       /* int x=f(n-1);
        int y=f(n-2);*/
        int p=f(n-1)+f(n-2);
        return p;
    }

    public static void main(String[] args) {

        int n=f(10);
        System.out.println(n);

    }
}
