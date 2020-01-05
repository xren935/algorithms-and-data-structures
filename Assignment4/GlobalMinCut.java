package Assign4; 
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
//created by Xingya Ren 

/****************************
 *
 * COMP251 template file
 *
 * Assignment 4
 *
 *****************************/

public class GlobalMinCut {

    static Random random;

    /**
     * One run of the contraction algorithm
     * Returns the min cut found
     *
     * @param   graph - the graph to find min cut of
     * @return  two ArrayLists of characters (placed together in another ArrayList)
     *          representing the partitions of the cut
     */
    public static ArrayList<ArrayList<Character>> global_min_cut(Graph graph) {
        ArrayList<ArrayList<Character>> cut = new ArrayList<ArrayList<Character>>();

        // For each node v, we will record
        // the list S(v) of nodes that have been contracted into v
        Map<Character, ArrayList<Character>> S = new HashMap<Character, ArrayList<Character>>();


        // TODO: Initialize S(v) = {v} for each v
        int numOfNodes = graph.getNbNodes(); 
        ArrayList<Character> nodes = new ArrayList<Character>();
        nodes = graph.getNodes(); 
        for(int i=0; i<numOfNodes; i++) {
        		ArrayList<Character> v = new ArrayList<Character>(); 
        		Character c = nodes.get(i); 
        		v.add(c); 
        		S.put(c, v); //set the value of each character to {the value} 
        		//System.out.println(S.toString()); S={a=[a], b=[b],....} 
        }
        	
        	while (graph.getNbNodes() > 2) {
            // select an edge randomly (DO NOT CHANGE THIS LINE)
            Edge edge = graph.getEdge(random.nextInt(graph.getNbEdges()));

            // TODO: fill in the rest
            //contract the edge 
            graph.contractEdge(edge); 
            //add all nodes in u to v 
        		for(Object o: S.keySet()) { //loop through the keys 
        			if((Character) o == (edge.node_1)) {
        				//when it is u
        				//add all the nodes in u to v 
        				ArrayList<Character> nodesToAdd = new ArrayList<Character>();
        				nodesToAdd.addAll(S.get((Character)o)); 
        				(S.get(edge.node_2)).addAll(nodesToAdd); //add all the nodes in u to v and put it back to the map 
        				S.put((Character) o, null); //clear u 
        			}
        		}
        }
        	//return S(u) S(v) 
        	 	ArrayList<Character> twoNodes = new ArrayList<Character>(); 
        		twoNodes = graph.getNodes(); //u and v 
        		Character nodeU = 0; //garbage value for initialization 
        		Character nodeV = 1;
        		//getting the two nodes 
        		//not sure if they will be the first two, so...
        		for(int i=0; i<twoNodes.size(); i++) {
        			if(twoNodes.get(i) != null) {
        				nodeU = twoNodes.get(i); 
        				twoNodes.set(i, null);
        				break;
        			}
        		}
        		for(int i=0; i<twoNodes.size(); i++) {
        			if(twoNodes.get(i) != null) {
        				nodeV = twoNodes.get(i); 
        				twoNodes.set(i, null);
        				break;
        			}
        		}
        		//return S(u) and S(v) 
        		cut.add(S.get(nodeU)); 
        		cut.add(S.get(nodeV)); 
        		return cut; 
        
        // TODO: assemble the output object
    }


    /**
     * repeatedly calls global_min_cut until finds min cut or exceeds the max allowed number of iterations
     *
     * @param graph         the graph to find min cut of
     * @param r             a Random object, don't worry about this, we only use it for grading so we can use seeds
     * @param maxIterations some large number of iterations, you expect the algorithm to have found the min cut
     *                      before then (with high probability), used as a sanity check / to avoid infinite loop
     * @return              two lists of nodes representing the cut
     */
    public static ArrayList<ArrayList<Character>> global_min_cut_repeated(Graph graph, Random r, int maxIterations) {
        random = (r != null) ? r : new Random();

        ArrayList<ArrayList<Character>> cut = new ArrayList<ArrayList<Character>>();
        int count = 0;
        do  {
            
			// TODO: use global_min_cut()
        			//create a copy of the graph 
        			Graph copyGraph = new Graph(graph); 
        			//graph = copyGraph; 
        			cut = global_min_cut(copyGraph); 
            ++ count;


        } while (get_cut_size(graph, cut) > graph.getExpectedMinCutSize() && count < maxIterations);
        //update the graph ...? 
        if (count > maxIterations) System.out.println("Forced stop after " + maxIterations + " iterations... did something go wrong?");
        return cut;
        
    }

    /**
    * @param graph  the original unchanged graph
    * @param cut    the partition of graph into two lists of nodes
    * @return       the number of edges between the partitions
    */
    public static int get_cut_size(Graph graph, ArrayList<ArrayList<Character>> cut) {
        ArrayList<Edge> edges = graph.getEdges();
        int cut_size = 0;
        for (int i = 0; i < edges.size(); ++i) {
            Edge edge = edges.get(i);
            if (cut.get(0).contains(edge.node_1) && cut.get(1).contains(edge.node_2) ||
                    cut.get(0).contains(edge.node_2) && cut.get(1).contains(edge.node_1)) {
                ++cut_size;
            }
        }
        return cut_size;
    }
}
