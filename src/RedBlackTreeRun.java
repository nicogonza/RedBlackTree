/**
 * Created by n_gonzal on 10/9/2014.
 */
public class RedBlackTreeRun {
    public static void main(String[] args) {
        System.out.println("***********in testing Enviorment**************");
        RedBlackTree rbt = new RedBlackTree();
        System.out.println("created RB tree");
        System.out.println("inserting nodes..........");
        rbt.insert(4, "a");
        System.out.println("1 inserter");
        rbt.insert(2, "b");
        System.out.println("2 inserter");
        rbt.insert(6, "b");
        System.out.println("2 inserter");
        rbt.insert(8, "b");
        System.out.println("2 inserter");
        rbt.insert(1, "a");
        System.out.println("1 inserter");
        rbt.insert(3, "a");
        System.out.println("1 inserter");


        /*
        rbt.insert(18,"d");
        rbt.insert(5,"r");
        rbt.insert(23,"e");
        rbt.insert(19,"w");
        rbt.insert(3,"s");
        rbt.insert(8,"c");
        rbt.insert(6,"f");*/

        System.out.println("all nodes inserted successfully...printing");
        System.out.println();
        System.out.println(rbt);
    }
}
