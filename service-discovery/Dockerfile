#Start with a base image containing Java runtime
FROM openjdk:11-slim as build

# Add the application's jar to the container
COPY target/service-discovery.jar service-discovery.jar

#execute the application
ENTRYPOINT ["java","-jar","/service-discovery.jar"]