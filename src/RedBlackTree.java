
/**
 * Created by Nicolas Gonzalez on 10/9/2014.
 */
public class RedBlackTree implements TreeInterface {

    RedBlackNode curr;
    RedBlackNode header;
    public static RedBlackNode nullNode;
    RedBlackNode parent;
    RedBlackNode grand;
    RedBlackNode great;
    private RedBlackNode removed ;
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
        curr = getRightOf(header);
        //key has matched a key on the tree
        while(true){
            if(curr==nullNode)
                return null;
            if ( getKeyOf(curr)== key){
                return curr.getElement().getValue();
                }
            //looks at left subtree
            if (getKeyOf(curr) < key) {
                if(getRightOf(curr)!=nullNode)
                    curr = getRightOf(curr);
            }
            //look at right subtree
            if (getKeyOf(curr)> key) {
                if(getLeftOf(curr)!=nullNode)
                    curr = getLeftOf(curr);
            }


        }
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

        // Insertion overrides value if key was present
        if( curr != nullNode ){
           curr.setElement(item.getKey(),item.getValue());
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
        RedBlackNode found=null;
        if (header.getRightChild() != nullNode) {

            final int LEFT = 0;
            final int RIGHT = 1;

            int dir = RIGHT;

            curr = header;
            grand = parent = nullNode;
            curr.setRightChild(header.getRightChild());

            // Search and push a red down
            while (curr.get_child(dir) != nullNode) {
                int last = dir;

                // Update nodes
                grand = parent;
                parent = curr;
                curr = curr.get_child(dir);
                dir = getKeyOf(curr) < key ? RIGHT : LEFT;

                //save found node to delete
                if (getKeyOf(curr) == key)
                    found = curr;



      /* Push the red node down */
                if (!isred(curr) && !isred(curr.get_child(dir))) {
                    if (isred(curr.get_child(~dir))){
                        parent= parent.set_child(last, singleRotation(curr, dir));
                    }
                    else if (!isred(curr.get_child(~dir))) {
                        RedBlackNode tmp = parent.get_child(~last);

                        if (tmp != nullNode) {
                            if (!isred(tmp.get_child(~last)) && !isred(tmp.get_child(last))) {
              /* Color flip */
                                parent.setColor(1);
                                tmp.setColor(0);
                                curr.setColor(0);
                            }
                            else {
                                int dir2 = (grand.get_child(RIGHT) == parent) ? RIGHT : LEFT;

                                if (isred(tmp.get_child(last)))
                                    grand.set_child(dir2, doubleRotation(parent, last));
                                else if (isred(tmp.get_child(~last)))
                                    grand.set_child(dir2, singleRotation(parent, last));

              /* Ensure correct coloring */
                               curr.setColor(0);
                                grand.get_child(dir2).setColor(0);
                                grand.get_child(dir2).getLeftChild().setColor(1);
                                grand.get_child(dir2).getRightChild().setColor(1);

                            }
                        }
                    }
                }
            }



    /* Replace and remove if found */
            if (found != null) {
                removed=new RedBlackNode(found.getElement());
                found.setElement(getKeyOf(curr),curr.getElement().getValue());
                parent.set_child(
                        parent.get_child(RIGHT) == curr ? RIGHT : LEFT,
                        curr.get_child(curr.get_child(LEFT) == nullNode ? LEFT : LEFT));
            }

    /* Update root and make it black */
            if (header.get_child(RIGHT) != nullNode)
                header.get_child(RIGHT).setColor(1);

        }
    }


    public String remove(int key) {
        removed=null;
        System.out.println(key+" in remove");
        delete(key);
        return removed==null ? null:removed.getElement().getValue();
    }
    @java.lang.Override
    public String toString() {
        return treeToString(header.getRightChild(),"root")+"\n";

    }
    private String treeToString(RedBlackNode t,String p){
        StringBuilder prefix=new StringBuilder();
        StringBuilder tree=new StringBuilder();
        prefix.append(p);
        if(header.getRightChild()==nullNode){
            return "This tree is empty";
        }
        else{
            if(t!=nullNode && t!=null)
            tree.append(prefix+":"+getColorOf(t)+"("+getKeyOf(t)+","+t.getElement().getValue()+")\n");
            if(getLeftOf(t)!=nullNode && getLeftOf(t)!=null){
                tree.append(treeToString(getLeftOf(t), prefix+"-left"));
            }
            if(getRightOf(t)!=nullNode && getLeftOf(t)!=null){
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
        if(isred(parent)){
            setColorOf(grand,0);
            if((compare(item,grand.getElement())<0)!=
               (compare(item,parent.getElement())<0))
                parent=(rotate(item,grand));
            curr=rotate(item,great);
            setColorOf(curr,1);
        }
        setColorOf(header.getRightChild(),1);
    }

    private RedBlackNode singleRotation(RedBlackNode node,int dir){

        RedBlackNode tmp=node.get_child(~dir);

        node.set_child(~dir,tmp.get_child(dir));
        tmp.set_child(dir,node);

        node.setColor(0);
        tmp.setColor(1);

        return tmp;
    }
    private RedBlackNode doubleRotation(RedBlackNode node,int dir){
        node.set_child(~dir,singleRotation(node.get_child(~dir),~dir));
        return singleRotation(node,dir);
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

    public RedBlackNode getLeftOf(RedBlackNode a){
        return a==nullNode ? nullNode: a.getLeftChild();

    }
    public RedBlackNode getRightOf(RedBlackNode a){
        return a==nullNode ? nullNode: a.getRightChild();

    }

    public String getColorOf(RedBlackNode a){
        return a==nullNode||a==null ? "":a.getColorString();
    }
    public boolean isred(RedBlackNode a){
        return (getColorOf(a).equals("RED")) ? true:false;
    }
    public void setColorOf(RedBlackNode a,int col){
        if(a!=nullNode){
            a.setColor(col);
        }
    }
    public int getKeyOf(RedBlackNode a){
        return a==nullNode||a==null ? null:a.getElement().getKey();
    }
    public void makeEmpty(){
        header.setRightChild(nullNode);
    }


}


