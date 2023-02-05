#Start with a base image containing Java runtime
FROM openjdk:11-slim as build

# Add the application's jar to the container
COPY target/loans.jar loans.jar

#execute the application
ENTRYPOINT ["java","-jar","/loans.jar"]