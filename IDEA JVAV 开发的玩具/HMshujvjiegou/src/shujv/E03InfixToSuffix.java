package shujv;
//栈，使用栈来实现由中缀表达式转换后缀表达式子

import java.util.LinkedList;

public class E03InfixToSuffix {

    /*
        思路

        |   |
        |   |
        |   |
        _____

        a+b             ab+
        a+b-c           ab+c-
        a*b+c           ab*c+
        a+b*c           abc*+
        a+b*c-d         abc*+d-
        (a+b)*c         ab+c*
        (a+b*c-d)*e     abc*+d-e*
        a*(b+c)         abc+*

        1. 遇到非运算符 直接拼串
        2. 遇到 + - * /
            - 它的优先级比栈顶运算符高, 入栈, 如: 栈中是 + 当前是 *
            - 否则把栈里优先级 >= 它 的都出栈, 它再入栈, 如: 栈中是 +*, 当前是 -
        3. 遍历完成, 栈里剩余运算符依次出栈
        4. 带()
            - 左括号直接入栈, 左括号优先设置为0
            - 右括号就把栈里到左括号为止的所有运算符出栈
     */
    public static void main(String[] args) {
        System.out.println(infixToSuffix("a+b"));
        System.out.println(infixToSuffix("a+b-c"));
        System.out.println(infixToSuffix("a+b*c"));
        System.out.println(infixToSuffix("a*b-c"));
        System.out.println(infixToSuffix("(a+b)*c"));
        System.out.println(infixToSuffix("(a+b*c-d)*e"));
        System.out.println(infixToSuffix("a*(b+c)"));
    }

    //一个进行选择的方法,对运算符做匹配
    static int priority(char c) {
        return switch (c) {
            case '*', '/' -> 2;//当符号为*/时，返回一个2
            case '+', '-' -> 1;//当符号为+-时，返回一个1
            default -> throw new IllegalArgumentException("不合法的运算符！");
            //如果字符不匹配任何情况，
            // 它会抛出一个 IllegalArgumentException 异常，
            // 表示传入的字符不是合法的运算符。IllegalArgumentException 是 Java 标准库中的一个异常类，
            // 它继承自 RuntimeException。
            // 通常，它用于表示传递给方法的参数是不合法或无效的情况。
            // 如果某个方法期望接收特定类型或范围的参数值，
            // 并且传递的参数值不符合要求，那么这个方法通常会抛出 IllegalArgumentException 异常。
        };

    }

    //中缀转后缀.接收前缀表达式，返回的是后缀表达式子
    static String infixToSuffix(String exp) {
        LinkedList<Character> stack = new LinkedList<>();//因为注意放的是运算符，泛型是Character
        //在要创建对象的地方，输入类的名称，然后按下 Ctrl + 空格 来触发代码提示。
        //选择要创建的对象类，并按下 Enter，这将会生成对象的实例。
        StringBuilder sb = new StringBuilder(exp.length());//用StringBuilder来实现字符串转换成
        //StringBuilder 是 Java 中用于操作字符串的可变类，它允许你高效地构建和修改字符串，而不会像普通的 String 对象那样产生大量的中间字符串对象。
        //
        //在这里，exp.length() 用于指定 StringBuilder 的初始容量，
        // 这可以提高字符串构建的效率。
        // 如果你事先知道要构建的字符串的大致长度，
        // 指定初始容量可以减少内部数组的扩展次数，从而提高性能。

        //现在fori运算表达式子，遍历每一个元素
        /*
         * 1，遇到非运算符，直接拼串
         * 遇到 +- / *
         *   -栈是空的，直接入栈
         *   -它的优先级比栈顶运算符高，入栈
         *   -否则把栈里优先级>=它的 都出栈，它自己再入栈
         *   —遍历完成，栈里剩余运算符依次出栈
         *
         * */
        for (int i = 0; i < exp.length(); i++) {
            //使用了一个 for 循环来遍历字符串 exp 中的每个字符，
            // 并将每个字符存储在变量 c 中。
            // 这是一种常见的方法，用于逐个处理字符串的每个字符。
            // charAt 是 Java 字符串类 String 提供的方法之一，
            // 用于获取字符串中指定位置的字符
            char c = exp.charAt(i);
            switch (c) {
                case '*', '/', '+', '-' -> {
                    if (stack.isEmpty()) {
                        stack.push(c);
                    } else {
                        //如果它的优先级比栈顶运算符高，入栈
                        if (priority(c) > stack.peek()) {
                            stack.push(c);

                        }//否则把栈里优先级>=它的 都出栈，它自己再入栈
                        else {
                            while (!stack.isEmpty() && priority(stack.peek()) >= priority(c)) {
                                sb.append(stack.pop());
                            }
                            stack.push(c);

                        }
                    }
                }
                case '('->{
                    stack.push('(');                }
                case ')'->{
                    while (!stack.isEmpty() && stack.peek() != '(') {
                        sb.append(stack.pop());
                    }
                    stack.pop();
                }
                default -> {
                    sb.append(c);
                }


            }

        }
        while (!stack.isEmpty()) {
            sb.append(stack.pop());//如果非空的话，就给它弹出到空，
            // 并加到字符串sb上
        }
        return sb.toString();
    }
}

