#!/bin/bash

echo "test files :"
ls ./examples

read -p "Which test from examples (ex test) : " varname
echo

if ! [[ $varname = *.exp ]]; then
    varname=$varname.exp
fi

FILE=./examples/$varname

if test -f "$FILE"; then
    echo "[+] Creation parser"
    make parser >/dev/null

    echo "[+] Compilation"
    make compile >/dev/null

    read -p "Which name for the ast : " tree
    echo

    if test -f "./out/dot/$tree.dot"; then
        echo "file already exists"
        read -p "overwrite [y/n] : " ov
        echo

        if [ "$ov" == "y" ]; then
            echo "[+] Generation ast file dot"
            make run target="$FILE" name="$tree" >/dev/null
            #echo "}" >> out/dot/$tree.dot

            echo "[+] Generation ast file svg"
            dot -Tsvg ./out/dot/$tree.dot -o ./out/svg/$tree.svg >/dev/null

            echo "[+] Done, files are in out"
        fi

    else
        echo "[+] Generation ast file dot" >/dev/null
        make run target="$FILE" name="$tree" >/dev/null
        #echo "}" >> out/dot/$tree.dot

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





