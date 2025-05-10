# Etapa 1: Build de la app usando Gradle 8.5 y JDK 21
FROM gradle:8.5-jdk21 AS builder

WORKDIR /app

# Copia todos los archivos del proyecto
COPY . .

# Construye el JAR (sin ejecutar tests)
RUN gradle build -x test

# Etapa 2: Imagen de ejecución con JRE 21
FROM eclipse-temurin:21-jre-alpine

WORKDIR /app

# Copia el JAR generado desde la etapa anterior
COPY --from=builder /app/build/libs/*.jar app.jar

# Exponer el puerto 8080
EXPOSE 8080

# Comando de inicio
ENTRYPOINT ["java", "-jar", "app.jar"]