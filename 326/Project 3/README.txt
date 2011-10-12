1. The team members in this group are Kellen Donohue (kellend) and Zach Stein (steinz).

2. Outside Assistance:
The Java Tutorials at http://java.sun.com/docs/books/tutorial/uiswing/components/index.html were referenced when creating the Swing visualizer.

3. Time Spent: ~90 Hours
Kellen: ~40 Hours
	LinearProbing Hash Table: ~10 Hours
	DataCounterTesting: ~5 Hours
	QuickSort Implementation: ~3 Hours
	Sorting Tests: ~5 Hours
	Benchmarking and WriteUp: ~10 Hours
	Work on README: ~5 Hours
Zach: ~49 Hours
	BSTCounter and Tree Inheritance Restructuring: ~3 Hours
	AVLCounter Implementation: ~5 Hours
	SplayCounter Implementation: ~7 Hours
	JUnit Tests, Additional Tree Debugging, and Testing: ~4 Hours
	Iterative MergeSort Implementation: ~2 Hours
	JavaCounter, JavaHashCounter, JavaTreeCounter Implementations: ~1 Hours
	TreeListener, TreeVisualizer, TreeVisualizerLauncher: ~15 Hours
	Correlation Trials and Analysis: ~4 Hours
	Unix Time Tests and Analysis: ~3 Hours
	README: ~5 Hours

4. We used QuickSort as our sorting algorithm, because it is generally quite quick and it is interesting to implement.
In the worst case (partially ordered input), QuickSort will take O(n^2) time, but this case is very unlikely to appear in the inputs of this project.
Mergesort might perform better for very large data sets because disk accesses would be less scattered.

5. We thought the Hashtable would be the fastest, because of it's capacity of O(1) average insert and find operations.

6. Fastest Data Structure (Also See 7):
The BST actually turns out to be one of the fastest data structures.
The AVL Tree is likely slowed down by it's recursive implementation.
The Splay Tree is likely slowed down by the extra overhead of storing the path it followed in a stack - although picking a good initial stack size can help reduce this cost.
Our Hash Table beats the BST on very large inputs (war and peace concatenated onto the end of itself nine times), but such large inputs are unlikely in the real world.

The BST also uses very little extra memory - essentially only storing data and pointers.
The AVL Tree also has to store heights.
The Splay Tree has to store it's path in a stack.
The Hash Table's underlying array has empty indexes.

The BST is also very simple to implement.
The AVL Tree rotations are a bit tricky to code, but very much doable.
The Splay Tree was significantly harder because you have to keep track of so many nodes and because there are several cases (the implementation could have been simplified by creating a meta root node pointing to the actual root node, eliminating the special case when the path length was 2, but this was noticed too late to implement).

Overall, for this application, the BST seemed to be best, followed by the Hash Table.
The Splay Tree was faster than the AVL Tree and, although it had many more cases, not much harder to implement.
However, picking a bad initial stack size can slow it down considerably.

7. Best Data Structure (Also See 6):
The Hash Table ran faster than the self balancing trees, but not faster than the BST for all but very large inputs.
The Splay Tree was also competitive for reasonably sized inputs (Hamlet, The New Atlantis).
Both data structures have the potential to be quite fast if implemented well.
That being said, the Hash Table is probably slightly easier to code and slightly less vulnerable to sub-optimal programming decisions (such as initial array sizes, etc), although the decision of when to grow the table can be tricky.
As well, Hash Table is easier to debug than a tree, if there is no tree visualizer, as getting the current state of the tree is very difficult, while getting the current state of Hash Table simply involves iterating over an array.
But "better" will really depend on the programmer's preferences, the language of implementation, and the specific inputs.

8. Good / Bad Cases:
   BST will perform poorly on sorted / reverse sorted inputs.
   The hash table will perform poorly on inputs that hash to the same values, resulting in more collisions.
   The balancing trees should perform relatively uniformly independent of inputs.
   
   Our conclusion (See Benchmarking explanation file) reveals the Hashtable will perform best on very large data sets, while AVL will perform worst. Hashtable and Splay Tree are comparable for small data sets (e.g, the size of Hamlet).

9. See CorrelatorRuns.pdf.

We definitely do not have sufficient data to support the claim that Bacon wrote Shakespeare's plays.
Bacon and Shakespeare are similar on some documents ("Advancement of Learning" and "Macbeth" for instance). But Marlowe's "Edward II" consistently scores lower than Bacon when compared to Shakespeare's plays, which suggests that it is more likely that Marlowe wrote them than Bacon.
The last data table shows us that Bacon is closer to Darwin than to Shakespeare, and that Marlowe is coser to Shakespeare than Bacon is.
It is important to note that these numbers have a good amount of uncertainty, and are thusly all in pretty much the same range.

10.	
	8.  MergeSort, BubbleSort. We used ascending data, descending data, random data, and data with many duplicates. See Sorting.pdf
	
	11. Tree Visualization Tool - Zach: I had a lot of fun with this one. I would like to add buttons / more of a GUI, but don't have the time.
		Usage: java TreeVisualizerLauncher [ -b | -a | -s ]  [ -d ] [ -g ] [ -n n ] [ -w n ] <filename1>
		-b		-- Use an Unbalanced BST
		-a		-- Use an AVL Tree
		-s		-- Use a Splay Tree
		-w n	-- Wait n milliseconds between operations (default 1000, minimum is 1)
		-n n	-- Nodes are n pixels wide (default 10)
						 Smaller nodes are useful for seeing the general structure of large trees
		-d		-- Dump the contents of the tree to standard out after each operation
		-g		-- Just draw boxes for each node instead of the contents
					 	 Nice for seeing the general structure of large trees
					 	 Printing the node data is useful for validating the tree structure

		Examples:
			java TreeVisualizerLauncher -a -d texts/hamlet.txt
			java TreeVisualizerLauncher -s -g -w 100 -n 5 texts/hamlet.txt


11. Benchmarking: See Project3Benchmarks.pdf and benchmarking.txt.

12. Enjoyed / Hated / Possible Improvements
Zach: Creating the visualization tool was the most satisfying part of this project for me; seeing things a bit less abstractly is satisfying.
	  It was slightly discouraging that the BST often outperformed the self-balancing trees and hash table - but it made sense.
	  It was also hard to draw much of a conclusion about Shakespeare and Bacon from our resulting data, but it isn't surprising that the proposed question requires further study to attempt to answer more comprehensively.
Kellen: I liked implementing different sorting algorithms and hash table implementations, although not all of them made it into the project.
	    I disliked the benchmarking, I felt it was somewhat tedious. Also I felt we were not able to really answer the motivating question of this assignment, which was somewhat discouraging.
	  