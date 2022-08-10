# jsonlogprocessor
# Text file processor parse the json containt and persist into db after applying busines logic 

## Implementation

1. Application is developed in Spring Boot with Java 8 on Spring Tool Suite IDE. Database used is HSQLDB database.

2. You can run application from command line

	$ mvn spring-boot:run -Dspring-boot.run.arguments="D:\\Sonal\\interview\\sapient\\workpace\\jsonlogprocessor\\logfile.txt"

3. Logging is done on console and file

5. JUnit/Integration test implemented. Sample file is inbuild in test/resources which executed at time of Junit [integration test]

## Profiling 
	
	62,00,000 records process in ~10 min - 10k records in 1 second

## Implementation

1. Commandline Runner used
2. Parllel Stream used file procesessing 
3. Found events written to working directory 
	
	#Working Director => {project_path}data/mydb
	
	#Logs Directory => {project_path}logs/app.log [all the old log archived at logs\archived folder]