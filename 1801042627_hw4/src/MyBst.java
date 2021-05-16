package mustafa.hw4;
/**
 * binary search tree implementation
 * @author Mustafa Karakas
 *
 * @param <E> type of element
 */
public class MyBst<E extends Comparable<E>>{
	/**
	 * node of the tree
	 * @author Mustafa Karakas
	 *
	 * @param <E> type of the element
	 */
     static class Node<E>{
        private E data;
        
        private Node<E> left = null;
        private Node<E> right = null;
        /**
         * constructs node
         * @param data value which is holded by node
         */
        private Node(E data){
            this.data = data;          
        }
        /**
         * converts value to string and returns it
         */
        public String toString() {
        	return data.toString();
        }
        /**
         * returns the data
         * @return the data
         */
        E getData() {
        	return data;
        }
        /**
         * returns the left child
         * @return the left child
         */
        Node<E> getLeft(){
        	return left;
        }
        /**
         * returns the right child
         * @return the right child
         */
        Node<E> getRight(){
        	return right;
        }

    }
    private Node<E> root;
    /**
     * returns the root node of the bst
     * @return the root node of the bst
     */
    Node<E> getRoot(){
    	return root;
    }
    private boolean addCompleted = false;
    private boolean isRemoved= false;
    /**
     * constructs empty bst
     */
    public MyBst(){
        root = null;
    }
    /**
     * adds the item in the appropriate position
     * @param item value to be added
     * @return true if the value is added, it returns false if the bst has the given element
     */
    public boolean add(E item) {
    	root = add(root,item);
    	return addCompleted;
    }
    /**
     * helper add function 
     * @param proot node which is checked if its position proper for the data
     * @param data value to be added
     * @return true if the data is added
     */
    private Node<E> add(Node<E> proot,E data) {
    	if(proot == null) {
    		proot = new Node<E>(data);
    		addCompleted = true;
    	}
    	else if(data.compareTo(proot.data) == 0) {
    		addCompleted = false;
    	}
    	else if(proot.data.compareTo(data)<0) {
    		proot.right = add(proot.right,data);
    	}
    	else {
    		proot.left = add(proot.left,data);
    	}
    	return proot;
    }
    /**
     * traverse the bst inorder
     */
    public void inorderTraverse() {
    	inorderTraverse(root);
    }
    /**
     * inorder traverse's helper function  
     * @param proot root of the node about to be printed
     */
    private void inorderTraverse(Node<E> proot) {
    	if(proot == null)
    		return;
    	inorderTraverse(proot.left);
    	System.out.print(proot.data+ " ");
    	inorderTraverse(proot.right);
    	if(proot == root) {
    		System.out.println();
    	}
    }
    /**
     * searches and returns the item , if it does not exists returns null
     * @param item searched element
     * @return if it does not exists returns null else returns the item
     */
    public E find(E item) {
    	return find(root,item);
    }
    /**
     * helper method of the find
     * @param proot root node which is started searching from
     * @param item searched item
     * @return found element , if it does not returns null
     */
    private E find(Node<E> proot,E item) {
    	
    	if(proot == null)
    		return null;
    	else if(proot.data.compareTo(item) == 0) {
    		//System.out.println(proot.data+ " is equal to the " + item);
    		return proot.data;
    	}
    	else if(proot.data.compareTo(item) < 0 ) {
    		//System.out.println(proot.data+ " is smaller than " + item + " going to the right");
    		return find(proot.right,item);
    	}
    	else {
    		//System.out.println(proot.data+ " is greater than " + item + " going to the left");
    		return find(proot.left,item);
    	}
    }
    /**
     * returns true if the item exists
     * @param item searched item
     * @return true if the item exists
     */
    public boolean contains(E item) {
    	return find(root,item) != null;
    			
    }
    /**
     * removes the given element 
     * @param target searched element
     * @return deleted element
     */
    public E delete(E target) {
    	Node<E> temp = delete(root,target);
    	
    	if (temp == null)
    		return null;
    	
    	return temp.data;
    }
    /**
     * returns the # of childrens of the given node
     * @param proot node whose number of children are searched
     * @return number of children of given node
     */
    private int numberOfChildren(Node<E>proot) {
    	if (proot == null) 
    		return -1;
    	int x = (proot.left == null) ? 0:1;
    	int y = (proot.right == null) ? 0:1;
    	return x+y;
    }
    /**
     * deletes the given node
     * @param proot
     */
    void delete(Node<E> proot) {
    	delete(root,proot);
    }
    /**
     * helper of method which deletes node 
     * @param root the root which may hold the node we are searching for
     * @param proot searched node
     */
    void delete(Node<E> root,Node<E> proot) {
    	if(root == null)
    		return;
    	if(root.left == proot) {
    		root.left = null;
    	}
    	else if(root.right == proot) {
    		root.right = null;
    	}
    	else {
    		delete(root.left,proot);
    		delete(root.right,proot);
    	}
    }
    /**
     * helper method of the delete method
     * @param proot node which may have the searched element
     * @param item item to be removed
     * @return node which has deleted element
     */
    private Node<E> delete(Node<E> proot,E item) {
    	if(proot == null) {
    		return null;
        	
    	}
    	else if(proot.data.equals(item)) {
    		if(isLeaf(proot))
    		{
    			isRemoved = true;
    			if(proot == root)
    				root = null;
    			return null;
    		}
    		else if(numberOfChildren(proot) == 1) {
    			Node<E> child = (proot.left!=null) ? proot.left : proot.right;
    			if(proot == root) {
    				root = child;
    			}
    			isRemoved = true;
    			return child;
    		}
    		else { // has 2 child
    			Node<E> leftChild = proot.left;
    			if(leftChild.right == null) {
    				proot.data = leftChild.data;
    				proot.left = leftChild.left;
    				return proot;
    			}else {
    				E biggestOfSmallers=  findBiggestEndDelete(leftChild);
    				proot.data = biggestOfSmallers;
    				return proot;
    			}
    		}
    	}
    	else if(proot.data.compareTo(item)<0) {
    		proot.right = delete(proot.right,item);
    		return proot;
    	}
    	else {
    		proot.left = delete(proot.left,item);
    		return proot;
    	}
    
    	
    }
    /**
     * finds the biggest element which is descendant of the given node and deletes it
     * @param proot ancestor node
     * @return deleted element
     */
    private E findBiggestEndDelete(Node<E> proot) {
    	if(proot.right.right == null) {
    		E temp = proot.right.data;
    		proot.right = proot.right.left;
    		return temp;
    	}
    	return findBiggestEndDelete(proot.right);
    }
    /**
     * returns true if the given node has no child
     * @param prroot who is checked if it is leaf
     * @return true if the given node has no child
     */
    static boolean isLeaf(Node prroot) {
    	return prroot != null && prroot.left == null && prroot.right == null;
    }
    /**
     * traverse the tree preOrder
     * @param proot where it starts to traverse
     * @param depth depth of the node
     * @param s StringBuilder to hold values in it as string
     */
    private void preOrderTraverse(Node<E> proot,int depth,
    							  StringBuilder s) {
    	for (int i = 0; i < depth; i++) {
			s.append("  ");
		}
    	if(proot == null) {
    		s.append("null\n");
    	}
    	else {
    		s.append(proot.toString());
    		s.append("\n");
    		preOrderTraverse(proot.left,depth+1,s);
    		preOrderTraverse(proot.right,depth+1,s);
    	}
    }
    /**
     * returns tree as a string 
     */
    public String toString() {
    	StringBuilder s = new StringBuilder();
    	preOrderTraverse(root,1,s);
    	return s.toString();
    }
    /**
     * returns true if the tree is empty
     * @return true if the tree is empty else false
     */
    public boolean isEmpty() {
    	return (root == null);
    }
    /**
     * returns the value of root
     * @return
     */
    public E rootValue() {
    	return (root == null) ? null : root.data;
    }
    public static void main(String[] args) {
    	MyBst<Integer> t = new MyBst<>();
    	t.add(6);
    	t.add(5);
    	t.add(1);
    	t.add(7);
    	t.add(60);
    	t.add(51);
    	t.add(123);
    	t.add(73);
    	t.add(7);
    	t.add(98);
    	t.add(72);

    	
    	System.out.println(t);

    	t.inorderTraverse();
    	//t.delete(51);
    	t.delete(123);
    	System.out.println(t);
    	t.inorderTraverse();
    	t.delete(1);
    	System.out.println(t);
    	t.inorderTraverse();
    	t.delete(7);
    	System.out.println(t);
    	t.inorderTraverse();
    	t.delete(60);
    	System.out.println(t);
    	t.inorderTraverse();
    	
    	/*System.out.println(t.find(61));
    	System.out.println(t.find(73));
    	System.out.println(t.contains(7));
    	System.out.println(t.contains(1234));
    	*/
    	System.out.println(t);
    	t.inorderTraverse();
    	System.out.println(t+" "+t.isEmpty());
    	while(!t.isEmpty()) {
    		System.out.println("rooot: "+t.rootValue());
    		t.delete(t.rootValue());

    		System.out.println(t);
        	t.inorderTraverse();
    		
    	}
    }
    public static void main2(String[] args) {
    	MyBst<Cup<Integer>> t = new MyBst<>();
    	t.add(new Cup<Integer>(3));

    	t.add(new Cup<Integer>(4));
    	
    	t.add(new Cup<Integer>(2));
    	t.delete(new Cup<Integer>(2));
    	System.out.println(t);
	}
}