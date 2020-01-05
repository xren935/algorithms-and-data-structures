//created by Xingya Ren

import java.io.*;
import java.util.*;


/****************************
*
* COMP251 template file
*
* Assignment 2, Question 1
*
*****************************/


public class DisjointSets {

    private int[] par;
    private int[] rank; 
    
    /* contructor: creates a partition of n elements. */
    /* Each element is in a separate disjoint set */
    DisjointSets(int n) {
        if (n>0) {
            par = new int[n];
            rank = new int[n];
            for (int i=0; i<this.par.length; i++) {
                par[i] = i;
            }
        }
    }
    
    public String toString(){
        int pari,countsets=0;
        String output = "";
        String[] setstrings = new String[this.par.length];
        /* build string for each set */
        for (int i=0; i<this.par.length; i++) {
            pari = find(i);
            if (setstrings[pari]==null) {
                setstrings[pari] = String.valueOf(i);
                countsets+=1;
            } else {
                setstrings[pari] += "," + i;
            }
        }
        /* print strings */
        output = countsets + " set(s):\n";
        for (int i=0; i<this.par.length; i++) {
            if (setstrings[i] != null) {
                output += i + " : " + setstrings[i] + "\n";
            }
        }
        return output;
    }
    
    /* find resentative of element i */
    public int find(int i) {

        /* Fill this method (The statement return 0 is here only to compile) */
        if(par[i]==i){
        		//i's parent is itself --> self-loop 
        		return i; 
        }else {
        		int result = find(par[i]);
        		par[i] = result; 
        		return result; //return the rep 
        }    
    }

    /* merge sets containing elements i and j */
    public int union(int i, int j) {
    
        /* Fill this method (The statement return 0 is here only to compile) */
        //get the ranks of i and j 
    		int iRep = this.find(i); 
    		int jRep = this.find(j); 
    		//if elements are in the same set, we do nothing and return the rep
    		if(iRep==jRep) {
    			return iRep; 
    		}
    		//get the ranks 
    		int iRank = 0;
    		int jRank = 0;
    		iRank = rank[iRep];
    		jRank = rank[jRep]; 
    		//compare the ranks to see who should be the new parent 
    		if(iRank == jRank) {
    			//merge i into j 
    			this.par[iRep] = jRep; 
    			rank[jRep]++; //increment the rank by one 
    			return jRep; 
    		}else if(iRank < jRank) {
    			//merge i into j
    			this.par[iRep] = jRep; 
    			return jRep; 
    		}else {
    			//merge j into i 
    			this.par[jRep] = iRep; 
    			return iRep; 
    		}
    }
    
    public static void main(String[] args) {
        
        DisjointSets myset = new DisjointSets(6);
        System.out.println(myset);
        System.out.println("-> Union 2 and 3");
        myset.union(2,3);
        System.out.println(myset);
        System.out.println("-> Union 2 and 3");
        myset.union(2,3);
        System.out.println(myset);
        System.out.println("-> Union 2 and 1");
        myset.union(2,1);
        System.out.println(myset);
        System.out.println("-> Union 4 and 5");
        myset.union(4,5);
        System.out.println(myset);
        System.out.println("-> Union 3 and 1");
        myset.union(3,1);
        System.out.println(myset);
        System.out.println("-> Union 2 and 4");
        myset.union(2,4);
        System.out.println(myset);
        
    }

}
