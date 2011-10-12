#!/bin/bash
# param Takes a text to count

# author Kellen Donohue, Zach Stein
# author kellend, steinz
# June 1, 2009
# CSE 326 A
# Project 3 - timed_runs_word_count.sh

# author Zach
ds=([0]="-b" [1]="-a" [2]="-s" [3]="-h" [4]="-jt" [5]="-jh")

for index in 0 1 2 3 4 5
do
	echo "-----------"
	echo ${ds[index]}
	echo "-----------"
	for runs in 0 1 2
	do
		time java WordCount -frequency ${ds[index]} $1
	done
done
