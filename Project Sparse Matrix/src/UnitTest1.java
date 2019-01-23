/*import static org.junit.Assert.*;

import org.junit.Test;
//Sourcode from la4j.org

import org.la4j.matrix.sparse.CCSMatrix;
import java.util.Random;
import java.lang.Math;

class Pair {
 public CCSMatrix key;
 public SparseMatrix test;
 private double CCSDet ;
 private int SparseDet;
 private int absMax;
 private int size;
 private int[][] array2d;

 
 public Pair(int size, double sparcity, int absMax) {
     this.absMax = absMax;
     this.size = size;
     int m;
     int n;
     int nonZeroN = (int) (size*size*sparcity);
     this.key = new CCSMatrix();
     this.key = (CCSMatrix) key.blankOfShape(size, size);
     this.test = new SparseMatrix(size);
     Random rnd = new Random();
     array2d = new int[size][size];
     if (sparcity == 1.0) {
         for (int i=0; i<size;i++) {
             for (int j=0; j<size; j++) {
                 this.add(rnd, i, j, true);
             }
         }
         return;
     }
     for (int i=0; i<nonZeroN; i++) {
         m = rnd.nextInt(size);
         n = rnd.nextInt(size);
         this.add(rnd, m, n, false);
     }
 }

 private void add(Random rnd, int m, int n, boolean dense) {
     boolean negative = rnd.nextBoolean(); 
     int value = rnd.nextInt(absMax);
     if (negative){
         value = (-1) * value;
     }
     while (dense && value == 0) {
         value = rnd.nextInt(absMax);
     }
     array2d[m][n] = value;
     key.set(m,n,value);
     test.addElement(m,n, value);
 }

 public void print() {
     System.out.println(test.toString());
 }

 public void print2d() {
     String a = new String();
     for (int i=0; i<this.size; i++) {
         for (int j=0; j<this.size; j++) {
            a += "\t" + Integer.toString(array2d[i][j]);
         }
         a += '\n';
     }
     System.out.println(a);
 }

 public int getCCSDet() {return (int) Math.round(this.CCSDet);}
 public int getSparseDet() {return this.SparseDet;}

 public void determinant() {
     this.CCSDet = this.key.determinant();
     this.SparseDet = this.test.determinant();
 }


}

class UnitTest {
 /*
  * The Pair class creates identical matrices from your
  * SparseMatrix class and the la4j library and calculates
  * the determinant for both. for Pair(size, sparcity, absMax)
  *
  * size is the n*n size of the square matrix.
  *
  * sparcity determines how sparse the matrix will be
  * you can input values 0<x<=1. Values closer to one are
  * denser and values closer to zero are less dense. A 
  * sparcity of 1 means no zeros will be in the matrix.
  *
  * absMax sets the minimum and maximum values of the matrix
  * from -absMax <= x <= absMax.
  *
  * print() prints the string from SparseMatrix.toString().
  *
  * print2d() prints a 2d version of the matrix.
  *
  * determinant() calculates and stores the determinant.
  *
  * getCCSDet() returns the det from the linear algebra library.
  *
  * getSparseDet() returns the det from the SparseMatrix object.
  *
  * You can fiddle around with it however you like. 
  * Assuming your implementation is right, after the 
  * calculations get too large, like size=8, sparcity=0.9
  * some of the determinant results will be off by one
  * because of the roundoff error of the floating arithmatic.
  * Your program is still accurate.
  */

/* public static void main(String[] args) {
     for (int i=0; i<20; i++) {
         Pair a = new Pair(3, 0.9,100);
         a.determinant();
         int b, c;
         b = a.getCCSDet();
         c = a.getSparseDet();
         if (b != c) {
             a.print2d(); 
             System.out.println(a.getCCSDet());
             System.out.println(a.getSparseDet());
             System.out.println("");
         }
     }
 }
}*/
