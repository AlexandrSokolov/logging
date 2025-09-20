### Logging for docker 

### [JBoss Logging](https://docs.jboss.org/hibernate/orm/5.4/topical/html_single/logging/Logging.html)

### wildfly logging at runtime, cli commands example

### [JUL redirect to log4j2](apps/log4j2/TODO.md)

### Last9, OTLP, what is it?

### Mapped Diagnostic Context (MDC) for Logging Context
Enhancing Logs with Contextual Information
https://last9.io/blog/a-guide-to-spring-boot-logging/

- MDC
- NDC
- ClosingThreadContext

### Asynchronous Logging for Performance Optimization
https://last9.io/blog/a-guide-to-spring-boot-logging/

https://last9.io/blog/java-util-logging-configuration/

Set Up Last9 as the Logging Backend via OTLP
Last9 supports ingesting logs using the OpenTelemetry Protocol (OTLP):

Use OpenTelemetry SDK or a collector to export logs in OTLP format (gRPC or HTTP).
Point the exporter to your Last9 OTLP endpoint.
Ensure logs include relevant context like service name, environment, and trace ID (if available).
4. View Logs in Context Inside Last9
   Once your logs are flowing into Last9:

They appear alongside metrics and traces—correlated automatically.
You get a single-pane view of system behavior, great for debugging and alert triage.
No need for separate logging infrastructure like ELK, Loki, or a sidecar setup.

### Example: Using AOP for Method-Level Logging
https://last9.io/blog/a-guide-to-spring-boot-logging/

### Filters. Lot of examples:
http://logback.qos.ch/manual/filters.html
https://logging.apache.org/log4j/2.x/manual/filters.html
