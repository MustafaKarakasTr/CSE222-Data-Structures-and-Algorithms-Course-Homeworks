package mustafa.hw4;
import java.util.ArrayList;
import java.util.NoSuchElementException;
/**
 * max Heap implementation
 * @author Mustafa Karakas
 *
 * @param <E> type of element
 */
public class maxHeap <E extends Comparable<E>> extends myHeap<E> {
	
	/**
	 * constructs the maxHeap
	 * myHeap does have a constructer which checks that if the argument is true , if it is true that it will act like maxHeap otherwise it will act as a minheap
	 */
	public maxHeap() {
		super(true);
	}
	/**
	 * removes the smallest element in the heap
	 * @return smalles element
	 */
	E removeSmallest() {
		if(!arr.isEmpty()) {
			int minIndex = 0;
			for(int i=1;i<arr.size();i++) {
				if(arr.get(i).compareTo(arr.get(minIndex)) < 0) {
					minIndex = i;
				}
			}
			return remove(minIndex);
		}
		throw new NoSuchElementException();
		
	}
	public static void main(String[] args) {
		maxHeap<Integer> m = new maxHeap<>();
		for(int i=0;i<10;i++) {
			m.add(i);
		}
		System.out.println(m);
		
		m.remove();
		System.out.println(m);
		for(int i=0;i<9;i++) {
			System.out.println(m.remove());
			System.out.println(m);
		}
	}
	
}
