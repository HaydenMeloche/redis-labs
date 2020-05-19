# Redis Solar application - Redis for Java Developers

This is a sample application that was used as a playground for implementing various challenges throughout the RU102J course. Each challenge was completed in a individual commit. 

### Setup

#### Docker
Setup Redis (with Time series through docker)
```
docker run -p 6379:6379 -it --rm redislabs/redistimeseries
```
#### Building the Project
```
$ mvn package
```
Running the Test Suite
```
$ mvn test

```
Running an individual test case:
```
$ mvn test -Dtest=HelloTest
```
#### Loading the Sample Data
The sample application includes a data loader. You should run this after completing each programming challenge. Often, you'll be writing code that inserts data into the application. Running the data loader will ensure that this data is always up to date.
```
$ java -jar target/redisolar-1.0.jar load
```
Running the Sample Application
You'll also want to be able to run the sample application. From the command line, run the following:
```
$ java -jar target/redisolar-1.0.jar server config.yml
```
Now, you can then navigate to http://localhost:8081 to see the app.
