/**
 * Created by n_gonzal on 10/9/2014.
 */
public class RedBlackNode {
    //given
    private Element element;
    private RedBlackNode left;
    private RedBlackNode right;
    private int color;
    //added
    public RedBlackNode parent;
    public RedBlackNode grand;
    public RedBlackNode great;

    public RedBlackNode(Element e, RedBlackNode l, RedBlackNode r) {
        element = e;
        left = l;
        right = r;
        color = 0;
    }

    //added default constructor;
    public RedBlackNode() {
        element = null;
        left = null;
        right = null;
        color = 0;
    }

    //added modified constructor
    public RedBlackNode(Element e) {
        element = e;
        left = null;
        right = null;
        color = 0;
    }

    public Element getElement() {
        return element;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int a) {
        color = a;
    }

    public RedBlackNode getLeftChild() {
        return left;
    }

    public RedBlackNode getRightChild() {
        return right;
    }


    //All methods following this comment have been added additionally to original instructions.
    //added set element for node
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
    public RedBlackNode getParent(){
        return parent;
    }
    public void setParent(RedBlackNode a){
        parent=a;
    }
    public RedBlackNode getGrandParent(){
        return grand;
    }
    public void setGrand(RedBlackNode a){
         grand=a;
    }
    public RedBlackNode getGreat(){
        return great;
    }
    public void setGreatParent(RedBlackNode a){
        great=a;
    }


}
