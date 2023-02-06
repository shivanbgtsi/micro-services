#Start with a base image containing Java runtime
FROM openjdk:17-jdk-slim as build

# Add the application's jar to the container
COPY target/accounts.jar accounts.jar

#execute the application
ENTRYPOINT ["java","-jar","/accounts.jar"]