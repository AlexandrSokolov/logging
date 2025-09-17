
### Redirect of JUL to Log4j2 core

[JUL-to-Log4j bridge](https://logging.apache.org/log4j/2.x/log4j-jul.html)

Could not get it worked:
- `System.setProperty("java.util.logging.manager", "org.apache.logging.log4j.jul.LogManager");`
- Did not work either:
    ```java
    import org.apache.logging.log4j.jul.Log4jBridgeHandler;
    
    Log4jBridgeHandler.install(true,"QQ", true);
    Logger.getLogger("").setLevel(Level.FINER);
    ```