package shujv;

import org.junit.jupiter.api.Test;

import java.util.List;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;

public class TestArryQueue1 {
    //lbw 牛逼
    @Test public void offerLimit(){
        ArryQueue1<Integer> queue=new ArryQueue1<>(3);
        queue.offer(1);
        queue.offer(2);
        queue.offer(3);
        queue.offer(4);
        queue.offer(5);
        assertIterableEquals(List.of(1,2,3),queue);


    }
    /*@Test public void poll(){
        ArryQueue1<Integer> queue=new ArryQueue1<>(3);
        queue.offer(1);
        queue.offer(2);
        queue.offer(3);
        queue.offer(4);
        queue.offer(5);

    }*/
}
