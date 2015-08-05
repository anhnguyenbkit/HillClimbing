if [ "$1" = "c" ]; then
    cd src
    javac -target 1.6 *.java
else
    echo $1
    echo java -cp src Main "data/test/$1"
    java -cp src Main "data/test/$@"
fi
