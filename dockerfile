# Etapa 1: Construcción
FROM maven:3.9.6-eclipse-temurin-21 AS build
# Establece el directorio de trabajo en el contenedor
WORKDIR /app
# Copia el archivo pom.xml para descargar dependencias
COPY pom.xml .
# Descarga las dependencias del proyecto
RUN mvn dependency:go-offline
# Copia el código fuente de la aplicación al contenedor
COPY src ./src
# Compila y empaqueta la aplicación
RUN mvn clean package -DskipTests
# Etapa 2: Ejecución
FROM openjdk:17-jdk-alpine
# Establece el directorio de trabajo
WORKDIR /app
# Copia el archivo JAR generado desde la etapa de construcción
COPY --from=build /app/target/palfilo-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]