FROM gradle:6.2.2-jdk11 AS build
COPY src /app/src
COPY build.gradle /app
WORKDIR /app
RUN gradle bootJar --stacktrace

FROM openjdk:11.0.6-slim
COPY --from=build /app/build/libs/app-0.0.1-SNAPSHOT.jar /app/lend.jar
EXPOSE 8082
ENTRYPOINT ["java", "-jar", "/app/lend.jar"]
