public class E03PascalTriangle {
    //element用来求第i行j列的值
    //print方法用来打印杨辉三角,其中为三角形的高度，用双层循环，最外层循环每行
    //内层循环每行中的每列，显示数值
    //用记忆法进行缓存优化，计算结果用二维数组记录，行数为，列数会随每行的不同变化，可以先不指定
    //element函数传入一个二维数组

    public static int element(int[][] triangle,int i,int j){
        if (triangle[i][j]>0){
            return triangle[i][j];

        }
        //如果在二维数组中已经储存了计算结果
        if (j==0||i==j){
            triangle[i][j]=1;
            return  1;
        }

        triangle[i][j]=element(triangle,i-1,j-1)+element(triangle,i-1,j);//递归
        return triangle[i][j];


    }
    public static void print(int n){
            int [][] triangle=new int[n][];//显然行数就是n行数，列数先不指定，留空
        for (int i = 0; i < n; i++) {
            triangle[i]=new int[i+1];//这段指定了每一行的列数，
            // 这行代码为 triangle 中的第 i 行分配了一个具体的数组对象，
            // 长度为 i + 1，以表示帕斯卡三角形中的每个元素。
            printSpace(n,i);
            for (int j = 0; j <=i; j++) {
                System.out.printf("%-4d",element(triangle,i,j));

            }
            System.out.println();
        }
            //System.out.println("--------------");
    }
    public static void printSpace(int n,int i){
        int num =(n-1-i)*2;
        for (int j = 0; j < num; j++) {
            System.out.print(" ");

        }

    }

    public static void main(String[] args) {
        //System.out.println(element(4,2));
        print(20);
    }
}
