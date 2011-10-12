#!/bin/bash

# Kellen Donohue
# CSE 303 HW 2
# Problem 1
# 1-23-09

if [[ $# -lt 1 ]]
 then
 echo "0"
 exit 1
fi

temp=`mktemp`

wget "$1" -q -O $temp

if [[ $? -ne 0 ]]
 then
 echo "0"
 exit 2
fi

wc -c $temp | sed 's/[ ].*//g'

rm $temp
