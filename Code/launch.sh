#!/bin/bash

echo
echo "0 : complete tree"
echo "1 : ast"
read -p "Generate complete tree or ast [0/1] : " var
echo

if [ "$var" == "0" ]; then
    ./bash/launch1.sh
else
    ./bash/launch2.sh
fi