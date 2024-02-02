FROM openjdk:21-ea-18-jdk-buster as base
RUN apt-get update && apt-get upgrade -y
RUN apt-get install redis -y

LABEL author="Matthew Jeshua Martin"

WORKDIR /app

ENV JAVA_HOME=/usr/local/openjdk-21

ENV PATH="${JAVA_HOME}/bin:${PATH}"

COPY mvnw mvnw.cmd ./
COPY .mvn/ .mvn/
COPY pom.xml ./

RUN ./mvnw dependency:resolve

COPY src ./src



FROM base as test
CMD ["./mvnw", "test"]

FROM base as development
CMD ["./mvnw", "spring-boot:run"]

EXPOSE 8080
