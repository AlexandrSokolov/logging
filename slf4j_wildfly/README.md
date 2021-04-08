### Using `slf4j` API in java code for WildFly

Add dependency on:
```xml
    <dependency>
      <groupId>org.slf4j</groupId>
      <artifactId>slf4j-api</artifactId>
      <version>1.7.30</version>
    </dependency>
```

In java code:
```java
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

private static final Logger logger = LoggerFactory.getLogger(Slf4jWebLoggingExample.class);
```

Note: you need to check other logging libraries, used by 3rd parties. 
The full description you can find in [slf4j_mix/README.md](../slf4j_mix/README.md)

Here is example for log4j2 to redirect it to slf4j:
```xml
    <dependency>
      <groupId>org.apache.logging.log4j</groupId>
      <artifactId>log4j-to-slf4j</artifactId>
      <version>2.14.0</version>
    </dependency>
```

### 2 For MDC, you need to configure the logging system of Wildfly with the key, used in MDC.
For instance, you use it with `user` key:
```java
    try {
      MDC.put("user", UUID.randomUUID().toString());
      ...
    } finally {
      MDC.clear();
    }
```
Then you need to include `%X{user}` into the `formatter`: 

#### 2.1 At runtime via CLI:

Add new `CUSTOM_MDC_HANDLER` custom handler and configure it:
```
$ docker exec -it bm_wf sh -c "/opt/jboss/wildfly/bin/jboss-cli.sh --connect --controller=127.0.0.1:19990"
/subsystem=logging/periodic-rotating-file-handler=CUSTOM_MDC_HANDLER:add(file={"path"=>"server.log", "relative-to"=>"jboss.server.log.dir"}, suffix=".yyyy-MM-dd")
/subsystem=logging/periodic-rotating-file-handler=CUSTOM_MDC_HANDLER:write-attribute(name="level", value="DEBUG")
/subsystem=logging/periodic-rotating-file-handler=CUSTOM_MDC_HANDLER:write-attribute(name="append", value="true")
```
Add a new formatter which includes `%X{user}` line for `user` key in MDC:
```
/subsystem=logging/periodic-rotating-file-handler=CUSTOM_MDC_HANDLER:write-attribute(name="formatter", value="%d{yyyy-MM-dd HH:mm:ss,SSS} %-5p [%c] (%t) [%X{user}] %s%e%n") 
```
Check result:
```
/subsystem=logging/periodic-rotating-file-handler=CUSTOM_MDC_HANDLER:read-resource
{
    "outcome" => "success",
    "result" => {
        "append" => true,
        "autoflush" => true,
        "enabled" => true,
        "encoding" => undefined,
        "file" => {
            "path" => "server.log",
            "relative-to" => "jboss.server.log.dir"
        },
        "filter" => undefined,
        "filter-spec" => undefined,
        "formatter" => "%d{yyyy-MM-dd HH:mm:ss,SSS} %-5p [%c] (%t) [%X{user}] %s%e%n",
        "level" => "DEBUG",
        "name" => "CUSTOM_MDC_HANDLER",
        "named-formatter" => undefined,
        "suffix" => ".yyyy-MM-dd"
    }
}
```
Add new logger for your package. In this case `com.savdev.example` is used and link it to `CUSTOM_MDC_HANDLER` handler: 
```
/subsystem=logging/logger=com.savdev.example:add
/subsystem=logging/logger=com.savdev.example:write-attribute(name="level", value="DEBUG")
/subsystem=logging/logger=com.savdev.example:assign-handler(name="CUSTOM_MDC_HANDLER")
/subsystem=logging/logger=com.savdev.example:read-resource
```

After the investigation you should remove the new configurations:
```
/subsystem=logging/logger=com.savdev.example:remove
/subsystem=logging/periodic-rotating-file-handler=CUSTOM_MDC_HANDLER:remove
```


#### 2.2 Statically in `standalone-full.xml` file:

```xml
<subsystem xmlns="urn:jboss:domain:logging:3.0">
    <periodic-rotating-file-handler name="MDC_FILE" autoflush="true">
        <formatter>
            <named-formatter name="MDC_PATTERN"/>
        </formatter>
        <file relative-to="jboss.server.log.dir" path="server.log"/>
        <suffix value=".yyyy-MM-dd"/>
        <append value="true"/>
    </periodic-rotating-file-handler>
    <logger category="com.savdev" use-parent-handlers="false">
        <level name="DEBUG"/>
        <handlers>
            <handler name="MDC_FILE"/>
        </handlers>
    </logger>
    <formatter name="MDC_PATTERN">
        <pattern-formatter pattern="%d{yyyy-MM-dd HH:mm:ss,SSS} %-5p [%c] (%t) [%X{user}] %s%e%n"/>
    </formatter>
</subsystem>
```

