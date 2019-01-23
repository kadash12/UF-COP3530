import java.util.Random;
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
 * Generate an integer list of size 5000, 10000, 20000, and 50000.
 * One version will be randomly ordered, one version will be in descending order, and one version will be in ascending order.
 * @author Johnny Li
 */
public class IntGenerator {
	//Changing size to 5000, 10000, 20000, and 50000
	static int size = 50000;
	
	//Generate an string array with integers of size - 5000, 10000, 20000, and 50000 in ascending order.
	public static void IntGenA(){
		String [] intGen = new String [size]; 
		for(int i=0; i<size; i++){
			//Transfer integer to string
			String temp = Integer.toString(i+1);
			intGen [i] = temp;
		}
		//Print string array to be stored in file outside.
		System.out.println("Ascending - " + size);
		for(int i=0; i<size; i++){
			System.out.println(intGen[i]);
		}
		System.out.println("\n");
	}
	
	//Generate an string array with integers of size - 5000, 10000, 20000, and 50000 in descending order.
	public static void IntGenD(){
		String [] intGen = new String [size+1]; 
		for(int i=0; i<size; i++){
			//Transfer integer to string
			String temp = Integer.toString(i+1);
			intGen [i] = temp;
		}
		//Print string array to be stored in file outside.
		System.out.println("Descending - " + size);
		for(int i=size; i>0; i--){
			System.out.println(intGen[i-1]);
		}
		System.out.println("\n");
	}
	
	//Generate an string array with integers of size - 5000, 10000, 20000, and 50000 in random order.
	public static void IntGenR(){
		String [] intGen = new String [size];
		//Generate a random number
		Random rand = new Random(); 
		for(int i=0; i<size; i++){
			//Transfer integer to string
			//Generate a random number between 1 and size - 5000, 10000, 20000, and 50000.
			String temp = Integer.toString(rand.nextInt(size) + 1);
			intGen [i] = temp;
		}
		//Print string array to be stored in file outside.
		System.out.println("Random - " + size);
		for(int i=0; i<10000; i++){
			System.out.println(intGen[i]);
		}
		System.out.println("\n");
	}
	
	/**
    * Call methods to generate the integer files to be stored in a text file.
	*/
	public static void main(String[] args) {
		IntGenA();
		IntGenD();
		IntGenR();
	}
}