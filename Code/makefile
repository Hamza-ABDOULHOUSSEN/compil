
parser :
	java -jar ./lib/antlr-4.9.2-complete.jar ./expr.g4 -no-listener -visitor -o ./src/parser

compile :
	javac -cp ./lib/antlr-4.9.2-complete.jar:./src ./src/Main2.java -d ./bin

run :
	java -cp ./lib/antlr-4.9.2-complete.jar:./bin Main $(target)

