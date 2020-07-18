
echo "compiling program keywordSnipApp ..."



 javac SearchEngine.java 



java SearchEngine DirectBuffer 1080 ../Sample.txt

java SearchEngine IndirectBuffer 1080 ../Sample.txt

java SearchEngine BufferedIOStream 1080 ../Sample.txt

java SearchEngine ProgrammerManaged 1080 ../Sample.txt

java SearchEngine DirectBuffer 2080 ../Sample.txt

java SearchEngine IndirectBuffer 2080 ../Sample.txt

java SearchEngine BufferedIOStream 2080 ../Sample.txt

java SearchEngine ProgrammerManaged 2080 ../Sample.txt

pause

