/**
 * Created by n_gonzal on 10/9/2014.
 */
public class RedBlackTree implements TreeInterface {

    RedBlackNode root;
    RedBlackNode t=new RedBlackNode();
    @java.lang.Override
    public String find(int key) {
        //key has matched a key on the tree
        if(t.getElement().getKey()==key)
            return t.getElement().getValue();
        //looks at left subtree
        if(t.getElement().getKey()< key)
        {
            return find(t.getLeftChild().getElement().getKey());
        }
        //look at right subtree
        if(t.getElement().getKey()> key)
        {
            return find(t.getRightChild().getElement().getKey());
        }

        return null;
    }


    public void insert(int key, String value) {

        if(root==null) {
            root = new RedBlackNode(new Element(key,value));
        }
        RedBlackNode t=root;
        while (true) {
            int Comparison = compare(key,t.getElement().getKey());
            if (Comparison == 0) {
                t.SetElement(key, value);
            } else if (Comparison < 0) {
                if (t.getLeftChild() == null) {
                    t.setLeftChild(new RedBlackNode(new Element(key,value)));
                    afterInsertion(t.getLeftChild());
                    break;
                }
                t = t.getLeftChild();
            } else {
                if (t.getRightChild() == null) {
                    t.setRightChild(new RedBlackNode(new Element(key,value)));
                    afterInsertion((Node) n.getRight());
                    break;
                }
                t = t.getRightChild();
            }
        }
    }


    public void delete(int key) {
        throw new UnsupportedOperationException( );
    }


    public String remove(int key) {
        throw new UnsupportedOperationException( );
    }

    @java.lang.Override
    public String toString(){
        throw new UnsupportedOperationException( );

    }

    //All methods after this comment have been added
    public void afterInsertion(RedBlackNode t){
        if (n != null && n != root && isRed(parentOf(n))) {

            // Step 2a (simplest): Recolor, and move up to see if more work
            // needed
            if (isRed(siblingOf(parentOf(n)))) {
                setColor(parentOf(n), Color.black);
                setColor(siblingOf(parentOf(n)), Color.black);
                setColor(grandparentOf(n), Color.red);
                adjustAfterInsertion(grandparentOf(n));
            }

            // Step 2b: Restructure for a parent who is the left child of the
            // grandparent. This will require a single right rotation if n is
            // also
            // a left child, or a left-right rotation otherwise.
            else if (parentOf(n) == leftOf(grandparentOf(n))) {
                if (n == rightOf(parentOf(n))) {
                    rotateLeft(n = parentOf(n));
                }
                setColor(parentOf(n), Color.black);
                setColor(grandparentOf(n), Color.red);
                rotateRight(grandparentOf(n));
            }

            // Step 2c: Restructure for a parent who is the right child of the
            // grandparent. This will require a single left rotation if n is
            // also
            // a right child, or a right-left rotation otherwise.
            else if (parentOf(n) == rightOf(grandparentOf(n))) {
                if (n == leftOf(parentOf(n))) {
                    rotateRight(n = parentOf(n));
                }
                setColor(parentOf(n), Color.black);
                setColor(grandparentOf(n), Color.red);
                rotateLeft(grandparentOf(n));
            }
        }

        // Step 3: Color the root black
        setColor((Node) root, Color.black);
    }

    }

}
