FROM openjdk:11
COPY "./target/Movimiento-0.0.1-SNAPSHOT.jar" "app.jar"
EXPOSE 9030
ENTRYPOINT ["java", "-Dspring.profiles.active=docker", "-jar","app.jar"]