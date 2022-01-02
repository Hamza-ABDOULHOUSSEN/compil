#!/bin/bash

rm temp1 2>/dev/null
rm temp2 2>/dev/null
touch temp1
touch temp2

echo " 👉 🅢🅣🅐🅡🅣 ============================= Ast creation test ============================= 🅢🅣🅐🅡🅣 👈"

for directory in $(find examples -type d); do
    if [ "$directory" != "examples" ]; then
        basedir="${directory##*/}"

        count=$(find  $directory -type f | grep [^X].exp$ | wc -l)

        if [ $count != 0 ]; then
            echo
            echo
            echo "=================== $basedir ===================="

            for file in $(find  $directory -type f | grep [^X].exp$); do
                basename="${file##*/}"
                basename="${basename%.exp}"
                file="./$file"

                echo
                echo "=========== Ast creation : $basename ==========="
                echo "[+] Parser creation"
                make parser >/dev/null

                echo "[+] Compilation"
                make compile >/dev/null

                echo "[+] Ast file dot generation"
                mkdir -p "out/dot/$basedir"
                mkdir -p "out/svg/$basedir"
                make run target="$file" name="$basedir/$basename" >/dev/null 2>temp2

                if cmp -s temp1 temp2; then
                  echo "[+] Ast file svg generation"
                  dot -Tsvg ./out/dot/$basedir/$basename.dot -o ./out/svg/$basedir/$basename.svg >/dev/null
                  echo '### ✅ ✅ ✅ : Done, files are in out ###'
                else
                  echo '### ❌ ❌ ❌ : ERROR ###'
                fi
            done

        fi

    fi
done

rm temp1 2>/dev/null
rm temp2 2>/dev/null
