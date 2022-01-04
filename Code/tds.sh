
javac -cp ./lib/antlr-4.9.2-complete.jar:./src ./src/Main3.java -d ./bin
java -cp ./lib/antlr-4.9.2-complete.jar:./bin Main3 ./examples/test.exp TDS
dot -Tsvg ./out/dot/TDS.dot -o ./out/svg/TDS.svg