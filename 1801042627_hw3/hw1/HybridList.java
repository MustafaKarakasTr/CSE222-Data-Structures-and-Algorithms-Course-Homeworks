package mustafa.karakas.hw1;

import java.util.ArrayList;
import java.util.Iterator;

import java.util.ListIterator;

import mustafa.karakas.hw1.MArrayList;


/**
 * Has DoubleLL as component which has MArrayList as an element
 * @author Mustafa Karakas
 *
 * @param <E> type of element wanted to be stored
 */
public class HybridList<E> implements Iterable<E>{
	private DoubleLL<MArrayList<E>> list;
	private static final int  MAX_NUMBER = 5;
	private int size;
	/**
	 * constructs an empty HybridList
	 */
	public HybridList() {
		list = new DoubleLL<MArrayList<E>>();
		size = 0;
	}
	/**
	 * adds an element to the end of the list
	 * @param e element about to be added
	 * @return always returns true
	 */
	public boolean add(E e) {
		if(size == 0 || getLastNodeOfDLL().size() == MAX_NUMBER) {
			addNewArrayList();
		}
		getLastNodeOfDLL().add(e);
		size++;
		return true;
	}
	/**
	 * adds the element to the specified index but recursively
	 * @param index index to add element
	 * @param e elemen about to be added
	 */
	public void addRecursive(int index,E e) {
		//recursive function, I know that it is not the best function can be written but I like it
		if(validIndex(index) || index == size) {
			if(size == 0 || index == size) {
				add(e);
			}else {
				Iterator<MArrayList<E>> IterHasTheElementWhichHasGivenIndex = getListIterator(index);
				MArrayList<E> arrHastheElement = IterHasTheElementWhichHasGivenIndex.next();
				E element = arrHastheElement.get(indexInArrayList(index));
				arrHastheElement.set(indexInArrayList(index),e);
				add(++index,element);
			}
		}else {
			throw new IndexOutOfBoundsException();
		}
	}
	/**
	 * adds the given element to the given index
	 * @param index index to add given element
	 * @param e new value to be added
	 */
	public void add(int index,E e) {
		if(validIndex(index) || index == size) {
			if(size == 0 || index == size) {
				add(e);
			}else {
				E deleted=e;
				Iterator<MArrayList<E>> IterHasTheElementWhichHasGivenIndex = getListIterator(index);
				while(IterHasTheElementWhichHasGivenIndex.hasNext()) {
					MArrayList<E> arrHastheElement = IterHasTheElementWhichHasGivenIndex.next();
					for(int i=indexInArrayList(index);i<MAX_NUMBER && i<arrHastheElement.size();i++) {
						deleted = arrHastheElement.get(i);
						arrHastheElement.set(i,e);
						e=deleted;						
					}
				}
				add(deleted);
			}
		}else {
			throw new IndexOutOfBoundsException();
		}
	}
	/**
	 * adds a new node to the list
	 */
	private void addNewArrayList() {
		MArrayList<E> temp = new MArrayList<E>();
		list.add(temp);
	}
	/**
	 * returns the size of list
	 * @return the size of list
	 */
	private int getListSize() {
		return list.getSize();
	}
	/**
	 * returns the last MArrayList in the list
	 * @return
	 */
	private MArrayList<E> getLastNodeOfDLL() {
		return list.get(getListSize()-1);
		
	}
	/**
	 * returns the arraylist which has the element which has specified index
	 * @param index index which belongs to the wanted MArrayList
	 * @return the MArrayList which has the element which has the given index
	 */
	private MArrayList<E> getArray(int index) {
		int i=0;
		if(validIndex(index)) {
			for(MArrayList<E> temp : list) {
				i+=MAX_NUMBER;
				if(i>index) {
					return temp;
				}
			}
		}
		throw new IndexOutOfBoundsException();
	}
	/**
	 * returns the string which has elements
	 */
	@Override
	public String toString() {
		StringBuilder s = new StringBuilder();
		s.append("[");
		for(MArrayList<E> arr:list) {
            
			s.append(arr.toString());
		}
		s.append("]");
		return s.toString();
	}
	/**
	 * removes the object from the list
	 * @param o object about to be removed
	 * @return if the object removed returns false
	 */
	public boolean remove(Object o) {
		Iterator<MArrayList<E>> iter = list.iterator();
		while(iter.hasNext()) {
			MArrayList<E> obj = iter.next();
			if(obj.remove(o)) {
				fillTheGap(iter,obj);
				size--;
				return true;
			}
		}
		return false;
	}
	/**
	 * makes all nodes has the MAX_NUMBER of elements except the last one
	 * @param iter Iterator about to point to the node which is following to node which has less elements than MAX_NUMBER
	 * @param lastReturned the array which has the less element than
	 */
	private void fillTheGap(Iterator<MArrayList<E>> iter , MArrayList<E> lastReturned){
		MArrayList<E> nextArr=lastReturned; 
		while(iter.hasNext()) {
			nextArr = iter.next();
			if(lastReturned.size() < MAX_NUMBER) {
				lastReturned.add(nextArr.remove(0)); // if the next node exist that means it has an object at its 0. index ,
													// so it will take next's first element and will add it to the current arr
			}
			lastReturned = nextArr;
		}
		if(nextArr.size()==0) { // if the parameter 'arr' is the last array in the linked list nextArr is equal to it because it does not enter to the while loop
			iter.remove();
		}
	}
	/**
	 * returns the listIterator which points to the node which has the element which has the given index
	 * @param index index of element
	 * @return the listIterator which points to the node which has the element which has the given index
	 */
	private ListIterator<MArrayList<E>> getListIterator(int index){
		int indexOfNode = index/MAX_NUMBER; // if index == 6 and MAX_NUMBER is 5 then [1] node should be returned
		ListIterator<MArrayList<E>> temp = list.iterator(indexOfNode);
		return temp;
	}
	/**
	 * removes the element which has the given index
	 * @param index index of the element about to be removed
	 * @return deleted element
	 */
	public E remove(int index) {
		if(validIndex(index)) {
			ListIterator<MArrayList<E>> iter = getListIterator(index);
			MArrayList<E> arr = iter.next(); // the array which has the element about to be removed is returned
			E e= arr.remove(indexInArrayList(index)); // the element at the index%MAX_NUMBER index for ex: if index == 9 and MAX_NUMBER is 5 => second node's fourth element has the wanted element
			fillTheGap(iter, arr); // shifts the elements so that all the nodes except the last one has MAX_NUMBER of elements and the order is maintained
			size--;
			return e;
		}else {
			throw new IndexOutOfBoundsException();
		}
		
	}
	/**
	 * returns true if the given index is available
	 * @param index index which is queried if it is available or not
	 * @return true if the given index is available
	 */
	private boolean validIndex(int index) {
		return(index>=0 && index<size);
	}
	/**
	 * returns the size of the list
	 * @return the size of the list
	 */
	public int size() {
		return size;
	}
	/**
	 * makes Hybrid List empty
	 */
	public void clear() {
		list = new DoubleLL<MArrayList<E>>();
		size = 0;
	}
	/**
	 * changes the element at the given index
	 * @param index index of the element about to be changed
	 * @param e new value of the element
	 * @throws IndexOutOfBoundsException if the index is not valid
	 */
	public void set(int index,E e) throws IndexOutOfBoundsException{
		if(validIndex(index)) {
			Iterator <MArrayList<E>> iter = getListIterator(index);	
			MArrayList<E> arr = iter.next();
			arr.set(indexInArrayList(index),e);
		}
		else
			throw new IndexOutOfBoundsException();
	}
	/**
	 * returns the index of the element in the Arraylist which the element belongs to
	 * @param index index of element at the hybridList
	 * @return the index of the element in the Arraylist which the element belongs to
	 */
	private int indexInArrayList(int index) {
		return index%MAX_NUMBER;
	}
	/**
	 * returns the element at the given index
	 * @param index index of the element wanted
	 * @return the element at the given index
	 * @throws IndexOutOfBoundsException if the index is not valid
	 */
	public E get(int index) throws IndexOutOfBoundsException {
		if(validIndex(index)) {
			Iterator <MArrayList<E>> iter = getListIterator(index);	
			MArrayList<E> arr = iter.next();
			return arr.get(indexInArrayList(index));
			//arr.set(indexInArrayList(index),e);
		}else {
			throw new IndexOutOfBoundsException();
		}
	}
	/**
	 * returns the index of the given element
	 * @param e element whose index is being searched
	 * @return the index of the given element
	 */
	public int indexOf(E e) {
		int i=0;
		for(MArrayList<E> arr:list) {
			int index = arr.indexOf(e); 
			if(index != -1) {
				return i*MAX_NUMBER+index;
			}
			i++;
		}
		return -1;
	}
	/**
	 * returns true if the given element in the list
	 * @param e value searched
	 * @return true if the given element in the list
	 */
	public boolean contains(E e) {
		return (indexOf(e)!=-1);
	}
	/**
	 * iterator implementation for HybridList
	 */
	private class Iter implements Iterator<E>{
		private int curr = 0;
		private int lastRtr = -1;
		private ListIterator<E> ArrayIterator = null;
		private ListIterator<MArrayList<E>> DLLIterator;
		private MArrayList<E> arrPointed;
		/**
		 * constructs the iterator, it starts from the head of list
		 */
		private Iter() {
			curr = 0;
			lastRtr = -1;
			DLLIterator = list.listIterator();
			if(DLLIterator.hasNext()){
				
				arrPointed = DLLIterator.next();
				ArrayIterator = arrPointed.listIterator();
			}
		}
		/**
		 * returns true if the next item exists
		 * @return true if the next item exists
		 */
		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub
			return (ArrayIterator!= null &&(ArrayIterator.hasNext() || DLLIterator.hasNext()));
		}
		/**
		 * makes iterator go forward
		 * @return the element which has just visited
		 */
		@Override
		public E next() {
			// TODO Auto-generated method stub
			if(!hasNext()) {
				throw new IllegalStateException();
			}
			lastRtr = curr;
			curr++;
			//System.out.println("CURR VE lastRtr: "+curr+ " "+lastRtr);
			if(ArrayIterator != null &&(ArrayIterator.hasNext())) {
				return ArrayIterator.next();
			//}else if(DLLIterator.hasNext()){
			}else{ // if the last arrayList does not have any element, hasNext is not true so it will not come here 
				arrPointed =  DLLIterator.next();
				ArrayIterator = arrPointed.listIterator();
				return ArrayIterator.next(); // always has at least 1 element 
			}
			
		}
		/**
		 * removes the last returned element
		 * @throws if there is no last returned element, throws IllegalStateException
		 */
		@Override
		public void remove() {
			if(lastRtr == -1 || ArrayIterator == null) {
				throw new IllegalStateException();
			}
			System.out.println("Index Of deleted element "+lastRtr);
			//HybridList.this.remove(lastRtr);
			ArrayIterator.remove();
			if(DLLIterator.hasNext())
				fillTheGap(list.iterator(DLLIterator.nextIndex()),arrPointed);
			if(getLastNodeOfDLL().size() == 0) {
				list.remove(list.getSize()-1);
			}
			size--;
			lastRtr = -1;
			curr--;
		}
		
	}
	/**
	 * returns the iterator which starts from the head of the list
	 */
	@Override
	public Iterator<E> iterator() {
		// TODO Auto-generated method stub
		return new Iter();
	}
	
}
