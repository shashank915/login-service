FROM maven:3.5-jdk-8-alpine as builder
WORKDIR /app
COPY ./pom.xml /app/pom.xml
RUN mvn dependency:go-offline -B
COPY . /app

RUN mvn clean package

FROM openjdk:8-jre-alpine
COPY --from=builder /app/target/login-service.jar app.jar

ENTRYPOINT ["java","-jar","app.jar"]
