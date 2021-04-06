## Slf4j used as a logging bridge and logback is used as a real logging framework

TODO: it is a good practice to exclude dependency you do not use. 
See how exclusions are defined in [log4j2_mix/README.md](../log4j2_mix/README.md)

Maven dependency on `slf4j-api` and `logback-classic`:
```xml
  <dependencies>
    <dependency>
      <groupId>org.slf4j</groupId>
      <artifactId>slf4j-api</artifactId>
      <version>1.7.30</version>
    </dependency>
    <dependency>
      <groupId>ch.qos.logback</groupId>
      <artifactId>logback-classic</artifactId>
      <version>1.2.3</version>
    </dependency>
  </dependencies>
```

#### Dependency management, if your application calls the API of another logging framework.

You want to route logging calls to the `logback` implementation

- [log4j2](#1-log4j2)
- [log4j (v1)](#2-log4j)
- [jcl](#3-jcl)
- [jul](#4-jul)

##### 1. log4j2
```xml
    <dependency>
      <groupId>org.apache.logging.log4j</groupId>
      <artifactId>log4j-to-slf4j</artifactId>
      <version>2.14.0</version>
    </dependency>
```

##### 2. log4j
```xml
    <!--    for log4j version 1-->
    <!-- https://mvnrepository.com/artifact/org.slf4j/log4j-over-slf4j -->
    <dependency>
      <groupId>org.slf4j</groupId>
        <artifactId>log4j-over-slf4j</artifactId>
      <version>1.7.30</version>
    </dependency>
```

##### 3. jcl
```xml
    <dependency>
      <groupId>org.slf4j</groupId>
      <artifactId>jcl-over-slf4j</artifactId>
      <version>1.7.30</version>
    </dependency>
```

##### 4. jul
```xml
    <dependency>
      <groupId>org.slf4j</groupId>
      <artifactId>jul-to-slf4j</artifactId>
      <version>1.7.30</version>
    </dependency>
```

For JUL there is an issue, see: [Java logging: slf4j over jul and log4j2](https://stackoverflow.com/questions/66350345/java-logging-slf4j-over-jul-and-log4j2)

The solution is to change logger in `JulLoggingExample` onto:
```java
private static final Logger logger = Logger.getLogger(JulLoggingExample.class.getName());
```
which cannot be applied, cause in this case we do not control `logging.properties` location.