/**
 * Created by n_gonzal on 10/9/2014.
 */
public class RedBlackTreeRun {
    public static void main(String[] args) {
        System.out.println("***********in testing Enviorment**************");
        RedBlackTree p = new RedBlackTree();
        System.out.println("created RB tree");
        System.out.println("inserting nodes..........");
        p.insert(4, "A");
        p.insert(1, "N");
        p.insert(14, "N");
        System.out.println("nodes inserted successfully");
        System.out.println("printing tree");
        System.out.println(p);
    }
}
