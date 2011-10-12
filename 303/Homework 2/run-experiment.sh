#!/bin/bash

# Kellen Donohue
# HW # 2
# Problem 3
# 01-25-09

if [[ $# -lt 2 ]]
then
	echo "You need an input text file and an output file"
	exit 1
fi

./parse.sh $1 $2

rank=1
linecount=`grep . -c $1`

while [[ $rank -le linecount ]]
do
 address=`sed -n "$rank p" $1`
 measure=`./perform-measurement.sh $address`
 echo "Performing measurement on $address"
 if [[ $measure -ne 0 ]]
 then
  echo ...success
  echo $rank $address $measure >> $2
 else 
  echo ...failed
 fi
 (( rank=$rank+1 ))
done
