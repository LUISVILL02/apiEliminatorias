FROM openjdk:17-jdk-alpine

WORKDIR /app

COPY build/libs/my-spring-boot-app.jar /app/my-spring-boot-app.jar

EXPOSE 8083

CMD ["java", "-jar", "/app/my-spring-boot-app.jar"]
