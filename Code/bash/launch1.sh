#!/bin/bash

varname=$1

if [ "$varname" == "" ]; then

  echo "test files :"
  ls ./examples
  echo

  read -p "Which test from examples (ex test) : " varname
  echo

fi

if ! [[ $varname = *.exp ]]; then
    varname=$varname.exp
fi

FILE=./examples/$varname

if test -f "$FILE"; then
    echo "[+] Creation parser"
    java -jar ./lib/antlr-4.9.2-complete.jar expr.g4 -no-listener -no-visitor -o ./src/parser >/dev/null

    echo "[+] Compilation"
    javac -cp "./lib/antlr-4.9.2-complete.jar:./src" ./src/Main1.java -d ./bin >/dev/null

    echo "[+] Analyse"
    java -cp "./lib/antlr-4.9.2-complete.jar:./bin" Main1 ./examples/$varname >/dev/null

else
    echo "ERROR"
    echo "file : $FILE "
    echo "does not exist"
fi

echo

