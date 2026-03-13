FROM eclipse-temurin:17-jre-alpine
EXPOSE 8081
ADD target/SpringBootLearn-0.0.1-SNAPSHOT.jar testcont.jar
CMD ["java", "-jar", "testcont.jar"]