#!/bin/bash

rm temp1 2>/dev/null
rm temp2 2>/dev/null
touch temp1
touch temp2

echo " 👉 🅢🅣🅐🅡🅣 ============================= Syntax error detection ============================= 🅢🅣🅐🅡🅣 👈"

echo
echo "[+] Parser creation"
make parser >/dev/null
echo "[+] Compilation"
make compile >/dev/null
echo

for directory in $(find examples -type d | sort); do
    if [ "$directory" != "examples" ]; then
        basedir="${directory##*/}"

        count=$(find  $directory -type f | grep X.exp$ | wc -l)

        mkdir -p "out/ast/dot/$basedir"
        mkdir -p "out/ast/svg/$basedir"
        error=0

        if [ $count != 0 ]; then
            echo
            echo
            echo "=================== $basedir ===================="

            for file in $(find  $directory -type f | grep 'X.exp$' | sort); do
                basename="${file##*/}"
                basename="${basename%.exp}"
                file="./$file"

                echo
                echo "=========== Syntax check : $basename ==========="

                echo "[+] Ast file dot generation"
                make run target="$file" name="$basedir/$basename" >/dev/null 2>temp2

                if cmp -s temp1 temp2; then
                  error=1
                  echo "[+] Ast file svg generation"
                  dot -Tsvg ./out/ast/dot/$basedir/$basename.dot -o ./out/ast/svg/$basedir/$basename.svg >/dev/null
                  echo '### ❌ ❌ ❌ : The error was not seen ###'
                  echo '### files are in out ###'
                else
                  echo '### ✅ ✅ ✅ : The error was detected ###'
                fi

                echo
            done

        fi

        if [ error == 0 ]; then
            rmdir "out/ast/dot/$basedir"
            rmdir "out/ast/svg/$basedir"
        fi
    fi
done

rm temp1 2>/dev/null
rm temp2 2>/dev/null
