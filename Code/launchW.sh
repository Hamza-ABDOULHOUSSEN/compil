#!/bin/bash
echo which example \( test \) \?
read -r varname
echo thanks wait
java -jar ./lib/antlr-4.9.2-complete.jar expr.g4 -no-listener -no-visitor -o ./src/parser
javac  -cp "./lib/antlr-4.9.2-complete.jar;./src" ./src/Main.java -d ./bin
java -cp "./lib/antlr-4.9.2-complete.jar;./bin" Main ./examples/$varname.exp
