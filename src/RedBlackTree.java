
/**
 * Created by Nicolas Gonzalez on 10/9/2014.
 */
public class RedBlackTree implements TreeInterface {

    RedBlackNode root;
    //works as curr node
    RedBlackNode t;
    RedBlackNode header;
    RedBlackNode nullNode;
    public RedBlackTree(){
        root=null;
        t=root;
    }

    //to do
    public String find(int key) {
        t = root;
        //key has matched a key on the tree
        if (t.getElement().getKey() == key)
            return t.getElement().getValue();
        //looks at left subtree
        if (t.getElement().getKey() < key) {
            t = t.getLeftChild();
            return find(key);
        }
        //look at right subtree
        if (t.getElement().getKey() > key) {
            t = t.getRightChild();
            return find(key);
        }

        return null;
    }


    public void insert(int key, String value) {

        Element item=new Element(key,value);
        if(root==null){
            root=new RedBlackNode(item);
            return;
        }
        t=root;
        t.setParent(root);
        t.setGrand(root);
        //finds where to insert node
        while(true)
        {
            t.setGreatParent(getGrandOf(t));
            t.setGrand(getParentOf(t));
            t.setParent(t);
            if(compare(key,t)==0){
                t.setElement(key,value);
                return;
            }
            else if(compare(key,t)<0) {
                if (getLeftOf(t)== null) {
                    t.setLeftChild(new RedBlackNode(item));
                    handleReorient(getLeftOf(t));
                    break;
                }
                t = getLeftOf(t);
            }else{
                if(getRightOf(t)==null){
                    t.setRightChild(new RedBlackNode(item));
                    handleReorient(getRightOf(t));
                    break;
                }
                t=getRightOf(t);
            }
        }

    }



    public void delete(int key) {
        throw new UnsupportedOperationException();
    }


    public String remove(int key) {
        throw new UnsupportedOperationException();
    }
    @java.lang.Override
    public String toString() {
        return treeToString(root,"root");

    }
    private String treeToString(RedBlackNode t,String p){
        StringBuilder prefix=new StringBuilder();
        StringBuilder tree=new StringBuilder();
        prefix.append(p);
        if(t!=null){
            tree.append(prefix+":"+t.getColorString()+"("+getKeyOf(t)+","+t.getElement().getValue()+")\n");
            if(getLeftOf(t)!=null){
                tree.append(treeToString(getLeftOf(t), prefix+"-left"));
            }
            if(getRightOf(t)!=null){
                tree.append(treeToString(getRightOf(t),prefix+ "-right"));
            }


        }
        return tree.toString();

    }

    //All methods after this comment have been added
    //added getParent, getSibling,getGrandParent to easily find a nodes 'family'

private int compare(int toInsert,RedBlackNode r) {
    if(r==null)
        return 1;
    if(toInsert==r.getElement().getKey())
        return 0;
    //if toinsert should go in the left subtree else right subtree.
    if(toInsert<r.getElement().getKey())
        return -1;
    else
        return 1;
}
    /*public RedBlackNode getParent(RedBlackNode t) {
        RedBlackNode tmp = root;
        if(t==root)
            return null;
               while(tmp!=null){
                   if(tmp.getLeftChild()==t||tmp.getRightChild()==t)
                       return tmp;
                   else
                   {
                       if(tmp.getElement().getKey()>t.getElement().getKey())
                           tmp=tmp.getLeftChild();
                       else
                           tmp=tmp.getRightChild();
                   }
               }
        return null;
    }

    public RedBlackNode getSibling(RedBlackNode t) {
        if (getParent(t) != null) {
            if(getParent(t).getLeftChild()!=null && getParent(t).getRightChild()!=null){
                if(getParent(t).getLeftChild().getElement().getKey()==t.getElement().getKey())
                    return getParent(t).getRightChild();
                if(getParent(t).getRightChild().getElement().getKey()==t.getElement().getKey())
                    return getParent(t).getLeftChild();
            }


        }
        return null;
    }

    public RedBlackNode getGrandParent(RedBlackNode t) {
        return getParent(getParent(t));
    }*/


    //receives current node(calling to reorient)
    public void handleReorient(RedBlackNode t) {

        //double red problems
            t.setColor(0);
            setColorOf(getLeftOf(t),1);
            setColorOf(getRightOf(t), 1);
        //double red violation from parent and child
        if(getColorOf(getParentOf(t))=="RED"){
            setColorOf(getGrandOf(t),0);
            if((compare(t.getElement().getKey(),getGrandOf(t))<0)!=(compare(t.getElement().getKey(),t.getParent())<0))
                t.setParent(rotate(t, getGrandOf(t)));
            t=rotate(t,getGreatOf(t));
            t.setColor(1);
        }
        /*
        if (t != null && t != root && getParent(t)!=null && getParent(t).isRed()) {
            //parent and sibling of parent are red simply recolor
            if (getSibling(getParent(t))!=null && getSibling(getParent(t)).isRed()) {
                getParent(t).setColor(1);
                    if(getSibling(t)!=null)
                        getSibling(t).setColor(1);
                    if(getGrandParent(t)!=null)
                        getGrandParent(t).setColor(0);
                afterInsertion(getGrandParent(t));
            }
            //parent of t is the left child of the grandparent of t
            else if (getParent(t) == getGrandParent(t).getLeftChild()) {
                if (t == getParent(t).getRightChild()) {
                    t=getParent(t);
                    rotateLeft(t);
                }
                getParent(t).setColor(1);
                if(getGrandParent(t)!=null){
                    getGrandParent(t).setColor(0);
                rotateRight(getGrandParent(t));}
            }
            //parent of t is the right child of the grandparent of t
            else if (getParent(t) == getGrandParent(t).getRightChild()) {
                if (t == getGrandParent(t).getLeftChild()) {
                    t=getParent(t);
                   rotateRight(getParent(t));
                }
                getParent(t).setColor(1);
                if(getGrandParent(t)!=null){
                    System.out.println("calling.....");
                getGrandParent(t).setColor(0);
                rotateLeft(getGrandParent(t));}
            }
        }*/

        // root is always black
        root.setColor(1);
    }
    private RedBlackNode rotate(RedBlackNode torotate,RedBlackNode parent){

        if(compare(getKeyOf(torotate),parent)<0){
            if(compare(getKeyOf(torotate),getLeftOf(parent))<0){
                return parent.setLeftChild(rotateWithLeftChild(getLeftOf(parent)));
            }

            else
                return parent.setLeftChild(rotateWithRightChild(getRightOf(parent)));

        }
        else
        {
            if(compare(torotate.getElement().getKey(),getRightOf(parent))<0)
                return parent.setRightChild(rotateWithLeftChild(getLeftOf(parent)));
            else
                return parent.setLeftChild(rotateWithRightChild(getRightOf(parent)));

        }


    }
    public RedBlackNode rotateWithLeftChild(RedBlackNode q){
        System.out.println("rotating left  "+q.getElement().getKey());
        RedBlackNode a=getLeftOf(q);
        q.setLeftChild(getRightOf(a));
        a.setRightChild(q);
        return a;
        /*if (q.getRightChild() == null) {
            return;
        }
        RedBlackNode oldRight = q.getRightChild();
        System.out.println("old right is "+oldRight.getElement().getKey());
        q.setRightChild(oldRight.getLeftChild());
        //if node has no parent it is a root
        if (getParent(q) == null) {
            root=oldRight;

        } else if (getParent(q).getLeftChild() == q){
            getParent(q).setLeftChild(oldRight);
        } else {
            getParent(q).setRightChild(oldRight);
        }
        oldRight.setLeftChild(q);*/
    }


    public RedBlackNode rotateWithRightChild(RedBlackNode a) {
        System.out.println("rotating right");

        RedBlackNode q=getLeftOf(a);
        a.setLeftChild(getRightOf(q));
        q.setRightChild(a);
        return q;
        /*
        if (q.getLeftChild() == null) {
            return;
        }
        RedBlackNode oldLeft = q.getLeftChild();
        q.setLeftChild(oldLeft.getRightChild());
        if (getParent(q) == null) {
            root = oldLeft;
        } else if (getParent(q).getLeftChild() == q) {
            getParent(q).setLeftChild(oldLeft);
        } else {
            getParent(q).setRightChild(oldLeft);
        }
        oldLeft.setRightChild(q);*/
    }

    //helper methods to check null pointers
    private RedBlackNode getParentOf(RedBlackNode a){
        return a==null ? null: a.getParent();
    }
    private RedBlackNode getGrandOf(RedBlackNode a){
        return a==null ? null: a.getGrandParent();
    }
    private RedBlackNode getGreatOf(RedBlackNode a){
        return a==null ? null: a.getGreat();
    }
    private RedBlackNode getLeftOf(RedBlackNode a){
        return a==null ? null: a.getLeftChild();

    }
    private RedBlackNode getRightOf(RedBlackNode a){
        return a==null ? null: a.getRightChild();

    }
    private RedBlackNode parentOf(RedBlackNode a){
        return a==null ? null:a.getParent();
    }
    private String getColorOf(RedBlackNode a){
        return a==null ? "":a.getColorString();
    }
    private void setColorOf(RedBlackNode a,int col){
        if(a!=null){
            a.setColor(col);
        }
    }
    private int getKeyOf(RedBlackNode a){
        return a==null ? null:a.getElement().getKey();
    }


}


