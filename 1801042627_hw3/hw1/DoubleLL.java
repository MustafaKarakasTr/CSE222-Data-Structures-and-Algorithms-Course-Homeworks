package mustafa.karakas.hw1;

import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

/**
 * Implementation of Double Linked List
 * @author Mustafa Karakas
 *
 * @param <E>
 */
public class DoubleLL <E> implements Iterable<E>,Cloneable{
	/**
	 * The inner class which holds the data and the reference to the next node
	 * @author Mustafa
	 *
	 * @param <E> the type of data
	 */
	private static class Node<E>{
		private E data;
		
		private Node<E> next;
		private Node<E> prev;
		/**
		 * constructs the node with the specified position and data
		 * @param _data data of node
		 * @param _prev previous element of the node
		 * @param _next next element of the node
		 */
		private Node(E _data,Node<E> _prev,Node<E> _next) {
			data = _data;
			prev = _prev;
			next = _next;
			if(_prev != null)
				_prev.next = this;
			if(_next!=null)
				_next.prev = this;
		}
		/**
		 * adds the node which has the given element after the node
		 * @param _data data of new node
		 */
		private void addAfter(E _data) {
			Node<E> node = new Node<E>(_data, this, next);
			next = node;
			if(node.next!= null)
				node.next.prev = node;
		}
		
	}
	private int size = 0;
	private Node<E> head;
	private Node<E> tail;
	/**
	 * number of elements
	 * @return size of the linkedList
	 */
	public int getSize() {
		return size;
	}
	/**
	 * constructs a empty LinkedList
	 */
	public DoubleLL() {
		size = 0;
		head = tail = null;
	}
	/**
	 * Adds the element as the first element
	 * @param _data element to be inserted
	 */
	public void addFirst(E _data) {
		Node<E> node = new Node<E>(_data,null,head);
		head = node;
		if(tail == null) {
			tail = node;
		}
		size++;
	}
	/**
	 * Adds the element as the last element
	 * @param _data	element to be inserted
	 */
	public void addLast(E _data) {
		add(_data);
	}
	/**
	 * Makes list empty
	 */
	public void clear() {
		Node<E> nextNode;
		for(Node<E> toBeRemoved = head;
			toBeRemoved!=null;) 
		{
			nextNode = toBeRemoved.next;
			toBeRemoved.prev = null;
			toBeRemoved.next = null;
			toBeRemoved.data = null;
			toBeRemoved = nextNode;
		}
		head = tail= null;
        size = 0;
	}
	/**
	 * Appends the specified element to the end of this list.
	 * @param _data the element about to be added
	 * @return always returns true
	 */
	public boolean add(E _data) {
		//Node<E> node = new Node<E>(_data,null,null);
		if(head == null) {
			addFirst(_data);
			
		} else {
			Node<E> node = getNode(size-1);
			node.addAfter(_data);
			tail = node.next;
			size++;
		}
		return true;
	}
	/**
	 * returns the first element
	 * @return returns the first element
	 */
	public E element() {
		return (head == null)? null : head.data;
	}
	/**
	 * Inserts the specified element at the specified position in this list.
	 * @param index index at which the specified element is to be inserted
	 * @param _data element to be inserted
	 */
	public void add(int index,E _data) {
		if(head == null || index == 0)
			addFirst(_data);
		else if(index == size){
			add(_data);
		}
		else {
			Node<E> node = getNode(index-1);
			node.addAfter(_data);
			size++;
		}
	}
	/**
	 * returns a deep copy of this DoubleLL
	 */
	@Override
	public Object clone() {
		DoubleLL<E> temp = new DoubleLL<E>();
		for(E e:this) {
			temp.add(e);
		}
		return temp;
	}
	/**
	 * returns the index of given object
	 * @param o element to search for
	 * @return	index of object
	 */
	public int indexOf(Object o) {
		int i=0;
		if(o == null) {
			for(Node<E> node = head;node!=null;node=node.next){
				if(node.data == null) {
					return i;
				}
				i++;
			}	
		}else {
			for(Node<E> node = head;node!=null;node=node.next){
				if(node.data.equals(o)) {
					return i;
				}
				i++;
			}
		}
		return -1;	
	}
	/**
     * @return number of elements in the vector
     */
    public int size(){return size;}
	/**
	 * returns true if the list contains the given element 
	 * @param o element whose presence in the list is to be tested
	 * @return true if the list contaions the given element
	 */
	boolean contains(Object o) {
		return indexOf(o) != -1;
	}
	/**
	 * returns the elements of linkedList as a String
	 * @return elements of linkedList as a String
	 */
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		Node<E> node = head;
		for(int i=0;i<size;i++) {
			stringBuilder.append(node.data+" ");
			node = node.next;
		}
		stringBuilder.append("\n");
		return stringBuilder.toString();
	}
	/**
	 * returns the elements of linkedList as a String but backwards
	 * @return elements of linkedList as a String
	 */
	public String ReverseToString() {
		StringBuilder stringBuilder = new StringBuilder();
		Node<E> node = tail;
		for(int i=0;i<size;i++) {
			stringBuilder.append(node.data+" ");
			node = node.prev;
		}
		stringBuilder.append("\n");
		return stringBuilder.toString();
	}
	/**
	 * returns the node at the specified index
	 * @return the node at the specified index
	 */
	private Node<E> getNode(int index) {
		if(nodeAvailable(index)) {
			if(index == size()-1){
				
				return tail;
			}
			Node<E> node = head;
			for (int i = 0; i < index; i++) {
				node = node.next;
			}
			return node;
		}
		throw new IndexOutOfBoundsException();
	}
	/**
	 * checks if the given index is valid
	 * @param index index checked if it is valid
	 * @return returns true if the index is available
	 */
	private boolean nodeAvailable(int index) {
		return (index>=0 && size!=0 && index<size);
	}
	/**
	 * returns the element at the given index
	 * @param index index of wanted element
	 * @return element at the specified index
	 * @throws IndexOutOfBoundsException
	 */
	public E get(int index) {
		Node<E> node = getNode(index);
		if( node != null) {
			return node.data;
		}
		throw new IndexOutOfBoundsException();
	}
	/**
	 * removes the element at the specified index
	 * @param index index of the element to be removed
	 * @return removed element
	 * @throws IndexOutOfBoundsException
	 */
	public E remove(int index) throws IndexOutOfBoundsException{
		if(!nodeAvailable(index)) {
			throw new IndexOutOfBoundsException();
		}
		Node<E> node = getNode(index);
		if(node == head && node == tail) {
			head = tail = null;
			size--;
			return node.data;
		}else if(head == node) {
			head = head.next;
			head.prev = null;
			size--;
			return node.data;
		}else if(node == tail) {
			tail.prev.next = null;
			tail = tail.prev;
			size--;
			return node.data;
			
		}
		if(node != head)
			node.prev.next = node.next;
		if(node != tail) {
			node.next.prev = node.prev;
		}
		size--;
		return node.data;
		
		
	}
	/**
	 * removes the specified object from the list
	 * @param o the object which is wanted to be removed
	 * @return returned object
	 */
	public E remove(Object o) {
		int i=0;
		if(o == null) {
			for(Node<E> node = head;node != null;node= node.next){
				if(node.data == o) {
					return remove(i);
				}
				i++;
			}
		}else {
			for(Node<E> node = head;node != null;node= node.next){
				if(node.data.equals(o)) {
					return remove(i);
				}
				i++;
			}
		}
		return null;
	}
	/**
	 * Iterator implementation for DoubleLL
	 */
	private class Iter implements Iterator<E>{
		protected int nextIndex;
		protected Node<E> next;
		protected Node<E> lastReturned=null;
		/**
		 * constructs the iterator , it starts from the head of list
		 */
		private Iter() {
			next = head;
			nextIndex = 0;
		}
		/**
		 * removes the last returned element
		 */
		public void remove() {
			// TODO Auto-generated method stub
			if(lastReturned == null)
				throw new IllegalStateException();
			Node<E> lastNext = lastReturned.next;
			unlink(lastReturned);
			if(next == lastReturned) {
				next = lastNext;
			}else {
				nextIndex--;
			}
			lastReturned = null;
			
		}
		/*
		/**
		 * returns true if the iterator has next element
		 */
		@Override
		public boolean hasNext() {
			
			return nextIndex<size;
		}
		/**
		 * makes iterator move forward
		 */
		@Override
		public E next() {
			// TODO Auto-generated method stub
			if(hasNext()) {
				lastReturned = next;
				next = next.next;
				nextIndex++;
				return lastReturned.data;
			}
			throw new NoSuchElementException();
		}
		
		
	}
	/**
	 * ListIterator implementation for DoubleLL
	 */
	private class ListIter extends Iter implements ListIterator<E>{
		/**
		 * constructs iterator , starts from the head
		 */
		private ListIter() {
			next = head;
			nextIndex = 0;
		}
		/**
		 * returns true if the iterator has previous
		 * @return  true if the iterator has previous
		 */
		@Override
		public boolean hasPrevious() {
			
			return nextIndex>0;
		}
		/**
		 * makes iterator move backwards
		 * @return previous element
		 */
		@Override
		public E previous() {
			if(!hasPrevious()) {
				throw new NoSuchElementException();
			}
			lastReturned = next =(next == null) ? tail: next.prev;
			nextIndex--;
			return lastReturned.data;
		}
		/**
		 * returns the next index
		 * @return the next index
		 */
		@Override
		public int nextIndex() {
			// TODO Auto-generated method stub
			return nextIndex;
		}
		/**
		 * returns the previous index
		 * @return the previous index
		 */
		@Override
		public int previousIndex() {
			// TODO Auto-generated method stub
			return nextIndex-1;
		}
		/**
		 * changes the last returned element
		 * @param e new value of the last returned element 
		 */
		@Override
		public void set(E e) {
			if(lastReturned == null)
				throw new IllegalStateException();
			lastReturned.data = e;
			
		}
		/**
		 * adds the given element to the list
		 * @param e element which is wanted to be added
		 */
		@Override
		public void add(E e) {
			lastReturned = null;
			if(next == null) {
				linkLast(e);
			}else {
				linkBefore(e,next);
			}
			nextIndex++;
		}
		
	}
	/**
	 * links the given element before the given node
	 * @param e new element
	 * @param succ the node which will have the new element as its previous
	 */
	private void linkBefore(E e,Node<E> succ) {
		Node<E> pred = succ.prev;
		Node<E> node = new Node<E>(e,pred,succ);
		succ.prev = node;
		if(pred == null) {
			head = node;	
		}else {
			pred.next = node;
		}
		size++;
	}
	/**
	 * adds the element as a tail
	 * @param e new tail value
	 */
	private void linkLast(E e) {
		Node<E> l = tail;
		Node<E> node = new Node<E>(e,l,null);
		
		tail = node;
		if(head== null) {
			head = tail;
		}
		else {
			l.next = node;
		}
		size++;
	}
	/**
	 * removes the first element
	 */
	private void removeFirst() {
		if(head == null)
			return;
		if(head == tail) {
			head = tail = null;
		}
		else {
			head = head.next;
			head.prev.next = null;
			head.prev = null;
			
		}
		size--;
	}
	/**
	 * removes the last element
	 */
	private void removeLast() {
		unlink(tail);
	}
	/**
	 * removes the given node from list
	 * @param node node about to be removed
	 */
	private void unlink(Node<E> node) {
		final Node<E> prevNode = node.prev;
		final Node<E> nextNode = node.next;
		final E element = node.data;
		if(prevNode == null) {
			head = nextNode;
		}else{
			prevNode.next = nextNode;
			node.prev = null;
		}
		if(nextNode == null) {
			tail = prevNode;
		}
		else {
			nextNode.prev = prevNode;
			node.next = null;
		}
		node.data = null;
		size--;
		
		
	}
	/**
	 * returns the Iterator
	 */
	public Iterator<E> iterator(){
		return new Iter();
	}
	/**
	 * returns the ListIterator which starts from the initial position
	 */
	public ListIterator<E> listIterator() {
		// TODO Auto-generated method stub
		return new ListIter();
	}
	/**
	 * returns the iterator which starts from given index
	 * @param index start index
	 * @return ListIterator
	 */
	public ListIterator<E> iterator(int index) throws IndexOutOfBoundsException {
		if(nodeAvailable(index)){
			ListIterator<E> temp = listIterator();
			int i=0;
			while(temp.hasNext() && i!=index) {
				i++;
				temp.next();
			}
			return temp;
		}
		throw new IndexOutOfBoundsException();
	}
	
	
}
