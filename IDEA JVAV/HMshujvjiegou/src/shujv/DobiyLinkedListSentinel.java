package shujv;

//import org.w3c.dom.Node;

public class DobiyLinkedListSentinel {
    private static class Node{
        private Node prev;
        private int value;
        private Node next;
        public Node(Node prev, int value, Node next) {
            this.prev = prev;
            this.value = value;
            this.next = next;
        }

    }
    private Node head;//头哨兵
    private Node tail;//尾哨兵
    public Node findNode(int index){
        //用第一个存储数据的节点作为索引零，那么头指针的索引为-1，有时候需要遍历到他
        int i=-1;
        //java的for循环中赋值部分是只能新建一个变量，所以i在for外面声明赋值，用于循环的指针p在for里
        for (Node p=head;p!=tail;i++){
            if (i==index){
                return p;
            }
        }
        return null;
    }

    public DobiyLinkedListSentinel() {
        head= new Node(null, 666, null);
        tail= new Node(null, 999, null);
        head.next=tail;
        tail.prev=head;
    }
    public void addFirst(int value){
        Node NewNode= new Node(null, value, null);
        NewNode.prev=head;
        NewNode.next=findNode(0);
        head.next=NewNode;
        if (tail==head){
            tail.prev=NewNode;
        }
        findNode(0).prev=NewNode;

    }
    public  void removeFirst(){
        head.next=findNode(1);
        findNode(1).prev=head;
        findNode(0).prev=null;
        findNode(0).next=null;
        System.out.println("成功removeFirst");
    }
    public void insert(int index,int value){
            Node insertNode = new Node(null, value, null);
            insertNode.prev = findNode(index - 1);
            insertNode.next = findNode(index );//注意，是要插在索引节点之前
            findNode(index - 1).next = insertNode;
            findNode(index +1).prev = insertNode;
            System.out.println("成功insert");
    }
    public void remove(int index){
        findNode(index-1).next=findNode(index+1);
        findNode(index+1).prev=findNode(index-1);
        findNode(index).prev=null;
        findNode(index).next=null;
        System.out.println("suess remove");
    }

}
