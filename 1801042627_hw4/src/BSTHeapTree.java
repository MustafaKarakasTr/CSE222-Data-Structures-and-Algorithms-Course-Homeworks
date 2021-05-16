package mustafa.hw4;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;


/**
 * Binary search tree which holds a maxHeap in every node of it
 * @author Mustafa Karakas
 *
 */
public class BSTHeapTree <E extends Comparable<E>>{
	private MyBst<myHeap<Cup<E>>> tree;
	private static final int MAXSIZEOFHEAP = 7;
	private Cup<E> mode = null;
	private int size = 0;
	/**
	 * adds given item to the tree
	 * @param item item about to be added
	 * @return number of occurences of the item
	 */
	public int add (E item) {
		size++;
		return add(tree.getRoot(),item);
	}
	/**
	 * returns the size of the tree
	 * @return
	 */
	public int size() {
		return size;
	}
	/**
	 * constructs the BSTHeapTree
	 */
	public BSTHeapTree() {
		tree = new MyBst<myHeap<Cup<E>>>();
	}
	/**
	 * helper method of the add function
	 * @param root the node which may/will have the given Item 
	 * @param item element which is about to be added
	 * @return number of occurences of the added element in the tree
	 */
	private int add(MyBst.Node<myHeap<Cup<E>>> root ,E item) {
		if(root == null) {
			Cup<E> temp = new Cup<E>(item);
			maxHeap<Cup<E>> heapAdded = new maxHeap<Cup<E>>();
			heapAdded.add(temp);
			tree.add(heapAdded);
			return 1;
		}
			
		maxHeap<Cup<E>> heap = (maxHeap<Cup<E>>) root.getData();
		Cup<E> temp = new Cup<E>(item);
		Cup<E> found = heap.find(temp);
		if(found!= null) {	
			found.increaseOccurences();
			return found.getOccurences();
		}
		if(heap.size() < MAXSIZEOFHEAP) {
			heap.add(temp);
			return 1;
		}
		else {
			if(heap.rootValue().compareTo(temp) > 0){
				return add(root.getLeft(),item);
			}else {
				return add(root.getRight(),item);	
			}
		}
	}
	/**
	 * helper find method
	 * @param r node which may have the given val
	 * @param val searched value
	 * @return occurences of the given value in the tree
	 */
	private int find(MyBst.Node<myHeap<Cup<E>>> r ,  Cup<E> val) {
		if(r == null) {
			return 0;
		}
		myHeap<Cup<E>> heap = r.getData();
		Cup<E> found = heap.find(val);
		
		if(found != null) {
			return found.getOccurences();
		}else if(heap.rootValue().compareTo(val) > 0) {
			return find(r.getLeft(),val);
		}else {
			return find(r.getRight(),val);
		}
	}
	/**
	 * returns the number of occurences of the val
	 * @param val element whose number of occurences is wanted
	 * @return the number of occurences of the val
	 */
	public int find(E val) {
		return find(tree.getRoot(),new Cup<E>(val));
	}
	/**
	 * finds the value which has the greatest number of occurences and returns it
	 * @return the value which has the greatest number of occurences
	 */
	public E find_mode() {
		mode = null;
		if(tree.getRoot() == null)
			return null;
		find_mode(tree.getRoot());
		return mode.getVal();
	}
	/**
	 * helper method of the find_mode method
	 * @param node node may have the mode
	 */
	private void find_mode(MyBst.Node<myHeap<Cup<E>>> node){
		if(node == null||node.getData() == null) {
			return;
		}
		Cup<E> temp = find_mode_heap(node.getData());
		if(temp != null) {
			if(mode == null) {
				mode = temp;
			}else {
				if(mode.getOccurences() < temp.getOccurences()) {
					mode = temp;
				}
			}
		}
		find_mode(node.getLeft());
		find_mode(node.getRight());
	}
	

	/**
	 * returns the mode of the heap
	 * @param h heap where we searched for the mode
	 * @return mode of the heap
	 */
	private Cup<E> find_mode_heap(myHeap<Cup<E>> h) {
		if(h == null || h.size() == 0)
			return null;
		int maxOccurIndex = 0;
		for(int i=1;i<h.size();i++) {
			if(h.get(i).getOccurences()> h.get(maxOccurIndex).getOccurences() ) {
				maxOccurIndex = i;
			}
		}
		return h.get(maxOccurIndex);
	}
	/**
	 * removes the given item and returns the number of occurences of the item after removal
	 * if it returns -1 it means the item is not found, if it returns 0 there was an item and now there is not
	 * @param item item to be removed
	 * @return the number of occurences of the item after removal
	 */
	public int remove (E item) {
		Cup<E> temp = remove(tree.getRoot(),new Cup<E>(item));
		if(temp == null) return -1;
		size--;
		return temp.getOccurences();
	}
	/**
	 * helper remove function
	 * if the root of the current node is smaller than the searched element there is no way to this node has the searched element, so we search it in the right node
	 * otherwise it can be in this node or on it's left child
	 * when we find the item, if its number of occurences is more than 1 we decrease it
	 * if its number occurence is one than we are about to remove the last occurence of it so we should remove it from the heap
	 * 		when we remove it from the heap there are some details to care about:
	 * 			if the heap has no other element than the deleted element, then we should delete the node too.
	 * 			if the heap had the deleted element is not a leaf node, than it should take one of its children's element to add itself so that the BSTHeapTree structure is maintained.
	 * 				This process is the responsibility of the setProperly method
	 * @param node node may have the searched element
	 * @param searched element to be removed
	 * @return deleted element
	 */
	private Cup<E> remove(MyBst.Node<myHeap<Cup<E>>> node,Cup<E> searched){ // searched is needed to be able to allocate 1 times
		if(node == null) {
			return null;
		}
		
		if(node.getData().size()==0) {
			throw new NullPointerException();
		}
		int result = node.getData().rootValue().compareTo(searched);
		if( result < 0 ) {
			return remove(node.getRight(),searched); // maxheap does not have any element which is bigger than root value
		} else {
			Cup <E> found = node.getData().find(searched);
			if(found != null) {
				if(found.getOccurences() == 1) {
					if(node.getData().size() == 1) {
						tree.delete(node.getData());
						
					}
					else {
						node.getData().remove(found);
						if(!MyBst.isLeaf(node)) {
							setProperly(node);
						}
					}
				}
				found.decreaseOccurences();
				return found;
				
			} else {
				return remove(node.getLeft(),searched);
			}
		}
		
	}
	/**
	 * when we remove the last occurence of the given element we call this function.
	 * if it has a left child , takes the biggest element of the rightmost node of the left child to add itself
	 * else it takes the smallest element of the rightmost node of the right child
	 * so that BSTHeapTree structure is maintained
	 * Actually , When I tried to write this function ,I tried to delete biggest/smallest elements as you can understand from the name of the functions
	 * but ,they remove the biggest/smallest element of the rightmost/leftmost node of the left/right child actually.
	 * But I have never seen that it worked incorrectly.
	 * @param node the node may have the less number of elements than the MAXNUMBEROFELEMENT (7)
	 */
	private void setProperly(MyBst.Node<myHeap<Cup<E>>>  node) {
		Cup<E> temp = removeBiggestonRightmost(node.getLeft());
		if(temp == null) {
			temp = removeSmallestonLeftmost(node.getRight());
		}
		node.getData().add(temp);
	}
	/**
	 * removes and returns the smallest element of the leftmost node
	 * @param node ancestor node whose leftmost node's smallest element is about to be removed
	 * @return smallest element on the leftmost node of the given node
	 */
	private Cup<E> removeSmallestonLeftmost(MyBst.Node<myHeap<Cup<E>>>  node){
		if(node == null)
			return null;
		if(node.getLeft() == null) {
			Cup<E> temp = ((maxHeap<Cup<E>>) node.getData()).removeSmallest();
			if(node.getData().size() == 0) {
				tree.delete(node);
			}
			if(node.getRight() != null) {
				setProperly(node); 
			}
			return temp;
		}
		return removeSmallestonLeftmost(node.getLeft());
	}
	/**
	 * removes and returns the biggest element on node's rightmost node
	 * @param node ancestor node whose rightmost node's biggest element is about to be removed
	 * @return biggest element  on the rightmost node of the given node
	 */
	private Cup<E> removeBiggestonRightmost(MyBst.Node<myHeap<Cup<E>>>  node){
		if(node == null)
			return null;
		if(node.getRight() == null) {
			Cup<E> temp = node.getData().remove();
			if(node.getData().size() == 0) {
				tree.delete(node);
			}
			if(node.getLeft() != null)
				setProperly(node);
			return temp;
		}
		return removeBiggestonRightmost(node.getRight());
	}
	/**
	 * returns the tree as a string
	 *  @return string which has tree
	 */
	public String toString() {
		return tree.toString();
	}
	/**
	 * returns true if the tree is empty
	 * @return true if the tree is empty otherwise returns false
	 */
	public boolean isEmpty() {
		return tree.getRoot() == null;
	}
	
	public static void main(String[] args) {
		
		BSTHeapTree<Integer> tree = new BSTHeapTree<>();
		System.out.println(tree+"\n\n"+tree.find_mode());
		
		Random rand = new Random();
		  
        ArrayList<Integer> arr = new ArrayList<>();
		for(int i=0;i<30;i++) {
			int x = rand.nextInt(50);
			tree.add(x);
			arr.add(x);
		}
		System.out.println("First check: "+checkOccurences(tree, arr));
		System.out.println(tree);
		
		for(int i=0;i<30;i++) {
			int x = rand.nextInt(50);
			System.out.print(x+" ");
			tree.remove(x);
			arr.remove((Object)x);
			//System.out.println(tree);
			
			//System.out.println("\n"+tree);
			
		}
		System.out.println("\n"+tree);
		System.out.println("Second check: "+checkOccurences(tree, arr));
		System.out.println(tree+"\n\n"+tree.find_mode());
		
	}

	public static boolean checkOccurences(BSTHeapTree<Integer> tree, ArrayList<Integer> arr) {
		arr.sort(Comparable::compareTo);
		if(arr.isEmpty() && tree.isEmpty()) {
			return true;
		}
		if(arr.size() != tree.size())
			return false;
		int q = arr.get(0);
		int occur;
		boolean flag = false;
		// number of occurence check:
		for(int qw = 0;qw<arr.size();qw++) {
			occur = 0;
			int temp = qw;
			for(int x = qw;x<arr.size();x++) {
				if(arr.get(x).equals(arr.get(temp))) {
					occur++;
				}else {

					break;
				}
				
			}
			qw += occur;
			qw--;
			int occurencesInTree = tree.find(arr.get(temp));
			if( occurencesInTree != occur) {
				//System.out.println(occurencesInTree + "ERROOORRR" + occur+"value: " +arr.get(temp));
				
				return false;
			}else {
				//System.out.println(occurencesInTree + " == " + occur +" number of "+arr.get(temp));
			}
		}
		//System.out.println("Successfull");
		return true;
	}
}
