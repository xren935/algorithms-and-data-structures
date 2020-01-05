//created by Xingya Ren 

public class BellmanFord{

	
	/**
	 * Utility class. Don't use.
	 */
	public class BellmanFordException extends Exception{
		private static final long serialVersionUID = -4302041380938489291L;
		public BellmanFordException() {super();}
		public BellmanFordException(String message) {
			super(message);
		}
	}
	
	/**
	 * Custom exception class for BellmanFord algorithm
	 * 
	 * Use this to specify a negative cycle has been found 
	 */
	public class NegativeWeightException extends BellmanFordException{
		private static final long serialVersionUID = -7144618211100573822L;
		public NegativeWeightException() {super();}
		public NegativeWeightException(String message) {
			super(message);
		}
	}
	
	/**
	 * Custom exception class for BellmanFord algorithm
	 *
	 * Use this to specify that a path does not exist
	 */
	public class PathDoesNotExistException extends BellmanFordException{
		private static final long serialVersionUID = 547323414762935276L;
		public PathDoesNotExistException() { super();} 
		public PathDoesNotExistException(String message) {
			super(message);
		}
	}
	
    private int[] distances = null;
    private int[] predecessors = null;
    private int source;

    BellmanFord(WGraph g, int source) throws BellmanFordException{
        /* Constructor, input a graph and a source
         * Computes the Bellman Ford algorithm to populate the
         * attributes 
         *  distances - at position "n" the distance of node "n" to the source is kept
         *  predecessors - at position "n" the predecessor of node "n" on the path
         *                 to the source is kept
         *  source - the source node
         *
         *  If the node is not reachable from the source, the
         *  distance value must be Integer.MAX_VALUE
         *  
         *  When throwing an exception, choose an appropriate one from the ones given above
         */
        
        /* YOUR CODE GOES HERE */
    		//a list of edges 
    		//initialize distances 
    		int numOfVertices = g.getNbNodes();
		int numOfEdges = g.getEdges().size(); 
    		
		predecessors = new int[numOfVertices]; 
		distances = new int[numOfVertices]; 
		
    		for(int i=0; i<distances.length; i++) {
    			distances[i] = Integer.MAX_VALUE; 
    		}
    		distances[source] = 0; 
    		//initialize predecessors 
    		for(int i=0; i<predecessors.length; i++) {
    			predecessors[i] = -1; //garbage value to represent emptiness 
    		}
    		
    		
    		
    		for(int i=1; i<numOfVertices; i++) {
    			//for each edge 
    			//relax it 
    			for(int j=0; j<numOfEdges; j++) {
    				
    				int[]nodes = g.getEdges().get(j).nodes;
    				int u = nodes[0]; //source of the edge 
    				int v = nodes[1]; //destination of the edge 
    				int weight = g.getEdges().get(j).weight;
    		
    				if(((long)(distances[u])) + weight < (long)distances[v]) {
    					//update to shortest path
    					distances[v] = distances[u] + weight; 
    					predecessors[v] = u; 
    				}
    			}
    		}
    		
    		//Check for negative-weight cycles
    		for(int j=0; j<numOfEdges; j++) {
    			int[]nodes = g.getEdges().get(j).nodes;
    			int u = nodes[0]; //source 
    			int v = nodes[1]; //destination 
    			int weight = g.getEdges().get(j).weight; 
    			if( (long)distances[u] + weight < (long) distances[v]) {
					//negative cycle, throws exception 
					throw new BellmanFordException("NEGATIVE CYCLE");
			}
    		}
    		
    }
    
    

    public int[] shortestPath(int destination) throws BellmanFordException{
        /*Returns the list of nodes along the shortest path from 
         * the object source to the input destination
         * If not path exists an Exception is thrown
         * Choose appropriate Exception from the ones given 
         */

        /* YOUR CODE GOES HERE (update the return statement as well!) */
    		//private int[] distances = null;
        //private int[] predecessors = null;
    		//for all edges 
    		//check the list of predecessors --> source 
    		 
    		if(predecessors[destination] == source) {
    			int shortestPath[]; 
    			shortestPath = new int[2]; 
    			shortestPath[0] = source; 
    			shortestPath[1] = destination; 
    			return shortestPath; 
    		}else if (predecessors[destination] == -1) {
    			throw new PathDoesNotExistException("PATH DOES NOT EXIST"); 
    		}
    			//first add destination to the array
    			int shortestPath[]; 
    			shortestPath = new int[predecessors.length]; 
    			
    			//initialize shortestPath
    			for(int i=0; i<shortestPath.length; i++) {
    				shortestPath[i] = -1; 
    			}
    			
    			int parent = predecessors[destination]; 
    			
    			shortestPath[0] = destination; //destination added to the first slot
    			int i=1;
    	   			
    			//add the parents of the destination node to this array 
    				//if is -1 then this means the node is pointing to source  

    					for(i=1; i<shortestPath.length; i++) {
        				//trace back
        				//add parent to the list of shortestPath 
        				shortestPath[i] = parent; 
        				//System.out.println("parent is:"+ parent); 
        				//update parent 
        				
        				if(parent != source) {
        					System.out.println("parentsss:");
        					System.out.println(parent);
        					parent = predecessors[parent]; 
        				}else {
        				if(parent == source) {
        						//reached the end 
        						//return shortestPath; 		
        						int shortestPath_CorrectLength[] = new int[i+1]; 
        						//System.out.println("i here is: "+ i); 
        						//System.out.println("the no of nodes in a shortset path is"+ i); 
        	    						for(int j=0; j<(i+1);) {
        	    							for(int k=shortestPath_CorrectLength.length-1; k>=0; k--) { 
        	    								if(shortestPath[j] != -1) {
        	    									shortestPath_CorrectLength[k] = shortestPath[j]; 
        	    									j++; 
        	    								}else {
        	    									j++; 
        	    								}
        	    							}
        	    						}
        						}	
    					}	
    					}
    			
    				//i is the number of steps 
    					//System.out.println("i is "+ i); 
    					int counter=0; 
    					for(counter=0; counter<shortestPath.length; counter++) {
    						if(shortestPath[counter] == 0) {
    							break; //counter is the number of vertices in shortest path -1 
    						}
    					}
    					
    					if(parent == source) {
    						int numberOfNodes = counter+1; 
    						//now we have the number of nodes in the shortest path 
    						int shortestPath_CorrectLength[] = new int[numberOfNodes]; 
    						for(int placement=0; placement<numberOfNodes; placement++) {
    							shortestPath_CorrectLength[placement] = shortestPath[numberOfNodes-1-placement]; 
    						}
    						return shortestPath_CorrectLength; 
    					}else {
    						throw new PathDoesNotExistException("PATH DOES NOT EXIST"); 
    					}
    }

    public void printPath(int destination){
        /*Print the path in the format s->n1->n2->destination
         *if the path exists, else catch the Error and 
         *prints it
         */
        try {
            int[] path = this.shortestPath(destination);
            for (int i = 0; i < path.length; i++){
                int next = path[i];
                if (next == destination){
                    System.out.println(destination);
                }
                else {
                    System.out.print(next + "-->");
                }
            }
        }
        catch (BellmanFordException e){
            System.out.println(e);
        }
    }

    public static void main(String[] args){

        String file = args[0];
        WGraph g = new WGraph(file);
        try{
            BellmanFord bf = new BellmanFord(g, g.getSource());
            bf.printPath(g.getDestination());
        }
        catch (BellmanFordException e){
            System.out.println(e);
        }

   } 
}
