package shujv;

public interface Stack<E> {
    /*数组
    * 链表
    * 队列
    * 栈
    * 只能在其一端添加或删除数据，
    * */
    /*向栈顶压入元素
    * Params：value-待压入元素
    * Returns：压入成功返回trun，否则返回false*/
    boolean push(E value);
    /*从栈顶弹出元素
    * Returns：栈非空返回栈顶元素，栈空返回 null*/
    E pop();
    /*返回栈顶元素，不弹出
    * Returns：栈非空时返回栈顶元素，栈为空返回 null*/
    E peek();
    /*判断栈是否为空
    * Returns：空返回true，否则返回false*/
    boolean isEmpty();
    /*判断栈是否已满
    * Returns：满返回true，否则返回false*/
    boolean isFull();

}
