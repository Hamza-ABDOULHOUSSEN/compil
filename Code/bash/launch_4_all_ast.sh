#!/bin/bash

rm temp1 2>/dev/null
rm temp2 2>/dev/null
touch temp1
touch temp2

echo " 👉 🅢🅣🅐🅡🅣 ============================= Ast creation test ============================= 🅢🅣🅐🅡🅣 👈"

echo
echo "[+] Parser creation"
make parser >/dev/null
echo "[+] Compilation"
make compile >/dev/null
echo

for directory in $(find examples -type d | sort); do
    if [ "$directory" != "examples" ]; then
        basedir="${directory##*/}"

        count=$(find  $directory -type f | grep [^X].exp$ | wc -l)

        if [ $count != 0 ]; then
            echo
            echo
            echo "=================== $basedir ===================="

            for file in $(find  $directory -type f | grep [^X].exp$ | sort); do
                basename="${file##*/}"
                basename="${basename%.exp}"
                file="./$file"

                mkdir -p "out/ast/dot/$basedir"
                mkdir -p "out/ast/svg/$basedir"

                echo
                echo "=========== Ast creation : $basename ==========="

                echo "[+] Ast file dot generation"
                make run target="$file" name="$basedir/$basename" >/dev/null 2>temp2

                if cmp -s temp1 temp2; then
                  echo "[+] Ast file svg generation"
                  dot -Tsvg ./out/ast/dot/$basedir/$basename.dot -o ./out/ast/svg/$basedir/$basename.svg >/dev/null
                  echo '### ✅ ✅ ✅ : Done, files are in out ###'
                else
                  echo '### ❌ ❌ ❌ : ERROR ###'
                fi

                rmdir -p "out/ast/dot/$basedir" 2>/dev/null
                rmdir -p "out/ast/svg/$basedir" 2>/dev/null

            done

        fi

    fi
done

rm temp1 2>/dev/null
rm temp2 2>/dev/null
