import java.util.ArrayList;

/**
 *Project Summary: The program uses a recursive algorithm to calculate the determinant of a matrix. 
 * The program is able to read a matrix, print it out, and calculate and print the determinant. The matrix implementation
 * is that of a "sparse" matrix, only matrix elements that are non-zero should be allocated in memory.
 * @author Johnny Li
 * COP 3530 Section Number: 15A6
 */
public class SparseMatrix implements SparseInterface{
	//Form a 1D ArrayList of size 5x1 to hold rows of linked list.
	private ArrayList<LinkedList> list;
    private int size;

    //Test case 1&2 set  SparseMatrix(){ setSize(5);
    //JUnit Test set SparseMatrix(int size){ setSize(int size);
    //Constructor of SparseMatrix class that creates a default matrix of size 5x5.
    SparseMatrix(){
        setSize(5);
    }

    /**
     * Sets the maximum size of the matrix (Number of rows). Also clear the matrix (make all elements 0).
     * @param size - maximum size of matrix
     */
    public void setSize(int size){
        this.size=size;   
        //Create ArrayList based on size
        list = new ArrayList<LinkedList>(size);
        
        //Create a set of linked list of "size" is each index of the vector.
        for(int i=0; i<size; i++){
        	list.add(i, new LinkedList());
            list.get(i).setSize(size);
            //Convert ArrayList to Array
            LinkedList [] link = list.toArray(new LinkedList[list.size()]);
            link[i].clear();
            list.add(i,link[i]);
        }
    }

    /**
     * Remove (make 0) the element at the specified row and column.
     * Throws an error if row/column combination is out of bounds.
     * @param row - designated row for element to be removed
     * @param col - designated column for element to be removed
     */
    public void removeElement(int row, int col){
    	if((col<0) || (col>=size) || (row<0) || (row>=size)){
    		throw new IndexOutOfBoundsException("Index of row or column is out of bounds!");
    	}
        else{
        	list.get(row).removeElement(row,col);
        }
    }
    
    /**
     * Adds an element to the row and column passed as arguments (overwrites if element is already present at that position).
     * Throws an error if row/column combination is out of bounds.
     * @param row - designated row for element to be added at
     * @param col - designated column for element to be added at
     * @param data - integer value to be stored 
     */
    public void addElement(int row, int col, int data){
    	//Check if matrix is out of bounds
    	if((col<0) || (col>=size) || (row<0) || (row>=size)){
    		throw new IndexOutOfBoundsException("Index of row or column is out of bounds!");
    	}
        //Add Element to linked list
        else {
        	list.get(row).addElement(row, col, data);
        }
    }

    /**
     * Return the element at the specified row and column
     * Throws an error if row/column combination is out of bounds.
     * @param row - designated row of element to be retrieved
     * @param col - designated column of element to be retrieved 
     */
    public int getElement(int row, int col){
    	if((col<0) || (col>=size) || (row<0) || (row>=size)){
    		throw new IndexOutOfBoundsException("Index of row or column is out of bounds!");
    	}
        else{
        	return list.get(row).getElement(row,col);
        }
    }

    /**
     * Returns a new matrix which is the minor of the original.
     * Throws an error if row/column combination is out of bounds.
     * @param row - row dimension of minor matrix
     * @param col - column dimension of minor matrix
     */
    public SparseInterface minor(int row, int col) {
        SparseMatrix minor = new SparseMatrix();
        //Minor dimension is 1 less than original due to determination
        minor.setSize(size-1);              
        if((col<0) || (col>=size) || (row<0) || (row>=size)){
   			throw new java.lang.IndexOutOfBoundsException("Index of row or column is out of bounds!");
	   }
        //Increment variable
        int k = 0;       
        
        for(int i=0; i<(size); ++i){
            if(i != row){
            	minor.list.set(k++, list.get(i).minor(row,col));
            }
        }
        return minor;
    }

    /**
     * Returns the determinant of the matrix calculated recursively.
     */
    public int determinant(){
    	//1x1 element case
        if(size==1){
        	return list.get(0).getElement(0,0);
        }
        //Storage Variable 
        int det=0;
        Node node = list.get(0).getHead();
        //Check if its empty
        while(node != null){
        	//Based on given formula
        	//Taken with reference from: https://stackoverflow.com/questions/16602350/calculating-matrix-determinant
            det+=(int)Math.pow(-1,node.col)*(node.data)*minor(0,node.col).determinant();
            //Update node
            node=node.next;
        }
        return det;                                                                         
    }
    
    /**
     * Return the nonzero elements of your sparse matrix as a string.
     * The String is to be k lines, where k is the number of nonzero elements.
     * An empty matrix will return an empty string.
     * The print should be from left to right and from top to bottom (like reading a book) i.e. the matrix
     */
    public String toString(){
        String print="";
        for(int i=0; i<size; i++){
        	print += list.get(i).toString(i);
        	System.out.print(print);
        }
        return print;
    }

    /**
     * Should return the size of the matrix.
     */   
    public int getSize(){
        return this.size;
    }
    
    /**
     * Clear the matrix of all entries. 
     */
    public void clear(){
    	list.clear();
    }

    /**
     * Print nonzero matrix elements in the form "row column data"
     * Testing purpose
     */
    /*public void print(){
        String temp="";
        //Print matrix
        for(int i=0; i<size; i++){
        	temp += list.get(i).print()+ "\n";
        }
        System.out.println(temp);
    }*/
}
