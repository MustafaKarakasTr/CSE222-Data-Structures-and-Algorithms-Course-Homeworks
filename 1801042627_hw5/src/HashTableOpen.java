package Part2;

import java.util.TreeSet;


/**
 * Coalesced hashing technique uses the concept of Open Addressing to
 *	find first empty place for colliding element by using the quadratic probing and the concept of
 *	Separate Chaining to link the colliding elements to each other through pointers . 
 *  The deletion of a key is performed by linking its next entry to the entry that points the
 *	deleted key by replacing deleted entry by the next entry. 
 * @author Mustafa Karakas
 *
 * @param <K> Key
 * @param <V> Value
 */
public class HashTableOpen <K,V> implements KWHashMap<K, V>  {
	/**
	 * inner class which can hold key and values for hashmap
	 * @param <K> key 
	 * @param <V> value
	 */
	private Entry<K,V>[] table;
	private static final int INITIAL_SIZE = 11;
	private int size;
	private static final double LOAD_THRESHOLD = 0.5;
	private final Entry<K,V> DELETED = new Entry(null,null);
	/**
	 * inner class which can hold key and values for hashmap
	 * @param <K> key 
	 * @param <V> value
	 */
	private static class Entry<K, V> {
		/** The key */
		private  K key;
		/** The value */
		private V value;
		/** The next*/
		private int next = -1;
		/** Creates a new key‐value pair.
		@param key The key
		@param value The value
		*/
		public Entry(K key, V value) {
			this.key = key;
			this.value = value;
			next = -1;
		}
		/** Creates a new key‐value pair with next value.
		@param key The key
		@param value The value
		@param next next index
		*/
		public Entry(K key, V value,int next) {
			this.key = key;
			this.value = value;
			this.next = next;
		}
		/** Retrieves the key.
		@return The key
		*/
		public K getKey() {
			return key;
		}
		/**
		 * sets the key
		 * @param key new value of the key
		 */
		public void setKey(K key) {
			this.key = key;
		}
		/** Retrieves the value.
		@return The value
		*/
		public V getValue() {
			return value;
		}
		/**
		 * sets the next index
		 * @param newNext new next index
		 */
		public void setNext(int newNext) {
			int old = next;
			next = newNext;
		}
		/**
		 * returns the next index
		 * @return the next index
		 */
		public int getNext() {
			return next;
		}
		/** Sets the value.
		@param val The new value
		@return The old value
		*/
		public V setValue(V val) {
			V oldVal = value;
			value = val;
			return oldVal;
		}
		/**
		 * checks if the key of entries are equal
		 * @param obj other element
		 * @return returns true if the elements' key are equal
		 */
		@Override
		public boolean equals(Object obj) {
			return key.equals(((K)obj));
			
		}
	}
	/**
	 * constructs empty hash table
	 */
	public HashTableOpen() {
		int size = 0;
		table= new Entry[INITIAL_SIZE];
	}
	/**
	 * returns the value which represented by given key
	 * @return the value which represented by given key
	 */
	@Override
	public V get(Object key) {
		int index = indexOf(key);
		if(index == -1)
			return null;
		return table[index].getValue();
	}
	/**
	 * returns true if the hashmap is empty
	 * @return true if the hashmap is empty
	 */
	@Override
	public boolean isEmpty() {
		return (size==0);
	}
	/**
	 * increments the given index by the given parameter
	 * @param index incremented index
	 * @param incrementBy index will be incremented by this value
	 * @return incremented index
	 */
	private int incrementIndex(int index,int incrementBy) {
		index+=incrementBy;
		if(index>=table.length) {
			index = index%table.length;
			if(index<0)
				index += table.length;
		}
		return index;
	}
	/**
	 * returns the index of the given key , if it does not exist returns -1
	 * @param key key whose index is searched
	 * @return the index of the given key , if it does not exist returns -1
	 */
	public int indexOf(Object key) {
		int i = find(key,false);
		if(table[i] == null || table[i] == DELETED)
			return -1;
		if(table[i].getKey().equals(key))
			return i;
		return -1;
	}
	/**
	 * removes the entry which has the given key
	 * @param key whose entry wanted to be removed
	 * @return value of the removed key
	 */
	@Override
	public V remove(Object key) {
		// TODO Auto-generated method stub
		int index = indexOf(key);
		if(index == -1)
			return null;
		Entry<K, V> ent = remove(key,index);
		doNotPointToDELETED(key);
		return ( ent == null) ? null :ent.getValue();
	}
	/**
	 * when one element is removed , DELETED is placed in the map, this function changes the next value into -1 whose next is index of DELETED
	 * @param key deleted key
	 */
	private void doNotPointToDELETED(Object key) {
		int index = getIndex(key,table.length);
		int next = table[index].getNext();
		while(index != -1 && next != -1 ) {
			
			if(table[next] == DELETED) {
				table[index].setNext(-1);
				return;
			}
			index = next;
			next = table[index].getNext();
		}
	}
	/**
	 * helper function of the remove function
	 * @param key key about to be removed
	 * @param index possible index of the wanted key
	 * @return removed entry, if it does not exist, returns null
	 */
	private Entry<K, V> remove(Object key, int index) {
		if(index == -1 || table[index] == null || table[index] == DELETED) {
			return null;
		}
		
		Entry<K, V> old = table[index];
		Entry<K, V> ent = remove(key,table[index].getNext());
		
		if(ent == null) {
			table[index] = DELETED;
			size--;
		}else {	
			int oldNext = table[index].getNext();
			table[index] = ent;
			table[index].setNext(oldNext);
		}
		return old;
	}
	/**
	 * finds the given key in the map
	 * @param key wanted key
	 * @param setNext if it is true when we find the place to put given key, it sets the next of the previous element to found index
	 * @return found index
	 */
	private int find(Object key,boolean setNext) {
		int index = getIndex(key,table.length);
		if(table[index] != null) {
			K key2 = table[index].getKey();
			if(key2!= null && key2.equals(key))
				return index;
		}
		int prevIndex = index;
		int i=-1;
		//System.out.println("for value :"+ key);
		for(;table[index] == DELETED || (table[index] != null && !table[index].equals(key));i+=2,index = incrementIndex(index, i)) 
		{ 
			if(table[index] == DELETED && setNext)
				return index;
			if(table[index] != DELETED && table[prevIndex] != DELETED &&(table[index].getKey().hashCode()%table.length)==(table[prevIndex].getKey().hashCode()%table.length))
				prevIndex = index;
			else if((table[index] != DELETED) &&  table[prevIndex] != DELETED &&(table[prevIndex].getNext() != -1 || (table[index].getKey().hashCode()%table.length)==(table[prevIndex].getKey().hashCode()%table.length))){
				setNext = false;
			}
			
		}
		if(setNext && prevIndex != index &&(table[prevIndex] != DELETED&& (key.hashCode()%table.length)==(table[prevIndex].getKey().hashCode()%table.length))) {
			table[prevIndex].setNext(index);
		}
		
		return index;
	}
	/**
	 * makes hashmap bigger so that collisions handled
	 */
	private void rehash() {
		Entry<K,V>[] oldTable = table;
		table = new Entry[oldTable.length*2+1];
		size = 0;
		for(Entry<K,V> ent : oldTable) {
			if(ent != null && ent != DELETED) {
				put(ent.getKey(),ent.getValue());
			}
		}
	}
	/**
	 * puts the given key with the specified value to map, if the key exists, value is upgraded
	 * @param key key of the entry
	 * @param value value of the entry
	 * @return returns the old value of the key, if the key did not exist , returns null
	 */
	@Override
	public V put(K key, V value) {
		int index = find(key,true);
		Entry<K,V> old = table[index];
		if(old != null && old != DELETED) {
			V temp = old.getValue();
			old.setValue(value);
			return temp;
		}else {
			table[index] = new Entry<K,V>(key,value);
			size++;
			if(size > table.length * LOAD_THRESHOLD) {
				rehash();
			}
			return null;
		}	
	}

	/**
	 * returns the size of the map
	 */
	@Override
	public int size() {
		return size;
	}
	/**
	 * returns the index of the key
	 * @param key key whose (possible) index is wanted
	 * @param table2 length of the table which returned index belongs to
	 * @return returns the index of the key
	 */
	private int getIndex(Object key ,int length) {
		int index = key.hashCode() ;
		index = index % length;
		if(index<0) {
			index += length;
		}
		return (index);
	}
	/**
	 * returns the string which contains structure of the hashtable
	 * @return the string which contains structure of the hashtable
	 */
	@Override
	public String toString() {
		StringBuilder s = new StringBuilder();
		int index = -1;
		s.append("index \t value \t next\n");
		for(int i=0;i<table.length;i++) {
			s.append(i+" \t ");
			if(table[i] == DELETED) {
				s.append("deleted " +"\t" +table[i].getNext()+"\n");
			}
			else if(table[i] != null)
				s.append(table[i].getValue() +"\t "+table[i].getNext()+"\n");
			else {
				s.append("null\n");
			}
		}
		return s.toString();
		
	}
	
	
}
