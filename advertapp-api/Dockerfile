FROM maven:3.8.3-openjdk-17 AS build
COPY src /home/advertapp-api/src
COPY pom.xml /home/advertapp-api
WORKDIR /home/advertapp-api
RUN mvn clean install -DskipTests=true

FROM eclipse-temurin:17.0.7_7-jre-jammy
RUN apt install curl
EXPOSE 8080
COPY --from=build /home/advertapp-api/target/advertapp-api-1.0.0.jar /usr/local/lib/advertapp-api.jar
ENTRYPOINT ["java", "-jar", "/usr/local/lib/advertapp-api.jar"]