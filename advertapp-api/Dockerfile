FROM maven:3.8.3-openjdk-17 AS build
COPY src /home/advertapp-api/src
COPY pom.xml /home/advertapp-api
WORKDIR /home/advertapp-api
RUN mvn clean install -DskipTests=true

FROM openjdk:19-alpine
RUN apk --no-cache add curl
EXPOSE 8080
COPY --from=build /home/advertapp-api/target/advertapp-api-1.0.0.jar /usr/local/lib/advertapp-api.jar
ENTRYPOINT ["java", "-jar", "/usr/local/lib/advertapp-api.jar"]