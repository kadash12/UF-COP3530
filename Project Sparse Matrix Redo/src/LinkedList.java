/**
 * Construct the Node that will be used in the linked list.
 * Each node contain the position and the value of the stored data in the matrix.
 * @author Johnny Li
 */
class Node{
	//Variables of node
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
	//Variables of Linked List
    private int numRows;	//row of matrix
    private int numCols;	//columns of matrix
    private Node head;	//First Node
    private Node tail;	//Last Node

  //Constructor of Linked List class
    public LinkedList(){
        this.numCols=0;
        this.head=null;
        this.tail=null;
    }
    
    //*********Getters and Setters**********************//
    //Set max row of Linked List
    public void setnumRows(int row){
        this.numRows=row;
    } 
    
    //Set max row of Linked List
    public void setnumCols(int col){
        this.numCols=col;
    } 
    
    //Get max rows of Linked List
    public int getNumRows(){
        return numRows;
    }
    
   //Get max columns of Linked List
    public int getNumCols(){
        return numCols;
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
    	//Node to be inserted 
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
            //Check if the node is null
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
        
        //Transverse linked list
        for(int i=0; i<numCols; i++) {
        	//If it is not a empty list and the head column matches, return element
            if((curr!=null) && (curr.col==col)){
                x = curr.data;
            }
            //If it is not empty, increment to next node
            else if(curr!=null){
            	curr=curr.next;
            }
        }
        return x;
    }

    //Remove element from linked list
    //Taken with reference from https://www.geeksforgeeks.org/linked-list-set-3-deleting-node/
    public void removeElement(int row, int col){
    	//Store head to be updated later
        Node inst = head;
        Node prev = null;
 
        //Check if it is empty
        if (inst != null){
        	//Check if position matches
        	if(inst.col == col){
	        	//Update head
	            head = inst.next;
	            return;
        	}
        }
        // If empty
        else if (inst == null){
            return;
            }
        // Search for column in middle 
        //Update node reference if in middle
        while (inst != null && inst.col != col){
            prev = inst;
            inst = inst.next;
        }    
        //Update to previous node
        if (prev.next != null)
        prev.next = inst.next;
    }

    //Form string of non-zero element in the form "row column data"
    public String toString(int row){
    	//Variable 
        String temp="";
        Node curr=head;
        
        //Loop through all to obtain values
        for(int i=0; i<numCols; ++i) {
        	//If it is not a empty list and the head column matches, return element
            if((curr!=null) && (curr.col==i)){
                temp += row + " " + curr.col + " " + curr.data + "\n";
                //Increment
                curr = curr.next;
            }
        }
        return temp;
    }
}
