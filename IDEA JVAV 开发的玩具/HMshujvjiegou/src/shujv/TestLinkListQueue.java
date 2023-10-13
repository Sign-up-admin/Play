package shujv;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
/*assertEquals 是一个 JUnit 框架提供的断言方法，
用于在单元测试中比较两个值是否相等。
它通常用于验证预期结果与实际结果是否一致，
从而确定代码是否按预期工作。
assertEquals 方法有多个重载形式，
通常用于比较不同类型的值，包括基本数据类型和对象。
以下是 assertEquals 方法的基本用法：
java
Copy code

assertEquals(expected, actual);
expected 是预期的值。
actual 是实际的值。
assertEquals
方法将比较这两个值是否相等。
如果它们相等，则断言通过，否则将抛出 AssertionError 异常，指示测试失败。
以下是一个示例，
演示如何使用 assertEquals 进行单元测试：*/


public class TestLinkListQueue {
    //@Test
    /*public void offerLimit(){
        LinkListQueue<Integer> queue =new LinkListQueue<>(3);
        queue.offer(1);
        queue.offer(2);
        queue.offer(3);
        queue.offer(4);
        queue.offer(5);
        assertIterableEquals(list.of(1,2,3),queue);


    }*/
    @Test//好，那抹好
    public void offer(){//好，那抹好
        LinkListQueue<Integer> queue =new LinkListQueue<>();
        queue.offer(1);
        queue.offer(2);
        queue.offer(3);
        queue.offer(4);
        queue.offer(5);

        assertIterableEquals(List.of(1,2,3,4,5),queue);

    }
    @Test//好，那抹好
    public void peek(){
        LinkListQueue<Integer> queue =new LinkListQueue<>();
        queue.offer(1);
        queue.offer(2);
        queue.offer(3);
        queue.offer(4);
        queue.offer(5);
        assertEquals(1,queue.peek());
    }
    @Test//好，那抹好
    public void pool(){
        LinkListQueue<Integer> queue =new LinkListQueue<>();
        queue.offer(1);
        queue.offer(2);
        queue.offer(3);
        queue.offer(4);
        queue.offer(5);

        assertEquals(1,queue.poll());
        assertEquals(2,queue.poll());
        assertEquals(3,queue.poll());
        assertEquals(4,queue.poll());
        assertEquals(5,queue.poll());
        assertNull(queue.poll());//这个断言的意思是默认期望值是null，实际值是queue.poll()
    }

}
