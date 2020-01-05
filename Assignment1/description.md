  Exercise 1 (40 points). Building a Hash Table We want to compare the performance of hash
tables implemented using chaining and open addressing. In this assignment, we will consider hash
tables implemented using the multiplication and linear probing methods. We will (respectively)
call the hash functions h and g and describe them below. Note that we are using the hash function
h to define g.

Collisions solved by chaining (multiplication method): h(k) = ((A · k) mod 2^w) >> (w − r)
Open addressing (linear probing): g(k, i) = (h(k) + i) mod 2^r

In the formula above, r and w are two integers such that w > r, and A is a random number
such that 2^(w−1) < A < 2^w. 
In addition, let n be the number of keys inserted, and m the number of slots in the hash tables. Here, we set 
m = 2^r and r = ceiling(w/2). 
The load factor α is equal to (n/m).

We want to estimate the number of collisions when inserting keys with respect to keys and the choice of values for A. 

Your first task is to complete the two java methods Open_Addressing.probe and Chaining.chain.
These methods must implement the hash functions for (respectively) the linear probing and multiplication methods. They take as input a key k, as well as an integer 0 ≤ i < m for the linear
probing method, and return a hash value in [0, m[.
Next, you will implement the method insertKey in both classes, which inserts a key k into
the hash table and returns the number of collisions encountered before insertion, or the number
of collisions encountered before giving up on inserting, if applicable. Note that for this exercise as
well as for the rest of the homework, we define the number of collisions in open addressing as the
number of keys encountered, or "jumped over" before inserting or removing a key. For chaining, we
simply consider the number of other keys in the same bin at the time of insertion as the number
collisions. You can assume the key is not negative.
You will also implement a method removeKey, this one only in Open_Addressing. This method
should take as input a key k, and remove it from the hash table while visiting the minimum number of slots possible. Like insertKey, it should output the number of collisions. If the key is not
in the hash table, the method should simply not change the hash table, and output the number
of slots visited. You will notice from the code and comments that empty slots are given a value
of −1. If applicable, you are allowed to use a different notation of your choice for slots containing
a deleted element.
