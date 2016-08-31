svenis-backend
================

Java backend for the svenis app. 

## Stack
 	* Spark Framework
	* H2 Database
	* FlyWay DB 

## Setup
You need maven in order to build and run the project. 
1. `brew update`
2. `brew install maven`

## Running
The first steps in the process is to rebuild the database and package.
```
mvn flyway:migrate install
```

Then run the main method.
```
mvn exec:java -Dexec.mainClass="com.svenis.Main"
```  

 
