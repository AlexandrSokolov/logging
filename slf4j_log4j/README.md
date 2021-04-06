## SLF4J with log4j

Note: `log4j` is legacy solution. Do not use this mix.

In the code you reference only `slf4j` interfaces: 

```java
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Slf4jLog4j2LoggingExample {

  private static final Logger logger = LoggerFactory.getLogger(Slf4jLog4j2LoggingExample.class);

}
```

You depend on `slf4j` api:
```xml
    <dependency>
      <groupId>org.slf4j</groupId>
      <artifactId>slf4j-api</artifactId>
      <version>1.7.30</version>
    </dependency>
```

Dependency on real `log4j` logging framework:
```xml
    <dependency>
      <groupId>org.slf4j</groupId>
      <artifactId>slf4j-log4j12</artifactId>
      <version>1.7.30</version>
    </dependency>
```
