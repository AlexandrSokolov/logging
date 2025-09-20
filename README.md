
## Logging-related topics:
- [Logging usage principles](#logging-usage-principles)
- [Logging dependencies roles](#logging-dependencies-roles)
- [Logging API](#logging-api)
- [Logging API, available options](#logging-api-available-options)
- [Logging implementation](#logging-implementation)
- [Logging implementation options](#logging-implementation-options)
- [Logging bridges](#logging-bridges)
- [Logging bridges for Logback](#logging-bridges-for-logback)
- [Logging bridges for Log4j](#logging-bridges-for-log4j)
- [Logging in Spring with a default logging framework](apps/spring_logback/README.md#logging-in-spring)
- [Logging in Spring with `log4j-core` logging framework](apps/spring_log4j/README.md)

## Change logging levels:  
- [Change logging level at runtime via Spring Actuator](apps/spring_logback/README.md#change-logging-level-at-runtime-via-actuator)
- [Change logging level at runtime via `LoggingSystem`](apps/spring_logback/README.md#change-logging-level-at-runtime-via-loggingsystem)
- [Set logging level during application startup](apps/spring_logback/README.md#set-logging-level-during-application-startup)
- [Built-time logging configuration in Spring Apps](apps/spring_logback/README.md#built-time-logging-configuration)
- [Log2j2, set logging level per package](libs/log4j2/src/test/resources/log4j2-test.properties)
- [Logback, set logging level per package](libs/slf4j/src/test/resources/logback-test.xml)

## Legacy solution:
- [Logging Legacy solutions](#legacy-solutions)

### Logging dependencies roles

When we configure logging dependencies there are three logging roles to understand:
- [Logging APIs](#logging-api)
- [Logging implementation](#logging-implementation)
- [Logging bridges/bindings/adapters](#logging-bridges) 

### Logging API

Logging APIs - they provide a standard interface for logging, allowing you to write log statements
without being tied to a specific logging framework.

As a developer of your application, you need to decide which logging API you are going to use to write your logs 
in your application’s Java classes. It does  not depend on any other logging API, used in libraries you depend on.

You can have multiple logging API dependencies in the same project with no issues.

### Logging API, available options

1. Log4j2 API
    ```java
    import org.apache.logging.log4j.Logger;
    import org.apache.logging.log4j.LogManager;
    
    class L {
      //getLogger() associates the returned Logger with the enclosing class, that is, `L` in this example.
      private static final Logger logger = LogManager.getLogger();
      public void performAction() {
        //postponed evaluations of parameters:
        logger.info(() -> "Performing action...");
      } 
    }
    ```
   Currently only log4j2 API provides functional style with postponed evaluations of parameters.
   This functional method invocation approach makes `log4j2` api a preferable choice over the other options.

   Maven dependency:
    ```xml
    <!-- https://mvnrepository.com/artifact/org.apache.logging.log4j/log4j-api -->
    <dependency>
        <groupId>org.apache.logging.log4j</groupId>
        <artifactId>log4j-api</artifactId>
        <version>2.25.1</version>
    </dependency>
    ```
2. SLF4J (Simple Logging Facade for Java).
    ```java
    import org.slf4j.Logger;
    import org.slf4j.LoggerFactory;
   
    public class MyClass {
      private static final Logger logger = LoggerFactory.getLogger(MyClass.class);
      public void performAction() {
        logger.info("Performing action...");
      }
    }
    ```
   It allows developers to switch between logging implementations easily.

    Maven dependency:
    ```xml
    <!-- https://mvnrepository.com/artifact/org.slf4j/slf4j-api -->
    <dependency>
        <groupId>org.slf4j</groupId>
        <artifactId>slf4j-api</artifactId>
        <version>2.0.17</version>
    </dependency>
    ``` 
3. JCL (Commons Logging)
    ```java
    import org.apache.commons.logging.Log;
    import org.apache.commons.logging.LogFactory;
    
    public class MyClass {
        private static final Log logger = LogFactory.getLog(MyClass.class);
        public void performAction() {
            logger.info("Performing action...");
        }
    }
    ```

    Maven dependency:
    ```xml
    <!-- https://mvnrepository.com/artifact/commons-logging/commons-logging -->
    <dependency>
        <groupId>commons-logging</groupId>
        <artifactId>commons-logging</artifactId>
        <version>1.3.5</version>
    </dependency>
    ```
    The 1.2 version was released in 2014 and had some hard-to-debug issues with class loading 
    and interactions between the API and the frameworks. 
    This project was not touched till 2023 with a new 1.3.0 release.
    Not clear how this project will be supported in the future.

    The only purpose to use this option is to update existing projects with old JCL versions to the new one.
    While it does not require any additional steps, only dependency update.     
    **In new projects you should use Log4j2 or Slf4j logging APIs.**
4. TODO JBoss Logging
   - plays the same role as JCL - tries to get which logging implementation is used, 
   allowing you to decouple it from API.

### Logging implementation

Logging implementations (or frameworks) - the actual implementations that provide logging capabilities like 
writing the logs into file, databases, sending logs to remote services - they handle how and where logs are stored.

- It must be only one logging implementation in the project.
- If you develop a library, do not add compile dependency on a logging implementation. 
  Only the library consumers should decide it. 
- In a library you can add logging implementation with a test scope only. 


### Logging implementation options

1. Logback - default logging implementation for Spring projects
    Maven dependency:
    ```xml
    <!-- https://mvnrepository.com/artifact/ch.qos.logback/logback-classic -->
    <dependency>
        <groupId>ch.qos.logback</groupId>
        <artifactId>logback-classic</artifactId>
        <version>1.5.18</version>
    </dependency>
    ```
   Logback, developed as a successor to Log4j (version 1.x.x), offers improved features and performance. 
   It is designed to be backward-compatible with Log4j while providing a more efficient and flexible logging solution. 
   Logback incorporates SLF4J, a simple logging facade for Java.

2. Log4j core
    Maven dependency:
    ```xml
    <!-- https://mvnrepository.com/artifact/org.apache.logging.log4j/log4j-core -->
    <dependency>
        <groupId>org.apache.logging.log4j</groupId>
        <artifactId>log4j-core</artifactId>
        <version>2.25.1</version>
    </dependency>
    ```
3. [JUL (java.util.logging ) - Java’s built-in logging facility.](jul/README.md)
    ```java
    import java.util.logging.Logger;
    
    public class MyClass {
        private static final Logger logger = Logger.getLogger(MyClass.class.getName());
        public void performAction() {
            logger.info("Performing action...");
        }
    }
    ```
   Do not use it. The only benefit it provides - no additional dependency.

### Logging bridges

Logging bridges/bindings/adapters - redirect logging statements from different logging APIs to a chosen implementation.

They ensure compatibility between different logging APIs and frameworks, allowing them to work together seamlessly.

If you don’t use a logging bridge then you will see logs from your code but will not be able to see logs 
written by the library which has incompatible logging API.

Logging bridges choices depend directly on the chosen implementation.
You need to redirect logging statements from all the possible logging APIs (or deprecated logging libraries) to
the logging implementation you use in your project.

### Logging bridges for Logback

In order to use logback as a logging implementation we define bridges that redirect calls to slf4j.

[Logback](apps/logback/pom.xml):
```xml
<dependencies>
  <!-- translate log4j2 calls to SLF4J API (Log4j-to-SLF4J bridge)-->
  <!-- https://mvnrepository.com/artifact/org.apache.logging.log4j/log4j-to-slf4j -->
  <dependency>
    <groupId>org.apache.logging.log4j</groupId>
    <artifactId>log4j-to-slf4j</artifactId>
    <version>2.25.1</version>
  </dependency>
  <!-- translate log4j-1.x (but not log4j 2.x) records to SLF4J API-->
  <!-- https://mvnrepository.com/artifact/org.slf4j/log4j-over-slf4j -->
  <dependency>
    <groupId>org.slf4j</groupId>
    <artifactId>log4j-over-slf4j</artifactId>
    <version>2.0.17</version>
  </dependency>
  <!-- translate jcl calls to SLF4J API (JCL-to-SLF4J bridge)-->
  <!-- https://mvnrepository.com/artifact/org.slf4j/jcl-over-slf4j -->
  <dependency>
    <groupId>org.slf4j</groupId>
    <artifactId>jcl-over-slf4j</artifactId>
    <version>2.0.17</version>
  </dependency>   
  <!-- translate JUL (Java Logging) calls to Log4j API (JUL-to-Log4j bridge)-->
  <!-- https://mvnrepository.com/artifact/org.slf4j/jul-to-slf4j -->
  <dependency>
    <groupId>org.slf4j</groupId>
    <artifactId>jul-to-slf4j</artifactId>
    <version>2.0.17</version>
  </dependency>
</dependencies>
```
Note, for log4j v.1, you still need to find exclude dependencies in the project on log4j:
```xml
    <dependency>
      <groupId>com.savdev.example.logging</groupId>
      <artifactId>log4j-v1-lib</artifactId>
      <version>1.0.0</version>
      <exclusions>
        <exclusion>
          <groupId>log4j</groupId>
          <artifactId>log4j</artifactId>
        </exclusion>
      </exclusions>
    </dependency>
```
And you get in the log warnings:
```text
log4j:WARN No appenders could be found for logger (/some/package/SomeClass).
log4j:WARN Please initialize the log4j system properly.
log4j:WARN See http://logging.apache.org/log4j/1.2/faq.html#noconfig for more info.
```
   
### Logging bridges for Log4j

[Log4j (version 2.x.x)](apps/log4j2/README.md)
```xml
<dependencyManagement>
 <dependencies>
   <!--Since version 1.3.0 Apache Commons Logging natively supports Log4j API.-->
  <!--So instead of using a bridge for jcl we can just force the version.-->
  <dependency>
    <groupId>commons-logging</groupId>
    <artifactId>commons-logging</artifactId>
    <version>1.3.5</version>
  </dependency>
 </dependencies>
</dependencyManagement>
<dependencies>
  <!--translate SLF4J calls to Log4j API (SLF4J-to-Log4j bridge)-->
  <!-- https://mvnrepository.com/artifact/org.apache.logging.log4j/log4j-slf4j2-impl -->
  <dependency>
    <groupId>org.apache.logging.log4j</groupId>
    <artifactId>log4j-slf4j2-impl</artifactId>
    <version>2.25.1</version>
    <scope>compile</scope>
  </dependency>
  <!-- translate JUL (Java Logging) calls to Log4j API (JUL-to-Log4j bridge)-->
  <!-- https://mvnrepository.com/artifact/org.apache.logging.log4j/log4j-jul -->
  <dependency>
    <groupId>org.apache.logging.log4j</groupId>
    <artifactId>log4j-jul</artifactId>
    <version>2.25.1</version>
    <scope>runtime</scope>
  </dependency>
</dependencies>
```

### Logging usage principles

- In the code we use only logging API, but not logging API implementation. 
  Multiple logging APIs are usually available in projects, so you could choose one you want.
- If you develop a library, do not add dependency on a logging implementation. Only the library consumers can decide what to use.
  You can still add logging implementation with a test scope only.
- Use - `slf4j` or `log4j2` as logging API. Do not use JCL. JCL version 1.2 is a legacy solution. 
  JCL version 1.3.x is reasonable only to update existing projects without code change, that use the old version.
- It must be only one logging implementation in the project. 
- Do not forget about bridges, that must redirect all the logging calls to the chosen logging framework.
- For standalone Java and Spring-based applications control the logging dependency via maven.
- For Wildfly configure the actual logging dependency via the application configuration.
  Do not include logging implementation into the dependencies. 


### Legacy solutions
- Log4j (version 1.x.x) - no separation between API and implementation
    ```xml
        <!-- https://mvnrepository.com/artifact/log4j/log4j -->
        <dependency>
          <groupId>log4j</groupId>
          <artifactId>log4j</artifactId>
          <version>1.2.17</version>
        </dependency>
    ```
- [JUL](libs/jul/README.md)
- JCL v1.2 - The 1.2 version was released in 2014 and had some hard-to-debug issues with class loading
  and interactions between the API and the frameworks.

  This project was not touched till 2023 with a new 1.3.0 release.
  The only purpose to use this option is to update existing projects with old JCL versions to the new one.
  While it does not require any additional steps, only dependency update.     
  **In new projects you should prefer Log4j2 or Slf4j logging APIs.**


