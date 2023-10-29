package shujv;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;


public class TestArrayStack {
    @Test
    public static void pop(){

    }

    @Test public static void push(){
        LinkedListStack <Object> stack =new LinkedListStack<>();
        stack.push(1);
        stack.push(2);
        stack.push(4);
        stack.push(5);
        assertFalse(stack.push(4));
        assertIterableEquals(List.of(3,2,1),stack);

    }
    public static void main(String[] args) {

    }
}
