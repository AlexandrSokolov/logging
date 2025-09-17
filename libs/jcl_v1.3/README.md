### Apache Commons Logging (JCL)

Also known as Jakarta Commons Logging.

Since the version 1.3 it supports natively Log4j2 API.

The only purpose to use this option is to update existing projects with old JCL versions to the new one.
While it does not require any additional steps, only dependency update.

In new projects you could just use Log4j2 or Slf4j logging APIs.

### Logger creation:

```java
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
...
private static final Log logger = LogFactory.getLog(JclLog4jLoggingExample.class);
```

### Maven dependency

```xml
<!-- https://mvnrepository.com/artifact/commons-logging/commons-logging -->
<dependency>
  <groupId>commons-logging</groupId>
  <artifactId>commons-logging</artifactId>
  <version>1.3.5</version>
</dependency>
```
The actual logging is done by the logging implementation/framework. 
If you want to use log4j (v.2) add:
```xml
<!-- https://mvnrepository.com/artifact/org.apache.logging.log4j/log4j-core -->
<dependency>
  <groupId>org.apache.logging.log4j</groupId>
  <artifactId>log4j-core</artifactId>
  <version>2.25.1</version>
</dependency>
```

### Logging properties file

No need anymore of `commons-logging.properties`.

You just choose logging implementation, and it find by itself the required config.

In this project we use log4j2 as an implementation:
```xml
    <!-- https://mvnrepository.com/artifact/org.apache.logging.log4j/log4j-core -->
    <dependency>
      <groupId>org.apache.logging.log4j</groupId>
      <artifactId>log4j-core</artifactId>
      <version>2.25.1</version>
      <scope>test</scope>
    </dependency>
```
And log4j2 automatically found [a test logging file](src/test/resources/log4j2-test.properties)
