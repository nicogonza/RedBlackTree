
/**
 * Created by Nicolas Gonzalez on 10/9/2014.
 */
public class RedBlackTree implements TreeInterface {

    RedBlackNode root;
    //works as curr node
    RedBlackNode curr;
    RedBlackNode header;
    static RedBlackNode nullNode;
    RedBlackNode parent;
    RedBlackNode grand;
    RedBlackNode great;
    //intializers
    public RedBlackTree(){
        header=new RedBlackNode(new Element(-9999999,"header"));
        header.setColor(1);
        header.setLeftChild(nullNode);
        header.setRightChild(nullNode);
    }
    static {
        nullNode=new RedBlackNode(null);
        nullNode.setColor(1);
        nullNode.setLeftChild(null);
        nullNode.setRightChild(null);
    }

    //to do
    public String find(int key) {
        curr = root;
        //key has matched a key on the tree
        if (curr.getElement().getKey() == key)
            return curr.getElement().getValue();
        //looks at left subtree
        if (curr.getElement().getKey() < key) {
            curr = curr.getLeftChild();
            return find(key);
        }
        //look at right subtree
        if (curr.getElement().getKey() > key) {
            curr = curr.getRightChild();
            return find(key);
        }

        return null;
    }


    public void insert(int key, String value) {
        curr=parent = grand = header;
        Element item=new Element(key,value);
        nullNode.setElement(key,value);
        while(compare(item,curr.getElement()) != 0 )
        {
            great = grand; grand = parent; parent = curr;
            curr = compare(item, curr.getElement()) < 0 ?
                    getLeftOf(curr) : getRightOf(curr);

            // Check if two red children; fix if so
            if( getColorOf(getLeftOf(curr)).equals("RED") && getColorOf(getRightOf(curr)).equals("RED")){
                handleReorient( item );
            }

        }

        // Insertion fails if already present
        if( curr != nullNode ){
           //curr.setElement(item.getKey(),item.getValue());
            return;
        }

        curr = new RedBlackNode( item, nullNode, nullNode );

        // Attach to parent
        if( compare(item, parent.getElement()) < 0 )
            parent.setLeftChild(curr);
        else
            parent.setRightChild(curr);
        handleReorient(item);


    }



    public void delete(int key) {
        throw new UnsupportedOperationException();
    }


    public String remove(int key) {
        throw new UnsupportedOperationException();
    }
    @java.lang.Override
    public String toString() {
        return treeToString(header.getRightChild(),"root");

    }
    private String treeToString(RedBlackNode t,String p){
        StringBuilder prefix=new StringBuilder();
        StringBuilder tree=new StringBuilder();
        prefix.append(p);
        if(t!=nullNode){
            tree.append(prefix+":"+getColorOf(t)+"("+getKeyOf(t)+","+t.getElement().getValue()+")\n");
            if(getLeftOf(t)!=nullNode){
                tree.append(treeToString(getLeftOf(t), prefix+"-left"));
            }
            if(getRightOf(t)!=nullNode){
                tree.append(treeToString(getRightOf(t),prefix+ "-right"));
            }


        }
        return tree.toString();

    }

    //All methods after this comment have been added
    //added getParent, getSibling,getGrandParent to easily find a nodes 'family'

private int compare(Element item,Element current){
    if(item==getRightOf(header).getElement())
        return 1;
    if(item.getKey()==current.getKey())
        return 0;
    //if toinsert should go in the left subtree else right subtree.
    if(item.getKey()<current.getKey())
        return -1;
    else
        return 1;
}


    //receives current node(calling to reorient)
    public void handleReorient(Element item) {
        //double red children, recolor
            setColorOf(curr,0);
        if(getLeftOf(curr)!=nullNode)
            setColorOf(getLeftOf(curr),1);
        if(getRightOf(curr)!=nullNode)
            setColorOf(getRightOf(curr), 1);

        //double red violation from parent and child must rotate
        if(getColorOf(parent).equals("RED")){
            setColorOf(grand,0);
            if((compare(item,grand.getElement())<0)!=
               (compare(item,parent.getElement())<0))
                parent=(rotate(item,grand));
            curr=rotate(item,great);
            setColorOf(curr,1);
        }
        setColorOf(header.getRightChild(),1);
    }

    private RedBlackNode rotate(Element item,RedBlackNode parent){
        if(compare(item,parent.getElement())<0){
            if(compare(item,getLeftOf(parent).getElement())<0){
                return parent.setLeftChild(rotateWithLeftChild(getLeftOf(parent)));//LL
            }

            else
                return parent.setLeftChild(rotateWithRightChild(getLeftOf(parent)));//LR

        }
        else
        {
            if(compare(item,getRightOf(parent).getElement())<0)
                return parent.setRightChild(rotateWithLeftChild(getRightOf(parent)));//RL
            else
                return parent.setRightChild(rotateWithRightChild(getRightOf(parent)));//RR

        }


    }
    public RedBlackNode rotateWithLeftChild(RedBlackNode k2){
        RedBlackNode k1=getLeftOf(k2);
        k2.setLeftChild(getRightOf(k1));
        k1.setRightChild(k2);
        return k1;
    }


    public RedBlackNode rotateWithRightChild(RedBlackNode k1) {
        RedBlackNode k2=getRightOf(k1);
        k1.setRightChild(getLeftOf(k2));
        k2.setLeftChild(k1);
        return k2;

    }

    //helper methods to check null pointers

    private RedBlackNode getLeftOf(RedBlackNode a){
        return a==nullNode ? nullNode: a.getLeftChild();

    }
    private RedBlackNode getRightOf(RedBlackNode a){
        return a==nullNode ? nullNode: a.getRightChild();

    }
    private String getColorOf(RedBlackNode a){
        return a==nullNode ? "":a.getColorString();
    }
    private void setColorOf(RedBlackNode a,int col){
        if(a!=nullNode){
            a.setColor(col);
        }
    }
    private int getKeyOf(RedBlackNode a){
        return a==nullNode ? null:a.getElement().getKey();
    }


}


