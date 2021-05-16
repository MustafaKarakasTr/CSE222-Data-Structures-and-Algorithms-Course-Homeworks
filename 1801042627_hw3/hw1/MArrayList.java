package mustafa.karakas.hw1;


import java.util.Arrays;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;


/**
 * ArrayList implementation
 * @author Mustafa Karakas
 *
 * @param <E> type of the elements which are wanted to be stored
 */
public class MArrayList<E> implements Iterable<E> {
	private E []theData;
	private int capacity;
	private int size;
	private static final int INITIAL_CAPACITY=10;
	/**
	 * constructs empty ArrayList
	 */
	@SuppressWarnings("unchecked")
	MArrayList(){
		capacity=INITIAL_CAPACITY;
		theData=(E[]) new Object[capacity];
		size = 0;
	}
	/**
	 * adds the element at the end
	 * @param anEntry value to be inserted
	 * @return always returns true
	 */
	public boolean add(E anEntry) {
		if(size==capacity)
			reallocate();
		
		theData[size++]=anEntry;
		return true;
	}
	/**
	 * adds the element at the specified index
	 * @param index index of the element 
	 * @param anEntry value to be inserted
	 */
	public void add(int index, E anEntry) {
		if(index<0 || index>size)
			throw new ArrayIndexOutOfBoundsException(index);
		if(size==capacity)
			reallocate();
		for(int i=size;i>index;i--)
			theData[i]=theData[i-1];
		theData[index]=anEntry;
		size++;
	}
	/**
	 * returns the element at the specified index
	 * @param index index of the element to be returned
	 * @return index of the element 
	 */
	public E get(int index) {
		if(index<0 || index>=size)
			throw new ArrayIndexOutOfBoundsException(index);
		return theData[index];
	}
	/**
	 * 
	 * @param index  index of element to be changed
	 * @param newValue new value of the elmenent at the specified element
	 * @return old value
	 */
	public E set(int index, E newValue) {
		if(index<0 || index>=size)
			throw new ArrayIndexOutOfBoundsException(index);
		E oldValue=theData[index];
		theData[index]=newValue;
		return oldValue;
	}
	
	/**
	 * makes array bigger
	 */
	private void reallocate() {
		capacity*=2;
		theData=Arrays.copyOf(theData, capacity);
		System.out.println("Reallocate "+theData.length+" "+size);
	}
	/**
	 * returns the size of the list
	 * @return the size of the list
	 */
	public int size() {
		return size;
	}
	/**
	 * shows if the array is empty
	 * @return	true if the array is empty
	 */
	public boolean isEmpty() {
	        return size == 0;
	}
	/**
	 * returns the elements as a string
	 */
	public String toString() {
		StringBuilder s = new StringBuilder();
		s.append("[ ");
		for(int i=0;i<size();i++) {
			s.append(get(i)+" ");
		}
		s.append("]");
		return s.toString();
	}
	/**
	 * returns the index of given object
	 * @param o element to search for
	 * @return	index of object
	 */
	public int indexOf(Object o) {
		if(o == null){
			for(int i = 0;i<size();i++){
				if(get(i) == null) {
					return i;
				}
			}	
		}else {
			for(int i = 0;i<size();i++){
				if(get(i).equals(o)) {
					return i;
				}				
			}
		}
		return -1;	
	}
	/**
	 * returns true if the list contains the given element 
	 * @param o element whose presence in the list is to be tested
	 * @return true if the list contaions the given element
	 */
	boolean contains(Object o) {
		return indexOf(o) != -1;
	}
	/**
     * removes the given element 
     * @param e element to be removed
     * @return the removed element
     */
    public boolean remove(Object o){
        if(o!=null) {
        	for(int i=0;i<size;i++){
	            if(o.equals(theData[i])){
	                remove(i);
	                return true;
	            }
        	}
        }else {
        	for(int i=0;i<size;i++){
        		if(theData[i] == o) {
        			remove(i);
        			return true;
        		}
        	}
        }
        return false;
    }
    /**
	 * removes the element at the specified index
	 * @param index index of element to be removed
	 * @return element removed
	 */
	public E remove(int index) {
		if(index<0 || index>=size)
			throw new ArrayIndexOutOfBoundsException(index);
		E returnValue=theData[index];
		for(int i=index+1;i<size;i++) 
			theData[i-1]=theData[i];
		size--;
		if(size<capacity/4) {
			shrink();
		}
		return returnValue;		
		
	}
	/**
	 * makes array smaller
	 */
	private void shrink() {
		capacity/=2;
		theData=Arrays.copyOf(theData, capacity);
		//System.out.println("Shrink "+theData.length+" "+size);
		
	}
	/**
	 * makes arrayList empty
	 */
	 public void clear() {
		capacity=INITIAL_CAPACITY;
		theData=(E[]) new Object[capacity];
		size = 0;
	 }
	 /**
	  * returns Iterator
	  * @return Iterator
	  */
	 public Iterator<E> iterator(){
		 return new Iter();
	 }
	 /**
	  * Implementation of Iterator in MArrayList
	  * @author Mustafa Karakas
	  *
	  */
	  class Iter implements Iterator<E>{
		 protected int curr=0;
		 protected int lastRtr = -1;
		/**
		 * returns true if the next element exists
		 * @return true if the next element exists
		 */
		 @Override
		public boolean hasNext() {
			return curr!=size();
		}
		/**
		 * returns the next element if exists
		 * @return the next element if exists
		 */
		@Override
		public E next() {
			if(!hasNext()) {
				throw new NoSuchElementException();
			}
			lastRtr = curr;
			return theData[curr++];
			
		}
		/**
		 * removes the last returned element if exists
		 */
		@Override
		public void remove() {
			if(lastRtr<0 || lastRtr>=size) {
				throw new NoSuchElementException();
			}
			MArrayList.this.remove(lastRtr);
			lastRtr = -1;
			curr--;
		}
	 }
	 /**
	  * returns a ListIterator which starts from the index 0
	  * @return ListIterator which starts from the index 0
	  */
	 public ListIterator<E> listIterator(){
		 return new listIter(0);
	 }
	 /**
	  * returns a ListIterator which starts from the given index
	  * @param index index of returned ListIterator
	  * @return ListIterator which starts from the given index
	  */
	 public ListIterator<E> listIterator(int index){
		 return new listIter(index);
	 }
	 /**
	  * ListIterator implementation of MArrayList
	  * @author Mustafa
	  *
	  */
	  private class listIter extends Iter implements ListIterator<E>{
		/**
		 * starts the iterator from where the user wants
		 * @param index
		 */
		 public listIter(int index) {
			if(index<0 || index>size) {
				throw new NoSuchElementException();
			}
			curr = index;
		}
		 
		/**
		 * returns if the iterator has previous
		 * @return if the iterator has previous
		 */
		 @Override
		public boolean hasPrevious() {
			return curr != 0;
		}

		/**
		 * returns previous element
		 * @return previous element
		 */
		 @Override
		public E previous() {
			if(!hasPrevious()) {
				throw new NoSuchElementException();
			}
			lastRtr = curr = curr-1;
			return theData[lastRtr];
		}

		/**
		 * returns the next element's index
		 * @return the next element's index
		 */
		 @Override
		public int nextIndex() {
			// TODO Auto-generated method stub
			return curr;
		}

		/**
		 * returns the previous element's index
		 * @return the previous element's index
		 */
		 @Override
		public int previousIndex() {
			// TODO Auto-generated method stub
			return curr-1;
		}

		/**
		 * changes the last returned element
		 * @param e the new value of last returned element
		 */
		 @Override
		public void set(E e) {
			if(lastRtr == -1) {
				throw new IllegalStateException();
			}
			MArrayList.this.set(lastRtr,e);
			
		}
		
		/**
		 * adds a new element to the list
		 * @param new element inserted
		 */
		 @Override
		public void add(E e) {
			MArrayList.this.add(curr++,e);
			lastRtr = -1;
			
		}
		 
	 }
	 
}