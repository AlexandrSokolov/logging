
### JUL logging levels:

SEVERE (highest value)
WARNING
INFO
CONFIG
FINE
FINER
FINEST (lowest value)

### JUL configuration

You must align the log level for BOTH the package AND the appender:
```properties
java.util.logging.ConsoleHandler.level=FINER
com.savdev.example.level=FINER
```

### Path to the logging properties file

Default `logging.properties` location
- `$JAVA_HOME/conf/logging.properties` for Java 9 and above
- `${JAVA_JRE}/lib/logging.properties` for Java 8 and below: 

You cannot set the file via code:
```java
System.setProperty(
  "java.util.logging.config.file",
  Objects.requireNonNull(JulLibTest.class.getClassLoader().getResource("jul.logging.properties"))
    .getFile());
```

[To set the file programmatically from the class path](src/test/java/com/savdev/example/logging/jul/JulLibTest.java):
```java
import java.util.logging.LogManager;

try (InputStream stream = JulLibTest.class.getClassLoader().getResourceAsStream("jul.logging.properties")){
  LogManager.getLogManager().readConfiguration(stream);
}
```
Or pass to the app/test:
```bash
-Djava.util.logging.config.file=/full/path/to/the/jul.logging.properties
```