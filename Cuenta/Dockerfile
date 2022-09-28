FROM openjdk:11
COPY "./target/Cuenta-0.0.1-SNAPSHOT.jar" "app.jar"
EXPOSE 9040
ENTRYPOINT ["java", "-Dspring.profiles.active=docker", "-jar","app.jar"]