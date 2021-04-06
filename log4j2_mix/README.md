## Log4j2 used both as a logging bridge and a real logging framework

See also [Which JAR files do I need?](http://logging.apache.org/log4j/2.x/faq.html#which_jars)

Maven dependency on `log4j-api` and  `log4j-core`. 
Note: in dependencies in artifact id, `log4j`, but not `log4j2` is used:
```xml
  <dependencies>
    <dependency>
      <groupId>org.apache.logging.log4j</groupId>
      <artifactId>log4j-api</artifactId>
      <version>2.14.0</version>
    </dependency>
    <dependency>
      <groupId>org.apache.logging.log4j</groupId>
      <artifactId>log4j-core</artifactId>
      <version>2.14.0</version>
    </dependency>
  </dependencies>
```

#### Dependency management, if your application calls the API of another logging framework.

You want to route logging calls to the Log4j 2 implementation

- [slf4j and logback](#slf4j-and-logback)
- [jcl and log4j (v1)](#jcl-and-log4j-v1)
- [log4j (v1)](#log4j-v1)
- [jul](#jul)

##### slf4j and logback

Add dependency on `log4j-slf4j-impl` 
```xml
    <dependency>
      <groupId>org.apache.logging.log4j</groupId>
      <artifactId>log4j-slf4j-impl</artifactId>
      <version>2.14.0</version>
    </dependency>
```
Exclude `logback-core`, `logback-classic`:
```xml
  <dependencies>
    <dependency>
      <groupId>com.savdev.example.logging</groupId>
      <artifactId>logback</artifactId>
      <version>1.0.0</version>
      <exclusions>
        <exclusion>
          <groupId>ch.qos.logback</groupId>
          <artifactId>logback-core</artifactId>
        </exclusion>
        <exclusion>
          <groupId>ch.qos.logback</groupId>
          <artifactId>logback-classic</artifactId>
        </exclusion>
      </exclusions>
    </dependency>
  </dependencies>
```

##### jcl and log4j (v1)

Add dependency on `log4j-jcl`:
```xml
    <dependency>
      <groupId>org.apache.logging.log4j</groupId>
      <artifactId>log4j-jcl</artifactId>
      <version>2.14.0</version>
    </dependency>
```
Exclude `commons-logging` jcl and `log4j` - the real dependency framework, used for `jcl-log4j` dependency:
```xml
    <dependency>
      <groupId>com.savdev.example.logging</groupId>
      <artifactId>jcl-log4j</artifactId>
      <version>1.0.0</version>
      <exclusions>
        <exclusion>
          <groupId>commons-logging</groupId>
          <artifactId>commons-logging</artifactId>
        </exclusion>
        <exclusion>
          <groupId>log4j</groupId>
          <artifactId>log4j</artifactId>
        </exclusion>
      </exclusions>
    </dependency>
```

##### log4j (v1)

Add dependency on `log4j-1.2-api`
```xml
    <dependency>
      <groupId>org.apache.logging.log4j</groupId>
      <artifactId>log4j-1.2-api</artifactId>
      <version>2.14.0</version>
    </dependency>
```
Exclude `log4j`:

```xml
    <dependency>
      <groupId>com.savdev.example.logging</groupId>
      <artifactId>log4j</artifactId>
      <version>1.0.0</version>
      <exclusions>
        <exclusion>
          <groupId>log4j</groupId>
          <artifactId>log4j</artifactId>
        </exclusion>
      </exclusions>
    </dependency>
```

##### jul

Add dependency on `log4j-jul`

Mvn dependency:
```xml
    <dependency>
      <groupId>org.apache.logging.log4j</groupId>
      <artifactId>log4j-jul</artifactId>
      <version>2.14.0</version>
    </dependency>
```
Set `java.util.logging.manager` system property to `org.apache.logging.log4j.jul.LogManager`, for instance:
`-Djava.util.logging.manager=org.apache.logging.log4j.jul.LogManager`



