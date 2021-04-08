### Using log4j2 API in java code for WildFly

Wildfly provides its own logging modules. 
For Wildfly 10 there is no logging module, configured for `log4j2`.

You have the following options (from the easiest one):
- [Use `log4j2`, delegate real logging to `slf4j` (recommended)](#1-via-log4j2-api-and-delegating-real-logging-to-slf4j-wildfly-logging-module)
- [Add `log4j2` support to WildFly logging system.](#2-add-log4j2-support-to-wildfly-logging-system)
- [Avoid using WildFly logging, use your own logging configuration](#3-exclude-wildfly-logging-module-add-dependency-on-log4j2-in-your-app-with-the-logging-configuration)

For **MDC/NDC/ThreadContext** support, how wildfly should be configured, see: [slf4j_wildfly/README.md](../slf4j_wildfly/README.md)


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
   
Delegate real logging to  `slf4j` WildFly logging module by adding dependecy:
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
