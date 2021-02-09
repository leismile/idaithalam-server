#
# Build stage
#
FROM maven:3.6.0-jdk-11-slim AS build
#FROM adoptopenjdk/openjdk11:alpine
LABEL maintainer="info@virtualan.io"
COPY . /home/app/
RUN mvn -f /home/app/pom.xml clean package

#
# Package stage
#
FROM openjdk:11-jre-slim
COPY --from=build /home/app/target/idaiserver-0.1.0.jar /openapi/virtualan/idaiserver.jar
ENTRYPOINT ["java", "-jar", "/openapi/virtualan/idaiserver.jar"] 
