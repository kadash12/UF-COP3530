/**
 * Project Summary: The program create input files of four sizes: 5000, 10000, 20000, and 50000 integers with one integer per line.
 * One version will be randomly ordered, one version will be in descending order, and one version will be in ascending order.
 * Two sorting algorithm will be ran to obtain average case, worst case (descending) and best case (ascending). For each run, 
 * the system clock with be used to get time values.
 * The two sorting algorithm used is bubble sort and merge sort.
 * @author Johnny Li
 * COP 3530 Section Number: 15A6
 */

/**
 * Merge sorting test with integers of size - 5000, 10000, 20000, and 50000.
 * One version will be randomly ordered, one version will be in descending order, and one version will be in ascending order.
 * @author Johnny Li
 */
public class MergeSort {
	//Internal Clock Start
	public static long start;    
	
	//Merge sorting algorithm
	//Taken with reference from https://www.geeksforgeeks.org/merge-sort/
	public static void Merge(int [] numbers, int begin, int middle, int last){
		//Declare/Initialize variables    
		//Temporary array storage for merged numbers
		int mergeNum [] = new int[last - begin + 1];
		// Position to insert merged number
		int mergePos = 0;                 
		// Left element position partition
		int left = begin;                 
		// Right element position partition
		int right = middle + 1;                
		
		// Add smallest element from left or right partition to merged numbers
		while (left <= middle && right <= last) {
			if (numbers[left] < numbers[right]) {
				mergeNum[mergePos] = numbers[left];
		        ++left;
		    } 
		    else {
		        mergeNum[mergePos] = numbers[right];
		        ++right;
		     }
		     ++mergePos;
		}
		// If left partition is not empty, add remaining elements to merged numbers
		while (left <= middle) {
		     mergeNum[mergePos] = numbers[left];
		     ++left;
		     ++mergePos;
		 }
		// If right partition is not empty, add remaining elements to merged numbers
		  while (right <= last) {
		     mergeNum[mergePos] = numbers[right];
		     ++right;
		     ++mergePos;
		  }
		// Copy merge number back to numbers
		for (mergePos = 0; mergePos < last - begin + 1; ++mergePos) {
		     numbers[begin + mergePos] = mergeNum[mergePos];
		}
	}
	
	//Recursively divide the list to subarrays.
	//Taken with reference from https://www.geeksforgeeks.org/merge-sort/
    public static void sort(int numbers[], int begin, int last){
    	//Internal Clock Start
    	start = System.currentTimeMillis();    
    	//Begin sorting, check if initial starting value is less than length of list
    	if (begin < last){
            //Get the middle point
            int middle = (begin + last)/2;
            //Sort through first half.
            sort(numbers, begin, middle);
            //Sort through second half.
            sort(numbers , middle+1, last);
            //Merge the sorted halves
            Merge(numbers, begin, middle, last);
        }
    }
    
	/**
	 * Takes a integer input file from the command line and apply bubble sort. 
	 * @param String [] of integer list of size 5000, 10000, 20000, and 50000.
	 */
	public static void main(String[] args) {
		//Convert String array to Integer array
		int[] numbers = new int[args.length];
		for(int i = 0; i<args.length; i++){
		   numbers[i] = Integer.parseInt(args[i]);
		}
		//Call Merge Sorting Algorithm
		sort(numbers, 0, numbers.length-1);
		 //Print sorted list.
		System.out.println("Merge Sort - " + numbers.length);
  		for(int i=0; i<numbers.length; i++){
  			System.out.println(numbers[i]);
  		}
  		//Internal Clock End
  		long end = System.currentTimeMillis() - start;
  		System.out.println("\n" + end + " ms");
	}
}
