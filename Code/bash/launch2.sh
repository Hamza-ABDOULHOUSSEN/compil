#!/bin/bash

varname=$1

if [ "$varname" == "" ]; then
    echo "test files :"
    ls ./examples
    echo

    read -p "Which test from examples (ex test) : " varname
    echo
fi

if ! [[ $varname = *.exp ]]; then
    varname=$varname.exp
fi

FILE=./examples/$varname

if test -f "$FILE"; then
    echo "[+] Creation parser"
    make parser >/dev/null

    echo "[+] Compilation"
    make compile >/dev/null

    tree=$2

    if [ "$tree" == "" ]; then

        read -p "Which name for the ast : " tree
        echo

    fi

    if test -f "./out/dot/$tree.dot"; then
        ov="y"

        if [ "$2" == "" ]; then
            echo "file already exists"
            read -p "overwrite [y/n] : " ov
            echo
        fi

        if [ "$ov" == "y" ]; then
            echo "[+] Generation ast file dot"
            make run target="$FILE" name="$tree" >/dev/null

            echo "[+] Generation ast file svg"
            dot -Tsvg ./out/dot/$tree.dot -o ./out/svg/$tree.svg >/dev/null

            echo "[+] Done, files are in out"
        fi

    else
        echo "[+] Generation ast file dot" >/dev/null
        make run target="$FILE" name="$tree" >/dev/null

        echo "[+] Generation ast file svg"
        dot -Tsvg ./out/dot/$tree.dot -o ./out/svg/$tree.svg >/dev/null

        echo "[+] Done, files are in out"
    fi

else
    echo "ERROR"
    echo "file : $FILE "
    echo "does not exist"
fi

echo





