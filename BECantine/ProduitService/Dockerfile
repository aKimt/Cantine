FROM openjdk:18-jdk-alpine
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} BECantine.jar
EXPOSE 8989
ENTRYPOINT ["java","-jar","/BECantine.jar"]