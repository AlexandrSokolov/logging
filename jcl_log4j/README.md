## Apache Commons Logging (JCL)

Also known as Jakarta Commons Logging.

Note: Legacy solution (`slf4j` or `log4j2` - modern replacers).

In your code you use only JCL interfaces, but not JUL or Log4j classes:
```xml
    <dependency>
      <groupId>commons-logging</groupId>
      <artifactId>commons-logging</artifactId>
      <version>1.2</version>
    </dependency>
```

```java
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
...
private static final Log logger = LogFactory.getLog(JclLog4jLoggingExample.class);
```

The actual logging is done by the logging framework, you add to your classpath.

```xml
    <!-- https://mvnrepository.com/artifact/log4j/log4j -->
    <dependency>
      <groupId>log4j</groupId>
      <artifactId>log4j</artifactId>
      <version>1.2.17</version>
    </dependency>
```





