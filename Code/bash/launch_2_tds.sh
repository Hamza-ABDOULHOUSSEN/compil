#!/bin/bash

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

    rm temp1 2>/dev/null
    rm temp2 2>/dev/null
    touch temp1
    touch temp2

    echo "[+] Parser creation"
    make parser >/dev/null

    echo "[+] Compilation"
    javac -cp ./lib/antlr-4.9.2-complete.jar:./src ./src/Main3.java -d ./bin >/dev/null

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
            java -cp ./lib/antlr-4.9.2-complete.jar:./bin Main3 $FILE $tree >temp2

            if cmp -s temp1 temp2; then
                echo "[+] TDS file svg generation"
                dot -Tsvg ./out/tds/dot/$tree.dot -o ./out/tds/svg/$tree.svg >/dev/null

                echo "[+] Done, files are in out"
            else
                cat temp2
                echo "ERROR : dot generation"
                rmdir -p "out/tds/dot/$treedir" 2>/dev/null
                rmdir -p "out/tds/svg/$treedir" 2>/dev/null
            fi

        fi

    else
        echo "[+] TDS file dot generation"
        java -cp ./lib/antlr-4.9.2-complete.jar:./bin Main3 $FILE $tree >temp2

        if cmp -s temp1 temp2; then
           echo "[+] TDS file svg generation"
           dot -Tsvg ./out/tds/dot/$tree.dot -o ./out/tds/svg/$tree.svg >/dev/null

           echo "[+] Done, files are in out"
        else
            cat temp2
            echo "ERROR : dot generation"
            rmdir -p "out/tds/dot/$treedir" 2>/dev/null
            rmdir -p "out/tds/svg/$treedir" 2>/dev/null
        fi

    fi

    rm temp1 2>/dev/null
    rm temp2 2>/dev/null

else
    echo "ERROR"
    echo "file : $FILE "
    echo "does not exist"
fi

echo
