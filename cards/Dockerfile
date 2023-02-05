#Start with a base image containing Java runtime
FROM openjdk:11-slim as build

# Add the application's jar to the container
COPY target/cards.jar cards.jar

#execute the application
ENTRYPOINT ["java","-jar","/cards.jar"]