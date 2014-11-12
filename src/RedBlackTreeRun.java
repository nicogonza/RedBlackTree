/**
 * Created by n_gonzal on 10/9/2014.
 */
public class RedBlackTreeRun {
    public static void main(String[] args) {
        System.out.println("***********in testing Enviorment**************");
        RedBlackTree rbt = new RedBlackTree();
        RBTValidator validate = new RBTValidator();
        System.out.println(rbt);
       System.out.println("test one, delete red leaf 10");
        rbt.insert(5, "na");
        rbt.insert(10, "sucks");
        rbt.delete(10);
        System.out.println(rbt);
        System.out.println(validate.validate(rbt.toString()));
        rbt.makeEmpty();

        System.out.println("test two, delete black leaf,failed 5");
        rbt.insert(5, "hey");
        rbt.insert(10, "yo");
        rbt.insert(12, "ya");
        rbt.insert(25, "tr");
        rbt.delete(5);
        System.out.println("after delete\n"+rbt);
        System.out.println(validate.validate(rbt.toString()));
        rbt.makeEmpty();

        System.out.println("test three, delete black with two red children 10");
        rbt.insert(5, "h");
        rbt.insert(10, "t2");
        rbt.insert(12, "ttweklve");
        rbt.delete(10);
        System.out.println(rbt);
        System.out.println(validate.validate(rbt.toString()));
        rbt.makeEmpty();

        System.out.println("test four, delete a black node with single red child 1");
        rbt.insert(1, "a");
        rbt.insert(14, "n");
        rbt.insert(4, "d");
        rbt.insert(18, "r");
        rbt.insert(5, "e");
        rbt.insert(23, "w");
        rbt.insert(19, "s");
        rbt.insert(3, "c");
        rbt.insert(8, "h");
        rbt.insert(6, "f");
        rbt.delete(1);
        System.out.println(rbt);
        //System.out.println(validate.validate(rbt.toString()));
        rbt.makeEmpty();
//failed
        System.out.println("Test five, delete a black node with two red children (large tree) 6");
        rbt.insert(1, "a");
        rbt.insert(14, "n");
        rbt.insert(4, "d");
        rbt.insert(18, "r");
        rbt.insert(5, "e");
        rbt.insert(23, "w");
        rbt.insert(19, "s");
        rbt.insert(3, "c");
        rbt.insert(8, "h");
        rbt.insert(6, "f");
        rbt.remove(6);
        System.out.println(rbt);
        System.out.println(validate.validate(rbt.toString()));
        rbt.makeEmpty();

        System.out.println("//test six, delete a red node with 2 black children (large tree) 15");
        rbt.insert(10, "a");
        rbt.insert(15, "n");
        rbt.insert(8, "d");
        rbt.insert(3, "r");
        rbt.insert(18, "e");
        rbt.insert(12, "w");
        rbt.insert(14, "s");
        rbt.delete(14);//just a red leaf deletion
        rbt.delete(15);
        System.out.println(rbt);
        System.out.println(validate.validate(rbt.toString()));
        rbt.makeEmpty();

        System.out.println("test seven, ensure the black traverses are done correctly, black children double rotation 14");
        rbt.insert(8, "a");
        rbt.insert(2, "n");
        rbt.insert(12, "d");
        rbt.insert(1, "r");
        rbt.insert(5, "e");
        rbt.insert(9, "w");
        rbt.insert(14, "s");
        rbt.insert(18, "c");
        rbt.insert(4, "h");
        rbt.insert(7, "f");
        rbt.insert(6, "nein");
        rbt.delete(14);
        System.out.println(rbt);
        System.out.println(validate.validate(rbt.toString()));
        rbt.makeEmpty();

        System.out.println("test eight, remove root, only one key 5");
        rbt.insert(5, "nl");
        rbt.delete(5);
        System.out.println(rbt);
        System.out.println(validate.validate(rbt.toString()));
        rbt.makeEmpty();

        System.out.println("test nine, give choice where it has 2 red children and picks wrong 55");
        rbt.insert(24, "a");
        rbt.insert(10, "n");
        rbt.insert(41, "d");
        rbt.insert(7, "r");
        rbt.insert(23, "e");
        rbt.insert(44, "w");
        rbt.insert(34, "s");
        rbt.insert(11, "c");
        rbt.insert(29, "h");
        rbt.insert(35, "f");
        rbt.insert(55, "nein");
        rbt.insert(40, "tw");
        rbt.insert(28, "cddd");
        rbt.insert(33, "hghs");
        rbt.insert(43, "now");
        rbt.delete(55);
        System.out.println(rbt);
        System.out.println(validate.validate(rbt.toString()));
        rbt.makeEmpty();

        System.out.println("test ten, check the 2 black children sibling, 2 black children current 5,6,7");
        rbt.insert(8, "a");
        rbt.insert(2, "n");
        rbt.insert(12, "d");
        rbt.insert(1, "r");
        rbt.insert(5, "e");
        rbt.insert(9, "w");
        rbt.insert(14, "s");
        rbt.insert(18, "c");
        rbt.insert(4, "h");
        rbt.insert(7, "f");
        rbt.insert(6, "nein");
        rbt.delete(5);
        rbt.delete(6);
        rbt.delete(7);
        System.out.println(rbt);
        System.out.println(validate.validate(rbt.toString()));
        rbt.makeEmpty();

        System.out.println("test eleven, check a single rotation works on black children 14");
        rbt.insert(8, "a");
        rbt.insert(2, "n");
        rbt.insert(12, "d");
        rbt.insert(1, "r");
        rbt.insert(5, "e");
        rbt.insert(9, "w");
        rbt.insert(14, "s");
        rbt.insert(18, "c");
        rbt.insert(0, "h");
        rbt.insert(-3, "f");
        rbt.insert(-5, "nsnsnsnsnns");
        rbt.delete(14);
        System.out.println(rbt);
        System.out.println(validate.validate(rbt.toString()));
        rbt.makeEmpty();

        System.out.println("test twelve, delete root with lots of nodes 8");
        rbt.insert(8, "a");
        rbt.insert(2, "n");
        rbt.insert(12, "d");
        rbt.insert(1, "r");
        rbt.insert(5, "e");
        rbt.insert(9, "w");
        rbt.insert(14, "s");
        rbt.insert(18, "c");
        rbt.insert(4, "h");
        rbt.insert(7, "f");
        rbt.insert(6, "nein");
        rbt.delete(8);
        System.out.println(rbt);
        System.out.println(validate.validate(rbt.toString()));
        rbt.makeEmpty();


        System.out.println("test thirteen, double replacement? not sure if this is double 5");
        rbt.insert(10, "a");
        rbt.insert(5, "n");
        rbt.insert(25, "d");
        rbt.insert(3, "r");
        rbt.insert(7, "e");
        rbt.insert(18, "w");
        rbt.insert(28, "s");
        rbt.insert(18, "c");
        rbt.insert(1, "h");
        rbt.delete(5);
        System.out.println(rbt);
        System.out.println(validate.validate(rbt.toString()));
        rbt.makeEmpty();

        //a remove test with a replacement node
        rbt.insert(10, "a");
        rbt.insert(5, "n");
        rbt.insert(25, "d");
        rbt.insert(3, "r");
        rbt.insert(7, "e");
        rbt.insert(18, "w");
        rbt.insert(28, "s");
        rbt.insert(18, "c");
        rbt.insert(1, "h");
        System.out.println(rbt.remove(5));
        System.out.println(rbt);
        System.out.println(validate.validate(rbt.toString()));
        rbt.makeEmpty();

        //remove test with non existent key
        rbt.insert(10, "a");
        rbt.insert(5, "n");
        rbt.insert(25, "d");
        rbt.insert(3, "r");
        rbt.insert(7, "e");
        rbt.insert(18, "w");
        rbt.insert(28, "s");
        rbt.insert(18, "c");
        rbt.insert(1, "h");
        System.out.println(rbt.remove(9));
        System.out.println(rbt);
        System.out.println(validate.validate(rbt.toString()));
        rbt.makeEmpty();

    }
}
