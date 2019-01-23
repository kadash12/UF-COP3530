import java.util.HashMap;
import java.util.Map;

/**
 * Stores the constructors and getters of program.
 * @author Johnny Li
 */
public class HuffTree{
	//copied from https://opendsa-server.cs.vt.edu/ODSA/Books/Everything/html/Huffman.html
	//*******************************************************
	public HuffBaseNode root;

	// Constructors
	HuffTree(){ 
		root= null;
	}
	HuffTree(char el, int wt){ 
		root = new HuffLeafNode(el, wt); 
	}
	HuffTree(HuffBaseNode l, HuffBaseNode r, int wt){ 
		root = new HuffInternalNode(l, r, wt); 
	}

	//returns root
    HuffBaseNode root() { 
    	return root; 
    }

    //returns the root weight
    int weight(){ 
    	return root.weight(); 
    }

    //compares two HuffTrees
    int compareTo(Object t) {
        HuffTree that = (HuffTree)t;
        if (root.weight() < that.weight()) return -1;
        else if (root.weight() == that.weight()) return 0;
        else return 1;
    }

    //returns symbol
    char symbol(){ 
    	return root.symbol(); 
    }
    //*******************************************************
    //Hash Map creation was an open suggestion by Academic Tutoring Center
    //Set Hash map
    private HashMap<Character, String> map = new HashMap<>();
    public HashMap getMap(){ 
    	return map; 
    }

}
