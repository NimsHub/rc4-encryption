FROM openjdk:17-alpine
LABEL authors="nims"
COPY target/rc4-encryption-1.0.jar app.jar
WORKDIR /app
ENTRYPOINT ["java","-jar", "app.jar"]