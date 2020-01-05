//created by Xingya Ren 
import java.io.*;
import java.util.*;




public class FordFulkerson {

	
	public static ArrayList<Integer> pathDFS(Integer source, Integer destination, WGraph graph){
		ArrayList<Integer> Stack = new ArrayList<Integer>();
		
		//STUDENT CODE STARTS HERE 
		Stack<Integer> myStack = new Stack<Integer>();
		HashSet<Integer> visited = new HashSet<Integer>();
		//first, add the source node to the stack;
		myStack.push(source); 
		while(true) {
			//if the destination is on top of myStack, add it to the Stack and quite
			if(myStack.peek() == destination) { 
				Stack.add(destination);
				break;
			}
			Integer currentNode = myStack.pop();		
			if(!visited.contains(currentNode)) { //if not visited
				//visit it and add to the stack 
				visited.add(currentNode);
				Stack.add(currentNode);
			}	
			//create a helper method to get the adj nodes that are not visited 
			ArrayList<Integer> adjNodes = getAdjacents(currentNode, graph, visited);
			if(adjNodes.isEmpty() && (currentNode==source)) {
				//we don't have a path, clear and quit 
				Stack.clear();
				break;
			}else if(adjNodes.isEmpty() && (currentNode != source)) {
					myStack.push(source); //add to stack 
					Stack.clear();
					Stack.add(source);		
					continue;
			}else {
				for(Integer adjNode : adjNodes) {
					//now we can add all adjacent nodes into myStack 
						myStack.push(adjNode);	
				}
			}		
		}
		return Stack;
	}
	
	//Helper method that fetches the adjacent nodes that are not visited 
	public static ArrayList<Integer> getAdjacents(int curr, WGraph graph, HashSet<Integer> visited){
		ArrayList<Integer> adjacent = new ArrayList<Integer>();
		for(Edge oneEdge : graph.getEdges()) {
			if(oneEdge.nodes[0] == curr && oneEdge.weight != 0 && !visited.contains(oneEdge.nodes[1])) {
				adjacent.add(oneEdge.nodes[1]); 
			}
		}
		return adjacent;	
	}
	
	
	public static void fordfulkerson(Integer source, Integer destination, WGraph graph, String filePath){
		String answer="";
		String myMcGillID = "260780000"; //Please initialize this variable with your McGill ID
		int maxFlow = 0;
		
		WGraph myGraph = new WGraph(graph); //copy constructor! 
		//Follow the psuedo-code seen in lecture 
		//while there is a path 
			//augment
			//and update the graph 
		while(!pathDFS(source, destination, myGraph).isEmpty()) {
			ArrayList<Integer> temporaryPath = pathDFS(source, destination, myGraph);
			WGraph temporaryG = new WGraph();
			int i = 0;
			int j = 1;
			while(j < temporaryPath.size()) {
				//add edges to the tempG
				temporaryG.addEdge(myGraph.getEdge(temporaryPath.get(i), temporaryPath.get(j)));
				i++;
				j++;
			}
			
			//loop through the edges to find the edge in the path that has the smallest weight 
				//this weight becomes the bottleneck value 
			int bottleNeck = temporaryG.listOfEdgesSorted().get(temporaryG.listOfEdgesSorted().size() - 1).weight;
			//initialize counters 
			int p = 0;
			int q = 1;
			while (q<temporaryPath.size()) {
                //updating the graph edge capacities
				Edge tmpEdge = myGraph.getEdge(temporaryPath.get(p), temporaryPath.get(q));
				int newWeight = tmpEdge.weight - bottleNeck;
				myGraph.setEdge(temporaryPath.get(p), temporaryPath.get(q), newWeight);
				//loop on 
				p++;
				q++;
			}
			
			//update the maxFlow of the graph! 
			maxFlow = maxFlow + bottleNeck;
			
		}
		for(Edge originalEdge: graph.getEdges()) {
			for(Edge myEdge: myGraph.getEdges()) {
				if(originalEdge.nodes[0] == myEdge.nodes[0] && originalEdge.nodes[1] == myEdge.nodes[1]) {
					graph.setEdge(originalEdge.nodes[0], originalEdge.nodes[1], originalEdge.weight - myEdge.weight);
				}
			}
		}
		///////
		answer += maxFlow + "\n" + graph.toString();	
		writeAnswer(filePath+myMcGillID+".txt",answer);
		System.out.println(answer);
	}
	
	
	public static void writeAnswer(String path, String line){
		BufferedReader br = null;
		File file = new File(path);
		// if file doesnt exists, then create it
		
		try {
		if (!file.exists()) {
			file.createNewFile();
		}
		FileWriter fw = new FileWriter(file.getAbsoluteFile(),true);
		BufferedWriter bw = new BufferedWriter(fw);
		bw.write(line+"\n");	
		bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}
	
	 public static void main(String[] args){
		 String file = args[0];
		 File f = new File(file);
		 WGraph g = new WGraph(file);
		 fordfulkerson(g.getSource(),g.getDestination(),g,f.getAbsolutePath().replace(".txt",""));
	 }
}
