#!/bin/bash

# An awesome bash script that runs -r, -DFS, and -BFS solves on each file handed to it
# usage ./run_tests.sh [OPTIONS]... FILES...
# OPTIONS are: search methods: "-r" "-bfs" "-dfs" "-ptr" "-bin" "-three" "-d8"
#              "-ns" No Spacing "-v" use visualizer "-p" faster update interval
# FILES  are mazes


r=1 # do random search
bfs=1 # do bfs search
dfs=1 # do dfs search
v= #use visualizer
p=500 #update interval
space=0 #print spaces
ptr=1 #do BestFS ptr search
bin=1 #do BestFS bin search
three=1 #do BestFS three search
d8=1 #do BestFS dheap 8 search

# Option handling
for arg in $@
do
	if [ $arg = "-r" ]
	then
		r=0
	elif [ $arg = "-bfs" ]
	then
		bfs=0
	elif [ $arg = "-dfs" ]
	then
		dfs=0	
	elif [ $arg = "-ns" ]
	then
		space=1
      	elif [ $arg = "-v" ]
	then
	        v=-v
	elif [ $arg = "-p" ]
	then
	        p=20
	elif [ $arg = "-ptr" ]
	then
		ptr=0
	elif [ $arg = "-bin" ]
	then
		bin=0
	elif [ $arg = "-three" ]
	then
		three=0
	elif [ $arg = "-d8" ]
	then
		d8=0
	fi
done

# Process files
for arg in $@
do
	# If this arg isn't an option
	if [[ `echo "$arg" | sed "s/^[^-].*$//g"` = "" ]]
	then
		if [ $space == 0 ]
		then
			echo "--------------------------------------------------------------------------------" #Spacing
		fi		
		echo "Solutions For: $arg"
		if [ $space == 0 ]
		then
			echo "----------------------------------------" #Spacing
		fi

		# Run the requested tests
		if [ $r == 0 ]
		then
		        java MazeRunnerLauncher -r $v -p $p "$arg"		
			if [ $space == 0 ]
			then			
				echo "----------------------------------------" #Spacing
			fi
		fi
		if [ $dfs == 0 ]
		then
			java MazeRunnerLauncher -DFS $v -p $p "$arg"
			if [ $space == 0 ]
			then			

				echo "----------------------------------------" #Spacing
			fi
		fi
		if [ $bfs == 0 ]
		then
			java MazeRunnerLauncher -BFS $v -p $p "$arg"
			if [ $space == 0 ]
			then
				echo "----------------------------------------" #Spacing
			fi
		fi
		if [ $ptr == 0 ]
		then
			java MazeRunnerLauncher -BestFS -ptr $v -p $p "$arg"
			if [ $space == 0 ]
			then
				echo "----------------------------------------" #Spacing
			fi
		fi
		if [ $bin == 0 ]
		then
			java MazeRunnerLauncher -BestFS -bin $v -p $p "$arg"
			if [ $space == 0 ]
			then
				echo "----------------------------------------" #Spacing
			fi
		fi
		if [ $three == 0 ]
		then
			java MazeRunnerLauncher -BestFS -three $v -p $p "$arg"
			if [ $space == 0 ]
			then
				echo "----------------------------------------" #Spacing
			fi
		fi
		if [ $d8 == 0 ]
		then
			java MazeRunnerLauncher -BestFS -dheap 8 $v -p $p "$arg"
			if [ $space == 0 ]
			then
				echo "----------------------------------------" #Spacing
			fi
		fi

		if [ $space == 0 ]
		then
			echo "--------------------------------------------------------------------------------" #Spacing
		fi	
	fi
done
