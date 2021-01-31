FROM openjdk:8-jre-alpine

ENV MAVEN_VERSION 3.2.5

COPY . /data/idaithalam-server
WORKDIR /data/idaithalam-server

RUN ["mvn", "clean", "install"]

ADD target/idaiserver-0.1.0.jar /openapi/virtualan/idaiserver.jar

ENTRYPOINT ["java", "-jar", "/openapi/virtualan/idaiserver.jar"] 
