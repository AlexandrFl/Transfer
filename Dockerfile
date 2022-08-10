FROM openjdk:8-jdk-alpine
EXPOSE 8090
ADD target/transfer-0.0.1-SNAPSHOT.jar j.jar
ENTRYPOINT ["java", "-jar", "/j.jar"]