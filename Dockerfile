FROM maven:3.5-jdk-8-alpine as builder
WORKDIR /app
COPY ./pom.xml /app/pom.xml
RUN mvn dependency:go-offline -B
COPY . /app

RUN mvn clean package

FROM openjdk:8-jre-alpine
COPY --from=builder /app/target/login-service.jar app.jar

RUN wget -O dd-java-agent.jar 'https://search.maven.org/classic/remote_content?g=com.datadoghq&a=dd-java-agent&v=LATEST'
ENTRYPOINT ["java","-javaagent:dd-java-agent.jar","-Ddatadog.slf4j.simpleLogger.defaultLogLevel=debug","-jar","app.jar"]
