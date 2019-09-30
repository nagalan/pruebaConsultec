# Microservicio Java para Gestion de Clientes

## Credenciales Autenticacion
- Usuario ROl USER: user / passwod

## Ambiente Desarrollo
- Maven: 3.6.0
- Java Version: 1.8.0_211
- OS: windows 10
- IDE: Visual Studio Code v.1.38.1

## Setup

- Para poder ejecutar se debe tener instalado maven (o maven para windows)
- JDK 1.8

## Scripts
- `mvn spring-boot:run` Inicia servidor (refresco automatico ante cambios)
- `mvn test` Corre los test unitarios y de integración del proyecto

### Java

Se utiliza [Java](https://www.java.com/) como lenguaje de desarrollo, compilando con Maven 3 y Java 1.8.

### Framework

Este proyecto utiliza [Spring](https://spring.io/projects/spring-framework) como framework con tecnologia [Spring](https://spring.io/projects/spring-boot) para simplificar la implementación.

### Spotbugs

En el proyecto se utiliza [Spotbugs](https://spotbugs.github.io/) para análisis estático de código. 

### H2

En el proyecto se utiliza [H2](http://www.h2database.com/html/quickstart.html) como Base de Datos Embebida.

Se podrá acceder a la consola en la ruta `/h2-console`

Ejemplo: `http://localhost:6001/h2-console`

### Documentación de la API

El proyecto utiliza el estándar [OpenAPI](https://www.openapis.org/) de documentación utilizando Swagger como editor y visualización de API Docs con Swagger-UI embebido.
Se debe documentar la especificación del API la clase `SwaggerConfiguration.java`.

Se podrá acceder a Swagger-UI en la ruta `/swagger-ui.html`

Ejemplo: `http://localhost:6001/gestion-clientes/v1/swagger-ui.html`


### Docker

Se puede generar localmente la imagen de Docker para este Microservicio ejecutando el comando:

`mvn clean package`

El nombre de la imagen se genera de la siguiente manera ${docker.image.prefix}/${project.artifactId}:${project.version}

Luego iniciar un contenedor con:

`docker run -d -e spring.profiles.active=dev -p 6005:6001 --name dv-gestion-cliente consultec/gestion-cliente:0.0.1-SNAPSHOT`