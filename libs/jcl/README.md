### Apache Commons Logging (JCL)

Also known as Jakarta Commons Logging.

Note: Legacy solution (`slf4j` or `log4j2` - modern replacers).

Used in old applications to avoid using JUL or Log4j classes

### Logger creation:

```java
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
...
private static final Log logger = LogFactory.getLog(JclLog4jLoggingExample.class);
```

### Maven dependency

```xml
    <dependency>
      <groupId>commons-logging</groupId>
      <artifactId>commons-logging</artifactId>
      <version>1.2</version>
    </dependency>
```
The actual logging is done by the logging implementation/framework. 
If you want to use log4j (v.1) add:
```xml
    <!-- https://mvnrepository.com/artifact/log4j/log4j -->
    <dependency>
      <groupId>log4j</groupId>
      <artifactId>log4j</artifactId>
      <version>1.2.17</version>
    </dependency>
```
Available options:
- Log4j
- Slf4j
- JDK 1.4 Logging
- SimpleLog
- Avalon LogKit

### Related issues

[`log4j.configuration` property in `commons-logging.properties` is ignored](src/test/resources/commons-logging.properties)

To make the properties file visible for log4j run with: `-Dlog4j.configuration=log4j-test.properties` property
or set it programmatically: `System.setProperty("log4j.configuration", "log4j-test.properties");`

To diagnose commons-logging issues run your app/test with: `-Dorg.apache.commons.logging.diagnostics.dest=STDOUT`