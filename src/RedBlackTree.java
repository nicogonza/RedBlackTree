

/**
 * Created by Nicolas Gonzalez on 10/9/2014.
 */
public class RedBlackTree implements TreeInterface {

    RedBlackNode root;
    RedBlackNode t;


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

        if (root == null) {
            root = new RedBlackNode(new Element(key, value));
            root.setColor(1);
        }
        else{t =root;
        while (t!=null){
            boolean Comparison = compare(key, t.getElement().getKey());
            if (Comparison == true) {
                t.SetElement(key, value);
            } else if (Comparison==false) {
                if (t.getLeftChild() == null) {
                    t.setLeftChild(new RedBlackNode(new Element(key, value)));
                     afterInsertion(t.getLeftChild());
                    break;
                }
                t = t.getLeftChild();
            } else {
                if (t.getRightChild() == null) {
                    t.setRightChild(new RedBlackNode(new Element(key, value)));
                    afterInsertion(t.getRightChild());
                    break;
                }
                t = t.getRightChild();
            }
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
        RedBlackNode tmp = root;

        String tree="";
        if(tmp==null)
            return "this tree is empty";
       tree="\nroot:" + tmp.getColorString() + "(" + tmp.getElement().getKey() + "," + tmp.getElement().getValue() + ")";
        while (tmp != null) {
            if(tmp.getLeftChild()!=null){
                tree+="\nroot-left:" + tmp.getLeftChild().getColorString() + "(" + tmp.getLeftChild().getElement().getKey() + "," + tmp.getLeftChild().getElement().getValue() + ")";
            }

            if(tmp.getRightChild()!=null){
                System.out.println("has right node true");
                tree+="\nroot-right:" + tmp.getRightChild().getColorString() + "(" + tmp.getRightChild().getElement().getKey() + "," + tmp.getRightChild().getElement().getValue() + ")";
                tmp=getParent(tmp);
            }
            tmp=tmp.getLeftChild();
        }
        return tree;
    }

    //All methods after this comment have been added
    //added getParent, getSibling,getGrandParent to easily find a nodes 'family'
    //compares 2 keys to find if they are the same node.
    private boolean compare(int key, int key1) {
        if (key == key1)
            return true;
        else return false;
    }
    public RedBlackNode getParent(RedBlackNode t) {
        RedBlackNode tmp = root;
        if (tmp == null)
            return null;
        if (tmp.getLeftChild() == t || tmp.getRightChild() == t)
            return tmp;
        if (tmp.getElement().getKey() > t.getElement().getKey())
            return getParent(t.getLeftChild());
        if (tmp.getElement().getKey() < t.getElement().getKey())
            return getParent(t.getLeftChild());
        return null;
    }

    public RedBlackNode getSibling(RedBlackNode t) {
        if (getParent(t) != null) {
            if (t.getElement().getKey() < getParent(t).getElement().getKey())
                return getParent(t).getRightChild();
            else
                return getParent(t).getLeftChild();
        }
        return null;
    }

    public RedBlackNode getGrandParent(RedBlackNode t) {
        return getParent(getParent(t));
    }

    public void rotateLeft(RedBlackNode t) {
        throw new UnsupportedOperationException("coming soon");


    }

    public void rotateRight(RedBlackNode t) {
        throw new UnsupportedOperationException("coming soon");



    }

    public void afterInsertion(RedBlackNode t) {
        if (t != null && t != root && getParent(t).isRed()) {

            if (getSibling(getParent(t)).isRed()) {
                getParent(t).setColor(1);
                getSibling(t).setColor(1);
                getGrandParent(t).setColor(0);
                afterInsertion(getGrandParent(t));
            }

            else if (getParent(t) == getGrandParent(t).getLeftChild()) {
                if (t == getParent(t).getRightChild()) {
                    //rotateLeft(t = getParent(t));
                }
                getParent(t).setColor(1);
                getGrandParent(t).setColor(0);
               // rotateRight(getGrandParent(t));
            }
            else if (getParent(t) == getGrandParent(t).getRightChild()) {
                if (t == getGrandParent(t).getLeftChild()) {
                    //rotateRight(t = getParent(t));
                }
                getParent(t).setColor(1);
                getGrandParent(t).setColor(0);
                //rotateLeft(getGrandParent(t));
            }
        }

        // root is always black
        root.setColor(1);
    }

}


