FROM openjdk:17-alpine

EXPOSE 8080

ADD target/SpringBootHomework1-0.0.1-SNAPSHOT.jar app.jar

CMD ["java", "-jar", "app.jar"]