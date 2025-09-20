
This projects provides the same functionality as [Spring project with `Logback` logging framework](../spring_logback/README.md):

The only purpose of this project is to configure explicitly `log4j-core` as a logging framework instead of `logback`.

Changing the logging framework usually does not make much sense,
cause even with `logback` as a logging implementation, you can still use `log4j` API in the project.

Changes:

Add
```xml
  <dependencyManagement>
    <dependencies>
      <!-- enforce the version JCL that supports Log4j API-->
      <!-- see https://logging.apache.org/log4j/2.x/manual/installation.html#impl-core-bridge-jcl-->
      <!-- https://mvnrepository.com/artifact/commons-logging/commons-logging -->
      <dependency>
        <groupId>commons-logging</groupId>
        <artifactId>commons-logging</artifactId>
        <version>1.3.5</version>
      </dependency>
    </dependencies>
  </dependencyManagement>
```
Exclude `spring-boot-starter-logging`:
```xml
    <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-web</artifactId>
      <exclusions>
        <exclusion>
          <groupId>org.springframework.boot</groupId>
          <artifactId>spring-boot-starter-logging</artifactId>
        </exclusion>
      </exclusions>
    </dependency>
```
Include `spring-boot-starter-log4j2`:  
```xml
    <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-log4j2</artifactId>
    </dependency>
```

Logging-related dependency tree:
```text
[INFO] +- org.springframework.boot:spring-boot-starter-log4j2:jar:3.5.0:compile
[INFO] |  +- org.apache.logging.log4j:log4j-slf4j2-impl:jar:2.24.3:compile
[INFO] |  +- org.apache.logging.log4j:log4j-core:jar:2.24.3:compile
[INFO] |  \- org.apache.logging.log4j:log4j-jul:jar:2.24.3:compile

```