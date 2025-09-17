## Apache Log4j (v1)

Do not use it. It was maintained until 2015.  

#### Configuration file

- `log4j.xml`
- `log4j.properties`

The location of the config files must be in the classpath.

So you can either:
1. put the file into the `resources` folder of your project, or
2. add any external location of the file to the classpath

Note: `log4j-test.properties` for test is not known. You should either name it as `log4j.properties` or pass it as:
```java
System.setProperty("log4j.configuration", "log4j-test.properties");
```


