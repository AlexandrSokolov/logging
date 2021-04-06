## Using log4j2 API in java code

Wildfly provides its own logging modules. 
For Wildfly 10 there is no logging module, configured for `log4j2`.

You have the following options:

1. Exclude Wildfly Logging module, add dependency on `log4j2` in your app with the logging configuration.
  See [How to setup Log4j2 for an application deployed in WildFly 9?](https://stackoverflow.com/questions/35402632/how-to-setup-log4j2-for-an-application-deployed-in-wildfly-9)
   
2. Add `log4j2` logging module to Wildfly.

3. Reference in your code to `log4j2` API, but send all logging messages to `slf4j`, by adding:
```xml
    <dependency>
      <groupId>org.apache.logging.log4j</groupId>
      <artifactId>log4j-to-slf4j</artifactId>
      <version>2.14.0</version>
    </dependency>
```