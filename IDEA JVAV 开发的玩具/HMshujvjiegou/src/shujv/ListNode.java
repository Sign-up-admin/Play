package shujv;

public class ListNode {
    int val;
    ListNode next;

    // 私有构造方法，用于创建链表节点
    public ListNode(int val) {
        this.val = val;
    }
    @Override
    public String toString() {
        StringBuilder sb=new StringBuilder(64);
        sb.append("[");
        ListNode p=this;
        while (p!=null){
            sb.append(p.val);
            if (p.next!=null){
                sb.append(",");

            }
            p=p.next;
        }
        sb.append("]");
        return sb.toString();
    }

    // 静态工厂方法，接受一组整数参数，创建链表并返回头节点
    public static ListNode of1 (int... values) {
        if (values == null || values.length == 0) {
            return null;
        }
        ListNode dummy = new ListNode(0); // 创建一个虚拟头节点
        ListNode current = dummy;

        for (int value : values) {
            current.next = new ListNode(value);
            current = current.next;
        }

        return dummy.next; // 返回真正的头节点
    }

}