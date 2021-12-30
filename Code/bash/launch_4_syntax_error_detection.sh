#!/bin/bash

rm temp1 2>/dev/null
rm temp2 2>/dev/null
touch temp1
touch temp2

echo "========================================================== Syntax error detection =========================================================="

for file in $(find * | grep 'X.exp$'); do
    basename="${file##*/}"
    basename="${basename%.exp}"
    file="./$file"

    echo
    echo "=========== Creation arbre : $basename ==========="
    echo "[+] Creation parser"
    make parser >/dev/null

    echo "[+] Compilation"
    make compile >/dev/null

    echo "[+] Generation ast file dot"
    make run target="$file" name="$basename" >/dev/null 2>temp2

    if cmp -s temp1 temp2; then
      echo "[+] Generation ast file svg"
      dot -Tsvg ./out/dot/$1.dot -o ./out/svg/$1.svg >/dev/null
      echo '### ❌ ❌ ❌ : The error was not seen ###'
      echo '### files are in out ###'
    else
      echo '### ✅ ✅ ✅ : The error was detected ###'
    fi

    echo
done

rm temp1 2>/dev/null
rm temp2 2>/dev/null
