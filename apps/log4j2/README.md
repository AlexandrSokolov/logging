In this case we redirect all existing logging calls to log4j2-core implementation:
```xml
<!-- https://mvnrepository.com/artifact/org.apache.logging.log4j/log4j-core -->
<dependency>
  <groupId>org.apache.logging.log4j</groupId>
  <artifactId>log4j-core</artifactId>
  <version>2.25.1</version>
</dependency>
```

Most redirections can be controlled just via adding bridge dependencies into `pom.xml`.

Exceptions:
1. Old log4j version 1. It is not enough to include the bridge:
    ```xml
    <!-- translate Log4j 1.x calls to Log4j API (Log4j-to-Log4j2 bridge)-->
    <!-- you must remove all dependencies on log4j-->
    <dependency>
      <groupId>org.apache.logging.log4j</groupId>
      <artifactId>log4j-1.2-api</artifactId>
      <version>${log4j2.version}</version>
    </dependency>
    ```
   You must find all the dependencies that use `log4j:log4j` and exclude them, example:
    ```xml
        <dependency>
          <groupId>com.savdev.example.logging</groupId>
          <artifactId>log4j-v1-lib</artifactId>
          <version>1.0.0</version>
          <exclusions>
            <exclusion>
              <groupId>log4j</groupId>
              <artifactId>log4j</artifactId>
            </exclusion>
          </exclusions>
        </dependency>
    ```
2. Commons logging, force the version update:
    ```xml
      <dependencyManagement>
        <dependencies>
          <!--Since version 1.3.0 Apache Commons Logging natively supports Log4j API.-->
          <!--So instead of using a bridge for jcl we can just force the version.-->
          <dependency>
            <groupId>commons-logging</groupId>
            <artifactId>commons-logging</artifactId>
            <version>1.3.5</version>
          </dependency>
        </dependencies>
      </dependencyManagement>
    ```
3. JUL. It is not enough to include JUL bridge:
    ```xml
        <!-- translate JUL (Java Logging) calls to Log4j API (JUL-to-Log4j bridge)-->
    <!-- https://mvnrepository.com/artifact/org.apache.logging.log4j/log4j-jul -->
    <dependency>
      <groupId>org.apache.logging.log4j</groupId>
      <artifactId>log4j-jul</artifactId>
      <version>${log4j2.version}</version>
    </dependency>
    ```
   [TODO, could not get it done](TODO.md)


