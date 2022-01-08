#!/bin/bash

rm temp1 2>/dev/null
rm temp2 2>/dev/null
touch temp1
touch temp2

echo " 👉 🅢🅣🅐🅡🅣 ============================= Semantics error detection ============================= 🅢🅣🅐🅡🅣 👈"

echo
echo "[+] Parser creation"
make parser >/dev/null
echo "[+] Compilation"
javac -cp ./lib/antlr-4.9.2-complete.jar:./src ./src/Main3.java -d ./bin >/dev/null
echo

for directory in $(find examples -type d | sort); do
    if [ "$directory" != "examples" ]; then
        basedir="${directory##*/}"

        count=$(find  $directory -type f | grep Y.exp$ | wc -l)

        mkdir -p "out/tds/dot/$basedir"
        mkdir -p "out/tds/svg/$basedir"

        if [ $count != 0 ]; then
            echo
            echo
            echo "=================== $basedir ===================="

            for file in $(find  $directory -type f | grep 'Y.exp$' | sort); do
                basename="${file##*/}"
                basename="${basename%.exp}"
                file="./$file"

                echo
                echo "=========== Semantics check : $basename ==========="

                echo "[+] TDS file dot generation"
                java -cp ./lib/antlr-4.9.2-complete.jar:./bin Main3 $file $basedir/$basename >/dev/null 2>temp2

                if cmp -s temp1 temp2; then
                  echo "[+] TDS file svg generation"
                  dot -Tsvg ./out/tds/dot/$basedir/$basename.dot -o ./out/tds/svg/$basedir/$basename.svg >/dev/null
                  echo '### ❌ ❌ ❌ : The error was not seen ###'
                  echo '### files are in out ###'
                else
                  erreur=$(head -1 temp2)
                  erreur="${erreur##*RuntimeException: }"
                  echo $erreur
                  echo '### ✅ ✅ ✅ : The error was detected ###'
                fi

                echo
            done

        fi

        rmdir "out/tds/dot/$basedir" 2>/dev/null
        rmdir "out/tds/svg/$basedir" 2>/dev/null

    fi
done

rm temp1 2>/dev/null
rm temp2 2>/dev/null
