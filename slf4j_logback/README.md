Logback is not used directly without slf4j.
Logback depends on slf4j. So you can create loggers as:
```java
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogbackLoggingExample {

  private static final Logger logger = LoggerFactory.getLogger(LogbackLoggingExample.class);

}
```