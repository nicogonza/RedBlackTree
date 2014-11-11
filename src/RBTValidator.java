import java.util.Scanner;

/**
 * Created by Nico on 11/8/14.
 */
public class RBTValidator {
    String[] lines= new String[30];
    int length;
    public boolean validate (String a){
        System.out.println("string comming in "+a);
        Scanner scanner = new Scanner(a);
        int count=0;
        //files up array with each line from string
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            lines[count]=line;
            count++;
            // process the line
        }
        scanner.close();
        length=count;
        Element item=new Element(getKey(0),getValue(0));
        RedBlackTree t=makeTree(new RedBlackNode(item));
        return chkproperties(t.header.getRightChild())!=0 ? true:false;

     }

    private int chkproperties(RedBlackNode root) {
        int l,r;
        //root not black violation
        if(root==null)
            return 1;
            else{
            RedBlackNode curr=root;
            RedBlackNode left=curr.getLeftChild();
            RedBlackNode right=curr.getRightChild();
            //double red violation
            if(root.getColor()==0)
                if(left!=null && left.getColor()==0 ||right!=null &&right.getColor()==0)
                    return 0;
            //binary search tree violation
            l=chkproperties(left);
            r=chkproperties(right);
            if(left!=null && left.getElement().getKey()>curr.getElement().getKey()|| right!= null && right.getElement().getKey()<curr.getElement().getKey())
                return 0;
            // black node height violation
            if(l!=0 && r!=0 && l!=r)
                return 0;
            if(l!=0 && r!=0)
                return curr.getColor()==0? l:l+1;
            else
                return 0;

        }

    }

    private String getColor(int l){
            return lines[l].substring(lines[l].indexOf(":") + 1, lines[l].indexOf("("));
        }
        private int getKey(int l){
            return Integer.parseInt(lines[l].substring(lines[l].indexOf("(") + 1, lines[l].indexOf(",")));
        }
        private String getValue(int l){
            return lines[l].substring(lines[l].indexOf(",")+1,lines[l].indexOf(")"));
        }
        private String getPrefix(int l){
            return lines[l].substring(0,lines[l].indexOf(":"));
        }
        private RedBlackTree makeTree(RedBlackNode r){
            RedBlackTree tree = new RedBlackTree();
            tree.header.setRightChild(new RedBlackNode(new Element(getKey(0),getValue(0))));
            tree.root=tree.header.getRightChild();
            tree.root.setColor(1);
            tree.parent=tree.root;
            tree.curr=tree.root;
            String prefix=getPrefix(0);
            System.out.println("length "+(length));
            for(int i=1;i<length;i++){
                System.out.println("inside for");
                if((prefix+"-left").equals(getPrefix(i))){
                    tree.curr.setLeftChild(new RedBlackNode(new Element(getKey(i),getValue(i))));
                    tree.parent=tree.curr;
                    tree.curr=tree.curr.getLeftChild();
                    tree.curr.setColor((getColor(i).equals("RED")) ? 0:1);
                    System.out.println("-left" + tree.curr.getElement().getKey()+"parent "+tree.parent.getElement().getKey());
                    prefix=getPrefix(i);
                }
                else if((prefix+"-right").equals(getPrefix(i))){
                    tree.curr.setRightChild(new RedBlackNode(new Element(getKey(i),getValue(i))));
                    tree.parent=tree.curr;
                    tree.curr=tree.curr.getRightChild();
                    tree.curr.setColor((getColor(i).equals("RED")) ? 0:1);
                    System.out.println("-right" + tree.curr.getElement().getKey()+"parent "+tree.parent.getElement().getKey());
                    prefix=getPrefix(i);
                }
                else{
                    System.out.println("inside else");
                    i--;
                    tree.curr=tree.parent;
                    prefix=getPrefix(i-1);
                    System.out.println(prefix+tree.curr.getElement().getKey());
                }
            }
            System.out.println("tis the tree built from input\n"+tree.toString());
          return tree;
        }


}
