package mustafa.hw4;
/**
 * container class which holdes number of occurences of given value
 * @author Mustafa Karakas
 *
 * @param <E> type of value
 */
public class Cup<E extends Comparable<E>> implements Comparable<Cup<E>>{
		private int occurences = 1;
		private E val;
		/**
		 * constructs the Cup
		 * @param _val
		 */
		public Cup(E _val) {
			val = _val;
		}
		/**
		 * increases the occurences of the value
		 */
		public void increaseOccurences() {
			occurences++;
		}
		/**
		 * decreases the occurences of the value
		 */
		public void decreaseOccurences() {
			occurences--;
		}
		/**
		 * returns the occurences of the value
		 * @return the occurences of the value
		 */
		public int getOccurences() {
			return occurences;
		}
		/**
		 * returns the value
		 * @return returns the value
		 */
		public E getVal() {
			return val;
		}
		/**
		 * compares this cup with the given cup
		 *  @param other cup 
		 */
		@Override
		public int compareTo(Cup<E> o) {
			return val.compareTo(o.getVal());
		}
		/**
		 * returns the value as a string
		 */
		public String toString() {
			return String.format(val + ","+occurences );
		}
		@Override
		public boolean equals(Object o) {
			if(o == null)
				return false;
			Cup<E> c = (Cup<E>) o;
			return val.equals(c.val);
		}
	}