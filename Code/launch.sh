#!/bin/bash
var1=$1
var2=$2
var3=$3

if [ "$var1" != "0" ] && [ "$var1" != "1" ] && [ "$var1" != "2" ] && [ "$var1" != "3" ] && [ "$var1" != "4" ] && [ "$var1" != "5" ] && [ "$var1" != "6" ] && [ "$var1" != "7" ]; then
  echo
  echo "0 : complete tree"
  echo "1 : ast"
  echo "2 : tds"
  echo "3 : test all complete tree"
  echo "4 : generate all ast"
  echo "5 : generate all tds"
  echo "6 : syntax error detection"
  echo "7 : semantics error detection"
  read -p "Which option [0/1/2/3/4/5/6/7] : " var1
  echo
fi

if [ "$var1" == "0" ]; then
    ./bash/launch_0_complete_tree.sh $var2
elif [ "$var1" == "1" ]; then
    ./bash/launch_1_ast.sh $var2 $var3
elif [ "$var1" == "2" ]; then
    ./bash/launch_2_tds.sh $var2 $var3
elif [ "$var1" == "3" ]; then
    ./bash/launch_3_all.sh
elif [ "$var1" == "4" ]; then
    ./bash/launch_4_all_ast.sh
elif [ "$var1" == "5" ]; then
    ./bash/launch_5_all_tds.sh
elif [ "$var1" == "6" ]; then
    ./bash/launch_6_syntax_error_detection.sh
elif [ "$var1" == "7" ]; then
    ./bash/launch_7_semantics_test.sh
else
    echo "ERROR press 0 1 2 3 4 5 6 or 7"
fi
