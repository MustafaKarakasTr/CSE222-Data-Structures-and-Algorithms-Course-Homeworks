

import Part1.*;
import Part2.*;
import java.util.ArrayList;
import java.util.Random;


public class test {
	
	public static void main(String[] args) {
		System.out.println("\nTEST OF PART 1\n");
		HashMapIterable.testPart1();
		System.out.println("\nTEST OF PART 2\n");
		TEST_PART2(100);
		TEST_PART2(1000);
		TEST_PART2(10000);
		if(CORRECTNESS_TEST_PART2(1000))
			System.out.println("SUCCESSFUL");
		part2_last_test();
		
	}
	public static void TEST_PART2(int N){
        HashTableChainLinked<Integer,Integer> hashtableChain = new HashTableChainLinked<Integer,Integer>();
        HashTableChainTree<Integer,Integer> hashtableTreeSetChain = new HashTableChainTree<Integer,Integer>();
        HashTableOpen<Integer,Integer> hashtableCoalesced = new HashTableOpen<Integer,Integer>();
        long startTime;
        long endTime;
        double duration;


        startTime = System.nanoTime();
        for (int i = N; i > 1 ; i--){
            //randNum = rand.nextInt(300);
            hashtableChain.put(2*i,2*i);
        }
        endTime = System.nanoTime();

        duration = ((double) (endTime - startTime)/1000000 );

        System.out.println("Duration for adding "+ N +" elements in HashTableChain:: "+ duration+ " ms");


        startTime = System.nanoTime();
        for (int i = N; i > 1 ; i--){
            //randNum = rand.nextInt(300);
            hashtableTreeSetChain.put(2*i,2*i);
        }
        endTime = System.nanoTime();

        duration = ((double) (endTime - startTime)/1000000 );

        System.out.println("Duration for adding "+ N +" elements in HashTableTreeSetChain:: "+ duration+ " ms");


        startTime = System.nanoTime();
        for (int i = N; i > 1 ; i--){
            //randNum = rand.nextInt(300);
            hashtableCoalesced.put(2*i,2*i);
        }
        endTime = System.nanoTime();

        duration = ((double) (endTime - startTime)/1000000 );

        System.out.println("Duration for adding "+ N +" elements in HashTableCoalesced:: "+ duration+ " ms");




        System.out.println("");



        startTime = System.nanoTime();
        for (int i = 1; i < N ; i++){
            //System.out.println(hashtableChain.get(2*i));
            hashtableChain.get(2*i);
        }
        endTime = System.nanoTime();

        duration = ((double) (endTime - startTime)/1000000 );

        System.out.println("Duration for Get() "+ N +" elements in HashTableChain:: "+ duration+ " ms");


        startTime = System.nanoTime();
        for (int i = 1; i < N ; i++){
            //System.out.println(hashtableTreeSetChain.get(2*i));
            hashtableTreeSetChain.get(2*i);
        }
        endTime = System.nanoTime();

        duration = ((double) (endTime - startTime)/1000000 );

        System.out.println("Duration for Get() "+ N +" elements in HashTableTreeSetChain:: "+ duration+ " ms");


        startTime = System.nanoTime();
        for (int i = 1; i < N ; i++){
            //System.out.println(hashtableCoalesced.get(2*i));
            hashtableCoalesced.get(2*i);
        }
        endTime = System.nanoTime();

        duration = ((double) (endTime - startTime)/1000000 );

        System.out.println("Duration for Get() "+ N +" elements in HashTableCoalesced:: "+ duration+ " ms");



        System.out.println("");




        startTime = System.nanoTime();
        for (int i = 1; i < N ; i++){
            Integer a = hashtableChain.remove(2*i);
            //System.out.println(a);
        }
        endTime = System.nanoTime();

        duration = ((double) (endTime - startTime)/1000000 );

        System.out.println("Duration for Removing "+ N +" elements in HashTableChain:: "+ duration+ " ms");


        startTime = System.nanoTime();
        for (int i = 1; i < N ; i++){
            Integer a = hashtableTreeSetChain.remove(2*i);
            //System.out.println(a);
        }
        endTime = System.nanoTime();

        duration = ((double) (endTime - startTime)/1000000 );

        System.out.println("Duration for Removing "+ N +" elements in HashTableTreeSetChain:: "+ duration+ " ms");


        startTime = System.nanoTime();
        for (int i = 1; i < N ; i++){
            Integer a = hashtableCoalesced.remove(2*i);
            //System.out.println(a);
        }
        endTime = System.nanoTime();

        duration = ((double) (endTime - startTime)/1000000 );

        System.out.println("Duration for Removing "+ N +" elements in HashTableCoalesced:: "+ duration+ " ms");




        System.out.println("");




        startTime = System.nanoTime();
        for (int i = 1; i < N ; i++){
            hashtableChain.remove(5*i);
        }
        endTime = System.nanoTime();

        duration = ((double) (endTime - startTime)/1000000 );

        System.out.println("Duration for Removing NOT EXIST "+ N +" elements in HashTableChain:: "+ duration+ " ms");


        startTime = System.nanoTime();
        for (int i = 1; i < N ; i++){
            hashtableTreeSetChain.remove(2*i);
        }
        endTime = System.nanoTime();

        duration = ((double) (endTime - startTime)/1000000 );

        System.out.println("Duration for Removing NOT EXIST "+ N +" elements in HashTableTreeSetChain:: "+ duration+ " ms");


        startTime = System.nanoTime();
        for (int i = 1; i < N ; i++){
            hashtableCoalesced.remove(2*i);
        }
        endTime = System.nanoTime();

        duration = ((double) (endTime - startTime)/1000000 );

        System.out.println("Duration for Removing NOT EXIST "+ N +" elements in HashTableCoalesced:: "+ duration+ " ms");

        System.out.println("\n");




        //System.out.println(hashtableChain.toString());
        //System.out.println(hashtableCoalesced.get(732));

    }
	public static boolean CORRECTNESS_TEST_PART2(int N){
        HashTableChainLinked<Integer,Integer> hashtableChain = new HashTableChainLinked<Integer,Integer>();
        HashTableChainTree<Integer,Integer> hashtableTreeSetChain = new HashTableChainTree<Integer,Integer>();
        HashTableOpen<Integer,Integer> hashtableCoalesced = new HashTableOpen<Integer,Integer>();
        

       
       
        for (int i = N; i > 1 ; i--){
            //randNum = rand.nextInt(300);
            hashtableChain.put(2*i,2*i);
        }
  
        for (int i = N; i > 1 ; i--){
            //randNum = rand.nextInt(300);
            hashtableTreeSetChain.put(2*i,2*i);
        }
        

        for (int i = N; i > 1 ; i--){
            //randNum = rand.nextInt(300);
            hashtableCoalesced.put(2*i,2*i);
        }
       
        int test;
        for (int i = N; i > 1 ; i--){
        	test = 2*i;
        	//element which exists searched
            if(hashtableChain.get(2*i) != test || hashtableCoalesced.get(2*i) != test || hashtableTreeSetChain.get(2*i) != test) { 
            	System.out.println("ERROR OCCURED");
            	return false;
            }
            // element which does not exist searched
            if(hashtableChain.get(-2*i) != null || hashtableCoalesced.get(-2*i) != null || hashtableTreeSetChain.get(-2*i) != null) {
            	System.out.println("ERROR OCCURED");
            	return false;
            }
            
        }
        


        System.out.println("");
        //TEST OF REMOVING ELEMENT NOT IN THE MAP
        for (int i = 1; i < N ; i++){
             if(hashtableChain.remove(-5*i) != null) {
             	System.out.println("ERROR OCCURED");
             	return false;
             }
        }
        for (int i = 1; i < N ; i++){
             if(hashtableTreeSetChain.remove(-5*i) != null) {
             	System.out.println("ERROR OCCURED");
             	return false;
             }
         }
         for (int i = 1; i < N ; i++){
             
             if(hashtableCoalesced.remove(-5*i) != null) {
             	System.out.println("ERROR OCCURED");
             	return false;
             }
         }


        
       
        // TEST OF REMOVING ELEMENT IN THE MAP
        for (int i = 2; i < N ; i++){
            Integer a = hashtableChain.remove(2*i);
            if(!a.equals(2*i)) {
            	System.out.println("ERROR OCCURED");
            	return false;
            }
        }
        
        for (int i = 2; i < N ; i++){
            Integer a = hashtableTreeSetChain.remove(2*i);
            if(!a.equals(2*i)) {
            	System.out.println("ERROR OCCURED");
            	return false;
            }
        }
       for (int i = 2; i < N ; i++){
            Integer a = hashtableCoalesced.remove(2*i);
            if(!a.equals(2*i)) {
            	System.out.println("ERROR OCCURED");
            	return false;
            }
        }
      
        
        return true;

    }
	public static void part2_last_test() {
		HashTableOpen<Integer, Integer> hash = new HashTableOpen<Integer,Integer>();
		
		System.out.println("isEmpty should return true : "+hash.isEmpty() + " size:" + hash.size());
		
		
		
		Random r = new Random();
		ArrayList<Integer> arr = new ArrayList<>();
		int x;
		for(int i=0;i<900 ;i++) {
			x = r.nextInt(300);
			x*=11;
			if(!arr.contains(x))
			{
				arr.add(x);
				hash.put(x,x);
		
			}
			
		}
		
		
		
		int[] array = new int[100];
		for(int i=0;i<100;i++) {
			int inArr = arr.remove(0);
			
			hash.remove(inArr);
			array[i] = inArr;
			
		}
		for(int i=0;i<arr.size();i++) {
			if(i<100 && hash.get(array[i])!= null) {
				System.out.println("Error");
				return;
			}else {
				
				if(!hash.get(arr.get(i)).equals(arr.get(i)) ) {
					System.out.println(hash.get(arr.get(i))+" "+arr.get(i) + "Error");
					return;
				}
			}
		}
		//System.out.println(arr+"\n----------\n"+hash+"\n-------");
		for(int i=0;i<arr.size();i++) {
			int inArr = arr.get(i);
			if(!hash.get(inArr).equals(inArr)) {
				System.out.println("ERRROOORwwR");
				return;
			}
		}
		for(int i=0;i<100;i++) {
			Integer num = r.nextInt()%50;
			num+=50;
			arr.remove(num);
			hash.remove(num);
		}
		for(int i:array) {
			if(hash.get(i) != null) {
				System.out.println("NOT REMOVED");
				return;
			}
		}
		for(int i=0;i<arr.size();i++) {
			int inArr = arr.get(i);	
			if(hash.get(inArr) == null) {
				System.out.println(hash + "\n-----------s\n"+inArr);
				System.out.println("ERRROOORR");
				return;
			}

		}
		System.out.println("isEmpty should return false :" + hash.isEmpty()  + " size:" + hash.size());
		System.out.println("Main 2 test Successfully completed");
	}
}
