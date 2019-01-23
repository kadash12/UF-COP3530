import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Project Summary: The program implement Huffmans algorithm. 
 * The program is able to read a given text file, encode the characters, and decode the file. The program also
 * print of the frequency of the elements and the code of the characters in 0's and 1's.
 * @author Johnny Li
 * COP 3530 Section Number: 15A6
 */
public class HuffmanEncoder implements HuffmanCoding{
    //return int array with character and frequency
    private int [] array;
    public void setArray (int hash[]){
		this.array=hash;
    }
    
    /**
     * Create a table with symbols and their frequencies.
     * Output a string of the symbols and their frequencies. 
     * Taken with reference to http://www.java2s.com/Code/Java/File-Input-Output/Readfilecharacterbycharacter.htm
     * @param inputFile - text file
     */
    public String getFrequencies(File inputFile) throws FileNotFoundException {
        //Check if input file exists or can be read
        if((!inputFile.exists())||(!(inputFile.isFile() && inputFile.canRead()))){
            System.out.println("ERROR: Input file can not be read.");
            return null;
        }
        
        //convert to readable file
        FileInputStream illiad = new FileInputStream(inputFile);
        //Storage variable
        String hashTable = " ";
        char character = '~';
        int hash[] = new int [128];
        
        //read every character in the file
        try{
	    	while (illiad.available() > 0){
	    		 character = (char) illiad.read();
	    		 if(character != 10){ //remove new line in text
	        	 //set int array index to symbols and increment for each occurrence
	             hash[character]+= 1;
	    		 }
	    	}
        }
        //catch any exceptions and print an error
        catch (IOException e){
            System.out.println("ERROR: " + e);
        }
        
        //Store array
        setArray(hash);
        
        //Setup the string
        for(int i=0; i<128; i++){
            if(hash[i]!=0){
            	hashTable += (char)i + " " + hash[i] + "\n";
            }
        }
        return hashTable;
	}

    /**
     * Algorithm to build Huffman Tree.
     * copied from https://opendsa-server.cs.vt.edu/ODSA/Books/Everything/html/Huffman.html
     * @return HuffTree - Huffman tree
     */
    public HuffTree buildTree(File inputFile) throws FileNotFoundException, Exception{    	
    	HuffTree tmp1, tmp2, tmp3 = null;
    	//create the heap
        Hheap AHeap = new Hheap(array);

        while (AHeap.heapsize() > 1){ // While two items left
            tmp1 = AHeap.removeMin();
            tmp2 = AHeap.removeMin();
            tmp3 = new HuffTree(tmp1.root(), tmp2.root(), tmp1.weight() + tmp2.weight());
            AHeap.insert(tmp3);   // Return new tree to heap
        }
        return tmp3;  // Return the tree
    }
    

	//Traverse the Huffman Tree and print out the characters and frequencies
	public String traverseHuffmanTree(HuffTree huffTree) throws Exception {		
		//Variable storage
		String output = "";
		//Check if null
 	   	if((huffTree == null) && (huffTree.root() == null)){ 
 		   System.out.println("ERROR: Tree is Null.");
           return null;
 	   	}
		//Check if the left child is null
        if(huffTree.root.left() != null){
        	//Left branch gets a value of 0
            traverse(huffTree.root.left(), "0", huffTree.getMap());
        }
        //Check if the right child is null
        if(huffTree.root.right() != null){
        	//Right branch gets a value of 1
            traverse(huffTree.root.right(), "1", huffTree.getMap());
        }
        //Copied from https://stackoverflow.com/questions/1066589/iterate-through-a-hashmap
        //Set up string output
        Iterator element = huffTree.getMap().entrySet().iterator();
        while (element.hasNext()) {
            Map.Entry pair = (Map.Entry)element.next();
            output += pair.getKey()+"  "+pair.getValue()+"\n";
        }
        return output;
	}
	
    //Hash Map creation was an open suggestion by Academic Tutoring Center
	//Takes in a starting node and traverses through all of its children
    public void traverse (HuffBaseNode child, String output, HashMap map){
    	//If children are null, add to hashmap
        if((child.left() == null) && ((child.right() == null))){
        	map.put(child.symbol(), output);
            return;
        }
        //Check if the left child is null
        if(child.left() != null){
        	//Build string
            traverse(child.left(), output + "0", map);
        }
        //Check if the right child is null
        if(child.right() != null){       
        	//Build string
        	traverse(child.right(), output + "1", map);
        }
    }

    //take a file and a HuffTree and encode the file.
    //output a string of 1's and 0's representing the file
    public String encodeFile(File inputFile, HuffTree huffTree) throws Exception{
    	 //Variable Storage
    	 String output = "";
    	 //Check if null
    	 if((huffTree == null) && (huffTree.root() == null)){  
		   System.out.println("ERROR: Tree is Null.");
           return null;
    	 }
    	 //Build hash map
         traverseHuffmanTree(huffTree);
         HashMap treeMap = huffTree.getMap();
         //Taken with reference to http://www.java2s.com/Code/Java/File-Input-Output/Readfilecharacterbycharacter.htm
         //Repeat from frequency
         try{
             FileInputStream illiad = new FileInputStream(inputFile);
             char character; 
             while (illiad.available() > 0){
	    		 character = (char) illiad.read();
                 //remove new line and returns in text
                 if(character != 13 && character!= 10 ){
                     output += treeMap.get(character);
                 }
             }
         }
         //catch any exceptions and print an error
         catch (IOException e){
        	 System.out.println("ERROR: " + e);
         }
         return output;
    }

    //take a String and HuffTree and output the decoded words
    public String decodeFile(String input, HuffTree huffTree) throws Exception{
		//Variable Storage
		 String output = "";
		 
		 //Check if null
		 if((huffTree == null) && (huffTree.root() == null)){  
			   System.out.println("ERROR: Tree is Null.");
		      return null;
		 }
		 //Counter Variable
	    int count=0;
	    HuffBaseNode trans = huffTree.root();
	    //Goes through string input
	    while(count < input.length()){
	    	//Check if not  null
	        if (trans.left() != null || trans.right() != null){
	        	//Left branch gets a value of 0
	            if (input.charAt(count) == '0'){
	                trans = trans.left();
	            }
	            //Right branch gets a value of 1
	            else if (input.charAt(count) == '1'){
	                trans = trans.right();
	            }
	        }
	        //Check if child is null
	        if (trans.left() == null || trans.right() == null){
	        	//Build string
	            output += trans.symbol();
	            trans = huffTree.root();
	        }
	        //Increment
	        count++;
	    }
	    return output;
	}
    
    //Construct decoder output
    private StringBuilder decodeBuilder(StringBuilder code, HuffBaseNode root, StringBuilder decoded) {
		//Check if it is a leaf node
		if (root.isLeaf()){
			//Add value of root
			decoded.append(String.valueOf(((HuffLeafNode)root).weight()));
			return code;
		}
		//Empty string
		else if (code.length() == 0) {
			System.out.println("ERROR: Tree is Null.");
			return null;
		}
		//Right branch gets a value of 1
		else if (code.charAt(0) == '1'){
			return decodeBuilder(code.deleteCharAt(0), ((HuffInternalNode)root).right(), decoded);
		}
		//Left branch gets a value of 0
		else if (code.charAt(0) == '0') {
			return decodeBuilder(code.deleteCharAt(0), ((HuffInternalNode)root).left(), decoded);
		}
		//Not a 0 or a 1, add a newline
		else {
			decoded.append("\n");
			return code.deleteCharAt(0);
		}
	}
}
