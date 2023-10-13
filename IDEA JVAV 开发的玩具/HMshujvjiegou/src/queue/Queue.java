package queue;

public interface Queue <E>{
    //这是自定义队列接口方法
    /*向队列尾插入值
Params: value -待插入值
Returns: 插入成功返回 true, 插入失败返回 false
    * */
    boolean offer(E value);

    /*从对列头获取值,并移除
Returns:如果队列非空返回对头值,否则返回null
*/
    E poll();
    /*从对列头获取值,并移除
Returns: 如果队列非空返回对头值,否则返回 null
*/
    E peek();
    /*检查队列是否为空,不删除
Returns: 空返回true,否则返回 false
*/
    boolean isEmpty();
    //现在我们希望实现功能，给队列一个容量，判断队列是否
    //满了，如果满了返回true，没满返回false
    //还可以实现当队列满时不加入新的节点，
    // 使用offer方法时返回false
    public boolean isFull();
    //检查队列是否已满
}
