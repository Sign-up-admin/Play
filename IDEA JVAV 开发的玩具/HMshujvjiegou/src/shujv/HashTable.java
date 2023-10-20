package shujv;

//红黑树，b树，时间复杂度达到了，对数级别
//比红黑树，b树，什么树。都更快的查找到数据
/*哈希表
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
    //
    static class Entry {
        int hash;//哈希码
        Object key;//键
        Object value;//值
        Entry next;
        public Entry(int hash, Object key, Object value) {
            this.hash = hash;
            this.key = key;
            this.value = value;
        }

    }

}
