#Starts with a base image containing java runtime
FROM openjdk:17-jdk-slim

#Information about who maintains the image
MAINTAINER samridhi_parihar.com

#Add the application's jar to the image
COPY target/accounts-0.0.1-SNAPSHOT.jar accounts-0.0.1-SNAPSHOT.jar

#execute the application
ENTRYPOINT ["java","-jar","accounts-0.0.1-SNAPSHOT.jar"]