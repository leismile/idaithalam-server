#
# Build stage
#
FROM maven:3.6.0-jdk-11-slim AS build
#FROM adoptopenjdk/openjdk11:alpine
LABEL maintainer="info@virtualan.io"
COPY . /data/idaithalam-server
WORKDIR /data/idaithalam-server
RUN ["mvn", "clean", "install"]

#
# Package stage
#
FROM openjdk:11-jre-slim
COPY --from=build /data/idaithalam-server/target/demo-0.0.1-SNAPSHOT.jar /openapi/virtualan/idaiserver.jar
ENTRYPOINT ["java", "-jar", "/openapi/virtualan/idaiserver.jar"] 
