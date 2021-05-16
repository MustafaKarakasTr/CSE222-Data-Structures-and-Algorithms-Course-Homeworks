package mustafa.hw4;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;
/**
 * Heap data structure implementation
 * @author Mustafa Karakas
 *
 * @param <E> type of element
 */
public class myHeap <E extends Comparable<E>> implements Iterable<E>  , Comparable<myHeap<E>>{
	protected ArrayList<E> arr;
	int multiplier = 1;
	/**
	 * constructs the heap
	 */
	public myHeap() {
		arr = new ArrayList<E>();
	}
	/**
	 * constructs the heap
	 * if it is a max Heap than isMax is true
	 * @param isMax if it is true than heap is a max heap else it is min heap
	 */
	protected myHeap(boolean isMax) {
		if(isMax){
			multiplier = -1;
		}
		arr = new ArrayList<E>();
	}
	/**
	 * returns the number of elements
	 * @return the number of elements
	 */
	public int size() {
		return arr.size();
	}
	/**
	 * adds the given element
	 * @param e added element
	 * @return always return true
	 */
	public boolean add(E e) {
		arr.add(e);
		int indexOfAdded = arr.size()-1;
		pushUp(indexOfAdded);
		return true;
	}
	/**
	 * swaps the elements at the given index with each other
	 * @param i index of the first element which is wanted to be swapped
	 * @param j index of the second element which is wanted to be swapped
	 */
	protected void swap(int i,int j) {
		E e = arr.get(i);
		arr.set(i,arr.get(j));
		arr.set(j,e);
	}
	/**
	 * removes the smallest element from the heap
	 * @return removed element
	 */
	public E remove() {
		if(arr.size() == 0) {
			return null;
		}
		E e = arr.get(0);
		arr.set(0,arr.get(arr.size()-1));
		arr.remove(arr.size()-1);
		
		putDown(0);
		
		return e;
		
	}
	/**
	 * returns the index of left child of given index
	 * @param i index whose left child's index is searched
	 * @return index of left child
	 */
	protected static int getLeft(int i) {
		return i*2+1;
	}
	/**
	 * returns the index of right child of given index
	 * @param i index whose right child's index is searched
	 * @return index of right child
	 */
	protected static int getRight(int i) {
		return i*2+2;
	}
	/**
	 * 
	 * @param e element searched 
	 * @return true if the element exists
	 */
	public boolean search(E e) {
		if(arr == null || arr.size() == 0) {
			return false;
		}
		return search(0,e);
		
	}
	/**
	 * In heap data structure every node has the least element of the its subtrees
	 * this algorithm benefits from this property and does not go any of the child of the given index if the given index has bigger element than searched
	 * helper method of the public search method
	 * @param index position of the node
	 * @param e element searched 
	 * @return true if the index has the given element , return false if the given index is not valid or given index has element which has bigger element than searched
	 */
	protected boolean search(int index,E e) {
		int result;
		if(index >= arr.size() ||((result = arr.get(index).compareTo(e))*multiplier > 0)) {
			return false;
		} else if(result == 0) {
			return true;
		} else {
			return (search(index*2+1,e) || search(index*2+2,e));
		}
	}
	/**
	 * returns the element at the given index
	 * @param i index of the wanted element
	 * @return the element at the given index
	 */
	protected E get(int i) {
		return arr.get(i);
	}
	/**
	 * returns true if the heap is empty
	 * @return
	 */
	public boolean isEmpty() {
		return arr.size() == 0;
	}
	/**
	 * adds elements of the given heap to itself
	 * @param heap
	 */
	public void merge(myHeap<E> heap) {
		int size = heap.size();
		for(int i=0;i<size;i++) {
			add(heap.get(i));
		}
	}
	/**
	 * returns string which contains element of heap
	 */
	public String toString() {
		return arr.toString();
	}
	/**
	 * returns the index of Ith largest element
	 * @param index order of the element
	 * @return index of the Ith largest element
	 */
	@SuppressWarnings("unchecked")
	protected int findIndexOfIthLargest(int index) {
		if(index<1 || index > arr.size())
			throw new IndexOutOfBoundsException();	
		ArrayList<E> temp = (ArrayList<E>)arr.clone();
		temp.sort(new myComporator());
		E e = temp.get(temp.size() - index);
		return arr.indexOf(e);
	}
	/**
	 * removes Ith largest element
	 * @param order order of the element
	 * @return returned element
	 * @throws IndexOutOfBoundsException if the given order does not implies any element 
	 */
	public E removeIthLargestElement(int order) throws IndexOutOfBoundsException {
		if(order > arr.size() || order < 1) {
			throw new IndexOutOfBoundsException();
		}
		int index = findIndexOfIthLargest(order);
		return remove(index);
	}
	/**
	 * return the element at the specified index
	 * @param index index of the element which is about to be removed
	 * @return return the element at the specified index
	 */
	protected E remove(int index) {
		if(index == arr.size()-1) {
			return arr.remove(arr.size()-1);
		}
		E e = arr.get(index);
		arr.set(index,arr.get(arr.size()-1));
		arr.remove(arr.size()-1);
		setOrder(index);
		return e;
	}
	/**
	 * removes the given value
	 * @param val element about to be removed
	 * @return index of the removed element, if it is not found returns -1
	 */
	protected int remove(E val) {
		int index = indexOf(val);
		if(index != -1)
			remove(index);
		return index;
	}
	/**
	 * returns index of the given value, if it does not exist ,returns -1
	 * @param val searched element
	 * @return index of the given value, if it does not exist ,returns -1
	 */
	protected int indexOf(E val) {
		return arr.indexOf(val);
	}
	/**
	 * if the heap does not obey the rules because of the element in the given index, change its position so that it obeys
	 * @param index index of the suspicious element 
	 */
	protected void setOrder(int index) {
		if(!isBiggerThanParent(index)) {
			pushUp(index);
		}
		else if(!isSmallerThanChild(index)) {
			putDown(index);
		}
	}
	/**
	 * returns true if the index is smaller than its children or false
	 * @param index index of the parent
	 * @return true if the index is smaller than its children or false
	 */
	private boolean isSmallerThanChild(int index) {
		if(!indexAvailable(index))
			throw new IndexOutOfBoundsException();
		if(!indexAvailable(getLeft(index))) {
			return true;
		}else if(arr.get(index).compareTo(arr.get(getLeft(index))) * multiplier>0) {
			return false;
			
		}else if(indexAvailable(getRight(index)) && arr.get(index).compareTo(arr.get(getRight(index))) * multiplier >0) {
			return false;
		}else {
			return true;
		}
		
	}
	/**
	 *  if the given index and its childrens relationship is not acceptable, changes their positions
	 * @param index index of the parent
	 */
	private void putDown(int index) {
		
		int left,right,curIndex;
		curIndex = index;
		int smallerChild;
		while(true) {
			left = getLeft(curIndex);
			right = getRight(curIndex);
			if(left >= arr.size()) {
				break;
			}
			
			smallerChild = left;
			if(right < arr.size() && arr.get(right).compareTo(arr.get(left)) * multiplier < 0) {
				smallerChild = right;
			}
			if(arr.get(curIndex).compareTo(arr.get(smallerChild))* multiplier > 0 ) {
				E x = arr.get(curIndex);
				arr.set(curIndex,arr.get(smallerChild));
				arr.set(smallerChild,x);
				curIndex = smallerChild;
			}else {
				break;
			}
		}
		
	}
	/**
	 * if the given index and its parents relationship is not acceptable, changes their positions
	 * @param index index of the child
	 */
	private void pushUp(int index) {
		int parentIndex = getParent(index);
		while(indexAvailable(parentIndex)) {
			if(arr.get(index).compareTo(arr.get(parentIndex))* multiplier < 0) {
				swap(index,parentIndex);
			}else {
				break;
			}
			index = parentIndex;
			parentIndex = getParent(index);
		}
		
	}
	/**
	 * returns true if the index is reachable
	 * @param i checked index
	 * @return true if the index is reachable
	 */
	protected boolean indexAvailable(int i) {
		return (i>= 0 && i<arr.size());
	}
	/**
	 * returns the index of parent of element at the specified index
	 * @param index index of child
	 * @return index of parent
	 */
	protected int getParent(int index) {
		return (index-1)/2;
	}
	/**
	 * returns the value of the root element
	 * @return the value of the root element
	 */
	E rootValue() {
	    return (arr.size() == 0) ? null : arr.get(0);
	}
	/**
	 * returns true if the relationship between parent and child is acceptable
	 * @param index child's index
	 * @return true if the relationship between parent and child is acceptable
	 */
	private boolean isBiggerThanParent(int index) {
		if(!indexAvailable(index)) {
			throw new IndexOutOfBoundsException();
		}
		int parentIndex;
		if(index == 0) {
			return true;
		}else if(indexAvailable(parentIndex = getParent(index))) {
			if(arr.get(index).compareTo(arr.get(parentIndex))* multiplier >= 0) {
				return true;
			}else {
				return false;
			}
		}else {
			return false;
		}
	}
	/**
	 * class to sort array according to its compare method
	 *
	 */
	class myComporator implements Comparator<E> {
		  
	    /**
	     * compares given values
	     * @return -1 if s1<s2 , 0 if s1 == s2 , 1 if s1>s2
	     * @param s1 first element to compare
	     * @param s2 element to compare with first element
	     */
	    public int compare(E s1, E s2)
	    {
	        return s1.compareTo(s2);
	    }
	}
	/**
	 * returns an iterator for heap class
	 * @return iterator for heap class
	 */
	@Override
	public Iterator<E> iterator() {
		return new Iter();
	}
	/**
	 * iterator implementation for Heap class
	 * @author Mustafa Karakas
	 *
	 */
	protected class Iter implements Iterator<E> {
		protected int index = 0;
		protected int lastReturned = -1;
		/**
		 * returns true if there is a next element
		 * @return true if there is a next element
		 */
		@Override
		public boolean hasNext() {
			return index != arr.size();
		}
		/**
		 * returns the next element
		 * @return the next element
		 */
		@Override
		public E next() {
			if(hasNext()) {
				lastReturned = index;
				return arr.get(index++);
			}
				
			throw new  NoSuchElementException();
		}
		
	}
	/**
	 * returns an iterator which has set method
	 * @return an iterator which has set method
	 */
	public HeapIterator heapIterator() {
		return new HeapIterator();
	}
	/**
	 * Iterator implementation that has set method
	 * @author Mustafa Karakas
	 *
	 */
	public class HeapIterator extends Iter{
		/**
		 * sets the last element returned to given value 
		 * @param e new value of the last returned element
		 */
		public void set(E e) {
			if(lastReturned == -1) {
				throw new NoSuchElementException();
			}
			arr.set(lastReturned,e);
			setOrder(lastReturned);
			
		}
	}
		/**
	 * searches for the value 
	 * @param val searched value
	 * @return found element
	 */
	public E find(E val) {
		return find(0,val);
	}
	
	/**
	 * searches for the value 
	 * @param index index of the node who searched if it has the value or its children have
	 * @param val searched value
	 * @return found element
	 */
	protected E find(int index, E val) {
		if(index>=arr.size()) {
			return null;
		}
		E element = arr.get(index);
		if(element.compareTo(val) == 0) {
			return element;
		}else if(element.compareTo(val)*multiplier < 0) {
			E found = find(getLeft(index),val);
			if(found != null) {
				return found;
			}else {
				return find(getRight(index),val);
			}
		}else {
			return null; 
		}
		
	}
	/**
	 * compares heaps
	 * @param o other heap
	 */
	@Override
	public int compareTo(myHeap<E> o) {
		if(arr.size() == 0 || o == null || o.size() == 0)
			throw new NoSuchElementException();
		return arr.get(0).compareTo(o.arr.get(0));
	}
	/**
	 * returns true if the heaps' root values are same
	 * @return true if the heaps' root values are same
	 */
	@Override
	public boolean equals(Object o) {
		if(o == null)
			return false;
		myHeap<E> h = (myHeap<E>) o;
		return arr.get(0).equals(h.arr.get(0));
	}
	public static void main(String[] args) {
		
		
	}
	
}

