/**
 *  Huffman tree node interface
 *  copied from https://opendsa-server.cs.vt.edu/ODSA/Books/Everything/html/Huffman.html
 */
interface HuffBaseNode{
    boolean isLeaf();
    int weight();
    HuffBaseNode left();
    HuffBaseNode right();
    char symbol();
}

/** 
 * Huffman tree node: Leaf class 
 * copied from https://opendsa-server.cs.vt.edu/ODSA/Books/Everything/html/Huffman.html
 */
class HuffLeafNode implements HuffBaseNode{
	//Storage Variable
    private int weight;
    private char symbol;
    private HuffBaseNode left;
    private HuffBaseNode right;

    //Constructor
    HuffLeafNode(char let, int wt){
    	left=null; right=null; symbol=let; weight=wt;
    }

    /** 
     * @return The left child 
     */
    public HuffBaseNode left() { 
    	return left; 
    }

    /** 
     * @return The right child 
     */
    public HuffBaseNode right() { 
    	return right; 
    }

    /** 
     * @return The symbol value 
     */
    public char symbol() { 
    	return symbol; 
    }

    /** 
     * @return The weight 
     */
   public int weight() {
    	return weight; 
    }

   /** 
    * Return true 
    */
   public boolean isLeaf(){ 
	   return true; 
	   }
}

/** 
 * Huffman tree node: Internal class 
 * copied from https://opendsa-server.cs.vt.edu/ODSA/Books/Everything/html/Huffman.html
 */
class HuffInternalNode implements HuffBaseNode{
	//Storage Variable
    private int weight;
    private char symbol;
    private HuffBaseNode left;
    private HuffBaseNode right;

    //Constructor 
    HuffInternalNode(HuffBaseNode l, HuffBaseNode r, int wt){
    	left=l; right=r; weight = wt; 
    }

    /** 
     * @return The left child 
     */
    public HuffBaseNode left() { 
    	return left; 
    }

    /** 
     * @return The right child 
     */
    public HuffBaseNode right() { 
    	return right; 
    }

    /** 
     * @return The symbol value 
     */
    public char symbol() { 
    	return symbol; 
    }

    /** 
     * @return The weight 
     */
    public int weight() {
    	return weight; 
    }

    /** 
     * @return false 
     */
    public boolean isLeaf(){ 
    	return false; 
    	}
}
