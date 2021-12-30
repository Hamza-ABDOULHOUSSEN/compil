#!/bin/bash

rm temp1 2>/dev/null
rm temp2 2>/dev/null
touch temp1
touch temp2

echo "========================================================== Ast creation test =========================================================="

for file in $(find * | grep '[^X].exp$'); do
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
      dot -Tsvg ./out/dot/$basename.dot -o ./out/svg/$basename.svg >/dev/null
      echo '### ✅ ✅ ✅ : Done, files are in out ###'
    else
      echo '### ❌ ❌ ❌ : ERROR ###'
    fi
done

rm temp1 2>/dev/null
rm temp2 2>/dev/null
