# Getting Started with Spring Boot

## 1. What is Spring Boot?
Spring Boot makes it easy to create stand-alone, production-grade Spring based Applications that you can "just run".

## 2. Key Features
-   Create stand-alone Spring applications
-   Embed Tomcat, Jetty or Undertow directly (no need to deploy WAR files)
-   Provide opinionated 'starter' dependencies to simplify your build configuration
-   Automatically configure Spring and 3rd party libraries whenever possible
-   Provide production-ready features such as metrics, health checks, and externalized configuration
-   Absolutely no code generation and no requirement for XML configuration

## 3. Spring Initializr (start.spring.io)
The easiest way to start is using the Spring Initializr.
-   **Project**: Maven Project
-   **Language**: Java
-   **Spring Boot**: Latest Stable
-   **Dependencies**: Spring Web, Spring Data JPA, H2 Database (for starting)

## 4. Main Application Class
Annotated with `@SpringBootApplication`.
-   `@Configuration`: Tags the class as a source of bean definitions.
-   `@EnableAutoConfiguration`: Tells Spring Boot to start adding beans based on classpath settings.
-   `@ComponentScan`: Tells Spring to look for other components, configurations, and services in the `com/example` package.
