
In this case we redirect all existing logging calls to logback via slf4j.

Most redirections can be controlled just via adding bridge dependencies into `pom.xml`.

Exceptions:
1. Old log4j version 1. It is not enough to include the bridge:
    ```xml
        <!-- translate log4j-1.x (but not log4j 2.x) records to SLF4J API-->
        <!-- https://mvnrepository.com/artifact/org.slf4j/log4j-over-slf4j -->
        <dependency>
          <groupId>org.slf4j</groupId>
          <artifactId>log4j-over-slf4j</artifactId>
          <version>2.0.17</version>
        </dependency>
    ```
    You must find dependencies that use `log4j:log4j` and exclude it:
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
2. JUL. It is not enough to include JUL bridge:
    ```xml
        <!-- translate JUL (Java Logging) calls to Log4j API (JUL-to-Log4j bridge)-->
        <!-- https://mvnrepository.com/artifact/org.slf4j/jul-to-slf4j -->
        <dependency>
          <groupId>org.slf4j</groupId>
          <artifactId>jul-to-slf4j</artifactId>
          <version>2.0.17</version>
        </dependency>
    ```
   If you want to see log messages redirect from JUL to Slf4j, you still must:
- either to configure [JUL logging property](src/test/resources/jul.logging.properties) and its full pass via:
   `-Djava.util.logging.config.file=/full/path/to/jul.logging.properties`
- or [configure it programmatically:](src/test/java/com/savdev/example/logging/app/logback/LogbackAppTest.java)
    ```java
        SLF4JBridgeHandler.removeHandlersForRootLogger();
        SLF4JBridgeHandler.install();
        //you cannot set log level only for specific package:
        //Logger.getLogger("com.savdev.example").setLevel(Level.FINER);
        Logger.getLogger("").setLevel(Level.FINER);
    ```
  Note: in case you use `SLF4JBridgeHandler` in the code, [`jul.logging.properties`](src/test/resources/jul.logging.properties) is not needed.