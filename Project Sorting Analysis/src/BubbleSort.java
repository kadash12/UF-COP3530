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
 * Bubble sorting test with integers of size - 5000, 10000, 20000, and 50000.
 * One version will be randomly ordered, one version will be in descending order, and one version will be in ascending order.
 * @author Johnny Li
 */
public class BubbleSort {	
	//Bubble sorting algorithm
	//Taken with reference from https://www.geeksforgeeks.org/bubble-sort/
	public static void Bubble(int [] numbers){
		//Internal Clock Start
		long start = System.currentTimeMillis();    
		
		//Sort through entire integers list.
        for (int i = 0; i < numbers.length-1; i++){
            for (int j = 0; j < numbers.length-i-1; j++){
            	//Swap if only the next value is higher.
                if (numbers[j] > numbers[j+1]){
                    //Temporary storage
                    int temp = numbers[j];
                    //Swap values
                    numbers[j] = numbers[j+1];
                    numbers[j+1] = temp;
                }
            }
        }
        //Internal Clock End
  		long end = System.currentTimeMillis() - start;
       //Print sorted list.
  		System.out.println("Bubble Sort - " + numbers.length);
  		for(int i=0; i<numbers.length; i++){
  			System.out.println(numbers[i]);
  		}
  		//Print Time
  		System.out.println("\n" + end + " ms");
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
		//Call Bubble Sorting Algorithm
		Bubble(numbers);
	}
}
