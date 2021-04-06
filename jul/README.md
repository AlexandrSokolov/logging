## Java Util Logging (JUL)

**Note: Do note use this solution for logging!!!**

- [Configuration file](#configuration-file)
- [Default config location](#default-the-config-file-location)
- [Customizing of the config file location](#customizing-location-to-an-external-loggingproperties-file)
- [Using the config file from the resources](#accessing-loggingproperties-from-the-resources)

#### Configuration file

`logging.properties`

#### Default the config file location

`${JAVA_JRE}/lib/logging.properties` 

#### Customizing location to an external `logging.properties` file

Set `java.util.logging.config.file` system setting.

#### Accessing `logging.properties` from the `resources`

Load the `logging.properties` file from the resources via `LogManager`
```java
public class JulLoggingExample {

  private static final Logger logger = logger();

  public static Logger logger() {

    try (InputStream stream = JulLoggingExample.class.getClassLoader().getResourceAsStream("logging.properties")) {
      LogManager.getLogManager().readConfiguration(stream);
      return Logger.getLogger(JulLoggingExample.class.getName());
    } catch (IOException e) {
      throw new IllegalStateException(e);
    }
  }
}
```

Note: do not load file via file name from resources as:
```java
public class JulLoggingExample {

  private static final Logger logger = logger();
  
  public static Logger logger() {
    try {
      URL res = JulLoggingExample.class.getClassLoader().getResource("logging.properties");
      File file = Paths.get(res.toURI()).toFile();
      System.setProperty(
        "java.util.logging.config.file",
        file.getAbsolutePath());
      return Logger.getLogger(JulLoggingExample.class.getName());
    } catch (URISyntaxException e) {
      throw new IllegalStateException(e);
    }
  }
}
```
The reason is, in case you run the jar as:
`$ java -cp target/jul_module-1.0.0.jar com.savdev.example.logging.jul.JulLoggingExample`
it will not work. The `logging.properties` will not be found. 

It will run only when is run as:
`$ java -classpath target/classes com.savdev.example.logging.jul.JulLoggingExample`