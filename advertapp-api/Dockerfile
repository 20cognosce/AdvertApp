FROM maven:3.8.3-openjdk-17 AS build
COPY src /home/app/src
COPY pom.xml /home/app
WORKDIR /home/app
RUN mvn clean install -DskipTests=true

FROM openjdk:17
EXPOSE 8080
COPY --from=build /home/app/target/advertapp-api-1.0.0.jar /usr/local/lib/advertapp-api.jar
ENTRYPOINT ["java", "-jar", "/usr/local/lib/advertapp-api.jar"]