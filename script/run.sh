#!/bin/sh

cd ..
# Build
mvn clean install
cd target/conf
# Change config file based on linux env
sed -i 's#logs\\FacebookCollector.log#../logs/FacebookCollector.log#g' log4j.properties
cd ../bin
# Run program
java -jar FacebookCollector-0.1-SNAPSHOT.jar -config.path=../conf -page.id=1597890090324971 -enable.filter=false
