//Name: Xingya Ren 
//ID: 260784116
//No collaboration

package Assign1;
import java.io.*;
import java.util.*;


public class main {     

     
    public static void main(String[] args) {
    //TODO:build the hash table and insert keys using the insertKeyArray function.
    	
    	ArrayList<ArrayList<Integer>> hashTable = new ArrayList<ArrayList<Integer>>(); 
    int keys[] = {86, 85, 6, 97, 19, 66, 26, 14, 15, 49, 75, 64, 35, 54, 31, 9, 82, 29, 81, 13}; 
    	int keys1[] = {12, 14, 77, 74, 63, 21, 69, 13, 84, 93, 35, 89, 45, 60, 15, 57, 51, 18, 42, 62}; 
    	//A is given; A=554
    	//w is computed using the formula shown in class; w=10 
    	//seed doesn't matter
 	Chaining chain = new Chaining(10,1,554);
    Open_Addressing oA = new Open_Addressing (10,1,554); 
    int chainCollision = chain.insertKeyArray(keys); 
    int oACollision = oA.insertKeyArray(keys);

    
    System.out.println("******Printing the numbers of collisions*******");
    System.out.println("1st array of keys:");
    System.out.println("Chaining: "+ chainCollision); 
    System.out.println("Open Addressing: "+ oACollision); 
    System.out.println("\n");

    Chaining chain1 = new Chaining(10,1,683);
    Open_Addressing oA1 = new Open_Addressing (10,1,683); 
    int chainCollision1 = chain1.insertKeyArray(keys1); 
    int oACollision1 = oA1.insertKeyArray(keys1);
    	
    System.out.println("2nd array of keys:");
    System.out.println("Chaining: "+ chainCollision1); 
    System.out.println("Open Addressing: "+ oACollision1); 

    }
}

