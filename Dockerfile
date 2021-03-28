FROM openjdk:8-jdk-alpine
COPY webapp/target/webapp-1.0.jar app.jar
ENTRYPOINT java -jar /app.jar