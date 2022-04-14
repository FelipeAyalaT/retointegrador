FROM maven:3.8.4-jdk-11-slim AS build
COPY src /home/app/src
COPY pom.xml /home/app

ENV HOST_MONGODB_SERVER="mongodb_server"
ENV HOST_API_CONFIGSERVER="apiconfigserver"

RUN mvn -f /home/app/pom.xml clean package

FROM openjdk:11-jre
ENV DOCKERIZE_VERSION v0.6.1
RUN wget https://github.com/jwilder/dockerize/releases/download/$DOCKERIZE_VERSION/dockerize-alpine-linux-amd64-$DOCKERIZE_VERSION.tar.gz \
    && tar -C /usr/local/bin -xzvf dockerize-alpine-linux-amd64-$DOCKERIZE_VERSION.tar.gz \
    && rm dockerize-alpine-linux-amd64-$DOCKERIZE_VERSION.tar.gz

COPY --from=build /home/app/target/favoritos-*SNAPSHOT.jar /opt/app.jar

ENV HOST_MONGODB_SERVER="mongodb_server"
ENV HOST_API_CONFIGSERVER="apiconfigserver"

ENTRYPOINT ["java","-jar","/opt/app.jar"]
EXPOSE 8085