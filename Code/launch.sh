#!/bin/bash
var1=$1
var2=$2
var3=$3

if [ "$var1" != "0" ] && [ "$var1" != "1" ] && [ "$var1" != "2" ] && [ "$var1" != "3" ]; then
  echo
  echo "0 : complete tree"
  echo "1 : ast"
  echo "2 : test all complete tree"
  echo "3 : check"
  read -p "Generate complete tree or ast [0/1/2/3] : " var1
  echo
fi

if [ "$var1" == "0" ]; then
    ./bash/launch1.sh $var2
elif [ "$var1" == "1" ]; then
    ./bash/launch2.sh $var2 $var3
elif [ "$var1" == "2" ]; then
    ./bash/launch1All.sh $var2 $var3
elif [ "$var1" == "3" ]; then
    ./bash/launchalltree.sh
else
    echo "ERROR press 0 1 2 or 3"
fi
