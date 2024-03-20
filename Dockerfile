FROM eclipse-temurin:17-jdk

LABEL authors="egcologne"

COPY gestion.equipos/target/gestion.equipos-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]