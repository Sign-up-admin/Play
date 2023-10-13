package shujv;
//二叉树层的序遍历，使用队列
public class E01Leetcode102 {
/*画画时间
*               1
*             /   \
*            2     3
*          /   \ /   \
*         4    5 6    7
* 要求的遍历顺序是从上倒下，从左往右，
* 1，2，3，4，5，6，7
*          头[2.3]尾，待处理的树节点
*     出 1
* */
public static void main(String[] args) {
    //创建一个二叉树,参数是，值，左，右
    TreeNode root=new TreeNode(1,
            new TreeNode(2,
                    new TreeNode(4),
                    new TreeNode (5)),
            new TreeNode(3,
                    new TreeNode(6),
                    new TreeNode(7)
            )
    );
    //创建一个以链表实现的队列，因为数组实现的有容量限制
     LinkListQueue<TreeNode> queue=new LinkListQueue<>();
     //给这个新建的链表队列，导入树
     queue.offer(root);
     //如果队列非空，循环继续 ！（是非的意思）加上返回值为true的 isEmpty
         int c1=1;//当前层的节点数
     while (!queue.isEmpty()){
         int c2=0;//下一层节点数
             for (int i = 0; i < c1; i++) {
         //做一个指针节点。使用pool（读取队头的值，并且出队）方法接收值
         TreeNode n=queue.poll();
         //打印,让输出更漂亮
         System.out.print(n+" ");

         //来个判断,当前节点是否有左右孩子，以便进行入队
         if (n.Left!=null){
             queue.offer(n.Left);//左孩子入队
             c2++;

         }
         if (n.right!=null){
             queue.offer(n.right);//右孩子入队
             c2++;
         }
         //通过一个内层循环来实现换行，
                 // 每次换行都是在每层的最后一个节点，
                 //就是说一个二层的节点，有两个节点，那么内存循环就需要循环两次
                 // ，之后输出一个换行
                 // 需要知道每一层的最后一个节点
                 //
             }
                 System.out.println();
                 c1=c2;

     }

}
}
