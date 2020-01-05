//created by Xingya Ren

import java.util.*;

public class Kruskal{

    public static WGraph kruskal(WGraph g){

        /* Fill this method (The statement return null is here only to compile) */
        //takes a graph as input and returns  a new graph object that is the MST of the given graph
        //sort the list of nodes in ascending weights
    			WGraph minSpanTree = new WGraph(); 
    			DisjointSets setForTheGraph = new DisjointSets(g.getNbNodes()); //because we can't do union on a graph we turn it into a set 
    			//loop through the list of sorted edges 
    			for(Edge e: g.listOfEdgesSorted()) {
    				if(IsSafe(setForTheGraph, e)) {
    					//is safe to add  --> add it 
    					minSpanTree.addEdge(e); 
    					setForTheGraph.union(e.nodes[0], e.nodes[1]); 
    				}
    			}
    			return minSpanTree; 
    }

    public static Boolean IsSafe(DisjointSets p, Edge e){

        /* Fill this method (The statement return 0 is here only to compile) */
    		//nodes p; edge e 
    		//returns true if it is safe to add the edge e to this graph 
    				//false if not 
    		//Using DisjointSets from the previous question 
    		//DisjointSets has: int find(int i) which returns the representative 
    						//  int union(int i, int j) which merges two sets and return the rep 
        
    		//sort the nodes in ascending order 
    	    //an array list that holds the sorted edges 
    		int node1 = e.nodes[0];
    		int node2 = e.nodes[1]; 
    		int node1Rep, node2Rep; 
    		node1Rep = p.find(node1); 
    		node2Rep = p.find(node2); 
    		if(node1Rep == node2Rep) {
    			//in the same set --> not safe 
    			return false; 
    		}else{
    			return true; //different rep's --> different sets 
    		}
    }

    public static void main(String[] args){

        String file = args[0];
        WGraph g = new WGraph(file);
        WGraph t = kruskal(g);
        System.out.println(t);

   } 
}
