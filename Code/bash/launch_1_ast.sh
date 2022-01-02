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
    echo "[+] Parser creation"
    make parser >/dev/null

    echo "[+] Compilation"
    make compile >/dev/null

    tree=$2

    if [ "$tree" == "" ]; then

        read -p "Which name for the ast : " tree
        echo

    fi

    if [[ $tree =~ .+/.* ]]; then
        treename="${tree##*/}"
        treedir="${tree%/$treename}"
        mkdir -p "out/dot/$treedir"
        mkdir -p "out/svg/$treedir"
    fi

    if test -f "./out/dot/$tree.dot"; then
        ov="y"

        if [ "$2" == "" ]; then
            echo "file already exists"
            read -p "overwrite [y/n] : " ov
            echo
        fi

        if [ "$ov" == "y" ]; then
            echo "[+] Ast file dot generation"
            make run target="$FILE" name="$tree" >/dev/null

            echo "[+] Ast file svg generation"
            dot -Tsvg ./out/dot/$tree.dot -o ./out/svg/$tree.svg >/dev/null

            echo "[+] Done, files are in out"
        fi

    else
        echo "[+] Ast file dot generation" >/dev/null
        make run target="$FILE" name="$tree" >/dev/null

        echo "[+] Ast file svg generation"
        dot -Tsvg ./out/dot/$tree.dot -o ./out/svg/$tree.svg >/dev/null

        echo "[+] Done, files are in out"
    fi

else
    echo "ERROR"
    echo "file : $FILE "
    echo "does not exist"
fi

echo
