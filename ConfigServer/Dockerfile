FROM openjdk:11
COPY "./target/ConfigServer-0.0.1-SNAPSHOT.jar" "app.jar"
EXPOSE 9080
ENTRYPOINT ["java", "-jar","app.jar"]