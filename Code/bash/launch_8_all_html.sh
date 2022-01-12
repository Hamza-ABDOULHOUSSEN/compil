#!/bin/bash

rm temp1 2>/dev/null
rm temp2 2>/dev/null
touch temp1
touch temp2

echo " 👉 🅢🅣🅐🅡🅣 ============================= html generator ============================= 🅢🅣🅐🅡🅣 👈"

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
                mkdir -p "out/tds/dot/$basedir"
                mkdir -p "out/tds/svg/$basedir"
                mkdir -p "out/html/$basedir"

                echo
                echo "=========== Ast creation : $basename ==========="

                echo "[+] Ast file dot generation"
                make run target="$file" name="$basedir/$basename" >/dev/null 2>temp2

                if cmp -s temp1 temp2; then
                  echo "[+] Ast file svg generation"
                  dot -Tsvg ./out/ast/dot/$basedir/$basename.dot -o ./out/ast/svg/$basedir/$basename.svg >/dev/null
                  echo '### ✅ ✅ ✅ : Done, files are in out ###'
                else
                  echo '### ❌ ❌ ❌ : ERROR AST ###'
                fi

                rm temp2 2>/dev/null
                touch temp2

                echo
                echo "=========== TDS creation : $basename ==========="

                echo "[+] TDS file dot generation"
                java -cp ./lib/antlr-4.9.2-complete.jar:./bin Main3 $file $basedir/$basename >temp2 2>/dev/null

                if cmp -s temp1 temp2; then
                    echo "[+] TDS file svg generation"
                    dot -Tsvg ./out/tds/dot/$basedir/$basename.dot -o ./out/tds/svg/$basedir/$basename.svg >/dev/null
                    echo '### ✅ ✅ ✅ : Done, files are in out ###'
                else
                    echo "[+] TDS file svg generation"
                    dot -Tsvg ./out/tds/dot/$basedir/$basename.dot -o ./out/tds/svg/$basedir/$basename.svg >/dev/null
                    echo '### ❌ ❌ ❌ : ERROR TDS ###'
                fi

                echo
                echo "=========== html creation : $basename ==========="

                echo "[+] html file generation"
                python3 html_generator/html_generator.py $basedir/$basename >/dev/null

                rmdir -p "out/ast/dot/$basedir" 2>/dev/null
                rmdir -p "out/ast/svg/$basedir" 2>/dev/null
                rmdir -p "out/tds/dot/$basedir" 2>/dev/null
                rmdir -p "out/tds/svg/$basedir" 2>/dev/null
                rmdir -p "out/html/$basedir" 2>/dev/null

            done

        fi

    fi
done

rm temp1 2>/dev/null
rm temp2 2>/dev/null
