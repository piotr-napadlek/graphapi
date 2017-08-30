# Prerequisites 
You need at least Java (1.8) and maven (3.3.9) in order to run the project. Make sure that your console responds to `java -version` and `mvn -version`

# Whitelisted Ports
Following ports are whitelisted for Facebook auth. Please choose one of them:
 - 8189
 - 8999
 - 8018
 - 9001
 
# Running
The easiest way to launch the app is to call `mvn spring-boot:run`. 
Use `mvn spring-boot:run -Dserver.port=9001` if you don't want to use default 8189 port.
You can also call `mvn clean install` to build the application and deploy packaged war to any application server. You can also start the project in your IDE.

# Usage
After Launching, open the browser and go for `http://localhost:<your_port>/poland/poznan/lidl`. 
You will be asked to log-in first. Please use tha button and login with facebook account. After successful login you can play with the url and search for 
different urls.


