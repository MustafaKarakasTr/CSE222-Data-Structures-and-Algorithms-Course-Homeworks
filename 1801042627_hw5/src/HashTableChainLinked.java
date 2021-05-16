package Part2;

import java.util.Iterator;
import java.util.LinkedList;
/**
 * Hashtable implementation with chaining
 * @param <K> Key
 * @param <V> Value
 */
public class HashTableChainLinked<K, V extends Comparable<V> > implements KWHashMap<K, V> {
	private LinkedList<Entry<K, V>>[] table;
	private static final int INITIAL_SIZE = 11;
	private int size;
	private static final double LOAD_THRESHOLD = 3.0; 
	/**
	 * constructs the empty hashmap
	 */
	@SuppressWarnings("unchecked")
	public HashTableChainLinked() {
		//table = (LinkedList<Entry<K,V>>[]) new Object[INITIAL_SIZE];
		table = new LinkedList[INITIAL_SIZE];
		size = 0;
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
	 * returns the value pointed by the given key
	 * @param key wanted value's key
	 * @return value pointed by the key
	 */
	@Override
	public V get(Object key) {
		int index = getIndex(key,table.length);
		if(table[index] == null)
			return null;
		for(Entry<K, V> entry : table[index]) {
			if(entry != null) {
				if(entry.getKey().equals(key)) {
					return entry.getValue();
				}	
			}
			
		}
		return null;
	}
	/**
	 * returns true if the map is empty
	 * @return true if the map is empty
	 */
	@Override
	public boolean isEmpty() {
		return (size == 0);
	}
	/**
	 * if the key does not exist adds the entry, otherwise changes the current value
	 * @param key key of the value
	 * @param value value of the entry which is about to be added
	 * @return if the key exists then returns the old value which is pointed by the key, otherwise returns the added value
	 */
	@Override
	public V put(K key, V value) {
		int index = getIndex(key,table.length);
		if(table[index] == null) {
			table[index] = new LinkedList<Entry<K, V>>();
		}
		V oldValOfKey = ifContainsChange(index , key,value);
		if(oldValOfKey == null) {
			table[index].add(new Entry<K, V>(key, value));
			size++;
			if(size > (table.length * LOAD_THRESHOLD )) {
				rehash();
			}
		}else {
			return oldValOfKey;
		}
		return null;
	}
	/**
	 * makes the table bigger
	 */
	private void rehash() {
		LinkedList<Entry<K, V>>[] newTable = new LinkedList[table.length*2+1];
		for(LinkedList<Entry<K, V>> list :table) {
			if(list == null)
				continue;
			for(Entry<K, V> entry : list) {
				int index = getIndex(entry.key, newTable.length);
				if(newTable[index] == null) {
					newTable[index] = new LinkedList<Entry<K, V>>();
				}
				newTable[index].add(entry);
			}
		}
		
		table = newTable;
	}
	/**
	 * if the LinkedList in the given index has the given key change its value
	 * @param index index of the LinkedList which holds the value
	 * @param key key of the value which is about to be changed
	 * @param value new value of the entry which has the given key
	 * @return old value of the key
	 */
	private V ifContainsChange(int index,K key,V value) {
		for(Entry<K, V> entry : table[index]) {
			if(entry.getKey().equals(key)) {
				V oldVal = entry.getValue();
				entry.setValue(value);
				return oldVal;
			}
		}
		return null;
	}
	/**
	 * removes the value which has given key
	 * @param key key of the value which is wanted to be removed
	 * @return removed value
	 */
	@Override
	public V remove(Object key) {
		if(size == 0)
			return null;

		int index = getIndex(key, table.length);
		if(table[index] == null)
			return null;
		
		Iterator<Entry<K, V>> iter = table[index].iterator();
		while(iter.hasNext()) {
			Entry<K, V> entry = iter.next();
			if(entry.equals(key)) {
				iter.remove();
				size--;
				return entry.getValue();
			}
			
		}
		return null;
	}
	/**
	 * returns the size of hashtable
	 */
	@Override
	public int size() {
		return size;
	}
	/**
	 * inner class which can hold key and values for hashmap
	 * @param <K> key 
	 * @param <V> value
	 */
	private static class Entry<K, V> implements Comparable{
		/** The key */
		private final K key;
		/** The value */
		private V value;
		/** Creates a new key‐value pair.
		@param key The key
		@param value The value
		*/
		public Entry(K key, V value) {
			this.key = key;
			this.value = value;
		}
		/** Retrieves the key.
		@return The key
		*/
		public K getKey() {
			return key;
		}
		/** Retrieves the value.
		@return The value
		*/
		public V getValue() {
			return value;
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
		 * checks if the keys of entries are equal
		 * @param obj other object
		 * @return returns true if the keys of entries are equal
		 */
		@Override
		public boolean equals(Object obj) {
			return key.equals(((K)obj));
			
		}
		/**
		 * compares the values of entries
		 * @return compareTo method of the value with the parameter which is value of the given obj
		 */
		@Override
		public int compareTo(Object obj) {
			if(obj instanceof Entry<?,?>) {
				return ((Comparable) value).compareTo(((Entry<K,V>)obj).getValue());
			}else {
				throw new IllegalStateException();
			}
		}
		/**
		 * Returns the value as a String 
		 * @return the value as a String
		 */
		@Override
		public String toString() {
			return value.toString();
		}
	}
	/**
	 * returns the string which contains structure of the hashtable
	 * @return the string which contains structure of the hashtable
	 */
	@Override
	public String toString() {
		StringBuilder s = new StringBuilder();
		int index = -1;
		for(LinkedList<Entry<K, V>> list :table) {
			s.append(++index +"->");
			
			s.append(table[index]);
			s.append("\n");
		}
		return s.toString();
		
	}
	
	public static void main(String[] args) {
		HashTableChainLinked<String, String> hash = new HashTableChainLinked<String,String>();
		System.out.println("isEmpty should return true : "+hash.isEmpty());
		for(int i=0;i<60;i++) {
			hash.put(Integer.toString(i),Integer.toString(i+1000));
		}
		System.out.println(hash+"\n-------");
		int oldSize = hash.size();
		/**
		 * for -10 to 0 it wont remove anything
		 * for 0 to 50 fifty it will remove 50 element
		 */
		for(int i=-10;i<50;i++) {
			hash.remove(Integer.toString(i));
		}
		int newSize= hash.size();
		System.out.println(oldSize +" - 50 = "+newSize+"\n"+"hash is not empty, is empty ? : "+hash.isEmpty());
		System.out.println(hash);
		hash.put("55","mustafa");
		//hash.put("49","1801042627");
		/**
		 * for the first 10 element it will return the value
		 * for 60 to the 69 it will return null
		 */
		for(int i = 50;i<70;i++) {
			System.out.println(i+"->"+hash.get(Integer.toString(i)));
		}
	}

}
