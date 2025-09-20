
You want to log http requests. You need:
- [Enable debug mode in properties](#enable-debug-mode)
- [Configure logging dependencies](#configure-logging-dependencies)

### Enable debug mode

1. Find out http engine, responsible for requests sending. In our case it is `Apache-HttpClient/4.5.14`:
    ```text
    [INFO] +- org.jboss.resteasy:resteasy-client:jar:6.2.4.Final:compile
    [INFO] |  +- org.apache.httpcomponents:httpclient:jar:4.5.14:compile
    ```
2. Find out the package, responsible for logging http requests. 
   To find it out, you could temporarily set `DEBUG` mode for all the packages.
   Or check `org.apache.httpcomponents:httpclient` dependency. 
   In our case these packages are `org.apache.http.headers` and `org.apache.http.wire`.

   In our test we send requests to the test server. 
   As a result in debug mode, enabled for all the packages, 
   we could also find in the logs `org.eclipse.jetty.server` package of Jetty sever, 
   responsible for request handling:
    ```text
    11:42:02,927 [qtp1022130643-38] DEBUG o.e.j.s.HttpChannel - REQUEST for /items on HttpChannelOverHttp@71ab064b{s=HttpChannelState@f149b51{s=IDLE rs=BLOCKING os=OPEN is=IDLE awp=false se=false i=true al=0},r=1,c=false/false,a=IDLE,uri=http://localhost:44723/items,age=4}
    GET /items HTTP/1.1
    Accept: application/json
    Host: localhost:44723
    Connection: keep-alive
    User-Agent: Apache-HttpClient/4.5.14 (Java/21.0.8)
    ```
3. Enable `DEBUG` mode for the particular package. 
   [In our case: `org.apache.http.headers` and `org.apache.http.wire`](src/test/resources/log4j2-test.xml)

### Configure logging dependencies

1. Choose a logging framework. In this project we use [`log4j-core`](pom.xml)
2. Configure logging bridges to redirect all the logging calls to the chosen framework. 
    - [logging bridges for `log4j-core`](../../README.md#logging-bridges-for-log4j)
    - [logging bridges for `logback`](../../README.md#logging-bridges-for-logback)
