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
    make compile >/dev/null

    tree=$2

    if [ "$tree" == "" ]; then

        read -p "Which name for the ast : " tree
        echo

    fi

    if [[ $tree =~ .+/.* ]]; then
        treename="${tree##*/}"
        treedir="${tree%/$treename}"
        mkdir -p "out/ast/dot/$treedir"
        mkdir -p "out/ast/svg/$treedir"
    fi

    if test -f "./out/ast/dot/$tree.dot"; then
        ov="y"

        if [ "$2" == "" ]; then
            echo "file already exists"
            read -p "overwrite [y/n] : " ov
            echo
        fi

        if [ "$ov" == "y" ]; then
            echo "[+] Ast file dot generation"
            make run target="$FILE" name="$tree" >/dev/null 2>temp2

            if cmp -s temp1 temp2; then
                echo "[+] Ast file svg generation"
                dot -Tsvg ./out/ast/dot/$tree.dot -o ./out/ast/svg/$tree.svg >/dev/null

                echo "[+] Done, files are in out"
            else
                cat temp2
                echo "ERROR : dot generation"
            fi

        fi

    else
        echo "[+] Ast file dot generation"
        make run target="$FILE" name="$tree" >/dev/null 2>temp2

        if cmp -s temp1 temp2; then
            echo "[+] Ast file svg generation"
            dot -Tsvg ./out/ast/dot/$tree.dot -o ./out/ast/svg/$tree.svg >/dev/null

            echo "[+] Done, files are in out"
        else
            cat temp2
            echo "ERROR : dot generation"
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
