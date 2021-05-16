package Part1;

import java.util.*;
/**
 * Iterable hashmap implementation
 * @param <K> Key
 * @param <V> Value
 */
public class HashMapIterable< K, V> extends HashMap<K, V> implements Iterable<K>{
	/**
	 * Iterator implementation for hashmap
	 * @param <K> Key
	 */
	private class MapIterator<K> implements Iterator<K>{
		private Set<K> set;
		private K[] array;
		private int index;
		private int howManySteps = 0;
		/**
		 * no parameter constructer for iterator
		 * it starts from the head
		 */
		public MapIterator() {
			set = (Set<K>) keySet();
			array = (K[])set.toArray();
			this.index = 0;
			
		}
		/**
		 * constructs the iterator
		 * it starts from the key , if it exist , otherwise starts from head
		 * @param key head of the iterator
		 */
		public MapIterator(K key) {
			set = (Set<K>) keySet();
			array = (K[])set.toArray();
			int temp = indexOf(key);
			if(temp == -1)
				this.index = 0;
			else {
				this.index = temp;
			}
	
		}
		/**
		 * returns the index of given key
		 * @param key key whose index is wanted
		 * @return if exist, the index of the given key otherwise returns -1
		 */
		private int indexOf(K key) {
			for(int i=0;i<array.length;i++) {
				if(array[i].equals(key)) {
					return i;
				}
			}
			return -1;
		}
		/**
		 * returns true if next index is available
		 * @return true if next index is available
		 */
		@Override
		public boolean hasNext() {
			
			return howManySteps<array.length && howManySteps > -array.length;
		}
		/**
		 * The function returns the next key in the Map. It returns the first key when there is no
		 * not-iterated key in the Map
		 * @return next element
		 */
		@Override
		public K next() {
			if(hasNext()) {
				if(index == array.length) {
					index = 0;
				}
				howManySteps++;
				return array[index++];
			}
			index = 0;
			return array[0];
		}
		/**
		 * the iterator points to the previous key in the Map. It returns the last key when the
	     * iterator is at the first key.
		 * @return previous element
		 */
		public K prev() {
			howManySteps--;
			if(index == 0) {
				index = array.length;
			}
			return array[--index];
		}
	}
	/**
	 * returns the mapIterator starts from the head
	 * @return the iterator starts from the head
	 */
	@Override
	public Iterator<K> iterator() {
		return new MapIterator();
	}
	/**
	 * returns the mapIterator starts from the head
	 * @return the iterator starts from the head
	 */
	public MapIterator<K> mapIterator(){
		return new MapIterator();
	}
	/**
	 * returns the mapIterator starts from the head
	 * @return the iterator starts from the head
	 */
	public MapIterator<K> mapIterator(K key){
		return new MapIterator(key);
	}
	public static void testPart1() {
		HashMapIterable<Integer ,Integer> hash = new HashMapIterable<Integer ,Integer>();
		for(int i=0;i<45;i++) {
			hash.put(i, i*2);
		}
		System.out.println("Hash itself:\n"+hash);
		System.out.println("\nEnhanced for loop");
		for(int x : hash) {
			System.out.print(x+" ");
		}
		System.out.println();
		
		HashMapIterable<Integer,Integer>.MapIterator <Integer> iter = hash.mapIterator(); 
		HashMapIterable<Integer,Integer>.MapIterator <Integer> iter2 = hash.mapIterator(10); 
		HashMapIterable<Integer,Integer>.MapIterator <Integer> iter3 = hash.mapIterator(-32412); 
		
		System.out.println("\nNO PARAMETER ITERATOR TEST");
		IteratorTest(iter);
		System.out.println("\nIterator with parameter test");
		IteratorTest(iter2);
		System.out.println("\nIterator with non exist parameter test");
		IteratorTest(iter3);
	}
	static void IteratorTest(HashMapIterable<Integer,Integer>.MapIterator <Integer> iter2 ) {

		System.out.println();
		
		System.out.println("\nhasNext");
		while(iter2.hasNext()) {
			System.out.print(iter2.next()+" ");
		}
		System.out.println();
		System.out.println("\n50 step backward");
		for(int i=0;i<50;i++)
			System.out.print(iter2.prev()+" ");
		
		System.out.println();
		System.out.println("\n50 step forward");
		for(int i=0;i<50;i++)
			System.out.print(iter2.next()+" ");
		System.out.println();
	}

}
