#!/bin/sh

JAR_FILE_NAME=./target/scala-2.13/workshop2-assembly-0.1.0-SNAPSHOT.jar
MAIN_CLASS=traits_demo.TraitsDemo

if [ ! -f ${JAR_FILE_NAME} ]; then
	echo "Jar file not present, you may need to run command 'sbt package' first!"
	
	exit 1
fi

java -cp $JAR_FILE_NAME $MAIN_CLASS

