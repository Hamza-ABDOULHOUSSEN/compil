#!/bin/Windows
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
    ./Windows/launch_0_complete_treeW.sh $var2
elif [ "$var1" == "1" ]; then
    ./Windows/launch_1_astW.sh $var2 $var3
elif [ "$var1" == "2" ]; then
    ./Windows/launch_2_tdsW.sh $var2 $var3
elif [ "$var1" == "3" ]; then
    ./Windows/launch_3_allW.sh $var2 $var3
elif [ "$var1" == "4" ]; then
    ./Windows/launch_4_all_treeW.sh $var2 $var3
elif [ "$var1" == "5" ]; then
    ./Windows/launch_5_all_tdsW.sh
elif [ "$var1" == "6" ]; then
     ./Windows/launch_6_syntax_error_detectionW.sh
elif [ "$var1" == "7" ]; then
     ./Windows/launch_7_semantics_testW.sh
else
    echo "ERROR press 0 1 2 3 4 5 6 or 7"
fi





java -jar ./lib/antlr-4.9.2-complete.jar expr.g4 -no-listener -visitor -o ./src/parser
javac  -cp "./lib/antlr-4.9.2-complete.jar;./src" ./src/Main2.java -d ./bin
java -cp "./lib/antlr-4.9.2-complete.jar;./bin" Main2 ./examples/Test_Unaire/bloc.exp abc
dot -Tsvg ./out/dot/abc.dot -o ./out/svg/abc.svg >/dev/null

