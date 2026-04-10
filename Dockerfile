FROM eclipse-temurin:25
LABEL maintainer="abade.mth@gmail.com"
WORKDIR /app
COPY target/DesafioTecnicoMuralis2026-0.0.1-SNAPSHOT.jar /app/agenda.jar
ENTRYPOINT ["java", "-jar", "agenda.jar"]
EXPOSE 8080