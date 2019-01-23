
/**
 * Construct the Node that will be used in the linked list.
 * Each node contain the position and the value of the stored data in the matrix.
 * @author Johnny Li
 */
class Node{
    public Node next;      //Data value of Node
    public int col;        //Column position in matrix
    public int row;			//Row position in matrix
    public int data;       //Column position in matrix

    //Constructor of Node class
    public Node(int col, int data){
        this.col=col;
        //this.row=row;		Not needed
        this.data=data;
        this.next=null;
    }
}

/**
 * Construct the linked list and methods of inferences.
 * @author Johnny Li
 */
public class LinkedList {
    private int size;	//Size of matrix
    private Node head;	//First Node
    private Node tail;	//Last Node

  //Constructor of Linked List class
    public LinkedList(){
        this.size=0;
        this.head=null;
        this.tail=null;
    }
    
    //*********Getters and Setters**********************//
    //Set max size of Linked List
    public void setSize(int sizeT){
        this.size=sizeT;
    } 
    
    //Get max size of Linked List
    public int getSize(){
        return size;
    }

    //Set value of head Node
    public void setHead(Node headT){
        this.head=headT;
    }
    
    //Get value of head Node
    public Node getHead(){
        return this.head;
    }

    //Set value of Tail Node
    public void setTail(Node tailT){
        this.tail=tailT;
    }
    
    //Get value of Tail Node
    public Node getTail(){
        return this.tail;
    }
  //**************************************************//
    
    //Removes all nodes from the list.
    public void clear(){
    	//Check if it is an empty list 
        if(head==null){                    
        	//Already empty
            return;
        }
        else{
        	//Make all entries 0
        	while (head!=null){
        		head.data=0;
        		head=head.next;
        	}
        }
    }
    
    //Add new element to linked list (from left-smallest to right- largest column)
    //Used as reference: https://www.geeksforgeeks.org/linked-list-set-2-inserting-a-node/
    public void addElement(int row, int col, int data){
        Node node = new Node(col,data);
        //Check if it is an empty list 
        if(head==null){                     
            tail=node;
            head=node;
            return;
        }
        //Check if node is the last node and make it the tail
        else if(col > tail.col){ 
            tail.next=node;
            tail=node;
            return;
        }
        //Set node to ordered columns
        else{        
            Node inst = head;
            Node prev = head;
            //Check if there is a next node
            while (inst != null){
            	//Check if the position is already taken
                if(col == inst.col){ 
                    inst.data=data;     
                    //Update data
                    return;
                }
                //Check if next column is bigger than the new one
                else if(col < inst.col){
                    if(head.next == null){
                        head=node;              
                        //Update head reference
                        head.next=inst;
                        tail=head.next;
                        return;
                    }
                    else if(head.next != null){
                    	//Update 'next' reference
                        prev.next=node;
                        node.next=inst;
                        return;
                    }
                }
                prev = inst;
                inst=inst.next;
            }
        }
    }

    //Returns the value of an element at a specific position
    public int getElement(int row,int col){
    	//Storage variable
        int x=0;
        Node curr = head;
        for(int i=0; i<size; i++) {
        	//If it is not a empty list and the head column matches, return element
            if((curr!=null) && (curr.col==i)){
                x = curr.data;
            }
        }
        return x;
    }

    //Remove element from linked list
    public void removeElement(int row, int col){
    	//Store head to be updated later
    	Node inst = head;
        Node prev = head;
        //Return if position is past last column
        if(tail.col < col){
        	//Already empty
            return;
        }
        //Check if it is empty
        while(inst != null){
        	//Check if position matches
            if(col==inst.col){
            	//If last node, update previous node.
                if(inst.next == null){
                    prev.next = null;
                    return;
                }
              //Update node reference if in middle
                prev.next=inst.next;      
                return;
            }
            //Update to next node
            prev=inst;          
            inst=inst.next;
        }
    }

    //Testing Purpose
    //Setup linked list for print, adds all element to a string
   /* public String print(){
        String temp="";
        Node node=head;
        for(int i=0; i<size; i++) {
        	//If it is not a empty list and the head column matches, return element in string
            if( (node!=null) && (node.col==i)){
            	//Setup linked list for print, adds all element to a string
                temp += node.data + "\t";
                node = node.next;
            }
            else{
            	//For empty column adds zero
                temp += "0\t";            
            }
        }
        return temp;
    }*/

    //Form string of non-zero element in the form "row column data"
    public String toString(int row){
        String temp="";
        Node curr=head;
        for(int i=0; i<size; ++i) {
        	//If it is not a empty list and the head column matches, return element
            if((curr!=null) && (curr.col==i)){
                temp += row + " " + curr.col + " " + curr.data + "\n";
                curr = curr.next;
            }
        }
        return temp;
    }

	//Reduce dimension
	//Creates a minor version of linked list matrix
    //Constructed with TA assistance.
	public LinkedList minor(int row, int col) {
	    LinkedList minor = new LinkedList();
	    Node mNode = minor.head;
	    minor.setSize(size-1);
	    Node node = head;
	    //Check if it is empty
	    while(node != null){
	    	//If column is less than removed column, add node to minor
	        if(col>node.col){
	        	//Check if it is empty
	            if(minor.head == null){
	                minor.head=new Node(node.col,node.data);
	                mNode=minor.head;
	            }
	            else if (minor.head != null){
	                mNode.next=new Node(node.col,node.data);
	                mNode = mNode.next;
	            }
	        }
	        //If column is greater than removed column, remove node to minor
	        else if(col<node.col){
	            if(minor.head == null){
	                minor.head=new Node(node.col,node.data);
	                minor.head.col=minor.head.col-1;
	                mNode=minor.head;
	            }
	            else if(minor.head != null){
	                mNode.next=new Node(node.col,node.data);
	                mNode=mNode.next;
	                //Reduce column
	                mNode.col=mNode.col-1;     
	            }
	
	        }
	        node=node.next;
	    }
	    return minor;
	}

}
