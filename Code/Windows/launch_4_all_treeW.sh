rm temp1 2>/dev/null
rm temp2 2>/dev/null
touch temp1
touch temp2

echo " 👉 🅢🅣🅐🅡🅣 ============================= Ast creation test ============================= 🅢🅣🅐🅡🅣 👈"

echo
echo "[+] Parser creation"
java -jar ./lib/antlr-4.9.2-complete.jar expr.g4 -no-listener -visitor -o ./src/parser
echo "[+] Compilation"
javac  -cp "./lib/antlr-4.9.2-complete.jar;./src" ./src/Main2.java -d ./bin
echo

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

                echo "[+] Ast file dot generation"
                mkdir -p "out/ast/dot/$basedir"
                mkdir -p "out/ast/svg/$basedir"
                java -cp "./lib/antlr-4.9.2-complete.jar;./bin" Main2 $file $basedir/$basename >/dev/null 2>temp2

                if cmp -s temp1 temp2; then
                  echo "### $(tput setaf 2)V V V $(tput setaf 7): Done, files are in out ###"
                else
                  echo "### $(tput setaf 1)X X X $(tput setaf 7): ERROR ###"
                fi

            done

        fi

    fi
done

rm temp1 2>/dev/null
rm temp2 2>/dev/null

powershell -noexit
