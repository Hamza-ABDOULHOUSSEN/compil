#!/bin/bash

rm temp1 2>/dev/null
rm temp2 2>/dev/null
touch temp1
touch temp2

for file in $(find * | grep '[^X].exp$'); do
    basename="${file##*/}"
    basename="${basename%.exp}"
    file="./$file"
    echo

    echo "=========== Analyse : $basename ==========="

    echo "[+] Creation parser"
    java -jar ./lib/antlr-4.9.2-complete.jar expr.g4 -no-listener -no-visitor -o ./src/parser >/dev/null

    echo "[+] Compilation"
    javac -cp "./lib/antlr-4.9.2-complete.jar:./src" ./src/Main1.java -d ./bin >/dev/null

    echo "[+] Analyse"
    java -cp "./lib/antlr-4.9.2-complete.jar:./bin" Main1 $file no >/dev/null 2>temp2

    if cmp -s temp1 temp2; then
      echo "### $(tput setaf 2)V V V $(tput setaf 7): test passed ###"
    else
      echo "### $(tput setaf 1)X X X $(tput setaf 7): test failed ###"
    fi
done

echo

rm temp1 2>/dev/null
rm temp2 2>/dev/null
