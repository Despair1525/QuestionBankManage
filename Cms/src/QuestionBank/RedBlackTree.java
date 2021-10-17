package QuestionBank;
public class RedBlackTree{
public Node root;
public RedBlackTree(){
root =null;
};


Node rotateLeft(Node node){
    Node x = node.right;
    Node y = x.left;
    x.left = node;
    node.right =y;
    node.parent =x;
    if(y != null) y.parent =node;

    return x;
};

Node rotateRight(Node node){
    Node x =  node.left;
    Node y = x.right;
    x.right = node;
    node.left = y;
    node.parent = x;
    if(y!= null) y.parent =node;
    return x;
};
boolean ll =false;
boolean lr =false;
boolean rr =false;
boolean rl =false;


Node insertHelp(Node root, Problem data)
    {
        // f is true when RED RED conflict is there.
        boolean f=false;
         
        //recursive calls to insert at proper position according to BST properties.
        if(root==null)
            return(new Node(data));
        else if(data.compareTo(root.data)<0)
        {
            root.left = insertHelp(root.left, data);
            root.left.parent = root;
            if(root!=this.root)
            {
                if(root.color=='R' && root.left.color=='R')
                    f = true;
            }
        }
        else
        {
            root.right = insertHelp(root.right,data);
            root.right.parent = root;
            if(root!=this.root)
            {
                if(root.color=='R' && root.right.color=='R')
                    f = true;
            }
        // at the same time of insertion, we are also assigning parent nodes
        // also we are checking for RED RED conflicts
        }
 
        if(this.ll) // for left rotate.
        {
            root = rotateLeft(root);
            root.color = 'B';
            root.left.color = 'R';
            this.ll = false;
        }
        else if(this.rr) // for right rotate
        {
            root = rotateRight(root);
            root.color = 'B';
            root.right.color = 'R';
            this.rr  = false;
        }
        else if(this.rl)  // for right and then left
        {
            root.right = rotateRight(root.right);
            root.right.parent = root;
            root = rotateLeft(root);
            root.color = 'B';
            root.left.color = 'R';
 
            this.rl = false;
        }
        else if(this.lr)  // for left and then right.
        {
            root.left = rotateLeft(root.left);
            root.left.parent = root;
            root = rotateRight(root);
            root.color = 'B';
            root.right.color = 'R';
            this.lr = false;
        }
        // when rotation and recolouring is done flags are reset.
        // Now lets take care of RED RED conflict
        if(f)
        {
            if(root.parent.right == root)  // to check which child is the current node of its parent
            {
                if(root.parent.left==null || root.parent.left.color=='B')  // case when parent's sibling is black
                {// perform certaing rotation and recolouring. This will be done while backtracking. Hence setting up respective flags.
                    if(root.left!=null && root.left.color=='R')
                        this.rl = true;
                    else if(root.right!=null && root.right.color=='R')
                        this.ll = true;
                }
                else // case when parent's sibling is red
                {
                    root.parent.left.color = 'B';
                    root.color = 'B';
                    if(root.parent!=this.root)
                        root.parent.color = 'R';
                }
            }
            else  
            {
                if(root.parent.right==null || root.parent.right.color=='B')
                {
                    if(root.left!=null && root.left.color=='R')
                        this.rr = true;
                    else if(root.right!=null && root.right.color=='R')
                        this.lr = true;
                }
                else
                {
                    root.parent.right.color = 'B';
                    root.color = 'B';
                    if(root.parent!=this.root)
                        root.parent.color = 'R';
                }
            }
            f = false;
        }
        return(root); 
    }
public void insert(Problem data)
    {
        if(this.root==null)
        {
            this.root = new Node(data);
            this.root.color = 'B';
        }
        else
            this.root = insertHelp(this.root,data);
    }
String[] takeInfo(String str){
        String[] part = str.split("(?<=\\D)(?=\\d)");
        return part;
    };
////////////////////////////////////////////////////////
public int compareProblem(String o,Problem A) {
        String[] values1 = takeInfo(o);
        String[] values2 = takeInfo(A.id);
        if( values1[0].equals(values2[0])){
            int a = Integer.parseInt(values1[1]);
            int b = Integer.parseInt(values2[1]);
            if (a==b) return 0;
            if(a<b) return -1;
            if(a>b) return 1;
        };
        int r=values1[0].compareTo(values2[0]);
        if(r<0) return -1;
        return 1;
    }
public Node findNode(Node temp,String id){
    if(root == null || temp == null) return null;
    System.out.println(temp.data.id);
    if(compareProblem(id, temp.data)==0)  return temp;
    if(compareProblem(id, temp.data)<0){
        return findNode(temp.left, id);
    };
    return findNode(temp.right, id);
};

public Problem find(String ID){
    Node r= findNode(root, ID);
    if (r==null)return null;
    return r.data;
};
};