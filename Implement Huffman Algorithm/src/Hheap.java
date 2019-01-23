import java.util.ArrayList;
import java.util.Arrays;

/**
 * Create min-heap to store elements and frequency.
 * Taken with reference to https://opendsa-server.cs.vt.edu/ODSA/Books/Everything/html/Heaps.html
 * @author Johnny Li
 */
public class Hheap{
	//Storage Variable
	private static HuffTree[] heap2 = new HuffTree [128];
    private ArrayList<HuffTree> heap = new ArrayList<>();
    private int size;

  //Constructor
    Hheap(int[] input){
    	size = 0;
        int count = 0;
        
      //Loop to create an array of nodes
        while(count < input.length){
        	//check if there is a value, if so create root node
            if(input[count] != 0){
                heap.add(new HuffTree((char)count, input[count]));
            }
            //Increment
            count++;           
        }
        //Set size of heap
        size=heap.size(); 
        
        //Test printing
        /*heap2 = heap.toArray(new int[heap.size()]);
          for(int i =0; i<heap.length; i++){
        	if(heap[i] != null){
        	  System.out.println((char)i + " " +heap[i].weight());}
          }*/
    }

    //Return size of heap
    public int heapsize(){
        return this.size;
    }
    
    //remove the minimum value in the heap
    public HuffTree removeMin(){
    	//Storage Variables
        int low=0;
        HuffTree temp = null;
        int count=0;
        int value=-1;
        //Loops when there is values
        while(count < heap.size()){
        	//Check if heap value is null
            if(heap.get(count) != null){
            	//If zero, set the low 
                if(low == 0){
                    low = heap.get(count).root().weight();      //set low to weight of first non-null node
                    temp = heap.get(count);
                    value = count;
                }
                //If the current node is less than low node, update swap
                if (heap.get(count).root().weight() < low){
                    low = heap.get(count).root().weight();
                    temp = heap.get(count);
                    value = count;
                }
            }
            //Increment
            count++;
        }
        //If test case is true
        if(value != -1){
        	//Set heap value to null
        	heap.set(value, null);    
        }
        //Decrement
        size+=-1;       
        return temp;
    }

    //Insert node into the heap
    public void insert(HuffTree tree){
    	//Counter Variable
        int count=0;
        while(count < heap.size()){
        	//Check if null, if so store value from tree
            if(heap.get(count) == null) {
                heap.set(count, tree);
                //Increment size
                size+=1; 
                return;
            }
            //Increment
            count++;
        }
        //Increment
        size+=1; 
    }
    
    //Heapify the heap
    HuffTree minheapify() {
	    //Set tree to heap value
		HuffTree min = heap2[0];
		//Storage variable
		int curr = 0;
		HuffTree tree1;
		HuffTree tree2;
		
		while(true) {
			tree1 = null;
			tree2 = null;
			
			//Left Tree
			int lNum = tree1.weight();
			if (lNum < 128) {
				 tree1 = heap2[tree1.weight()];
			}
			
			//Right Tree
			int rNum = tree2.weight();;
			if (rNum < 128) {
				tree2 = heap2[rNum];
			}
			
			//Check if null
			if (tree2 == null && tree1 == null) {
				heap2[curr] = null;
				break;
			}
			else if(tree2 == null) {
				//Store left value
				heap2[curr] = tree1;
				curr = tree1.weight();
			}
			else if(tree1 == null) {
				//Store right value
				heap2[curr] = tree2;
				curr = tree2.weight();
			}
			else {
				//Compare value
				if (tree1.compareTo(tree2) < 0){
					heap2[curr] = tree1;
					curr = tree1.weight();
				}
				else {
					heap2[curr] = tree2;
					curr = tree2.weight();
				}
			}
		}
		size--;
		return min;
    }
}