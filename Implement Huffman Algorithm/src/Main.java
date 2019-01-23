import java.io.File;
import java.io.FileNotFoundException;

//Testing Class
public class Main{
    public static void main (String args[]) throws FileNotFoundException, Exception {
    	//create Huffman Tree
        HuffTree temp = new HuffTree();
        HuffmanCoding tree = new HuffmanEncoder();
        //Input Illiad text file
        File inputFile = new File("test.txt");
        //Test printing
        //Frequency
        System.out.println(tree.getFrequencies(inputFile));
        tree.getFrequencies(inputFile);
        //Build Tree
        temp = tree.buildTree(inputFile);        
        String x =tree.traverseHuffmanTree(tree.buildTree(inputFile));
        System.out.println(x);
        //Encode
        String output = tree.encodeFile(inputFile, tree.buildTree(inputFile));
        System.out.println(output);
        //Decode
        String decode = tree.decodeFile(output,tree.buildTree(inputFile));
        System.out.println(decode);
        //Decoder Build
        /*StringBuilder sb = new StringBuilder(output);
        StringBuilder d = new StringBuilder(decode);
        StringBuilder decoderBuild = tree.decodeBuilder(sb,tree.buildTree(inputFile),d);*/
    }
}