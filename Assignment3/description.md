Exercise 1. (25 points) Ford-Fulkerson
We will implement the Ford-Fulkerson algorithm to calculate the Maximum Flow of a directed weighted graph. 
Here, you will use the files WGraph.java and FordFulkerson.java,
which are available on the course website. Your role will be to complete two methods in
the template FordFulkerson.java.
The file WGraph.java is similar to the file that you used in your previous assignment to
build graphs. The only differences are the addition of setter and getter methods for the
Edges and the addition of the parameters “source” and “destination”. There is also an
additional constructor that will allow the creation of a graph cloning a WGraph object.
Graphs are encoded using a similar format than the one used in the previous assignment.
The only difference is that now the first line corresponds to two integers, separated by
one space, that represent the “source” and the “destination” nodes. An example of such
file can be found on the course website in the file ff2.txt. These files will be used
as an input in the program FordFulkerson.java to initialize the graphs. This graph
corresponds to the same graph depicted in [CLRS2009] page 727.
Your task will be to complete the two static methods fordfulkerson(Integer source,
Integer destination, WGraph graph, String filePath) and pathDFS(Integer source,
Integer destination, WGraph graph). The second method pathDFS finds a path
via Depth First Search (DFS) between the nodes “source” and “destination” in the
“graph”. You must return an ArrayList of Integers with the list of unique nodes belonging to the path found by the DFS. 
The first element in the list must correspond to
the “source” node, the second element in the list must be the second node in the path,
and so on until the last element (i.e., the “destination” node) is stored. The method
fordfulkerson must compute an integer corresponding to the max flow of the “graph”,
as well as the graph encoding the assignment associated with this max flow. The method
fordfulkerson has a variable called myMcGillID, which must be initialized with your
McGill ID number.
Once completed, compile all the java files and run the command line java FordFulkerson
ff2.txt. Your program must use the function writeAnswer to save your output in a file.
An example of the expected output file is available in the file ff226000000.txt. This
output keeps the same format than the file used to build the graph; the only difference
is that the first line now represents the maximum flow (instead of the “source” and “destination” nodes). 
The other lines represent the same graph with the weights updated
to the values that allow the maximum flow. The file ff226000000.txt represents the
answer to the example showed in [CLRS2009] Page 727. You are invited to run other
examples of your own to verify that your program is correct.

Exercise 2. (25 points) Bellman-Ford
We want to implement the Bellman-Ford algorithm for finding the shortest path in
a graph where edges can have negative weights. Once again, you will use the object
WGraph. Your task is to complete the method BellmanFord(WGraph g, int source)
and shortestPath(int destination) in the file BellmanFord.java.
The method BellmanFord takes an object WGraph named g as an input and an integer
that indicates the source of the paths. If the input graph g contains a negative cycle,
then the method should throw an exception (see template). Otherwise, it will return
an object BellmanFord that contains the shortest path estimates (the private array of
integers distances), and for each node, its predecessor in the shortest path from the
source (the private array of integers predecessors).
The method shortestPath will return the list of nodes as an array of integers along
the shortest path from the source to the destination. If this path does not exists, the
method should throw an exception (see template).
Input graphs are available on the course webpage to test your program. Nonetheless, we
invite you to also make your own graphs to test your program.
