#!/bin/Windows
var1=$1
var2=$2
var3=$3

if [ "$var1" != "0" ] && [ "$var1" != "1" ] && [ "$var1" != "2" ] && [ "$var1" != "3" ] && [ "$var1" != "4" ] && [ "$var1" != "5" ]; then
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
    ./Windows/launch_0_complete_treeW.sh $var2
elif [ "$var1" == "1" ]; then
    ./Windows/launch_1_astW.sh $var2 $var3
elif [ "$var1" == "2" ]; then
    ./Windows/launch_2_allW.sh $var2 $var3
elif [ "$var1" == "3" ]; then
    ./Windows/launch_3_all_treeW.sh $var2 $var3
else
    echo "ERROR press 0 1 2 3 or 4"
fi





java -jar ./lib/antlr-4.9.2-complete.jar expr.g4 -no-listener -visitor -o ./src/parser
javac  -cp "./lib/antlr-4.9.2-complete.jar;./src" ./src/Main2.java -d ./bin
java -cp "./lib/antlr-4.9.2-complete.jar;./bin" Main2 ./examples/Test_Unaire/bloc.exp abc
dot -Tsvg ./out/dot/abc.dot -o ./out/svg/abc.svg >/dev/null

