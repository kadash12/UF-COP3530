public class Test {
	 
	/**
    This is a small set of testing code to make sure you are implementing our SparseInterface.
    You should be getting all "true" for the output

    This is NOT the full extent of our testing, but passing these cases are essential to
    passing our full tests.
	  */
	public static void main(String[] args){
		SparseInterface myTest = new SparseMatrix();
		
		myTest.setSize(3, 3);
	
	    System.out.println("11111Num Rows is 3: " + (myTest.getNumRows() == 3));
	    System.out.println("22222Num Cols is 3: " + (myTest.getNumCols() == 3));
	
		myTest.addElement(0, 0, 16);
		
		myTest.addElement(0, 1, 4);
		
		myTest.removeElement(0,1);
	
		String correctString = "0 0 16\n";
		
		System.out.println("33333toString is correct: " + correctString.equals(myTest.toString()));
		
		myTest.removeElement(0,0);
	    
	
	    myTest.addElement(2,2,4);
	
	    myTest.addElement(1,0,-3);
	
	    correctString = "1 0 -3\n2 2 4\n";
	
		System.out.println("44444toString is correct: " + correctString.equals(myTest.toString()));
		     
		
		myTest.removeElement(2, 2);
		
		myTest.removeElement(1, 0);
	
	    myTest.addElement(0, 0, 0);
	
	    correctString = "";
	
		//Because we are not storing 0 values in the matrix the toString should reflect an "empty" (all 0) matrix.
		System.out.println("66666toString is correct: " + correctString.equals(myTest.toString()));
		
		myTest.addElement(0, 1, 3);
		
		myTest.addElement(0, 1, 0);
		
		correctString = "";
	
		//Note that adding 0 to the matrix overwrites the data at that position to 0 as defined in the interface description
		//Because we are not storing 0 values, we can remove the element at that position.
		System.out.println("77777toString is correct: " + correctString.equals(myTest.toString()));
	
	    myTest.addElement(0, 0, 16);
	    myTest.addElement(0, 1, 4);
	    myTest.addElement(1, 1, 9);
	    myTest.addElement(2, 2, 7);
	    
	    SparseInterface addTest1 = new SparseMatrix();
	    addTest1.setSize(3, 3);
	    addTest1.addElement(0, 0, 1);
	    addTest1.addElement(1, 1, 2);
	    addTest1.addElement(2, 2, 3);
	    //Extra Testing
	   /* addTest1.setSize(4, 2);
	    addTest1.addElement(0, 0, 1);
		addTest1.addElement(1, 0, 3);
		addTest1.addElement(2, 0, 5);
		addTest1.addElement(3, 0, 7);

		addTest1.addElement(0, 1, 2);
		addTest1.addElement(1, 1, 4);
		addTest1.addElement(2, 1, 6);
		addTest1.addElement(3, 1, 8);*/
	    
	    SparseInterface addTest2 = new SparseMatrix();
	    addTest2.setSize(3, 3);
	    addTest2.addElement(0, 0, 3);
	    addTest2.addElement(0, 0, 2);
	    addTest2.addElement(0, 0, 1);
	    //Extra Testing
	   /* addTest2.setSize(4, 2);
	    addTest2.addElement(0, 0, 9);
		addTest2.addElement(1, 0, 11);
		addTest2.addElement(2, 0, 13);
		addTest2.addElement(3, 0, 15);

		addTest2.addElement(0, 1, 10);
		addTest2.addElement(1, 1, 12);
		addTest2.addElement(2, 1, 14);
		addTest2.addElement(3, 1, 16);*/
	    
	    addTest1.toString();
	    System.out.println("addTest1");
	    addTest2.toString();
	    System.out.println("addTest2");
	    
	    SparseInterface addTest3 = addTest1.addMatrices(addTest2);
	    
	    System.out.println("88888added matrix: (addTest3)\n"+addTest3.toString());
	
		SparseInterface multiplyTest1 = new SparseMatrix();
		/*multiplyTest1.setSize(3, 3);
		multiplyTest1.addElement(0, 0, 1);
		multiplyTest1.addElement(0, 1, 1);
		multiplyTest1.addElement(0, 2, 1);*/
		//Extra Testing 
		multiplyTest1.setSize(5, 1);
		multiplyTest1.addElement(0, 0, 1);
		multiplyTest1.addElement(1, 0, 2);
		multiplyTest1.addElement(2, 0, 3);
		multiplyTest1.addElement(3, 0, 4);
		multiplyTest1.addElement(4, 0, 5);
		
		SparseInterface multiplyTest2 = new SparseMatrix();
		/*multiplyTest2.setSize(3, 3);
		multiplyTest2.addElement(0, 0, 1);
		multiplyTest2.addElement(1, 0, 1);
		multiplyTest2.addElement(2, 0, 1);
		
		multiplyTest2.addElement(0, 1, 2);
		multiplyTest2.addElement(1, 1, 1);
		multiplyTest2.addElement(2, 1, 1);
		
		multiplyTest2.addElement(0, 2, 3);
		multiplyTest2.addElement(1, 2, 1);
		multiplyTest2.addElement(2, 2, 1);*/
		//Extra Testing
		multiplyTest2.setSize(1, 2);
		multiplyTest2.addElement(0, 0, 7);
		multiplyTest2.addElement(0, 1, 8);
		
		System.out.println("multiplyTest1");
		multiplyTest1.toString();
		System.out.println("multiplyTest2");
		multiplyTest2.toString();
		
		SparseInterface multiplyTest3 = multiplyTest1.multiplyMatrices(multiplyTest2);
		
		System.out.println("99999multiplied matrix: (multiplyTest3) \n"+multiplyTest3.toString());
		
		SparseInterface test1 = new SparseMatrix();
		    test1.setSize(3, 4);
		SparseInterface test2 = new SparseMatrix();
		    test2.setSize(4, 5);
		
		String x=" ";
		SparseInterface test3 = test1.addMatrices(test2);  //should return null
		if (test3==null){
			x="NULL";
		}
	    System.out.println("10101010added matrix: (test3) \n"+x);

		SparseInterface test4 = test1.multiplyMatrices(test2); //should work
		if (test4==null){
			x="NULL";
		}
		System.out.println("111111111111multiplied matrix: (test4) \n"+x);

		SparseInterface test5 = test2.multiplyMatrices(test1); //should return null
		if (test5==null){
			x="NULL";
		}
		System.out.println("12121212multiplied matrix: (test5) \n"+x);
    }
}
