package QuestionBank;
public class RedBlackTree{
public Node root;
private Node TNULL = new Node();
public RedBlackTree(){
    root =TNULL;
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
                if(root.color==1 && root.left.color==1)
                    f = true;
            }
        }
        else
        {
            root.right = insertHelp(root.right,data);
            root.right.parent = root;
            if(root!=this.root)
            {
                if(root.color==1 && root.right.color==1)
                    f = true;
            }
        // at the same time of insertion, we are also assigning parent nodes
        // also we are checking for RED RED conflicts
        }
 
        if(this.ll) // for left rotate.
        {
            root = rotateLeft(root);
            root.color = 0;
            root.left.color = 1;
            this.ll = false;
        }
        else if(this.rr) // for right rotate
        {
            root = rotateRight(root);
            root.color = 0;
            root.right.color = 1;
            this.rr  = false;
        }
        else if(this.rl)  // for right and then left
        {
            root.right = rotateRight(root.right);
            root.right.parent = root;
            root = rotateLeft(root);
            root.color = 0;
            root.left.color = 1;
 
            this.rl = false;
        }
        else if(this.lr)  // for left and then right.
        {
            root.left = rotateLeft(root.left);
            root.left.parent = root;
            root = rotateRight(root);
            root.color = 0;
            root.right.color = 1;
            this.lr = false;
        }
        // when rotation and recolouring is done flags are reset.
        // Now lets take care of RED RED conflict
        if(f)
        {
            if(root.parent.right == root)  // to check which child is the current node of its parent
            {
                if(root.parent.left==null || root.parent.left.color== 0)  // case when parent's sibling is black
                {// perform certaing rotation and recolouring. This will be done while backtracking. Hence setting up respective flags.
                    if(root.left!=null && root.left.color==1)
                        this.rl = true;
                    else if(root.right!=null && root.right.color==1)
                        this.ll = true;
                }
                else // case when parent's sibling is red
                {
                    root.parent.left.color = 0;
                    root.color = 0;
                    if(root.parent!=this.root)
                        root.parent.color = 1;
                }
            }
            else  
            {
                if(root.parent.right==null || root.parent.right.color== 0)
                {
                    if(root.left!=null && root.left.color==1)
                        this.rr = true;
                    else if(root.right!=null && root.right.color==1)
                        this.lr = true;
                }
                else
                {
                    root.parent.right.color = 0;
                    root.color = 0;
                    if(root.parent!=this.root)
                        root.parent.color = 1;
                }
            }
            f = false;
        }
        return(root); 
    }
//public void insert(Problem data)
//    {
//        if(this.root==null)
//        {
//            this.root = new Node(data);
//            this.root.color = 0;
//        }
//        else
//            this.root = insertHelp(this.root,data);
//
//    }
public void insert(Problem data) {
    Node node = new Node();
    node.parent = null;
    node.data = data;
    node.left = TNULL;
    node.right = TNULL;
    node.color = 1;

    Node y = null;
    Node x = this.root;

    while (x != TNULL) {
      y = x;
      if (node.data.compareTo(x.data) < 0) {
        x = x.left;
      } else {
        x = x.right;
      }
    }

    node.parent = y;
    if (y == null) {
      root = node;
    } else if (node.data.compareTo( y.data) <0) {
      y.left = node;
    } else {
      y.right = node;
    }

    if (node.parent == null) {
      node.color = 0;
      return;
    }

    if (node.parent.parent == null) {
      return;
    }

    fixInsert(node);
  }
private void fixInsert(Node k) {
    Node u;
    while (k.parent.color == 1) {
      if (k.parent == k.parent.parent.right) {
        u = k.parent.parent.left;
        if (u.color == 1) {
          u.color = 0;
          k.parent.color = 0;
          k.parent.parent.color = 1;
          k = k.parent.parent;
        } else {
          if (k == k.parent.left) {
            k = k.parent;
            rightRotate(k);
          }
          k.parent.color = 0;
          k.parent.parent.color = 1;
          leftRotate(k.parent.parent);
        }
      } else {
        u = k.parent.parent.right;

        if (u.color == 1) {
          u.color = 0;
          k.parent.color = 0;
          k.parent.parent.color = 1;
          k = k.parent.parent;
        } else {
          if (k == k.parent.right) {
            k = k.parent;
            leftRotate(k);
          }
          k.parent.color = 0;
          k.parent.parent.color = 1;
          rightRotate(k.parent.parent);
        }
      }
      if (k == root) {
        break;
      }
    }
    root.color = 0;
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
    if(root == null ||  temp == TNULL) return null;

      if(compareProblem(id, temp.data)==0)  return temp;
    if(compareProblem(id, temp.data)<0){
        return findNode(temp.left, id);
    };
   
    return findNode(temp.right, id);  
   
};

public Problem find(String ID){
    Node r= findNode(root, ID);
    if ( r == null)return null;
    return r.data;
};
void firstTravel(Node temp){
    if( temp == TNULL) return;
    System.out.print(temp.data);
    firstTravel(temp.left);
    firstTravel(temp.right);
};
public void display(){
    System.out.println("------------------------------------------------------Question Bank--------------------------------------------------");
        System.out.printf("%15s|%10s|%10s|%50s|%s\n", "ID", "Date", "Type", "Describe", "Key");
    if(root == null) {
        System.out.println("List is blank !");
        return;
    };
    firstTravel(root);
};

public int delete(String id){
    int check=deleteNodeHelper(this.root,id);
    return check;
};
   private int deleteNodeHelper(Node node, String key) {
		// find the node containing key
		Node z = TNULL;
		Node x, y;
		
                z = findNode(node, key);
                
                if (z == null) {
			System.out.println("Couldn't find key in the tree");
			return 0;
		} 
		y = z;
		int yOriginalColor = y.color;
		if (z.left == TNULL) {
			x = z.right;
			rbTransplant(z, z.right);
		} else if (z.right == TNULL) {
			x = z.left;
			rbTransplant(z, z.left);
		} else {
			y = minimum(z.right);
			yOriginalColor = y.color;
			x = y.right;
			if (y.parent == z) {
				x.parent = y;
			} else {
				rbTransplant(y, y.right);
				y.right = z.right;
				y.right.parent = y;
			}

			rbTransplant(z, y);
			y.left = z.left;
			y.left.parent = y;
			y.color = z.color;
		}
		if (yOriginalColor == 0){
			fixDelete(x);
		}
                return 1;
	}
	private void fixDelete(Node x) {
		Node s;
		while (x != root && x.color == 0) {
			if (x == x.parent.left) {
				s = x.parent.right;
				if (s.color == 1) {
					// case 3.1
					s.color = 0;
					x.parent.color = 1;
					leftRotate(x.parent);
					s = x.parent.right;
				}

				if (s.left.color == 0 && s.right.color == 0) {
					// case 3.2
					s.color = 1;
					x = x.parent;
				} else {
					if (s.right.color == 0) {
						// case 3.3
						s.left.color = 0;
						s.color = 1;
						rightRotate(s);
						s = x.parent.right;
					} 

					// case 3.4
					s.color = x.parent.color;
					x.parent.color = 0;
					s.right.color = 0;
					leftRotate(x.parent);
					x = root;
				}
			} else {
				s = x.parent.left;
				if (s.color == 1) {
					// case 3.1
					s.color = 0;
					x.parent.color = 1;
					rightRotate(x.parent);
					s = x.parent.left;
				}

				if (s.right.color == 0 && s.right.color == 0) {
					// case 3.2
					s.color = 1;
					x = x.parent;
				} else {
					if (s.left.color == 0) {
						// case 3.3
						s.right.color = 0;
						s.color = 1;
						leftRotate(s);
						s = x.parent.left;
					} 

					// case 3.4
					s.color = x.parent.color;
					x.parent.color = 0;
					s.left.color = 0;
					rightRotate(x.parent);
					x = root;
				}
			} 
		}
		x.color = 0;
	}



    private void rbTransplant(Node u, Node v){
		if (u.parent == null) {
			root = v;
		} else if (u == u.parent.left){
			u.parent.left = v;
		} else {
			u.parent.right = v;
		}
		v.parent = u.parent;
	}
public Node minimum(Node node) {
		while (node.left != TNULL) {
			node = node.left;
		}
		return node;
	}

public void leftRotate(Node x) {
		Node y = x.right;
		x.right = y.left;
		if (y.left != TNULL) {
			y.left.parent = x;
		}
		y.parent = x.parent;
		if (x.parent == null) {
			this.root = y;
		} else if (x == x.parent.left) {
			x.parent.left = y;
		} else {
			x.parent.right = y;
		}
		y.left = x;
		x.parent = y;
	}

public void rightRotate(Node x) {
		Node y = x.left;
		x.left = y.right;
		if (y.right != TNULL) {
			y.right.parent = x;
		}
		y.parent = x.parent;
		if (x.parent == null) {
			this.root = y;
		} else if (x == x.parent.right) {
			x.parent.right = y;
		} else {
			x.parent.left = y;
		}
		y.right = x;
		x.parent = y;
	}

};