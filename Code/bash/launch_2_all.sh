#!/bin/bash
cd examples

for file in $(find * | grep '[^X].exp$'); do
    if [[ $file != *X.exp ]]; then
        basename="${file##*/}"
        basename="${basename%.exp}"
        echo "=========== Creation arbre : $basename ==========="
        cd ..
        ./launch.sh 0 $file $basename

        cd examples
    fi
done

echo
cd ..