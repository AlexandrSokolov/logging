## The project demonstrates how:
- Spring controls logging by default
- to change log level for a custom package via Spring actuator
- to change log level for a custom package via a custom rest services (more secured way, compared to Actuator)

## Topics:
- [Logging API in Spring](#logging-in-spring)
- [Testing](#testing)
- [Run project](#run-project)
- [Change logging level at runtime via Spring Actuator](#change-logging-level-at-runtime-via-actuator)
- [Change logging level at runtime via `LoggingSystem`](#change-logging-level-at-runtime-via-loggingsystem)
- [Set logging level during application startup](#set-logging-level-during-application-startup)
- [Built-time logging configuration](#built-time-logging-configuration)
- [Logging-related dependencies](#logging-related-dependencies)

### Logging in Spring

In Spring `logback` is used as a logging framework if `spring-boot-starter-logging` is included into the dependencies. 
It is included by default via `spring-boot-starter`, so you do not need to configure it explicitly.

You can use the following logging APIs:
- [Slf4j Logging API](src/main/java/com/example/sb/logback/service/Slf4jService.java)
    ```java
    import org.slf4j.Logger;
    import org.slf4j.LoggerFactory;
    import org.springframework.stereotype.Service;
    
    @Service
    public class Slf4jService {
    
      private static final Logger logger = LoggerFactory.getLogger(Slf4jService.class);
    
      void slf4jLog() {
        logger.debug("Performing Slf4jService.slf4jLog...");
      }
    }
    ```
- Log4j2 Logging API
    ```java
    import org.apache.logging.log4j.LogManager;
    import org.apache.logging.log4j.Logger;
    import org.springframework.stereotype.Service;
    
    @Service
    public class Log4j2Service {
    
      private static final Logger logger = LogManager.getLogger();
    
      void log4j2Log() {
        logger.debug("Performing Log4j2Service.log4j2Log...");
      }
    }
    ```
  
### Testing

[Test the logging calls](src/test/java/com/example/sb/logback/service/LogsDemoServiceTest.java)

Debug level for the test is configured in the [test properties](src/test/resources/application.yaml)

### Run project

Run:
```bash
mvn spring-boot:run
```

Send a rest get call and check the logs in the console:
```bash
curl -i -X GET -w "\n" http://localhost:8080/rest/demo
```
You'll find nothing in the console. [The logging levels are set to `INFO`](src/main/resources/application.yaml):
```yaml
logging:
  level:
    com:
      example:
        sb:
          logback: info
    org:
      springframework: info
      jboss:
        resteasy: info
```

### Change logging level at runtime via actuator

Add the Spring Boot Actuator Maven dependency
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-actuator</artifactId>
    <scope>runtime</scope>
</dependency>
```
Enable the `/loggers` endpoint in [application.yaml](src/main/resources/application.yaml) file:
```yaml
management:
  endpoints:
    web:
      exposure:
        include:
          - loggers
  endpoint:
    loggers:
      access: read_only
```

View current loggers:
```bash
curl -i -X GET -w "\n" http://localhost:8080/actuator/loggers
```

View a specific logger:
```bash
curl -i -X GET -w "\n" http://localhost:8080/actuator/loggers/com.example.sb.log4j2
```

Changing Log Levels at Runtime;
```bash
curl -i -X POST -w "\n" -H 'Content-Type: application/json' -d '{"configuredLevel": "DEBUG"}' http://localhost:8080/actuator/loggers/com.example.sb.log4j2 
```
Change it back:
```bash
curl -i -X POST -w "\n" -H 'Content-Type: application/json' -d '{"configuredLevel": "INFO"}' http://localhost:8080/actuator/loggers/com.example.sb.log4j2 
```

Official docs on actuator and the related properties:
- [See `Actuator Endpoints` for more information](https://docs.spring.io/spring-boot/reference/actuator/endpoints.html)
- [Change log for the changed properties](https://github.com/spring-projects/spring-boot/wiki/Spring-Boot-3.4-Configuration-Changelog)


### Change logging level at runtime via  `LoggingSystem`

Change the log level to debug [via `LoggingSystem` and a custom rest service](src/main/java/com/example/sb/logback/rest/service/DebugLoggingConfigRestService.java):
Set `debug mode` for `com.example.sb.logback` and `com.savdev.example.logging` packages:
```bash
curl -i -X POST -w "\n" -H 'Content-Type: application/json' http://localhost:8080/rest/logging/config/debug/com.example.sb.logback
curl -i -X POST -w "\n" -H 'Content-Type: application/json' http://localhost:8080/rest/logging/config/debug/com.savdev.example.logging
```

Send the get request:
```bash
curl -i -X GET -w "\n" http://localhost:8080/rest/demo
```

In the log you can find all logging statements (except for JUL, TODO):
```text
2025-09-18T11:42:47.965+02:00 DEBUG 563993 --- [nio-8080-exec-8] c.e.sb.logback.service.LogsDemoService   : Slf4j call:
2025-09-18T11:42:47.965+02:00 DEBUG 563993 --- [nio-8080-exec-8] c.e.sb.logback.service.Slf4jService      : Performing Slf4jService.slf4jLog...
2025-09-18T11:42:47.965+02:00 DEBUG 563993 --- [nio-8080-exec-8] c.e.sb.logback.service.LogsDemoService   : Log4j2 call:
2025-09-18T11:42:47.966+02:00 DEBUG 563993 --- [nio-8080-exec-8] c.e.sb.logback.service.Log4j2Service     : Performing Log4j2Service.log4j2Log...
2025-09-18T11:42:47.966+02:00 DEBUG 563993 --- [nio-8080-exec-8] c.e.sb.logback.service.LogsDemoService   : Slf4j lib call:
2025-09-18T11:42:47.966+02:00 DEBUG 563993 --- [nio-8080-exec-8] c.savdev.example.logging.slf4j.Slf4jLib  : Performing action...
2025-09-18T11:42:47.966+02:00 DEBUG 563993 --- [nio-8080-exec-8] c.e.sb.logback.service.LogsDemoService   : Log4j2 lib call:
2025-09-18T11:42:47.967+02:00 DEBUG 563993 --- [nio-8080-exec-8] c.s.example.logging.log4j2.Log4j2Lib     : Performing action...
2025-09-18T11:42:47.967+02:00 DEBUG 563993 --- [nio-8080-exec-8] c.e.sb.logback.service.LogsDemoService   : JCL v1.3 lib call:
2025-09-18T11:42:47.967+02:00 DEBUG 563993 --- [nio-8080-exec-8] c.savdev.example.logging.jcl.JclV1_3Lib  : Performing action...
2025-09-18T11:42:47.968+02:00 DEBUG 563993 --- [nio-8080-exec-8] c.e.sb.logback.service.LogsDemoService   : Jul lib call:
2025-09-18T11:42:47.968+02:00 DEBUG 563993 --- [nio-8080-exec-8] c.e.sb.logback.service.LogsDemoService   : JCL v1.2 lib call:
2025-09-18T11:42:47.968+02:00 DEBUG 563993 --- [nio-8080-exec-8] com.savdev.example.logging.jcl.JclLib    : Performing action...
2025-09-18T11:42:47.968+02:00 DEBUG 563993 --- [nio-8080-exec-8] c.e.sb.logback.service.LogsDemoService   : Log4j (v1) lib call:
2025-09-18T11:42:47.968+02:00 DEBUG 563993 --- [nio-8080-exec-8] c.s.example.logging.log4j_v1.Log4V1jLib  : Performing action...
```

Set `info` mode back for `com.example.sb.logback` and `com.savdev.example.logging` packages:
```bash
curl -i -X POST -w "\n" -H 'Content-Type: application/json' http://localhost:8080/rest/logging/config/info/com.example.sb.logback
curl -i -X POST -w "\n" -H 'Content-Type: application/json' http://localhost:8080/rest/logging/config/info/com.savdev.example.logging
```

### Set logging level during application startup

We can use a `logging.level` property, responsible for setting a single package (and only package).
We can use a `spring.application.json` property, for more complicated configurations, including java classes.

1. Via environment variables

   ```bash
   export LOGGING_LEVEL_COM_EXAMPLE_SB_LOG4J2=DEBUG && java -jar target/sb-log4j2-1.0.0.jar
   ```
   or more for more complicated configurations:
   ```bash
   export SPRING_APPLICATION_JSON='{"logging.level.com.example.sb.log4j2":"debug"}' && java -jar target/sb-log4j2-1.0.0.jar
   ```
   or when you run via `mvn spring-boot:run`:
   ```bash
   export LOGGING_LEVEL_COM_EXAMPLE_SB_LOG4J2=DEBUG && mvn spring-boot:run
   ```
   for docker compose:
   ```yaml
       environment:
         - LOGGING_LEVEL_COM_EXAMPLE_SB_LOG4J2=DEBUG
   ```

   or (todo could not get it work for docker compose):
   ```bash
   export SPRING_APPLICATION_JSON='{"logging.level.com.example.sb.log4j2":"debug"}' && mvn spring-boot:run
   ```
    does not work in docker composition:
    ```yaml
       environment:
        - SPRING_APPLICATION_JSON:'{"logging.level.com.example.sb":"debug"}'
    ```
    could not be parsed with `=` as a separator:
    ```yaml
       environment:
        - SPRING_APPLICATION_JSON='{"logging.level.com.example.sb":"debug"}'
    ```

2. Via system properties

   ```bash
   java -Dlogging.level.com.example.sb.log4j2=debug -jar target/sb-log4j2-1.0.0.jar
   ```
   or more for more complicated configurations:
   ```bash
   java -Dspring.application.json='{"logging.level.com.example.sb.log4j2":"debug"}' -jar target/sb-log4j2-1.0.0.jar
   ```
   or when you run via `mvn spring-boot:run`:
   ```bash
   mvn spring-boot:run -Dspring-boot.run.jvmArguments="-Dlogging.level.com.example.sb.log4j2=debug"
   ```
   or (not recommended, format is complicated):
   ```bash
   mvn spring-boot:run -Dspring-boot.run.jvmArguments="-Dspring.application.json='{\"logging.level.com.example.sb.log4j2\":\"debug\"}'"
   ```

3. Via command line arguments:

   ```bash
   java -jar target/sb-log4j2-1.0.0.jar --logging.level.com.example.sb.log4j2=debug
   ```
   or when you run via `mvn spring-boot:run`:
   ```bash
   mvn spring-boot:run -Dspring-boot.run.arguments="--logging.level.com.example.sb.log4j2=debug"
   ```
   or:
   ```bash
   java -jar target/sb-log4j2-1.0.0.jar --spring.application.json='{"logging.level.com.example.sb.log4j2":"debug"}'
   ```
   or (not recommended, format is complicated):
   ```bash
   mvn spring-boot:run -Dspring-boot.run.arguments="--spring.application.json='{\"logging.level.com.example.sb.log4j2\":\"debug\"}'"
   ```

### Built-time logging configuration

The configuration file inside the package:
```yaml
logging:
  level:
    com:
      example:
        sb:
          logback: info
    org:
      springframework: info
      jboss:
        resteasy: info
```

### Logging-related dependencies:

Logging implementation:
- `logback-classic` - Spring logging default framework
Logging bridges, enabled by Spring:
- `log4j-to-slf4j` - to translate log4j calls to SLF4J API (Log4j-to-SLF4J bridge)
- `jul-to-slf4j` - to translate JUL (Java Logging) calls to Log4j API (JUL-to-Log4j bridge)


Logging bridge, added explicitly to the dependencies:
- `log4j-over-slf4j` - to translate log4j-1.x records to SLF4J API and fix 
  `java.lang.NoClassDefFoundError: org/apache/log4j/LogManager`

Logging API available for any Spring project:
- `log4j2` api is available through `log4j-to-slf4j` (not visible here, because we use dependencies on the custom libraries)
- `slf4j` api - is available through `logback-classic`

The project dependency tree:
```text
[INFO] --- dependency:3.8.1:tree (default-cli) @ spring-boot-logback-logging ---
[INFO] com.example:spring-boot-logback-logging:jar:1.0.0
[INFO] +- org.springframework.boot:spring-boot-starter-web:jar:3.5.0:compile
[INFO] |  +- org.springframework.boot:spring-boot-starter:jar:3.5.0:compile
[INFO] |  |  +- org.springframework.boot:spring-boot-starter-logging:jar:3.5.0:compile
[INFO] |  |  |  +- ch.qos.logback:logback-classic:jar:1.5.18:compile
[INFO] |  |  |  |  \- ch.qos.logback:logback-core:jar:1.5.18:compile
[INFO] |  |  |  +- org.apache.logging.log4j:log4j-to-slf4j:jar:2.24.3:compile
[INFO] |  |  |  \- org.slf4j:jul-to-slf4j:jar:2.0.17:compile
[INFO] +- org.jboss.resteasy:resteasy-core-spi:jar:6.2.10.Final:compile
[INFO] |  +- org.jboss.logging:jboss-logging:jar:3.6.1.Final:compile
[INFO] +- com.savdev.example.logging:slf4j-lib:jar:1.0.0:compile
[INFO] |  +- org.slf4j:slf4j-api:jar:2.0.17:compile
[INFO] +- com.savdev.example.logging:log4j2-lib:jar:1.0.0:compile
[INFO] |  \- org.apache.logging.log4j:log4j-api:jar:2.24.3:compile
[INFO] +- com.savdev.example.logging:jcl-v1.3-lib:jar:1.0.0:compile
[INFO] |  \- commons-logging:commons-logging:jar:1.3.5:compile
[INFO] +- com.savdev.example.logging:jul-lib:jar:1.0.0:compile
[INFO] +- com.savdev.example.logging:jcl-lib:jar:1.0.0:compile
[INFO] +- com.savdev.example.logging:log4j-v1-lib:jar:1.0.0:compile
[INFO] +- org.slf4j:log4j-over-slf4j:jar:2.0.17:compile
```
