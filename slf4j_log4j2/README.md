## SLF4J with log4j2

See also:
[Log4j 2 SLF4J Binding](https://logging.apache.org/log4j/2.x/log4j-slf4j-impl/)
[Is it worth to use slf4j with log4j2](https://stackoverflow.com/questions/41498021/is-it-worth-to-use-slf4j-with-log4j2)

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

Dependency on real `log4j2` logging framework:
```xml
  <dependencies>
    <dependency>
      <groupId>org.apache.logging.log4j</groupId>
      <artifactId>log4j-core</artifactId>
      <version>2.14.0</version>
    </dependency>
    <dependency>
      <groupId>org.apache.logging.log4j</groupId>
      <artifactId>log4j-slf4j-impl</artifactId>
      <version>2.14.0</version>
    </dependency>
  </dependencies>
```
