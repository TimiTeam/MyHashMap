#on linux
find src/main/ -name "*.java" > sources.txt
mkdir -p bin
javac -d bin -sourcepath src @sources.txt
if [ $? -eq 0 ]
then
	java -cp bin:com/gmail/timurworkspace/ com.gmail.timurworkspace.App
fi
