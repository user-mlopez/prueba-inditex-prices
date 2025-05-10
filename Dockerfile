# Etapa de construcción
FROM amazoncorretto:21-alpine-jdk AS builder

# Usar una ruta absoluta para el WORKDIR
WORKDIR extracted

# Añadir el archivo JAR en el contenedor
ADD ./build/libs/*.jar app.jar

# Extraer las capas usando layertools
RUN java -Djarmode=layertools -jar app.jar extract

# Etapa de producción
FROM amazoncorretto:21-alpine-jdk

# Usar una ruta absoluta para el WORKDIR
WORKDIR application

# Copiar las capas extraídas desde la etapa de construcción
COPY --from=builder extracted/dependencies/ ./
COPY --from=builder extracted/spring-boot-loader/ ./
COPY --from=builder extracted/snapshot-dependencies/ ./
COPY --from=builder extracted/application/ ./

# Exponer el puerto 8080
EXPOSE 8080

# Configurar el entrypoint para ejecutar la aplicación
ENTRYPOINT ["java", "org.springframework.boot.loader.launch.JarLauncher"]