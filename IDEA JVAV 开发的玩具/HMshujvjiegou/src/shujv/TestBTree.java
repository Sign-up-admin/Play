package shujv;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestBTree {
    //卢本伟牛逼
    @Test
    void spil1(){
         /*
                5               2|5
              /   \     ==>    / | \
           1|2|3   6          1  3  6

         */
        BTree tree = new BTree();
        BTree.Node root=tree.root;
        root.leaf=false;
        root.keys[0]=5;
        root.keyNumber=1;

        root.children[0]=new BTree.Node(new int[]{1,2,3});
        root.children[0].keyNumber=3;

        root.children[1]=new BTree.Node(new int[]{6});
        root.children[1].keyNumber=1;

        tree.split(root.children[0], root, 0);
        assertEquals("[2, 5]", root.toString());
        assertEquals("[1]", root.children[0].toString());
        assertEquals("[3]", root.children[1].toString());
        assertEquals("[6]", root.children[2].toString());
    }
    @Test
    @DisplayName("split(t=3)")
    void split2(){
        /*
                  6                 3|6
               /     \     ==>    /  |  \
           1|2|3|4|5  7         1|2 4|5  7

         */

        BTree tree = new BTree(3);
        BTree.Node root=tree.root;
        root.leaf=false;
        root.keys[0]=6;
        root.keyNumber=1;
        root.children[0]=new BTree.Node(new int[]{1,2,3,4,5});
        root.children[0].keyNumber=5;

        root.children[1]=new BTree.Node(new int[]{7});
        root.children[1].keyNumber=1;

        tree.split(root.children[0],root,0);
        assertEquals("[3, 6]",root.toString());
        assertEquals("[1, 2]",root.children[0].toString());
        assertEquals("[4, 5]",root.children[1].toString());
        assertEquals("[7]",root.children[2].toString());
    }


}
