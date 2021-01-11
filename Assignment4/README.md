Exercise 1. (30 points) Saving the Earth from aliens
In 2246 aliens declared war on humans. To save humanity, we need to disrupt the alien communication network while breaking the minimum possible number of links. You are tasked
with writing a set of utility methods to determine the global minimum cut of the network using
a randomized algorithm (contraction algorithm).
Here are the technical details about the network: the network is an instance of the class
Graph.java. Some examples of what the network could look like are described in the files
network_1.txt, network_2.txt, where the first line is the expected size of the min
cut and the following lines describe the edges, i.e. the line "a b" means that there is an
edge between node a and node b. To test your code, run: java GlobalMinCutTester
network_1.txt and java GlobalMinCutTester network_2.txt.

Your tasks:
• Implement Graph.contractEdge(). When contracting an edge e = (u, v), instead
of replacing u and v by single new supernode w as in the course slides, you will merge
the node u into v. More specifically, you will:
– Remove the edge e = (u, v), and remove the node u (use the removeNode()
method for that)
– Remove edges that had one end equal to u and other equal to v. Heads up: while
looping through edges and removing them, you may run into
ConcurrentModificationException depending on how you try doing it.
Thankfully Google and Stack Overflow still exist in 2246 and you can find multiple
ways to fix it.
– For all remaining edges, replace all occurrences of u with v
• Implement GlobalMinCut.global_min_cut(), which must return two lists of
nodes (characters) corresponding to the two partitions of the cut.
– For each node v, you will record the list S(v) of nodes that have been contracted into v
– Initially S(v) = v for each v
– Run the contraction algorithm. After each contraction of an edge e = (u, v) you must
update S(v), i.e. all the nodes that were in the supernode u must now be added to the
supernode v, because we just merged u into v
– Once you have only two nodes u and v left, return S(u) and S(v)
– For your convenience, the Graph class has several utility methods: getNbEdges(),
getNodes(), getEdge(), getExpectedMinCutSize() and others.
• Implement GlobalMinCut.global_min_cut_repeated(). This method is already almost done. Given a graph, this method should call global_min_cut() until
it either obtains the minimum cut or exceeds a large number of iterations, in which case you’ll know you did something wrong. More specifically, it has an int parameter maxIterations, we expect the algorithm to have found the min cut before then with high probability, it is used as a sanity check and to avoid infinite loops.
global_min_cut_repeated() also takes a Random object as a parameter, don’t
touch it and don’t worry about it, we only use it for grading so we can use seeds.
– You need to add a call to global_min_cut()
– Since global_min_cut() modifies the graph, you need to create copies of the
original graph at each iteration and pass the copy. Use the copy constructor
Graph(graph) provided. 

2. (30 points) Rescuing Anatoly
The aliens are also interested in multiplication methods, especially the Karatsuba algorithm,
which is so fast they fear it could thwart the success of their invasion. They have gone back
in time to kidnap Anatoly Alexeyevich Karatsuba to force him to reveal his secrets. Your task
is to implement his algorithm to show aliens any human can be replaced with a small piece of
code and convince them to return him to his family.
To make a convincing point, you will need to compare the naive and Karatsuba divide-andconquer methods to multiply two integers x and y. You will implement a recursive version of
both algorithms in Multiply.java.
Your tasks:
• Implement the naive multiplication algorithm in naive(int size, int x, int
y)
• Implement the Karatsuba algorithm in karatsuba(int size, int x, int y)
• Evaluate the number of arithmetic operation of each method by keeping track of their
efficiency (or cost).
– The variable size is the size of the integers x and y, and is defined as the number
of bits used to encode them (Note: we assume that x and y have the same size). We
define the size as the number of bits starting from the right that are used in the
product.
– We define the cost as the number of brute force arithmetic operations of the (addition, subtraction, or multiplication) executed by the algorithm multiplied by the size
(in bits) of the integers involved in this operation (Note: We ignore the multiplication by powers of 2 which can be executed using a bit shift. Of course, this is a crude
approximation).
– In particular, for the base case (i.e. when the size of the integers is 1 bit), this cost
will be 1 (brute force multiplication of two integers of size 1). In the induction case,
the naive method executes 3 arithmetic operations of integer of size m (i.e. cost is
3 · m), in addition of the number of operations executed by each recursive call to the
function. By contrast, the Karatsuba algorithm requires 6 arithmetic operations of
size m on the top of the cost of the recursion.– Each method (i.e. naive and karatsuba) will return an integer array result
that stores the value of the multiplication in the first entry of the array (i.e. result[0]),
and the cost of this computation in the second entry (i.e. result[1])
• The output of your program will print a list of numbers such that the first number of each
row is the size of the integers that have been multiplied, the second number is the cost of
the naive method, and the third number the cost of the Karatsuba method.
If you do not submit your two java programs in a single zip file on MyCourses, the aliens will
take over the world (as well as university administration), and your assignment will have to be
sent to Mars for evaluation, causing a delay of several years in the grading process.

