//Name: Xingya Ren 
//ID: 260784116
//No Collaboration
package Assign1;
import java.io.*;
import java.util.*;

public class Open_Addressing {
     public int m; // number of SLOTS AVAILABLE
     public int A; // the default random number
     int w;
     int r;
     public int[] Table;

     protected Open_Addressing(int w, int seed, int A) {
         this.w = w;
         this.r = (int) (w-1)/2 +1;
         this.m = power2(r);
         if (A==-1){
            this.A = generateRandom((int) power2(w-1), (int) power2(w),seed);
         }
        else{
            this.A = A;
        }
         this.Table = new int[m];
         for (int i =0; i<m; i++) {
             Table[i] = -1;  
         }
         
     }
     
                 /** Calculate 2^w*/
     public static int power2(int w) {
         return (int) Math.pow(2, w);
     }
     public static int generateRandom(int min, int max, int seed) {     
         Random generator = new Random(); 
                 if(seed>=0){
                    generator.setSeed(seed);
                 }
         int i = generator.nextInt(max-min-1);
         return i+min+1;
     }
        /**Implements the hash function g(k)*/
        public int probe(int key, int i) {
        //TODO: implement this function and change the return statement.
        int probeResult; 
        int theRExponent; 
        int theWExponent; 
        theWExponent = (int) Math.pow(2, w); 
        theRExponent = (int) Math.pow(2, r); 
        probeResult = (((A*key) % theWExponent) >> (w-r)); 
        probeResult = (probeResult+i) % theRExponent; 
        return probeResult;
     }
     
     
     /**Inserts key k into hash table. Returns the number of collisions encountered*/
        public int insertKey(int key){
            //TODO : implement this and change the return statement.
        		//get the key's hash code
        		//see if that slot is empty
        			//isEmpty then insert (return counter=0 
        			//notEmpty then try that slot+1, +2, ...till the end of array (counter++ 
        			//try the 0th, 1th, ... that slot-1 
        				//all full --> return
        		int i = 0; 
        		int j = 0; 
        		for(;i<m;) {
        			j = probe(key,i);
        			if (Table[j] == -1) {
        				Table[j] = key; 
        				return i;
        			}else {
        				i = i+1; 
        			}
        		}
        		//gets here: tried all the ones at the hashCode and after
        		//int tries; 
        		//i = 0; 
        		//j = probe(key, i); //the original hashCode
        		//tries = m-j;
        		//check the slots that come before this 
        		return i; 
             
        } 
        
        /**Sequentially inserts a list of keys into the HashTable. Outputs total number of collisions */
        public int insertKeyArray (int[] keyArray){
            //TODO
            int collision = 0;
            for (int key: keyArray) {
                collision += insertKey(key);
            }
            return collision;
        }
            
         /**Inserts key k into hash table. Returns the number of collisions encountered*/
        public int removeKey(int key){
            //TODO: implement this and change the return statement
               if (key == -1) {
            	   		throw new IllegalArgumentException("Key can't be negative");       	   
               }
               //get the hashCode
               int hashCode = probe(key,0); 
               //if the key is found 
               if(Table[hashCode] == key) {
            	   		//remove it
            	   		Table[hashCode] = -2; //-2 for deleted
            	   		return 0; 
               }else {
            	   		//keep looking
            	   		for(int i=1; i<m;) {
            	   			hashCode = probe(key, i); 
            	   			if(Table[hashCode] == key) {
            	   				//found it
            	   				Table[hashCode] = -2; 
            	   				return i; 
            	   			}else if(Table[hashCode] == -1){ 
            	   				//found an empty slot
            	   				//k is not in the tableu
            	   				return i; 
            	   			}else {
            	   				i = i+1; 
            	   			}
            	   		}
            	   		//gets here ---> looked through 
            	   		//k is not in the table
            	   		return m; 
               }
        }
}
