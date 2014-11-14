import java.util.Scanner;

/**
 * Created by Nico on 11/8/14.
 */
public class RBTValidator {
    String[] lines;
    int length;
    public RBTValidator(){
        lines=new String[50];
        length=0;
    }
    public boolean validate (String a){
        Scanner scanner = new Scanner(a);
        int count=0;
        //files up array with each line from string
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            lines[count] = line;
            count++;
            // process the line
        }
        scanner.close();
        length=count;
        //if root is not red return false
        if(getColor(0).equals("RED"))
            return false;
        else return chkproperties(makeTree(new RedBlackNode(new Element(getKey(0),getValue(0)))))!=0 ? true:false;

     }

    private int chkproperties(RedBlackNode root) {
        int l,r;
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
        private RedBlackNode makeTree(RedBlackNode r) {
            String prefix = getPrefix(0);
            r.setColor((getColor(0) == "RED") ? 0 : 1);
            RedBlackNode curr = r;


            for (int i = 1; i < length-1; i++) {
                if ((prefix + "-left").equals(getPrefix(i))) {
                    curr.setLeftChild(new RedBlackNode(new Element(getKey(i), getValue(i))));
                    curr.getLeftChild().setParent(curr);
                    curr = curr.getLeftChild();
                    curr.setColor((getColor(i).equals("RED")) ? 0 : 1);
                    prefix = getPrefix(i);
                } else if ((prefix + "-right").equals(getPrefix(i))) {
                    curr.setRightChild(new RedBlackNode(new Element(getKey(i), getValue(i))));
                    curr.getRightChild().setParent(curr);
                    curr = curr.getRightChild();
                    curr.setColor((getColor(i).equals("RED")) ? 0 : 1);
                    prefix = getPrefix(i);
                } else {
                    curr = curr.getParent();
                    prefix = prefix.substring(0, prefix.lastIndexOf("-"));
                    i--;


                }
            }
            return r;
        }

    private String treeToString(RedBlackNode root,String p){
        StringBuilder prefix=new StringBuilder();
        StringBuilder tree=new StringBuilder();
        prefix.append(p);
        if(root==null){
            return "This tree is empty";
        }
        else{
            if(root!=null)
                tree.append(prefix+":"+root.getColorString()+"("+root.getElement().getKey()+","+root.getElement().getValue()+")\n");
            if(root.getLeftChild()!=null){
                tree.append(treeToString(root.getLeftChild(), prefix+"-left"));
            }
            if(root.getRightChild() !=null){
                tree.append(treeToString(root.getRightChild(),prefix+ "-right"));
            }


        }
        return tree.toString();

    }
}
