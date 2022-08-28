FROM openjdk:18-jdk-alpine
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} BEConfig.jar
EXPOSE 8888
ENTRYPOINT ["java","-jar","/BEConfig.jar"]
