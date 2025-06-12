FROM maven:3.8-openjdk-17 as maven

WORKDIR /app

# Copia tudo de uma vez para garantir que o cache seja invalidado com qualquer mudança
COPY . .

RUN mvn clean install package -DskipTests=true

FROM openjdk:17-jdk-slim

WORKDIR /app

COPY --from=maven /app/target/festas-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-Dspring.profiles.active=qa", "-jar", "app.jar"]