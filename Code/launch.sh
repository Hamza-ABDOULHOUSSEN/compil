#!/bin/bash
var1=$1
var2=$2
var3=$3

if [ "$var1" != "0" ] && [ "$var1" != "1" ] && [ "$var1" != "2" ] && [ "$var1" != "3" ] && [ "$var1" != "4" ]; then
  echo
  echo "0 : complete tree"
  echo "1 : ast"
  echo "2 : test all complete tree"
  echo "3 : generate all ast"
  echo "4 : syntax error detection"
  echo "5 : symbol table"
  read -p "Which option [0/1/2/3/4/5] : " var1
  echo
fi

if [ "$var1" == "0" ]; then
    ./bash/launch_0_complete_tree.sh $var2
elif [ "$var1" == "1" ]; then
    ./bash/launch_1_ast.sh $var2 $var3
elif [ "$var1" == "2" ]; then
    ./bash/launch_2_all.sh
elif [ "$var1" == "3" ]; then
    ./bash/launch_3_all_tree.sh
elif [ "$var1" == "4" ]; then
    ./bash/launch_4_syntax_error_detection.sh
elif [ "$var1" == "5" ]; then
    ./bash/launch_5_tds.sh
else
    echo "ERROR press 0 1 2 3 or 4"
fi
