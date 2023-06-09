FROM --platform=linux/x86_64 eclipse-temurin:17-jdk-alpine
COPY build/libs/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]