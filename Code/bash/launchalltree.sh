#!/bin/bash

rm temp1 2>/dev/null
rm temp2 2>/dev/null
touch temp1
touch temp2

function testvalide() {
   if cmp -s temp1 temp2; then
      echo "[+] Generation ast file svg"
      dot -Tsvg ./out/dot/$1.dot -o ./out/svg/$1.svg >/dev/null
      echo '### ✅ ✅ ✅ : Done, files are in out ###'
   else
      echo '### ❌ ❌ ❌ : ERROR ###'
   fi
}

function testinvalide() {
   if cmp -s temp1 temp2; then
      echo "[+] Generation ast file svg"
      dot -Tsvg ./out/dot/$1.dot -o ./out/svg/$1.svg >/dev/null
      echo '### ❌ ❌ ❌ : The error was not seen ###'
      echo '### files are in out ###'
   else
      echo '### ✅ ✅ ✅ : The error was detected ###'
   fi

}

function parcours() {
    file=$1
    basename="${file##*/}"
    basename="${basename%.exp}"
    file="./$file"

    echo "=========== Creation arbre : $basename ==========="
    echo "[+] Creation parser"
    make parser >/dev/null

    echo "[+] Compilation"
    make compile >/dev/null

    echo "[+] Generation ast file dot"
    make run target="$file" name="$basename" >/dev/null 2>temp2

    if [ "$2" == "V" ]; then
        testvalide $basename
    else
        testinvalide $basename
    fi

    echo
}

echo "========================================================== Ast creation test =========================================================="
echo
for file in $(find * | grep '[^X].exp$'); do
    parcours $file "V"
done

echo

echo "========================================================== Syntax error detection =========================================================="
echo
for file in $(find * | grep 'X.exp$'); do
    parcours $file "X"
done

echo

rm temp1 2>/dev/null
rm temp2 2>/dev/null
