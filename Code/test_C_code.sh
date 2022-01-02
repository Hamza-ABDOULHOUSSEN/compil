#!/bin/bash

file=$1

if [ "$file" == "" ]; then
  read -p "Which test from examples (ex Test_Semantique/codestruct) : " file
  echo
fi

if ! [[ $file = *.exp ]]; then
    file=$file.exp
fi

SRC="./examples/$file"
DST="./C_code/copy.c"

rm -f ./C_code/copy.c 2>/dev/null
rm -f ./C_code/main.c 2>/dev/null

if test -f "$SRC"; then

    cp $SRC $DST

    cd C_code

    touch main.c
    cat deb.c > main.c
    cat copy.c >> main.c

    rm -f copy.c

    gcc main.c -o main
    ./main

    rm -f main.c
    rm -f main

    cd ..

else
    echo "ERROR"
    echo "file : $SRC "
    echo "does not exist"
fi
