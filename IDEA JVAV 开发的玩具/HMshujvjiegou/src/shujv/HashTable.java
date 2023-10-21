package shujv;

//红黑树，b树，时间复杂度达到了，对数级别
//比红黑树，b树，什么树。都更快的查找到数据
/*哈希表（这个表的有趣之处在于，使用hash进行数据访问，底层是数组，所以不需要遍历，直接就可以访问了，除了hash值是重复的情况）
 * 给每一份数据分配一个编号，放入表格（数组）
 * 建立编号与表格的索引关系，表格能容纳所有的数据，将来就可以通过编号快速查找数据
 *
 * 1.理想情况是编号当唯一，表格能容纳苏所有数据
 * 2.现实是不能说为了容纳所有数据，造一个超级大表格，编号也有可能重复
 *
 * 如果是通过数组的索引来定位数据的话，搜索速度和数据规模没有关系
 * 所以使用了这种思路以后
 * 查找数据的时间复杂度是O(1)
 *
 * 解决
 * 1.有限长度数组，以拉链的方式存储数据
 * 2.允许编号适当重复，通过数据自身来进行区分
 *
 * 规定这里的编号叫哈希编码
 * */
public class HashTable {

    static class Entry {
        int hash;//哈希码，它就是用来定位你接下来的这个键值它是存储在我们数组中哪个索引位置的
        Object key;//键（这是每个数据独一无二的）
        Object value;//值
        Entry next;//下一个节点

        public Entry(int hash, Object key, Object value) {
            this.hash = hash;
            this.key = key;
            this.value = value;
        }
    }

    Entry[] table = new Entry[16];//建议表格的长度是二的次方数，因为可以进行位运算
    int size = 0;//代表元素在表格中的个数
    float loadFacot = 0.75f;//定义负载因子,所以根据下面公式，负载因子乘以当前节点长度（size）等于table容量长度时，
    int threshold = (int) (loadFacot * table.length);//类型强转，触发扩容

    //实现哈希表的增删改查
    //根据 hash 码获取元素val
    Object get(int hash, Object key) {
        int idx = hash & (table.length - 1);//通过位运算，算出key（idx）
        //判断这个索引处有没有数据
        if (table[idx] == null) {
            return null;
        }
        //不为null的话，实际上存储的是一个链表的头节点，沿着头节点遍历整个链表（意思就是后面的节点是接在链表的后面的）
        Entry p = table[idx];//指针
        while (p != null) {
            //拿key进行对比
            if (p.key.equals(key)) {
                return p.value;
            }
            p = p.next;
        }
        return null;
    }

    //向
    // hash 表存入新key value 如果key 重复 ，则更新 value
    //如果找到空位了，直接插入。那沿着链表如果有重复的key，那就做更新，没有重复的key就做添加
    //hash值重复，key不重复，就从链表的尾部添加新增的节点
    void put(int hash, Object key, Object value) {
        //1.idx处有空位，直接新增
        int idx = hash & (table.length - 1);//对key进行模运算求出索引idx
        if (table[idx] == null) {
            //index处有空位，直接新增
            Entry entry = new Entry(hash, key, value);
            table[idx] = entry;//新增
            size++;
        } else {
            //2.idx处无空位，沿着链表查找 有重复的key更新，否则新增
            Entry p = table[idx];//指针，干脆以后习惯只要是链表就写个指针
            while (true) {
                if (p.key == key) {
                    p.value = value;
                }
                //退出whlie true 的条件
                if (p.next == null) {
                    break;
                }
                p = p.next;
            }
            p.next = new Entry(hash, key, value);//新增，退出循环时，说明p现在是在最后一个节点
            //元素lk做了新增，那整个哈希表的size
            size++;
        }
    }

    //扩容，当hash表中元素大于阈值时触发扩容
    private void resize() {
        Entry[] newTabel = new Entry[table.length << 1];//左移一位是乘二，右移是除二
        //转移到新链表、把链表拆分，一个链表拆分成两个链表
        for (int i = 0; i < table.length; i++) {
            Entry p = table[i];//拿到每个链表头
            if (p != null) {
                //如果链表头不为空，拆分链表，移动到新数组
                /*拆分规律
                 *          拿hash值 模以旧table的长度
                 *       一个链表最多拆成两个
                 *        hash & table.length == 0 的一组（把它们串成一个数组 a ）
                 *        hash & table.length != 0的一组（把它们串成一个数组 b ）
                 *        拆分的过程是我们有一个p指针，代表原来旧链表中的元素
                 *        b指针代表的是拆分后的新链表的尾指针
                 *
                 *                  p
                 *       0->8->16->24->32->40->48->null
                 *
                 *                       a
                 *       0->16->32->40->48->null
                 *
                 *               b
                 *       8->24->40->null
                 * */
                Entry a = null;
                Entry b = null;
                //还需要两个变量来记录拆分后这两个节点的头指针
                Entry aHead=null;
                Entry bHead=null;
                while (p != null) {
                    if ((p.hash & table.length) == 0) {
                        //正确指向
                        if (a != null) {
                            a.next = p;
                        }
                        //分配到a
                        a = p;
                    } else {
                        //正确指向
                        if (b != null) {
                            b.next = p;
                        }
                        //分配到b
                        b = p;
                    }
                    p = p.next;
                }
                //第一件收尾工作，让最后一位旧元素正确指向null
                if (a != null) {
                    a.next = null;
                    newTabel[i]=aHead;//最后那件事情
                }else {
                    //第一次进入时记录
                    aHead=p;
                }
                if (b != null) {
                    b.next = null;
                    newTabel[i+table.length]=bHead;//最后那件事情
                }else {
                    bHead=p;
                }
                //第二件收尾工作，现在a，b指针指向了它们各自的尾巴，但是我们要拿到这两个链表的头节点，这样才能让新table中存储各个新链表的头节点，塞到索引的某处
                //最后一件事情，把新链表的头节点存入数组， a 这个新链表在 table 中的索引位置保持不变，b 它在原来的索引位置基础上 加 旧链表 长度
                //规律； a链表 保持索引位置不变， b链表索引位置+table.length

            }
            table = newTabel; //代替掉旧数组
            threshold = (int) (loadFacot * table.length);//重新计算新阈值，因为换了
        }
    }

    //根据hash 码删除。返回值是删除的value
    Object remove(int hash, Object key) {
        int idx = hash & (table.length - 1);
        if (table[idx] == null) {
            //没啥可以删除的
            return null;
        }
        Entry p = table[idx];//以当前节点头节点开始循环，沿着链表查找
        Entry prev = null;//初始值是null，这个节点是指向链表中要删除节点的上一个节点，如果我们要删除一个中间节点，要删除的节点后面的节点需要重新被指向，在要删除节点的前面，把链表连上，被删除的节点就被覆盖了
        while (p != null) {
            if (p.key.equals(key)) {
                //找到了删除
                if (prev == null) {
                    //要删除的节点是根节点
                    table[idx] = p.next;
                } else {
                    prev.next = p.next;//相当于直接跳过了这个p节点
                }
                size--;
                return p.value;
            }
            p = p.next;
            prev = p;//每次记录上一个节点
        }
        return null;
    }
    //其实，jdk中带的hash表和这个参数是有点不一样的，jdk的不需要传入“hash码”
    //因为其实这个hash码是把key做哈希处理，生成一个哈希码。就是说其实传入key值就可以了

    //但是我们现在用求模运算来代替hash码和key之间的关系，把哈希码当作是被除数，数组的长度当作是除数，出来的余数，就是hash码
    //我感觉其实吧，可以直接换成hash函数，我知道我急，但是先别急，我的出发点是好的，但是先别出发。
    /*求模运算替换为位运算，因为位运算性能更好
    * 前提 数组长度是 2 的 n 次方
    * hash % 数组的长度 等价于 hash & （数组长度-1）
    * 位与运算（&）是一种二进制位运算，它对两个二进制数的每一位执行逻辑与操作。位与运算的规则如下：
        如果两个操作数的对应位都为1，结果位为1。
        如果两个操作数的对应位中有一个或两个为0，结果位为0。
        *
        * */

    /*哈希表的底层数组是固定大小的，需要扩容实现性能的保证，那什么时候扩容？
     * 负载因子                      N
     *           load factor（a）=  ——
     *                              M
     *           其中 M 是 数组 的长度，N 是实际数据长度，它俩的比值就是负载因子
     *              比值不宜过小和过大，工业经验是这个比值是0 .75最好
     *              扩容是容量翻倍，因为我们的索引值，算法是根据和数组长度值进行求余，
     *              所以新的容量翻倍不会影响求余和索引，并且还会把链表的长度变短
     * */

}
