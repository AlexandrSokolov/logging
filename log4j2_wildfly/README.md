### Using log4j2 API in java code for WildFly

Wildfly provides its own logging modules. 
For Wildfly 10 there is no logging module, configured for `log4j2`.

You have the following options (from the easiest one):
- [Use `log4j2`, delegate real logging to `slf4j` (recommended)](#1-via-log4j2-api-and-delegating-real-logging-to-slf4j-wildfly-logging-module)
- [Add `log4j2` support to WildFly logging system.](#2-add-log4j2-support-to-wildfly-logging-system)
- [Avoid using WildFly logging, use your own logging configuration](#3-exclude-wildfly-logging-module-add-dependency-on-log4j2-in-your-app-with-the-logging-configuration)

[**MDC/NDC/ThreadContext** support, how wildfly should be configured](../slf4j_wildfly/README.md)

[Logging configurations for testing](#4-logging-configurations-for-testing)

#### 1. Via `log4j2` API and delegating real logging to `slf4j` WildFly logging module.
   
    Use in your code `log4j2` interfaces as a logging bridge.
```xml
    <dependency>
      <groupId>org.apache.logging.log4j</groupId>
      <artifactId>log4j-api</artifactId>
      <version>2.14.0</version>
    </dependency>
```
```java
import org.apache.logging.log4j.CloseableThreadContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


  private static final Logger logger = LogManager.getLogger(SomeClass.class.getName());

  public void doLog() {
    try (final CloseableThreadContext.Instance ctc =
           CloseableThreadContext.put("user_session_id", UUID.randomUUID().toString())) {
      String note = "(originally, log4j2 war)";
      logger.info(() -> "This is an info message, via supplier, " + note);      // == INFO
      logger.error("This is an error message, {}", note);   // == ERROR
      logger.warn("This is a warning message, {}", note); // == WARNING
      logger.debug("Here is a debug message, {}", note);      // == DEBUG
      logger.info(logger.getClass().getName());
    }
  }
```
   
Delegate real logging to  `slf4j` WildFly logging module by adding dependency:
```xml
<dependency>
  <groupId>org.apache.logging.log4j</groupId>
  <artifactId>log4j-to-slf4j</artifactId>
  <version>2.14.0</version>
</dependency>
```

#### 2. Add `log4j2` support to WildFly logging system.

#### 3. Exclude Wildfly Logging module, add dependency on `log4j2` in your app with the logging configuration.

  Disadvantage: you cannot change logging configurations via WildFly CLI at runtime.
  See [How to setup Log4j2 for an application deployed in WildFly 9?](https://stackoverflow.com/questions/35402632/how-to-setup-log4j2-for-an-application-deployed-in-wildfly-9)

#### 4. Logging configurations for testing

You can also for testing delegate all real logging to `sl4j` with `logback`.
This allows to see log messages for `resteasy`/`spring` frameworks.

Extend the `deps` with a property:
```xml
  <properties>
    ...
    <logback.version>1.2.3</logback.version>
  </properties>
```
Add into the `deps` module a new dependency with `test` scope:
```xml
      <dependency>
        <groupId>ch.qos.logback</groupId>
        <artifactId>logback-classic</artifactId>
        <version>${logback.version}</version>
        <scope>test</scope>
      </dependency>
```
In the module, which uses `log4j` as a logging bridge and delegates real logging to `slf4j`/`logback`, add:
```xml
  <dependencies>
    ...
    <dependency>
      <groupId>org.apache.logging.log4j</groupId>
      <artifactId>log4j-api</artifactId>
    </dependency>
    <dependency>
      <groupId>org.apache.logging.log4j</groupId>
      <artifactId>log4j-to-slf4j</artifactId>
    </dependency>
    <dependency>
      <groupId>ch.qos.logback</groupId>
      <artifactId>logback-classic</artifactId>
    </dependency>
  </dependencies>
```