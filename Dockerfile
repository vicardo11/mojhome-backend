FROM maven:3.8.4-openjdk-17 AS build

WORKDIR /app

COPY pom.xml .
COPY lombok.config .
COPY aspect-library ./aspect-library
COPY finances ./finances

RUN mvn clean install -DskipTests

FROM openjdk:17-jdk-slim

WORKDIR /app

COPY --from=build /app/finances/target/finances-1.0.0.jar finances-1.0.0.jar

EXPOSE 8200

CMD ["java", "-jar", "finances-1.0.0.jar"]
