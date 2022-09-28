FROM openjdk:11
COPY "./target/Cliente-0.0.1-SNAPSHOT.jar" "app.jar"
EXPOSE 9050
ENTRYPOINT ["java", "-Dspring.profiles.active=docker", "-jar","app.jar"]