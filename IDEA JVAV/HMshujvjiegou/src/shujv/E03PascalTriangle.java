package shujv;

public class E03PascalTriangle {
     //杨辉三角中求i行j列中的值为(i-1)(j-1)+(i-1)(j)
    private static int element(int i,int j){
        if (j==0||i==j){
            return 1;//结束循环
        }
        return element(i-1,j-1)+element(i-1,j);
    }
    //打印三角形
    public static void print(int n){
        for (int i=0;i<n;i++){
            kongGe(n,i);
            for (int j=0;j<=i;j++){
                System.out.printf("%-4d",element(i,j));
            }
            System.out.println();
        }
        //打印三角形中的空格


    }
    public static void kongGe(int n,int i){
        int num=(n-1-i)*2;
        for (int j=0;j<num;j++){
            System.out.print(" ");
        }

    }
    public static void main(String[] args) {
        //System.out.println(element(5,4));

        print(9);
    }
}
