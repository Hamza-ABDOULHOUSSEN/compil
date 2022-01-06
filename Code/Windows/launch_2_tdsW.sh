varname=$1

if [ "$varname" == "" ]; then

  echo "test files :"
  cd ./examples
  find * | grep '[^X].exp$'
  cd ..
  echo

  read -p "Which test from examples (ex Test_Complet/test) : " varname
  echo

fi

if ! [[ $varname = *.exp ]]; then
    varname=$varname.exp
fi

FILE=./examples/$varname

if test -f "$FILE"; then
    echo "[+] Parser creation"
    java -jar ./lib/antlr-4.9.2-complete.jar expr.g4 -no-listener -visitor -o ./src/parser

    echo "[+] Compilation"
    javac  -cp "./lib/antlr-4.9.2-complete.jar;./src" ./src/Main3.java -d ./bin

    tree=$2

    if [ "$tree" == "" ]; then

        read -p "Which name for the tds : " tree
        echo

    fi

    if [[ $tree =~ .+/.* ]]; then
        treename="${tree##*/}"
        treedir="${tree%/$treename}"
        mkdir -p "out/tds/dot/$treedir"
        mkdir -p "out/tds/svg/$treedir"
    fi

    if test -f "./out/tds/dot/$tree.dot"; then
        ov="y"

        if [ "$2" == "" ]; then
            echo "file already exists"
            read -p "overwrite [y/n] : " ov
            echo
        fi

        if [ "$ov" == "y" ]; then
            echo "[+] TDS file dot generation"
            java -cp "./lib/antlr-4.9.2-complete.jar;./bin" Main3 $FILE $tree >/dev/null

            echo "[+] TDS file svg generation"
            echo -Tsvg ./out/tds/dot/$tree.dot -o ./out/tds/svg/$tree.svg >/dev/null

            echo "[+] Done, files are in out"
        fi

    else

        echo "[+] TDS file dot generation" >/dev/null
        java -cp "./lib/antlr-4.9.2-complete.jar;./bin" Main3 $FILE $tree >/dev/null

        echo "[+] TDS file svg generation"
        echo -Tsvg ./out/tds/dot/$tree.dot -o ./out/tds/svg/$tree.svg >/dev/null

        echo "[+] Done, files are in out"
    fi

else
    echo "ERROR"
    echo "file : $FILE "
    echo "does not exist"
fi

echo


