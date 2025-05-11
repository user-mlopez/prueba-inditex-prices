# Prueba técnica - Inditex tarifas productos
![Build Status](https://github.com/user-mlopez/prueba-inditex-prices/actions/workflows/sonar.yml/badge.svg) ![Sonar Quality Gate](https://sonarcloud.io/api/project_badges/measure?project=user-mlopez_prueba-inditex-prices&metric=alert_status)

Este proyecto es una API REST desarrollada como prueba técnica de un proceso de selección para la consultora BCNC group, tiene la siguiente definición inicial:  

El objetivo principal es crear una aplicación o servicio utilizando el framework Spring Boot que ofrezca un punto de acceso REST para realizar consultas sobre esta base de datos de precios. Este endpoint REST deberá:
- Aceptar como parámetros de entrada la fecha de consulta (o aplicación), el identificador del producto y el identificador de la marca.
- Proporcionar como resultado el identificador del producto, el identificador de la marca, la tarifa que se aplica, el intervalo de fechas durante el cual se aplica el precio y el precio final que debe aplicarse.

---

## Enlaces
🔗 **[Accede a la API](https://prueba-inditex-prices.onrender.com/openapi/swagger-ui/index.html)**   
⚠️ La aplicación puede tardar más de 50 segundos en cargar. 
Debido a que utiliza un plan Hobby de Render (gratuito), el cual detiene automáticamente la instancia tras un período de inactividad.  
🔗 **[Accede al informe de cobertura JaCoCo](https://user-mlopez.github.io/prueba-inditex-prices/index.html)**  
🔗 **[Accede al informe de calidad de SonarCloud](https://sonarcloud.io/summary/new_code?id=user-mlopez_prueba-inditex-prices&metric)**  

---

## Principales tecnologías y herramientas utilizadas
La implementación ha sido realizada con **Spring Boot** como framework principal junto con **Gradle** como herramienta de construcción y control de dependencias. El código ha sido implementado con estructura héxagonal. Para el control de versiones se ha utilizado **Git** y **GitHub**. **GitHub Actions** se ha utilizado para CI junto con las herramientas de testing **JaCoCo** para la cobertura de pruebas y **SonarQube** para el análisis de calidad del código.
En el soporte de CD se ha utilizado también **GitHub Actions** junto a **Docker**, y el despliegue de forma automática en **Render**.

- *Spring Boot* (Web, Data JPA, Actuator,...)
- *Spring Boot Test, JUnit*
- *Gradle*
- *MapStruct* (mapeo de DTOs)
- *Logbook* (logging estructurado)
- *Micrometer + Brave* (tracing)
- *SpringDoc OpenAPI* (documentación Swagger)
- *JaCoCo* (cobertura de pruebas)
- *SonarQube* (análisis estático)
- *Docker*
- *H2 Database* (modo runtime)
- *GitHub Actions*
- *Render* (despliegue en la nube)

---

## ⚙️ Instalación local

1. Clona el repositorio:
```bash
git clone https://github.com/user-mlopez/prueba-inditex-prices.git
cd nombre-del-repositorio
```

2. Compila y ejecuta la aplicación:
```bash
./gradlew build
./gradlew bootRun
```

3. Accede a la API en: '[http://localhost:8080/openapi/swagger-ui/index.html](http://localhost:8080/openapi/swagger-ui/index.html)'

---

## ⚙️ Pruebas y cobertura

Ejecuta las pruebas unitarias con:

```bash
./gradlew test
```

Genera el reporte de cobertura:

```bash
./gradlew jacocoTestReport
```

Abre el reporte en el navegador:

```bash
open build/reports/jacoco/test/html/index.html
```

---

## ⚙️ CI/CD con GitHub Actions

Cada `push` a la rama `main` activa el flujo de integración y entrega continua:

1. Compilación y ejecución de pruebas.
2. Generación de reportes de cobertura (JaCoCo).
3. Publicar el informe HTML de JaCoCo en GitHub Pages
4. Análisis con SonarQube.
5. Construcción de imagen Docker.
6. Despliegue automático en Render.
