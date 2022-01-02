#!/bin/bash

rm temp1 2>/dev/null
rm temp2 2>/dev/null
touch temp1
touch temp2

function test {
      basename="${file##*/}"
      basename="${basename%.exp}"
      file="./$file"
      echo

      echo "=========== Analyse : $basename ==========="

      #echo "[+] Creation parser"
      java -jar ./lib/antlr-4.9.2-complete.jar expr.g4 -no-listener -no-visitor -o ./src/parser >/dev/null

      #echo "[+] Compilation"
      javac -cp "./lib/antlr-4.9.2-complete.jar:./src" ./src/Main1.java -d ./bin >/dev/null

      #echo "[+] Analyse"
      java -cp "./lib/antlr-4.9.2-complete.jar:./bin" Main1 $file no >/dev/null 2>temp2

      if cmp -s temp1 temp2; then
        echo "### $(tput setaf 2)V V V $(tput setaf 7): test passed ###"
      else
        echo "### $(tput setaf 1)X X X $(tput setaf 7): test failed ###"
      fi
}
function testErreur {
      basename="${file##*/}"
      basename="${basename%.exp}"
      file="./$file"
      echo

      echo "=========== Analyse : $basename ==========="

      #echo "[+] Creation parser"
      java -jar ./lib/antlr-4.9.2-complete.jar expr.g4 -no-listener -no-visitor -o ./src/parser >/dev/null

      #echo "[+] Compilation"
      javac -cp "./lib/antlr-4.9.2-complete.jar:./src" ./src/Main1.java -d ./bin >/dev/null

      #echo "[+] Analyse"
      java -cp "./lib/antlr-4.9.2-complete.jar:./bin" Main1 $file no >/dev/null 2>temp2

      if cmp -s temp1 temp2; then
        echo "### $(tput setaf 1)X X X $(tput setaf 7): test failed ###"
      else
        echo "### $(tput setaf 2)V V V $(tput setaf 7): test passed ###"
      fi
}

echo "$(tput bold)=================== Test Unaire ===================="
echo
for file in $(find  examples/Test_Unaire/ -type f | grep '[^X].exp$'); do test
done

echo
echo
echo "$(tput bold)==================== Test Complet ===================="
echo
for file in $(find  examples/Test_Complet/ -type f | grep '[^X].exp$'); do test
done

echo
echo
echo "$(tput bold)==================== Test Autres ===================="
echo
for file in $(find  examples/Test_Autre/ -type f | grep '[^X].exp$'); do test
done

echo
echo
echo "$(tput bold)==================== Test Erreur ===================="
echo
for file in $(find  examples/Test_Erreur/ -type f | grep '.exp$'); do testErreur
done

echo

rm temp1 2>/dev/null
rm temp2 2>/dev/null
