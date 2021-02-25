http://logging.apache.org/log4j/2.x/faq.html#which_jars
https://stackoverflow.com/questions/41498021/is-it-worth-to-use-slf4j-with-log4j2

List of packages you need to find in dependencies, to remove them explicitly:
if `slf4j` is used as api with `logback` implementations:
  logback-core, logback-classic, slf4j, you need to add dependency on `log4j-slf4j-impl`

commons-logging -> log4j-jcl
log4j -> log4j-1.2-api
jul -> log4j-jul

To send logs from `slf4j` as a logging bridge with a logging framework, like `logback`:
```xml
  <dependencies>
    <dependency>
      <groupId>org.apache.logging.log4j</groupId>
      <artifactId>log4j-slf4j-impl</artifactId>
      <version>2.14.0</version>
    </dependency>
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

To send logs from `jcl` as a logging bridge with a logging framework, like `log4j`:
Mvn dependency, add dependency on `log4j-jcl`, exclude `commons-logging` and the logging framework itself: `log4j`:
```xml
  <dependencies>
    <dependency>
      <groupId>org.apache.logging.log4j</groupId>
      <artifactId>log4j-jcl</artifactId>
      <version>2.14.0</version>
    </dependency>
    <dependency>
      <groupId>some.group.id</groupId>
      <artifactId>some.artifact.id</artifactId>
      <version>some.version</version>
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
  </dependencies>
```


To send logs from `log4j` (version 1) to `log4j2`
Mvn dependency, add dependency on `log4j-1.2-api`, with excluded dependency on `log4j`:
```xml
  <dependencies>
    <dependency>
      <groupId>org.apache.logging.log4j</groupId>
      <artifactId>log4j-1.2-api</artifactId>
      <version>2.14.0</version>
    </dependency>
    <dependency>
      <groupId>some.group.id</groupId>
      <artifactId>some.artifact.id</artifactId>
      <version>some.version</version>
      <exclusions>
        <exclusion>
          <groupId>log4j</groupId>
          <artifactId>log4j</artifactId>
        </exclusion>
      </exclusions>
    </dependency>
  </dependencies>
```

To send logs from `java.util.logging` (JUL) to `log4j2`

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



