/**
 * Created by n_gonzal on 10/9/2014.
 */
public class RedBlackTreeRun {
    public static void main(String[] args) {
        System.out.println("***********in testing Enviorment**************");
        RedBlackTree rbt = new RedBlackTree();
        System.out.println("created RB tree");
        System.out.println("inserting nodes..........");
        rbt.insert(7, "a");
        System.out.println("1 inserter");
        rbt.insert(6, "b");
        System.out.println("1 inserter");
        rbt.insert(5, "c");
        System.out.println("1 inserter");
        rbt.insert(12, "d");
        rbt.insert(18,"e");



        System.out.println("all nodes inserted successfully...printing");
        System.out.println();
        System.out.println(rbt);
    }
}
