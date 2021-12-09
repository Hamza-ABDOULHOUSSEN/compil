#!/bin/bash

cd ..

for file in ./examples/*; do
    if [[ $file != *X.exp ]]; then
        basename="${file##*/}"
        basename="${basename%.exp}"
        echo "=========== Creation arbre : $basename ==========="
        ./launch.sh 1 $basename $basename
    fi
done

echo
cd bash