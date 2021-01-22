FROM openjdk:8-jre-alpine

ADD target/idaiserver-0.1.0.jar /openapi/virtualan/idaiserver.jar

ENTRYPOINT ["java", "-jar", "/openapi/virtualan/idaiserver.jar"] 
