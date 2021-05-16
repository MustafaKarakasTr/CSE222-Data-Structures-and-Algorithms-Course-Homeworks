import mustafa.hw4.*;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Random;

public class test {
	public static void main(String[] args) {
		/**
		 * Test Of Part 1
		 */
		System.out.println("\nTest Of Part 1:");
		testPart1();
		/**
		 * Test Of Part 2
		 */
		System.out.println("\nTest Of Part 2:\n");

		/*
		  1. Insert the 3000 numbers that are randomly generated in the range 0-5000 into the BSTHeapTree. 
		  Store these numbers in an array as well. Sort the numbers to find the number occurrences of all the numbers.
		 */
		BSTHeapTree<Integer> tree = new BSTHeapTree<>();
		Random rand = new Random();
		  
        ArrayList<Integer> arr = new ArrayList<>();
		for(int i=0;i<3000;i++) {
			int x = rand.nextInt(5000);
			tree.add(x);
			arr.add(x);
		}
		System.out.println("1. Insert the 3000 numbers that are randomly generated in the range 0-5000 into the BSTHeapTree.\n"
				+ "Store these numbers in an array as well. Sort the numbers to find the number occurrences of all the numbers.");
		if(BSTHeapTree.checkOccurences(tree, arr)) {
			System.out.println("Test 1 passed successfully\n");
		}
		else {
			System.out.println("Test 1 could not be passed\n");
		}
		System.out.println("2.Search for 100 numbers in the array and 10 numbers not in the array\n"
				+ "and make sure that the number of occurrences is correct.");
		boolean flag = true;
		boolean showMe = false;
		if(showMe)
			System.out.println("\n100 items which are in the array are about to be shown");
		for(int i=0;i<100;i++) {
			int num = rand.nextInt(5000);
			int occurenceInArray = getOccurenceInArrayList(arr, num);
			int occurenceInTree=  tree.find(num);
			if(showMe && occurenceInArray != 0) {
				System.out.println((i+1)+") "+occurenceInArray +" == "+ occurenceInTree + " value : "+ num);
			}
			else if(showMe) {
				i--; // 100 elements which are in the array test
			}
			if(occurenceInArray != occurenceInTree) {
				
				flag = false;
				break;
			}
		}
		if(showMe)
			System.out.println("\n10 items which are not in the array are about to be shown");
		for(int i=0;i<10;i++) {
			int num = rand.nextInt(5000) +5000;
			int occurenceInArray = getOccurenceInArrayList(arr, num);
			int occurenceInTree=  tree.find(num);
			if(showMe) {
				System.out.println((i+1)+") "+occurenceInArray +" == "+ occurenceInTree + " value : "+ num);
			}
			if(occurenceInArray != occurenceInTree) {
				System.out.println("Test 2 could not be passed\n");
				flag = false;
				break;
			}
		}
		if(flag) {
			System.out.println("Test 2 is passed successfully\n");
		}
		else {
			System.out.println("Test 2 could not be passed\n");
		}
		System.out.println("3. Find the mode of the BSTHeapTree. Check whether the mode value is correct.");
		int modeFromTree = tree.find_mode();
		int modeFromArrayList = getModeFromArrayList(arr);
		int modeInArrayListOfTreeMode = getOccurenceInArrayList(arr,modeFromTree);
		System.out.println("("+modeFromTree +" == " + modeFromArrayList +" || " +modeInArrayListOfTreeMode + " == "+ getOccurenceInArrayList(arr,modeFromArrayList)+")");
		if(modeFromTree == modeFromArrayList || getOccurenceInArrayList(arr,modeFromArrayList) == modeInArrayListOfTreeMode) {
			System.out.println("Test 3 is passed successfully\n");
		}else {
			System.out.println("Test 3 could not be passed successfully\n");
		}
		//System.out.println(getOccurenceInArrayList(arr, modeFromTree) + " "+ getOccurenceInArrayList(arr,getModeFromArrayList(arr)));
			//System.out.println("Test 3 is passed successfully");
		//System.out.println(arr);
		System.out.println("4. Remove 100 numbers in the array and 10 numbers not in the array and \n"
				+ "make sure that the number of occurrences after removal is correct.");
		for(int i=0;i<100;i++) {
			int x = rand.nextInt(5000);
			if(!arr.contains(x)) {
				i--; // 100 element which are in the tree test
			}
			tree.remove(x);
			arr.remove((Object)x);
		}
		for(int i=0;i<10;i++) {
			int x = rand.nextInt(5000)+5000; // 10 elements not in the array
			tree.remove(x);
			arr.remove((Object)x);
		}
		
		if(BSTHeapTree.checkOccurences(tree, arr)) {
			System.out.println("Test 4 is passed successfully\n");
		}else {
			System.out.println("Test 4 could not be passed successfully\n");
		}
		/*
		 * to be able to show that checkOccurences works fine, I will remove/add 1  element from/to tree and this function will return false 
		 */
		
		boolean removeTest = true;
		if(removeTest)
			tree.remove(arr.get(rand.nextInt(arr.size())));
		boolean addTest = false;
		if(addTest) {
			tree.add(rand.nextInt(5000));
		}
		if(BSTHeapTree.checkOccurences(tree, arr)) { 
			System.out.println("Function does not work properly");
		}else {
			System.out.println("function detected that there is something wrong thats good ");
		}
	}
	public static int getModeFromArrayList(ArrayList<Integer> arr) {
		if(arr == null || arr.isEmpty()) {
			return 0;
		}
		int occurance = getOccurenceInArrayList(arr, arr.get(0));
		int mode = arr.get(0);
		for(int i=1;i<arr.size();i++) {
			int occuranceOfi = getOccurenceInArrayList(arr, arr.get(i));
			if(occurance < occuranceOfi) {
				occurance= occuranceOfi;
				mode = arr.get(i);
			}
		}
		return mode;
	}
	public static int getOccurenceInArrayList(ArrayList<Integer> arr,int wanted) {
		int answer = 0;
		for(Integer x : arr) {
			if(x.equals(wanted)) {
				answer++;
			}
		}
		return answer;
	}

	
	public static void testPart1(){
		myHeap<Integer> h = new myHeap<Integer>();
		for(int i=0;i<10;i++) {
			h.add(10-i);
		}
		System.out.println("\nIs 4 in the heap: "+h.search(4));
		System.out.println("1 to 5:");
		for(int i=0;i<5;i++) {
			
			System.out.print(h.remove() + " ");
		}
		System.out.println("\nIs 4 in the heap: "+h.search(4));
		myHeap<Integer> merged = new myHeap<Integer>();
		for(int i=15;i>0;i-=2) {
			merged.add(i);
		}

		System.out.println("Heap before merging: "+h);
		System.out.println("Heap merged with: "+merged);
		
		h.merge(merged);
		System.out.println("Heap after merging\n"+h);
		System.out.println("The biggest element is removed: ");
		System.out.println("1. largest element: "+h.removeIthLargestElement(1));
		System.out.println(h);
		System.out.println("The 2. biggest element is removed: ");
		System.out.println("2. largest element: "+h.removeIthLargestElement(2));

		System.out.println(h);
		System.out.println("The 10. biggest element is removed: ");
		System.out.println("10. largest element: "+h.removeIthLargestElement(10));

		System.out.println(h);
		for(int i=1;i<5;i++) {
			h.removeIthLargestElement(i);
			System.out.println(h);
		}
		System.out.println(h);
		try {
			h.removeIthLargestElement(h.size()+1);
		}catch(Exception e) {
			System.out.println(e);
		}
		System.out.println("iterator");
		for(int i:h) {
			System.out.print(i+ " ");
		}
		System.out.println("\nheap iterator");
		myHeap.HeapIterator hi = h.heapIterator();
		try {
			hi.set(10); // tried to set before next
		}catch (NoSuchElementException e) {
			System.out.println(e);
		}
		System.out.println(h+"\nIf there is a 5 in the heap, set it to 11");
		while(hi.hasNext()) {
			if(hi.next().compareTo(5) == 0) 
				hi.set(11);
		}
		System.out.println(h);
		System.out.println("Heap in order:");
		while(!h.isEmpty()) {
			System.out.print(h.remove() + " ");
		}
		System.out.println();
	}
}
