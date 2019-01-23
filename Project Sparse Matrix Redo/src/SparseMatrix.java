import java.util.ArrayList;

/**
 * Project Summary: The program implement recursive methods to add two matrices and multiply two matrices. 
 * The program is able to read a matrix, print it out, and calculate the product or sum of the matrices. The matrix implementation
 * is that of a "sparse" matrix, only matrix elements that are non-zero should be allocated in memory.
 * @author Johnny Li
 * COP 3530 Section Number: 15A6
 */
public class SparseMatrix implements SparseInterface{
	//Form a 1D ArrayList of size row x columns to hold rows of linked list.
	private ArrayList<LinkedList> list;
	
	//Variables of size
    private int numRows;
    private int numCols;

    //Constructor of SparseMatrix class that creates a default matrix of size row x col.
    SparseMatrix(){
        setSize(numRows, numCols);
    }

    /**
     * Sets the maximum size of the matrix (Number of rows). Also clear the matrix (make all elements 0).
     * @param size - maximum size of matrix
     */
    public void setSize(int numRows, int numCols){
    	//Set size
        this.numRows=numRows;  
        this.numCols=numCols;
        
        //Create ArrayList based on size
        list = new ArrayList<LinkedList>(numRows);
        
        //Create a set of linked list of numRows is each index has numCols of the vector.
        for(int i=0; i<numRows; i++){
        	list.add(i, new LinkedList());
        	
        	//Set size in linked list
            list.get(i).setnumRows(numRows);
            list.get(i).setnumCols(numCols);
            
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
    	//Check if matrix is out of bounds
    	if((col<0) || (col>=numCols) || (row<0) || (row>=numRows)){
    		throw new IndexOutOfBoundsException("Index of row or column is out of bounds!");
    	}
    	//Remove Element in linked list
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
    	if((col<0) || (col>=numCols) || (row<0) || (row>=numRows)){
    		throw new IndexOutOfBoundsException("Index of row or column is out of bounds!");
    	}
        else {
        	//If data is 0, keep it sparse by removing it. 
        	if (data == 0){
        		list.get(row).removeElement(row,col);
        		return;
        	}
            //Add Element to linked list
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
    	//Check if matrix is out of bounds
    	if((col<0) || (col>=numCols) || (row<0) || (row>=numRows)){
    		throw new IndexOutOfBoundsException("Index of row or column is out of bounds!");
    	}
    	//Get Element in linked list at row x column
        else{
        	return list.get(row).getElement(row,col);
        }
    }

    /**
     * Add two valid matrices together. Return NULL if sizes incompatible.
     * @param SparseInterface matrixToAdd - takes another matrix as input.
     * @return the sum of the two matrices
     */
    public SparseInterface addMatrices(SparseInterface matrixToAdd){
    	//SparseMatrix to return sum values 
    	 SparseMatrix sum = new SparseMatrix();
    	//Set values to variable
    	int row1 = getNumRows();
    	int row2 = matrixToAdd.getNumRows();
    	int col1 = getNumCols();
    	int col2 = matrixToAdd.getNumCols();
    	int data =0;
    	//Check if size is compatible.
    	if ((row1 == row2) && (col1 == col2)){
    		//Set size of the new matrices
    		sum.setSize(row1,col1);
    		//Transverse matrices
    		for (int i=0; i<row1; i++){
    			for (int j=0; j<col1; j++){
    				if ((getElement(i, j) != 0) || (matrixToAdd.getElement(i, j) != 0)){
    				//Sum the matrices
    				data = getElement(i, j) + matrixToAdd.getElement(i, j); 	
    				sum.addElement(i, j, data);
    				}
    			}
    		}
    		return sum;
    	}
    	else{
    		//Sizes are incompatible.
    		//System.out.println("ADD NULL"); //Testing
    		return null;
    	} 	
    }
 
    /**
     * Multiple two valid matrices together. Return NULL if sizes incompatible.
     * @param SparseInterface matrixToMultiply - takes another matrix as input
     * @return returns the product of the two matrices
     */
	public SparseInterface multiplyMatrices(SparseInterface matrixToMultiply){
    	//SparseMatrix to return product values 
		SparseMatrix multi = new SparseMatrix();
		//Set values to variable
		int row1 = getNumRows();
		int row2 = matrixToMultiply.getNumRows();
		int col1 = getNumCols();
		int col2 = matrixToMultiply.getNumCols();
		int data =0;
		
		//Check if size is compatible.
		if (col1 == row2){
			//Set size of the new matrices
			multi.setSize(row1,col2);
			//Transpose matrices
			for (int i = 0; i < row1; i++) { 
				for (int j = 0; j < col2; j++) {
		            for (int k = 0; k < row2; k++) {
		    			//Multiple the matrices
		    			data += getElement(i, k) * matrixToMultiply.getElement(k, j); 				
					}
					multi.addElement(i, j, data);	
					//Reset data
					data = 0;	    				
				}
		   }
			return multi;
		}
		else{
			//Sizes are incompatible.
			//System.out.println("Multi NULL"); //Testing
			return null;
		} 	
	}

    /**
     * Return the nonzero elements of your sparse matrix as a string.
     * The String is to be k lines, where k is the number of nonzero elements.
     * An empty matrix will return an empty string.
     * The print should be from left to right and from top to bottom (like reading a book) i.e. the matrix
     */
    public String toString(){
    	//Variable 
        String print="";
        
        //Loop through all to obtain values
        for(int i=0; i<numRows; i++){
        	print += list.get(i).toString(i);
        }
        //Print
        System.out.print(print); 
        return print;
    }

    /**
     * Should return the number of rows of the matrix.
     */   
    public int getNumRows(){
        return this.numRows;
    }
    
    /**
     * Should return the number of columns of the matrix.
     */   
    public int getNumCols(){
        return this.numCols;
    }
    
    /**
     * Clear the matrix of all entries. 
     */
    public void clear(){
    	list.clear();
    }
}
