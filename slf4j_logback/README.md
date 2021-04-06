## SLF4J with logback

In the code you reference only `slf4j` interfaces: 

```java
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogbackLoggingExample {

  private static final Logger logger = LoggerFactory.getLogger(LogbackLoggingExample.class);

}
```

Logback is added to the dependency:
```xml
    <dependency>
      <groupId>ch.qos.logback</groupId>
      <artifactId>logback-core</artifactId>
      <version>1.2.3</version>
    </dependency>
    <dependency>
      <groupId>ch.qos.logback</groupId>
      <artifactId>logback-classic</artifactId>
      <version>1.2.3</version>
    </dependency>
```
