make parser >/dev/null
make compile >/dev/null

tree="TDS"
FILE="./examples/test.exp"

javac -cp ./lib/antlr-4.9.2-complete.jar:./src ./src/Main3.java -d ./bin
java -cp ./lib/antlr-4.9.2-complete.jar:./bin Main3 $FILE $tree >/dev/null
