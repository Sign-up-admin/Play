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
                Entry aHead = null;
                Entry bHead = null;
                while (p != null) {
                    if ((p.hash & table.length) == 0) {
                        //正确指向
                        if (a != null) {
                            a.next = p;
                        } else {
                            //第一次进入时记录
                            aHead = p;
                        }
                        //分配到a
                        a = p;
                    } else {
                        //正确指向
                        if (b != null) {
                            b.next = p;
                        } else {
                            //第一次进入时记录
                            bHead = p;
                        }
                        //分配到b
                        b = p;
                    }
                    p = p.next;
                }
                //第一件收尾工作，让最后一位旧元素正确指向null
                if (a != null) {
                    a.next = null;
                    newTabel[i] = aHead;//最后那件事情
                }
                if (b != null) {
                    b.next = null;
                    newTabel[i + table.length] = bHead;//最后那件事情
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

    /*为什么计算索引位子用式子hash & （数组长度-1）？
        答：（hash & （数组长度-1））等价于（hash % 数组长度）
        但实际上跟列出的第二个式子，最终运算结果是不一样的，但那前提是这时候数组的长度的必须是 二 的次方
        10进制中去除以10，100，1000时，余数就是被除数的后1，2，3位（后几位）
        2进制中去除以10，100，1000时，余数也是被除数后的1，2，3位（后几位）
        实际上计算机在进行模运算的时候，就是把十进制转化位二进制，保留后几位，就是求余

    * 为什么旧链表会拆分成两条，一条 hssh & 旧数组长度==0 另一条是不等于 0？
        答：旧数组长度换算成二进制后，其中的 1 就是我们要检查的倒数第几位
                旧数组长度 8 二进制 => 1000 检查 倒数第4位
                旧数组长度 16 二进制 => 10000 检查倒数第5位
           hash & 旧数组长度，就是用来检查扩容前后索引位置（余数）会不会变

    * 为神马拆分后的两条链条，一个原来的索引不变，而另一个索引+旧数组长度？
    *
        它们都有个共同的前提，数组的长度是 2 的 n次方

    */

    /*生成hash码的算法就是哈希算法
     *hash算法是将任意对象，分配一个编号的过程，其中编号是一个有限范围内的数字（如int 范围）
     * hash值不可能做到绝对的唯一，参见鸽笼问题，有限 对 无限，
     * 哈希算法和散列算法，摘要算法是同义词
     * */

    //写新的不需要手动输入hash码的各种方法
    public Object get(Object key) {
        //先根据key去调用它的hashCode的方法，生成hash码
        //然后再间接调用刚才这个带两个参数的方法
        int hash = getHash(key);
        return get(hash, key);
    }

    public void put(Object key, Object value) {
        int hash = getHash(key);
        put(hash, key, value);
    }

    public Object remove(Object key) {
        int hash = getHash(key);
        return remove(hash, key);
    }

    private static int getHash(Object key) {
        int hash = key.hashCode();
        return hash;
    }

    public static void main(String[] args) {
        String s1 = "bac";
        String s2 = new String("abc");

        System.out.println(s1.hashCode());
        System.out.println(s2.hashCode());
        int hash = 0;
        for (int i = 0; i < s1.length(); i++) {
            char c = s1.charAt(i);//从字符串中获取每一个字符
            System.out.println((int) c);
            hash = (hash << 5) + c - hash;//和之前是等价的，优化成位运算
        }
        System.out.println(hash);

    }
    /*实际上两个对象的hashcode在，是字符串类型，字符串一样时，code是一样的
     * 那么，不难猜到，jdk对字符串类型的重写了hashCode方法
     * 是如何生成的?
     *       原则 :让值相同的字符串生成相同的 hash 码，尽量让值不同的字符串生成不同的 hash 码
     *       因为在计算机中字符串中的每个字符都有数字11对应，所以
     *       解决方法是让不同位置(比如个位，十位)的值乘上不同的权重，比如最高位的a可以让它乘上100
     *       对于 abc a * 100 +b * 10 + c
     *       对于bac b * 100 + a * 10 + c
     *       区分开位置
     *      经验表明，如果让权重是一个较大的质数，那么效果会更好，让哈希冲突的机会更低
     *      比如31，最后来一个微笑（微笑）的优化
     *      开发中常见的二代哈希算法MurmurHash
     *
     *      思考
     *          1.我们的代码里使用了尾插法，如果改成使用头插法呢？
     *            早在jdk1.7时使用的是头插法，后面改用尾插法，因为在多线程下使用的话，头插法会会带来一个死循环的问题
     *          2.JDK的HashMap中采用了将对象hashCode高低位相互异或的方式减少冲突，怎么理解？
     *             他并没有直接使用对象的hashCode，而是把对象的hashCode的高位与低位，做异或运算，观察源码，发问，为什么
     *             多一个高低位的异或运算就能减少冲突？
     *          3.我们的HashTable中表格容量是 2 的 n次方，很多优化都是基于这个前提，能否不采用2的n次方作为表格容量？
     *              其实也有先例在jdk中，就是同名hashTable，它的初始容量是11，这其实jdk是对算法的取舍，如果采用
     *              2的n次方长度，哈希分散性不好，通过按位与运算让求余数效率高，选质数长度，哈希分散性好，但是只能用模运算，求余效率低
     *          4.JDK的HashMap 在链表长度过长会转换成红黑树，对此你怎么看？
     *              当长度为8的时候，转换为红黑树，是因为防患于未然，其实8的长度够了，但是有人专门造了一些用于攻击的哈希数据，这些哈希值之间都是
     *
     *
     * */
}
