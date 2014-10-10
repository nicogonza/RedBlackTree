/**
 * Created by n_gonzal on 10/9/2014.
 */
public class RedBlackNode {
    private Element element;
    private RedBlackNode left;
    private RedBlackNode right;
    private int color;

    public RedBlackNode(Element e, RedBlackNode L, RedBlackNode r){
        element =e;
        left=l;
        right=r;
        color=0;
    }

    public Element getElement(){
        return element;
    }
    public int getColor(){
        return color;
    }
    public RedBlackNode getLeftChild(){
        //to do
    }
    public RedBlackNode getRigthChild(){
        //to do
    }
}
