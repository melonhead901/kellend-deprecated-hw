#! /bin/bash

# Kellen Donohue
# HW #2
# Problem 2
# 01-23-09

if [[ $# -lt 2 ]]
then
	echo "You need an input HTML file and an output file"
	exit 1
fi

if [[ $? -ne 0 ]]
then
	echo "Given HTML file does not exist"
	exit 2
fi

# query all lines with htpp
# cut off anything  before URL
# cut off anything after URL
# remove anything with 100bestwebsite
# remove first line, which is junk
# remove all empty lines
grep http $1 | sed 's/.*http/http/g' | sed 's/\".*//g' | sed 's/.*100bestwebsite.*//g' | sed '1,/^$/ d' | sed -e 's/#.*//' -e '/^$/ d'  > $2

