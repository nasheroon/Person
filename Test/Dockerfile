FROM openjdk:8-jdk-alpine
WORKDIR /opt/app
ARG JAR_FILE=target/spring-boot-person.jar
COPY ${JAR_FILE} person.jar
ENTRYPOINT ["java","-jar","person.jar"]