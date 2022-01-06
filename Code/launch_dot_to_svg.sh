#!/bin/bash

var1=$1
var2=$2
var3=$3

if [ "$var1" != "0" ] && [ "$var1" != "1" ] && [ "$var1" != "2" ] && [ "$var1" != "3" ]; then
  echo
  echo "0 : ast"
  echo "1 : tds"
  echo "2 : generate all ast"
  echo "3 : generate all tds"
  read -p "Which option [0/1/2/3] : " var1
  echo

varname=$1


fi

if [ "$var1" == "0" ]; then
  varname=$2
  if [ "$varname" == "" ]; then

    echo "test files :"
    cd ./out/ast/dot
    find * | grep '[^X].dot$'
    cd ../../..
    echo

    read -p "Which test from examples (ex Test_Complet/test) : " varname
    echo

  fi

  if ! [[ $varname = *.dot ]]; then
        varname=$varname.dot
  fi

    FILE=./out/ast/dot/$varname

    if test -f "$FILE"; then
        echo "[+] Parser creation"
        java -jar ./lib/antlr-4.9.2-complete.jar expr.g4 -no-listener -no-visitor -o ./src/parser >/dev/null

        echo "[+] Compilation"
        javac -cp "./lib/antlr-4.9.2-complete.jar:./src" ./src/Main1.java -d ./bin >/dev/null

        tree=$2

        if [ "$tree" == "" ]; then

            read -p "Which name for the svg : " tree
            echo

        fi

        if [[ $tree =~ .+/.* ]]; then
            treename="${tree##*/}"
            treedir="${tree%/$treename}"
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
                echo "[+] Ast file svg generation"
                dot -Tsvg ./out/ast/dot/$tree.dot -o ./out/ast/svg/$tree.svg >/dev/null

                echo "[+] Done, files are in out"
            fi

        else
            echo "[+] Ast file svg generation"
            dot -Tsvg ./out/ast/dot/$tree.dot -o ./out/ast/svg/$tree.svg >/dev/null

            echo "[+] Done, files are in out"
        fi

    else
        echo "ERROR"
        echo "file : $FILE "
        echo "does not exist"
    fi
elif [ "$var1" == "1" ]; then
  varname=$2
    if [ "$varname" == "" ]; then

      echo "test files :"
      cd ./out/tds/dot
      find * | grep '[^X].dot$'
      cd ../../..
      echo

      read -p "Which test from examples (ex Test_Complet/test) : " varname
      echo

    fi

    if ! [[ $varname = *.dot ]]; then
          varname=$varname.dot
    fi

      FILE=./out/tds/dot/$varname

      if test -f "$FILE"; then
          echo "[+] Parser creation"
          java -jar ./lib/antlr-4.9.2-complete.jar expr.g4 -no-listener -no-visitor -o ./src/parser >/dev/null

          echo "[+] Compilation"
          javac -cp "./lib/antlr-4.9.2-complete.jar:./src" ./src/Main1.java -d ./bin >/dev/null

          tree=$2

          if [ "$tree" == "" ]; then

              read -p "Which name for the svg : " tree
              echo

          fi

          if [[ $tree =~ .+/.* ]]; then
              treename="${tree##*/}"
              treedir="${tree%/$treename}"
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
                  echo "[+] Ast file svg generation"
                  dot -Tsvg ./out/tds/dot/$tree.dot -o ./out/tds/svg/$tree.svg >/dev/null

                  echo "[+] Done, files are in out"
              fi

          else
              echo "[+] Ast file svg generation"
              dot -Tsvg ./out/tds/dot/$tree.dot -o ./out/tds/svg/$tree.svg >/dev/null

              echo "[+] Done, files are in out"
          fi

      else
          echo "ERROR"
          echo "file : $FILE "
          echo "does not exist"
      fi

elif [ "$var1" == "2" ]; then
  for directory in $(find out/ast/dot -type d); do
      if [ "$directory" != "out/ast/dot" ]; then
          basedir="${directory##*/}"

          count=$(find  $directory -type f | grep [^X].dot$ | wc -l)

          if [ $count != 0 ]; then
              echo
              echo
              echo "=================== $basedir ===================="

              for file in $(find  $directory -type f | grep [^X].dot$); do
                  basename="${file##*/}"
                  basename="${basename%.dot}"
                  file="./$file"

                  echo
                  echo "=========== Svg creation : $basename ==========="

                  mkdir -p "out/ast/svg/$basedir"
                  echo "[+] Ast file svg generation"
                  dot -Tsvg ./out/ast/dot/$basedir/$basename.dot -o ./out/ast/svg/$basedir/$basename.svg >/dev/null
                  echo '### ✅ ✅ ✅ : Done, files are in out ###'
              done
          fi
      fi
  done

  rm temp1 2>/dev/null
  rm temp2 2>/dev/null

elif [ "$var1" == "3" ]; then
      for directory in $(find out/tds/dot -type d); do
          if [ "$directory" != "out/tds/dot" ]; then
              basedir="${directory##*/}"

              count=$(find  $directory -type f | grep [^X].dot$ | wc -l)

              if [ $count != 0 ]; then
                  echo
                  echo
                  echo "=================== $basedir ===================="

                  for file in $(find  $directory -type f | grep [^X].dot$); do
                      basename="${file##*/}"
                      basename="${basename%.dot}"
                      file="./$file"

                      echo
                      echo "=========== Svg creation : $basename ==========="

                      mkdir -p "out/ast/svg/$basedir"
                      echo "[+] Tds file svg generation"
                      dot -Tsvg ./out/tds/dot/$basedir/$basename.dot -o ./out/tds/svg/$basedir/$basename.svg >/dev/null
                      echo '### ✅ ✅ ✅ : Done, files are in out ###'
                  done
              fi
          fi
      done

      rm temp1 2>/dev/null
      rm temp2 2>/dev/null

else
    echo "ERROR press 0 1 2 or 3"
fi



echo
