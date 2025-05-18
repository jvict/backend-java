# Usando uma imagem base do OpenJDK
FROM maven:3.8-openjdk-17 as maven

# Diretório de trabalho dentro do container
WORKDIR /app

COPY pom.xml pom.xml
RUN mvn dependency:go-offline
COPY . .
RUN mvn clean package -DskipTests=true

COPY /target/*.jar  app.jar

# Expor a porta que a aplicação vai rodar
EXPOSE 8080

# Comando para rodar a aplicação quando o container iniciar
ENTRYPOINT ["java", "-jar", "app.jar"]
