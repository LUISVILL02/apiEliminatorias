FROM openjdk:17-jdk-alpine

WORKDIR /app

COPY target/apiEliminatorias-0.0.1-SNAPSHOT.jar /app/apiEliminatorias.jar

EXPOSE 0

CMD ["java", "-jar", "/app/apiEliminatorias.jar"]
