FROM openjdk:11-jdk-slim

COPY . /data/idaithalam-server
WORKDIR /data/idaithalam-server

RUN ["mvn", "clean", "install"]

ADD target/idaiserver-0.1.0.jar /openapi/virtualan/idaiserver.jar

ENTRYPOINT ["java", "-jar", "/openapi/virtualan/idaiserver.jar"] 
