1. Team FaceException
	Kellen Donohue
	Zach Stein

2. Work Breakdown
Kellen:
	Stack Implementation and Tests (~3 Hours)
	Skew Heap Implementation and Tests (~6 Hours)
	Implemented AStar, BestFS, BFS, and DFS Maze Runners (~6 Hours)
	Wrote Maze Runner Launcher Tests (~1 Hour)
	Wrote the Maze Generator (~8 Hours)
	Abstracted PQ Tests (~1 Hour)
	Separated EC From Regular Files and Completed Turn In (~2 Hours)

Zach:
	Queue Implementation and Tests (~4 Hours)
	Binary, Three, and d-Heap Implementations and Tests (~5 Hours)
	Implemented the Generic Maze Runner (~6 Hours)
	Wrote run_tests.sh Shell Script for Testing (~2 Hours)
	Wrote the Visualizer (~6 Hours)

Both:
	Made edits to MazeRunnerLauncher as necessary (~2 Hours)
	Writing TESTING.txt and README.txt (~4 Hours)
	
3. It is difficult to recall just how much time we put into this project, but we estimate that we spent around 30 hours each working on various
aspects of this project. The breakdown of time spent is included approximately in the answer to the previous question. These numbers include time
spent designing and discussing how the project would work as a whole, as well as time spent actually coding. A good deal of time was spent
discussing how to eliminate repetitive code in the maze runners. This time is included above in the time listed implementing maze runners.

4. Assistance Received

Referenced when writing the visualizer:
Sun Java Tutorials: Creating a GUI with JFC/Swing > Using Swing Components
http://java.sun.com/docs/books/tutorial/uiswing/components/index.html

Referenced when writing the maze generator:
Wikipedia > Maze Generation Article

5. Extra Credit

Random heuristic
	Rewrite of random maze runner that uses BestFS with a Random Heuristic.
	Works with the visualizer.
	
	Use "-Rand" as the command line arugment for search type
	
Implemented A*:
	Add "-AStar" followed by a priority queue heap argument to the command line.
	
	The will run a BestFirstSearch, except using the A* heuristic descried on website.
	
Wrote a visualizer:
	Simply add "-v" as a command line argument to display the visualizer.
	"-p n" can be used to set the interval between updates (default 500).
	
	The visualizer extends JFrame and creates an array of bordered labels representing the maze.
	Black cells are unvisited, gray cells are visit_in_progress, yellow cells are visited, and green cells are on the solution path.
	The start cell is marked with an "S" and the donut is marked with a "D"

Wrote a maze generator:
	Run MazeGenerator with the following arguments:
	
	width height output-file [random]	
	
	width: 			The number of columns in the maze
	height: 		The number of rows in the maze
	output-file: 	The output file to write the generated maze to
	random: 		optional, "-r" if you want start and stop to be in random locations in the maze, they will be in the upper right and lower left 
	                respectively otherwise 
	
Wrote an ASCII animation generator: (Not really for credit)
	This generates a file suitable for display in an ASCII animation viewer (creating a viewer is a homework assignment in CSE 190M).
	Contact Marty Stepp (stepp@cs) for an example if desired.
	Animation is a "runner" moving along the solution path, 1 step per frame.

	Run ASCIImation with the following arguments:
	
	maze solution-path output-file
	
	maze: 			Text file of the maze
	solution-path:	Text file containing solution path of the maze, in the form output to console by MazeRunner
	output-file: 	File to write ASCIImation to, frames delimited by "=====\n"

Wrote a shell script to solve lots of mazes:
	Usage: ./run_tests.sh [-r] [-bfs] [-dfs] [-ptr] [-bin] [-three] [-d8] [-ns] [-v] [-p] MAZES
		Use -r -bfs -dfs -ptr -bin -three -d8 (8-heap) to indicate which data structures and maze runners to use
		-ns keeps the script from printing extra spacing and dividers between tests
		-v runs the visualizer with each solve
		-p tells the script to use a shorter update interval (20ms instead of 500ms)
		MAZES are the maze.txt files to solve

6. It is more important for DFS than BFS to check whether a cell has been visited because DFS will easily be caught in loops if it doesn't
verify that cells haven't been visited already.

For Example:
Consider the following portion of a maze:
  +-+-+-+
->      |
  + +-+ +
<-      |
  +-+-+-+

Say the solution path lies on one of the following routes:
  +-+-+-+    +-+-+-+
->******|  ->**    |
  + +-+*+    +*+-+ +
<-******|  <-**    |
  +-+-+-+    +-+-+-+

If DFS doesn't check if it has already visited cells, it may get
caught in the following loop instead of "falling back" and trying
to go left from the bottom left cell:
  +-+-+-+
->******|
  +*+-+*+
   *****|
  +-+-+-+

7. If our MazeRunners were given a maze too large to fit in main memory, DFS would likely run faster. This is because DFS tends to do work on
contiguous maze cells; BFS tends to jump around, doing work on cells all over the maze - meaning it will have to load those parts of the maze from
disk more often.

8. DFS and BestFS search are similar in that they tend to explore specific branches of the maze one at a time (DFS being more exhaustive).
BFS and BestFS search are similar in that they will alternate making progress on multiple branches (BFS alternating more between more branches).

9. A heuristic should be easy to compute because it will have to be computed many times (once for each cell in the maze in this case). It should
yield unique or at least widely varying values so that one option is clearly distinguished as being favorable to another. The heuristic isn't very
helpful if most cells produce the save value; you might as well select randomly, or just always select the path to the right.

10. BFS is guaranteed to find the shortest path because it essentially checks all possible paths in order from shortest to longest, advancing each
possible path one cell at a time. DFS and BestFS are not guaranteed to find the shortest possible path. For example, on bigmaze9.txt BFS finds a
solution path of length 96. DFS finds a solution path of length 252 and BestFS finds a solution path of length 134, which are clearly longer.

11. Pointer based heaps perform insertions and deletions in O(log n) (amortized time for our skew heap, worst case time for a leftist heap).
Array based heaps also perform insertions and deletions in O(log n), but, on average, insertions are actually performed in O(1). The only exception
to this is when the array needs to be grown, which is an O(n) operation. Pointer based heaps are much more efficient at merging than array based
heaps (O(log n) versus O(n)), but merging binary heaps was not necessary to this project. Therefore, it can be seen that array based heaps are
likely to be more efficient for this particular project.
	However, pointer based heaps are a bit simpler to code. For pointer based heaps, all operations are defined in terms of merge. Array based
heaps require more work (percolate up and down are distinct and non-trivial). The nodes present in pointer based heaps are also intuitive. Array
based heaps have no such class - only the abstraction of where an index's parents and children are. In our case, our pointer based skew heap also
has a significantly shorter source file than our array based binary or three heap.
	Pointer based heaps use more memory than array based heaps. This is because they must store pointers along with each element - the array based
heap only stores the elements and a count of the number of elements currently in the heap. However, pointer based heaps use all of the memory
allocated for them, when array based heaps usually have empty space in their array yet to be used. Array based heaps also have to grow their array
if they fill up, which is slow and costly.
	We did not notice any differences between our array and pointer based heaps in our tests.

12. It could be better to use a sorted array implementation of a priority queue versus a binary heap if you received all of your data in priority
sorted order (all operations would be O(1)). However, this structure would not be very flexible at all - at least some operations would be O(n).
However, a sorted array would be easy to implement and might not make much of a difference on a very small data set.

13. Enjoyed, Hated, Could Have Done Better
Zach:
	I enjoyed writing the list queue and array based heaps. Writing tests for them was kind of tedious but necessary - it caught bugs.
	Writing the visualizer was very rewarding because you could actually watch the maze runners solve the maze.
	I also enjoyed writing my run_tests.sh shell script and solving a lot of mazes while watching visualizers.
	I found discussing how to make the program generic and how to eliminate redundant code interesting and sometimes clever.
	At one point in the project, before we had an abstract generic maze runner, classes existed extending all of our data structures and
		implementing a MazeDS interface that defined put and get methods. However, it ended up being simpler to eliminate that extra layer of
	  	abstraction and instead write several maze runners extending an abstract, generic maze runner since different data structures required
	  	different types of maze cells (BestFS requires comparable maze cells), and we didn't want to make all maze cells more complicated than
	  	necessary. I am satisfied with our solution to this problem, and think it does a fair job eliminating redundancy and being extendible.
Kellen:
	I enjoyed creating the stack and skew heap implementations. Writing the stack was however repetitive, so perhaps if this project could somehow
	extend off the work from Project 1, that could be an improvement. I found writing tests to be an interesting exercise, JUnit seemed to be useful. I also
	added MazeRunnerTestLauncher, which was useful to ensure that we didn't break our previous working maze runners.  I also enjoyed dealing with
	data abstraction and eliminating redundant code.
