FROM openjdk:11 as build
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} productservice.jar
EXPOSE 5000
ENTRYPOINT ["java","-jar","/productservice.jar"]