#!/bin/bash
var1=$1
var2=$2
var3=$3

if [ "$var1" != "0" ] && [ "$var1" != "1" ]; then
  echo
  echo "0 : complete tree"
  echo "1 : ast"
  read -p "Generate complete tree or ast [0/1] : " var1
  echo
fi

if [ "$var1" == "0" ]; then
    ./bash/launch1.sh $var2
else
    ./bash/launch2.sh $var2 $var3
fi