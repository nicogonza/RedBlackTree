/**
 * Created by n_gonzal on 10/9/2014.
 */
public class RedBlackNode {
    //given
    private Element element;
    private RedBlackNode left;
    private RedBlackNode right;
    private int color=0;



    public RedBlackNode(Element e, RedBlackNode l, RedBlackNode r) {
        element = e;
        left = l;
        right = r;
    }

    //added default constructor;
    public RedBlackNode() {
        element = null;
        left = null;
        right = null;
    }

    //added modified constructor
    public RedBlackNode(Element e) {
        element = e;
        left = null;
        right = null;
    }

    public Element getElement() {
        return element;
    }

    public int getColor() {
        return color;
    }

    public RedBlackNode getLeftChild() {
        return left;
    }

    public RedBlackNode getRightChild() {
        return right;
    }


    //All methods following this comment have been added additionally to original instructions.
    //added set element for node
    public void setColor(int a) {
        color = a;
    }
    public RedBlackNode setRightChild(RedBlackNode n) {
        right = n;
        return right;
    }
    public RedBlackNode setLeftChild(RedBlackNode n) {
        left = n;return left;
    }

    public void setElement(int k, String v) {
        element = new Element(k, v);
    }

    //added isRed to easily check if is red  and imprpove readibility of tree code.

    public String getColorString() {
        if(getColor()==0)
            return "RED";
        return "BLACK";
    }

    public RedBlackNode get_child(int d){
        if(d==0){
            if(left!=null)
                return left;
        }
        else{
            if(right!=null)
                return right;
        }
        return null;
    }
    public RedBlackNode set_child(int d,RedBlackNode c){
        return (d==0)? setLeftChild(c):setRightChild(c);
    }


}
